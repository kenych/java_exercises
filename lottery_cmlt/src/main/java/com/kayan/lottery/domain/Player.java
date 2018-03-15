package com.kayan.lottery.domain;

import com.google.common.annotations.VisibleForTesting;
import org.joda.time.DateTime;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Player {

    @VisibleForTesting
    public Player(DateTime startDate, DateTime endDate, List<Integer> guessBalls) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.guessBalls = guessBalls;
    }

    public Player(DateTime endDate, List<Integer> guessBalls) {
        this.endDate = endDate;
        this.guessBalls = guessBalls;
    }

    @VisibleForTesting
    public Player(DateTime endDate) {
        this.endDate = endDate;
    }

    DateTime startDate;
    DateTime endDate;
    List<Integer> guessBalls;

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public List<Integer> getGuessBalls() {
        return guessBalls;
    }

    @Override
    public String toString() {
        return "Player{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", guessBalls=" + guessBalls +
                '}';
    }

    public void subscribeTo(Lottery lottery) {

        endDate = findNext(lottery.getRunDate());
        startDate = endDate.minusMonths(lottery.getSubscriptionLength()).withDayOfWeek(lottery.getRunDate().value());

        checkArgument(startDate.isAfterNow(), "Subscription end date is incorrect, the date should be at least after " +
                DateTime.now().plusMonths(lottery.getSubscriptionLength()).toDate());

    }

    public DateTime findNext(DayOfWeek dayOfWeek) {

//        if (endDate.getDayOfWeek() > dayOfWeek.value()) {
            endDate = endDate.plusWeeks(1);
//        }

        return endDate.withDayOfWeek(dayOfWeek.value());
    }
}
