package com.randomltd.engine;

public class MatchedPair<T> implements Matching<T> {

    private final Matching<T> leftPair;
    private final Matching<T> rightSide;

    public MatchedPair(Matching leftPair, Matching rightSide) {
        this.leftPair = leftPair;
        this.rightSide = rightSide;
    }

    @Override
    public T criteria() {
        return leftPair.criteria();
    }
}
