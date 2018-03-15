package kayantest.service;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static kayantest.infrastructure.Config.MAX_SESSIONS;
import static kayantest.infrastructure.Config.MAX_THREADS;

public class ScoreServicePerformanceTest {

    public static final ScoreServiceImpl SCORE_SERVICE = new ScoreServiceImpl(null, 1);
    AtomicInteger readLevelHits = new AtomicInteger();
    ScoreServiceImpl scoreService;

    @Before
    public void setUp() {
        scoreService = new ScoreServiceImpl(null, 1);

    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(MAX_THREADS);

        CountDownLatch counter = new CountDownLatch(MAX_SESSIONS);

        AtomicInteger playerId = new AtomicInteger();


        for (int i = 0; i < MAX_SESSIONS; i++) {
            executor.execute(new TestRequestHandler(SCORE_SERVICE, counter, playerId.incrementAndGet(), System.nanoTime(), readLevelHits));
            Thread.sleep(100);
        }

        counter.await();
        executor.shutdown();


    }

}
