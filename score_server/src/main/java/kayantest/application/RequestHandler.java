package kayantest.application;

import kayantest.domain.PlayerScore;
import kayantest.domain.exceptions.SessionException;
import kayantest.infrastructure.Config;
import kayantest.service.ScoreServer;
import static kayantest.infrastructure.Config.*;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class RequestHandler implements Runnable {

    ScoreServer scoreServer;

    CountDownLatch counter;

    Random random = new Random();

    public RequestHandler(ScoreServer scoreServer) {
        this.scoreServer = scoreServer;
    }

    public RequestHandler(ScoreServer scoreServer, CountDownLatch counter) {
        this.scoreServer = scoreServer;
        this.counter = counter;
    }

    @Override
    public void run() {

        int playerId = random.nextInt(20);
        System.out.println("player joins: " + playerId);

        String sessionKey = scoreServer.getSessionKey(playerId);
        System.out.println("session retrieved: " + sessionKey);

        for (int i = 0; i < MAX_SCORE_REGS_PER_SESSION; i++) {

//            sleep(0);

            int level = random.nextInt(Config.MAX_LEVELS);
            System.out.println("player: " + playerId + " plays in level: " + level);

            int score = random.nextInt(1000);
            System.out.println("player: " + playerId + " plays in level: " + level+ " and scores: "+score);


            try {
                scoreServer.registerScore(sessionKey, level, score);
                System.out.println("player: " + playerId + " plays in level: " + level + " and scores: " + score);
            } catch (SessionException e) {
                System.out.println(e);
                break;
            }

            PlayerScore[] top15 = scoreServer.getToplist(level);


            System.out.println("top list for " + level + ":" + Arrays.toString(top15));
        }

        counter.countDown();

    }

    private void sleep(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
