package com.kayan.lottery.domain;

public class Lottery {

    DayOfWeek runDate;
    Integer subscriptionLength;
    Range numberRange;
    private Integer drawLength;
    private Integer frequency;


    public Lottery(DayOfWeek runDate, Integer subscriptionLength, Range numberRange, Integer drawLength, Integer frequency) {
        this.runDate = runDate;
        this.subscriptionLength = subscriptionLength;
        this.numberRange = numberRange;
        this.drawLength = drawLength;
        this.frequency = frequency;
    }


    public DayOfWeek getRunDate() {
        return runDate;
    }

    public Integer getSubscriptionLength() {
        return subscriptionLength;
    }

    public Range getNumberRange() {
        return numberRange;
    }

    public Integer getDrawLength() {
        return drawLength;
    }

    public Integer getFrequency() {
        return frequency;
    }
}
