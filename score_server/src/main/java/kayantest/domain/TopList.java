package kayantest.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Collections.sort;

public class TopList {

    public static final Comparator<PlayerScore> DESCENDING_COMPARATOR = new Comparator<PlayerScore>() {
        @Override
        public int compare(PlayerScore playerScore, PlayerScore playerScore2) {
            return playerScore2.compareTo(playerScore);
        }
    };
    private PlayerScore min;

    public ConcurrentHashMap<Integer, Integer> playerScores;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();


    private int listSize = 15;


    public TopList() {
        playerScores = new ConcurrentHashMap<Integer, Integer>(listSize);
    }

    public TopList(int listSize) {
        this.listSize = listSize;
        playerScores = new ConcurrentHashMap<Integer, Integer>(listSize);
    }


    public void update(PlayerScore playerScore) {


        runInWritableLock(() -> {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            if (min == null) {
                min = playerScore;
            }

            Integer found = playerScores.get(playerScore.getPlayerId());
            if (playerScores.size() < listSize) {
                //add new one or update if higher that found
                if (found == null || (found != null && found < playerScore.getScore())) {
                    playerScores.put(playerScore.getPlayerId(), playerScore.getScore());
                    resetMin();
                }
            } else {
                //update if higher that found
                if (found != null && found < playerScore.getScore()) {
                    playerScores.put(playerScore.getPlayerId(), playerScore.getScore());
                    resetMin();
                    //new one add if higher than min
                } else if (playerScore.getScore() > min.getScore()) {
                    playerScores.remove(min.getPlayerId());
                    playerScores.put(playerScore.getPlayerId(), playerScore.getScore());
                    resetMin();
                }
            }

        });

    }

    private void runInWritableLock(Runnable runnable) {
        writeLock.lock();
        try {
           runnable.run();
        } finally {
            writeLock.unlock();
        }
    }


    private void resetMin() {
        for (Map.Entry<Integer, Integer> entry : playerScores.entrySet()) {
            if (entry.getValue() <= min.getScore()) {
                min = new PlayerScore(entry.getKey(), entry.getValue());
            }
        }
    }

    public List<PlayerScore> asList() {
        List<PlayerScore> list = Arrays.asList(asArray());
        sort(list, DESCENDING_COMPARATOR);
        return list;
    }

    public PlayerScore[] asArray() {


        if (!readLock.tryLock()) {
//        if (!writeLock.tryLock()){
            return null;
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        try {
            int i = 0;

            PlayerScore[] array = new PlayerScore[playerScores.size()];
            for (Map.Entry<Integer, Integer> entry : playerScores.entrySet()) {
                array[i++] = new PlayerScore(entry.getKey(), entry.getValue());
            }

            Arrays.sort(array, DESCENDING_COMPARATOR);
            return array;
        } finally {
            readLock.unlock();
//            writeLock.unlock();
        }
    }


}
