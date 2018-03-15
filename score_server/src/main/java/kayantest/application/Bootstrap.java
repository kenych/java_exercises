package kayantest.application;

import kayantest.service.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import static kayantest.infrastructure.Config.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Bootstrap {



    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = newFixedThreadPool(MAX_THREADS);

        SessionService sessionService = new SessionServiceImpl(EXPIRATION_DELAY_IN_SECONDS, 0);
        ScoreService scoreService = new ScoreServiceImpl(sessionService, MAX_LEVELS);
        ScoreServer scoreServer = new ScoreServerImpl(sessionService, scoreService);

        CountDownLatch counter = new CountDownLatch(MAX_SESSIONS);
        for (int i=0; i < MAX_SESSIONS; i++){
            executor.execute(new RequestHandler(scoreServer,counter));
        }

        counter.await();
        executor.shutdown();
    }
}
