package kayantest.service;

import kayantest.domain.LevelScore;
import kayantest.domain.PlayerLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

import static org.fest.assertions.api.Assertions.assertThat;


public class ScoreServiceConcurrentTest {

    public static final int MAX_LEVELS = 3;
    private ScoreService scoreService;

    ConcurrentMap<Integer, ConcurrentSkipListSet<Integer>> levelScores;


    private SessionService sessionService;


    @Before
    public void setUp() throws Exception {

        sessionService = new SessionServiceImpl(60, 60);
        createSortedLevels();
        scoreService = new ScoreServiceImpl(sessionService,MAX_LEVELS);

    }

    private void createSortedLevels() {
        levelScores = new ConcurrentHashMap<Integer, ConcurrentSkipListSet<Integer>>(MAX_LEVELS);
        for (int i = 0; i < MAX_LEVELS; i++) {
            levelScores.put(i, new ConcurrentSkipListSet<Integer>());
        }
    }

    @Test
    public void testRegisterScoreForLevelsPerSingleUser() throws InterruptedException {

        Random random = new Random();
        final CountDownLatch countDownLatch = new CountDownLatch(3);

        String session = sessionService.createSession(1);
        newThread(random, countDownLatch, session).start();
        newThread(random, countDownLatch, session).start();
        newThread(random, countDownLatch, session).start();

        countDownLatch.await();

        Integer playerId = sessionService.findPlayerBy(session);

        for (int level = 0; level < MAX_LEVELS; level++) {
            System.out.println("level: " + level + ":");

            Integer actualScoreServiceHighScore = scoreService.getHighScoreBy(new PlayerLevel(playerId, level));
            Integer expectedMax = levelScores.get(level).last();

            System.out.println(" expectedMax from levelScores: " + expectedMax);
            System.out.println(" score from actualScoreServiceHighScore: " + actualScoreServiceHighScore);
            System.out.println(Arrays.toString(scoreService.getTopList(level)));

            assertThat(actualScoreServiceHighScore).isEqualTo(expectedMax);
            assertThat(actualScoreServiceHighScore).isEqualTo(expectedMax);
            assertThat(scoreService.getTopList(level)[0].getScore()).isEqualTo(expectedMax);

        }

    }

    private Thread newThread(final Random random, final CountDownLatch countDownLatch, final String sessionId) {
        return new Thread() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {

                    int level = random.nextInt(MAX_LEVELS);
                    int score = random.nextInt(1000);

                    LevelScore levelScore = new LevelScore(level, score);

                    scoreService.registerScore(sessionId, levelScore);
                    levelScores.get(level).add(score);
                }

                countDownLatch.countDown();
            }
        };
    }

}
