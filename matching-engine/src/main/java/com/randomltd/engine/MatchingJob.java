package com.randomltd.engine;

import java.util.Queue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

import static com.randomltd.engine.Pairing.Side.Left;

public class MatchingJob<T> implements Runnable {
    Pairing pairingSide;
    ConcurrentMap<T, Queue<Matching<T>>> lonelyPairingSide;
    ConcurrentMap<T, Queue<Matching<T>>> lonelyOtherSide;
    private ConcurrentMap<T, Queue<Matching<T>>> matchedPairs;
    private CountDownLatch workDone;

    public MatchingJob(Pairing pairingSide,
                       ConcurrentMap<T, Queue<Matching<T>>> lonelyPairingSide,
                       ConcurrentMap<T, Queue<Matching<T>>> lonelyOtherSide,
                       ConcurrentMap<T, Queue<Matching<T>>> matchedPairs, CountDownLatch workDone) {
        this.pairingSide = pairingSide;
        this.lonelyPairingSide = lonelyPairingSide;
        this.lonelyOtherSide = lonelyOtherSide;
        this.matchedPairs = matchedPairs;
        this.workDone = workDone;
    }

    @Override
    public void run() {
        Matching<T> pair = findPair();
        if (pair != null) {
            putOrAdd(matchedPairs, createMatchedPairFrom(pair));
        } else {
            putOrAdd(lonelyPairingSide, pairingSide);
        }
        workDone.countDown();
    }

    private Matching<T> createMatchedPairFrom(Matching pair) {
        if (pairingSide.side() == Left) {
            return new MatchedPair(pairingSide, pair);
        } else {
            return new MatchedPair(pair, pairingSide);
        }
    }

    private void putOrAdd(ConcurrentMap<T, Queue<Matching<T>>> map, Matching<T> pairing) {
        Queue<Matching<T>> queue = map.get(pairing.criteria());
        if (queue == null) {
            queue = createNewQueueWithElement(pairing);
            queue = map.putIfAbsent(pairing.criteria(), queue);
        }
        //waiting list already existed for this criteria or was created by other thread right before this thread tried putIfAbsent
        if (queue != null) {
            queue.add(pairing);
        }
    }

    private Queue<Matching<T>> createNewQueueWithElement(Matching<T> pairingSide) {
        Queue<Matching<T>> newQueue = new LinkedBlockingQueue<Matching<T>>();
        newQueue.add(pairingSide);
        return newQueue;
    }


    private Matching<T> findPair() {
        Queue<Matching<T>> lonelyOtherQueue = lonelyOtherSide.get(pairingSide.criteria());
        return lonelyOtherQueue != null ? lonelyOtherQueue.poll() : null;
    }
}
