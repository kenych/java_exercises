package kayantest.service;

import kayantest.domain.PlayerLevel;
import kayantest.domain.PlayerScore;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static kayantest.infrastructure.Config.MAX_SCORE_READS_PER_SESSION;


public class TestRequestHandler implements Runnable {

    AtomicInteger readLevelTopHits ;
    long timeleft ;


    int playerId;

    ScoreServiceImpl scoreService;

    CountDownLatch counter;

    Random random = new Random();


    public TestRequestHandler(ScoreServiceImpl scoreService, CountDownLatch counter, int playerId, long timeleft, AtomicInteger readLevelTopHits) {
        this.scoreService = scoreService;
        this.counter = counter;
        this.playerId = playerId;
        this.timeleft = timeleft;
        this.readLevelTopHits = readLevelTopHits;
    }


    @Override
    public void run() {

//        int playerId = random.nextInt(20);
        System.out.println("player joins: " + playerId);

//        update();

        int writeNeeded = 0;
        for (int i = 0; i < MAX_SCORE_READS_PER_SESSION; i++) {

            sleep(50);


            if (writeNeeded++ > 20) {
                update();
                writeNeeded = 0;
            }

            PlayerScore[] playerScores = scoreService.getTopList(0);

            if (playerScores != null) {
                long leftSecs = TimeUnit.NANOSECONDS.toSeconds((System.nanoTime() - timeleft));
                leftSecs = leftSecs == 0 ? 1 : leftSecs;
                int counter = readLevelTopHits.incrementAndGet();
                System.out.println("readLevelTopHits: " + counter + " timeleft: " + leftSecs +
                        " tps: " + counter / leftSecs);

                System.out.println("top list for " + Arrays.toString(playerScores));
            } else {
                System.out.println("player: "+playerId+" updated, writer is busy...");
            }

        }

        counter.countDown();

    }

    private void update() {
        int score = random.nextInt(1_000_000);
        System.out.println("player: " + playerId + " and scores: " + score);

        scoreService.updateLevelTop(new PlayerLevel(playerId, 0), score);
    }

    private void sleep(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
