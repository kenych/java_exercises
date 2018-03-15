package com.kayan.lottery.domain;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.joda.time.format.DateTimeFormat.forPattern;

public class PlayerTest {

    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final int DRAW_LENGTH = 6;
    public static final Range NUMBER_RANGE = new Range(1, 60);
    public static final int FREQUENCY = 7;
    public static final int SUBSCRIPTION_LENGTH = 6;


    @Test
    public void testSubscribeTo() throws Exception {

    }

    @Test
    public void testNextDateForLastWeek() throws Exception {

        //given
        DateTime mondayThisWeek = forPattern(DATE_FORMAT_DD_MM_YYYY).parseDateTime("20/01/2014");
        Player player = new Player(mondayThisWeek);

        //when
        DateTime thursdayThisWeek = player.findNext(DayOfWeek.THURSDAY);

        //then
        assertThat(thursdayThisWeek.isAfter(mondayThisWeek)).isTrue();
    }

    @Test
    public void testNextDateForNextWeek() throws Exception {

        //given
        DateTime fridayThisWeek = forPattern(DATE_FORMAT_DD_MM_YYYY).parseDateTime("24/01/2014");
        Player player = new Player(fridayThisWeek);

        //when
        DateTime mondayNextWeek = player.findNext(DayOfWeek.MONDAY);

        //then
        assertThat(mondayNextWeek.isAfter(fridayThisWeek)).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void subscribeToIllegal() {
        //given
        DateTime lessThan6MonthSubscription = DateTime.now().plusMonths(1);
        Player player = new Player(lessThan6MonthSubscription);

        //when
        Lottery lottery = new Lottery(DayOfWeek.MONDAY, SUBSCRIPTION_LENGTH, NUMBER_RANGE, DRAW_LENGTH, FREQUENCY);

        //then
        player.subscribeTo(lottery);
    }


    @Test
    public void subscribeToOk() {
        //given
        DateTime sixMonthSubscription = DateTime.now().plusMonths(6);
        Player player = new Player(sixMonthSubscription);

        //when
        Lottery lottery = new Lottery(DayOfWeek.FRIDAY, SUBSCRIPTION_LENGTH, NUMBER_RANGE, DRAW_LENGTH, FREQUENCY);


        //then
        player.subscribeTo(lottery);
    }
}
