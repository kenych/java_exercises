package com.kayan.lottery.domain;

public class Range {

    Integer start;
    Integer end;

    public Range(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }


    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }
}
