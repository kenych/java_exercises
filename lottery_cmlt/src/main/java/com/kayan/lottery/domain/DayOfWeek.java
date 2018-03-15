package com.kayan.lottery.domain;


public enum DayOfWeek {


    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    int dayOfWeek;

    DayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int value() {
        return dayOfWeek;
    }
}
