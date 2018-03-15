package com.kayan.lottery.utils;


public class Tuple4<First, Second, Third, Forth> {
    public final First first;
    public final Second second;
    public final Third third;
    public final Forth forth;

    public Tuple4(First first, Second second, Third third, Forth forth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
    }
}
