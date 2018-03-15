package com.randomltd.engine;

import java.util.Map;
import java.util.Queue;

public class MatchingResult<T> {

    private Map<T, Queue<Matching<T>>> lonelyLeftSideMap;
    private Map<T, Queue<Matching<T>>> lonelyRightSideMap;
    private Map<T, Queue<Matching<T>>> matchedPairs;

    public MatchingResult(Map<T, Queue<Matching<T>>> lonelyLeftSideMap,
                          Map<T, Queue<Matching<T>>> lonelyRightSideMap,
                          Map<T, Queue<Matching<T>>> matchedPairs) {
        this.lonelyLeftSideMap = lonelyLeftSideMap;
        this.lonelyRightSideMap = lonelyRightSideMap;
        this.matchedPairs = matchedPairs;
    }

    public Map<T, Queue<Matching<T>>> getLonelyLeftSideMap() {
        return lonelyLeftSideMap;
    }

    public Map<T, Queue<Matching<T>>> getLonelyRightSideMap() {
        return lonelyRightSideMap;
    }

    public Map<T, Queue<Matching<T>>> getMatchedPairs() {
        return matchedPairs;
    }

    public int matchedBy(T criteria) {
        return matchedPairs.get(criteria) == null ? 0 : matchedPairs.get(criteria).size();
    }

    public int unmatchedLeftBy(T criteria) {
        return lonelyLeftSideMap.get(criteria) == null ? 0 : lonelyLeftSideMap.get(criteria).size();
    }

    public int unmatchedRightBy(T criteria) {
        return lonelyRightSideMap.get(criteria) == null ? 0 : lonelyRightSideMap.get(criteria).size();
    }

}
