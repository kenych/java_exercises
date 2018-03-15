package com.thisisrandom.javatest.util;

import java.util.concurrent.atomic.AtomicInteger;

public class TestIdGenerator {

    static AtomicInteger uniquePrefix = new AtomicInteger();

    public static String tradeEventId() {
        return "tradeEvt";
    }

    public static String tradeEventIdWithPrefix() {
        return uniquePrefix.incrementAndGet() + "-tradeEvt";
    }

    public static String shipEventId(String parId) {
        return parId == null ? "shipEvt" : parId + "-shipEvt";
    }

    public static String shipEventIdWithPrefix(String parId) {
        return parId == null ? uniquePrefix.incrementAndGet() + "shipEvt" : parId + "-shipEvt";
    }

    public static String riskEventId(String parId) {
        return parId + "-riskEvt";
    }

    public static String marginEventId(String parId) {
        return parId + "-marginEvt";
    }


}
