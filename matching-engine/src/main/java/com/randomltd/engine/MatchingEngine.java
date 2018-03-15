package com.randomltd.engine;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

import static com.randomltd.engine.Pairing.Side.Left;

public class MatchingEngine<T> {

    private static final int DEFAULT_THREAD_COUNT = 100;
    private static final int SEQUENTIAL_THREAD_SIZE = 1;

    private ExecutorService executorService;
    private CountDownLatch doneCounter;
    private int threadCount;
    private Queue<Pairing> jobQueue;

    private ConcurrentMap<T, Queue<Matching<T>>> lonelyLeftSide;
    private ConcurrentMap<T, Queue<Matching<T>>> lonelyRightSide;
    private ConcurrentMap<T, Queue<Matching<T>>> matchedPairs;


    public MatchingEngine(int threadCount) {
        this.threadCount = threadCount;
        lonelyLeftSide = new ConcurrentHashMap<T, Queue<Matching<T>>>();
        lonelyRightSide = new ConcurrentHashMap<T, Queue<Matching<T>>>();
        matchedPairs = new ConcurrentHashMap<T, Queue<Matching<T>>>();
    }

    public MatchingEngine() {
        this(DEFAULT_THREAD_COUNT);
    }

    public MatchingResult findMatches(List<Pairing<T>> list) {
        jobQueue = new ArrayBlockingQueue(list.size(), false, list);
        runJobs(threadCount);
        cleanUp();

        return new MatchingResult(lonelyLeftSide, lonelyRightSide, matchedPairs);
    }

    /**
     * It might happen that one side will be in the middle of the pair search when the other side will be in the middle
     * of adding itself to the lonely queue, that is right after pair search failed and just before it joins lonely queue,
     * In such case non of them will find each other and both will end up in lonely queue.
     * As this might only happen when jobs are run in a concurrent way, we pick up all unhappy
     * pairs, put them back to the queue, then run the job sequentially just for few unlucky pairs so they can meet each other.
     */
    private void cleanUp() {
        collectLonelyPotentialPairsFromTo(lonelyLeftSide, jobQueue);
        collectLonelyPotentialPairsFromTo(lonelyRightSide, jobQueue);
        runJobs(SEQUENTIAL_THREAD_SIZE);
    }

    private void processQueue() {
        for (Pairing<T> pairing = jobQueue.poll(); pairing != null; pairing = jobQueue.poll()) {
            executorService.submit(createJob(pairing));
        }
        waitUntilFinished();
    }

    private void runJobs(int threadSize) {
        executorService = Executors.newFixedThreadPool(threadSize);
        doneCounter = new CountDownLatch(jobQueue.size());
        processQueue();
    }

    private void collectLonelyPotentialPairsFromTo(ConcurrentMap<T, Queue<Matching<T>>> lonelyMap, Collection collection) {
        for (Map.Entry<T, Queue<Matching<T>>> entry : lonelyMap.entrySet()) {
            Queue<Matching<T>> queue = entry.getValue();
            for (Matching<T> pairing = queue.poll(); pairing != null; pairing = queue.poll()) {
                collection.add(pairing);
            }
        }
    }


    private void waitUntilFinished() {
        try {
            doneCounter.await();
        } catch (InterruptedException e) {
            new RuntimeException(e);
        }
    }

    private Runnable createJob(Pairing<T> pairing) {
        return pairing.side() == Left ?
                new MatchingJob(pairing, lonelyLeftSide, lonelyRightSide, matchedPairs, doneCounter) :
                new MatchingJob(pairing, lonelyRightSide, lonelyLeftSide, matchedPairs, doneCounter);
    }
}
