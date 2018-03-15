package com.thisisrandom.javatest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

public class TestPublisher implements Publisher {

    private Event lastEvent;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private ConcurrentMap<String, Event> map = new ConcurrentHashMap<>();

    @Override
    public void publish(Event event) {
        this.lastEvent = event;
        countDownLatch.countDown();
        map.put(event.getId(), event);
    }

    public Event getLastEvent() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Event result = lastEvent;
        lastEvent = null;
        return result;
    }

    public Event getByInitiatorId(String id) {
        Event event = null;
        while ((event = map.get(id)) == null) {
            safeSleep(100);
        }
        return event;
    }

    public static void safeSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            //ignore
        }
    }

}
