package com.randomltd.engine;

public interface Pairing<T> extends Matching<T> {
    enum Side {
        Left, Right;
    }

    Side side();
}
