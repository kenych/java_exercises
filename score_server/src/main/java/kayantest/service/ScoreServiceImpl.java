package kayantest.service;

import kayantest.domain.LevelScore;
import kayantest.domain.PlayerLevel;
import kayantest.domain.PlayerScore;
import kayantest.domain.TopList;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreServiceImpl implements ScoreService {

    AtomicInteger registerScoreHits = new AtomicInteger();
    AtomicInteger updateLevelTopHits = new AtomicInteger();
    long currTime = System.nanoTime();

    public static final PlayerScore[] EMPTY_PLAYER_SCORES = new PlayerScore[0];
    private SessionService sessionService;

    private ConcurrentMap<PlayerLevel, AtomicInteger> playerHighScores = new ConcurrentHashMap<PlayerLevel, AtomicInteger>();

    public CopyOnWriteArrayList<TopList> topListLevels = new CopyOnWriteArrayList<>();


    public ScoreServiceImpl(SessionService sessionService, int maxLevels) {
        this.sessionService = sessionService;

        for (int i = 0; i < maxLevels; i++) {
            topListLevels.add(new TopList());
        }

    }

    @Override
    public void registerScore(String sessionId, LevelScore levelScore) {

//        lock.lock();

//        try {
        long leftSecs = TimeUnit.NANOSECONDS.toSeconds((System.nanoTime() - currTime));
        leftSecs = leftSecs == 0 ? 1 : leftSecs;
        int counter = registerScoreHits.incrementAndGet();
        System.out.println("registerScoreHits: " + counter + " timeleft: " + leftSecs +
                " tps: " + counter / leftSecs);

        int playerId = sessionService.findPlayerBy(sessionId);

        int score = levelScore.getScore();

        PlayerLevel playerLevel = new PlayerLevel(playerId, levelScore.getLevel());

        AtomicInteger previousScore = playerHighScores.get(playerLevel);
        if (previousScore == null) {
            previousScore = playerHighScores.putIfAbsent(playerLevel, new AtomicInteger(score));
            //other thread might have already added
            if (previousScore != null) {
                update(playerLevel, score);
            } else {
                System.out.println("new record score: " + score + " registered for: " + playerLevel);
                updateLevelTop(playerLevel, score);
            }
        } else {
            update(playerLevel, score);
        }

//        } finally {
//            lock.unlock();
//        }


    }

    private void update(PlayerLevel playerLevel, int score) {
        while (true) {
            AtomicInteger previousMaxScore = playerHighScores.get(playerLevel);

            if (score <= previousMaxScore.get()) {
                return;
            }

            boolean successful = previousMaxScore.compareAndSet(previousMaxScore.get(), score);
            if (successful) {
                System.out.println("new record score: " + score + " registered for: " + playerLevel);
                updateLevelTop(playerLevel, score);
                return;
            }
        }
    }

    public void updateLevelTop(PlayerLevel playerLevel, int score) {

        long leftSecs = TimeUnit.NANOSECONDS.toSeconds((System.nanoTime() - currTime));
        int counter = updateLevelTopHits.incrementAndGet();

//        System.out.println("updateLevelTopHits: " + updateLevelTopHits.incrementAndGet() + " timeleft: " + TimeUnit.NANOSECONDS.toSeconds((System.nanoTime() - timeleft))
//                + " tps: " + counter / leftSecs
//        );


        topListLevels.get(playerLevel.getLevel()).
                update(new PlayerScore(playerLevel.getPlayerId(), score));
    }

    @Override
    public int getHighScoreBy(PlayerLevel playerLevel) {
        AtomicInteger score = playerHighScores.get(playerLevel);
        return score == null ? 0 : score.get();
    }

    public ConcurrentHashMap getTopListAsMap(int level) {
        return topListLevels.get(level).playerScores;
    }

    @Override
    public PlayerScore[] getTopList(int level) {


        TopList topList = topListLevels.get(level);
        if (topList != null) {
            return topList.asArray();
        } else {
            return EMPTY_PLAYER_SCORES;
        }

    }
}
