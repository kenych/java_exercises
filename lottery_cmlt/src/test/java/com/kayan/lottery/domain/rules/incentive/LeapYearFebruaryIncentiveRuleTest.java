package com.kayan.lottery.domain.rules.incentive;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Draw;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Win;
import com.kayan.lottery.domain.rules.Rule;
import junitparams.JUnitParamsRunner;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static com.kayan.lottery.TestUtils.TEN;
import static com.kayan.lottery.TestUtils.TWENTY;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class LeapYearFebruaryIncentiveRuleTest {

    public static final int LEAP_YEAR = 2016;
    public static final int FEBRUARY = 2;
    public static final int JANUARY = 1;
    Rule leapYearFebruaryIncentiveRule;

    @Before
    public void setUp() {
        leapYearFebruaryIncentiveRule = new LeapYearFebruaryIncentiveRule();
    }

    @Test
    public void testApplyWins() throws Exception {
        //given
        Game game = new Game(new Draw(new DateTime().
                withYear(LEAP_YEAR).
                withMonthOfYear(FEBRUARY).
                withDayOfMonth(1), null), null).won(new Win());

        //when
        Optional<Win> win = leapYearFebruaryIncentiveRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isTrue();

    }

    @Test
    public void testApplyLost() throws Exception {
        //given
        Game game = new Game(new Draw(new DateTime().
                withYear(LEAP_YEAR).
                withMonthOfYear(JANUARY).
                withDayOfMonth(1), null), null).won(new Win());

        //when
        Optional<Win> win = leapYearFebruaryIncentiveRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isFalse();

    }

    @Test
    public void testCalculateWonAmount() throws Exception {
        //given
        Win win = new Win().withAmount(TEN);

        //when
        BigDecimal wonAmount = leapYearFebruaryIncentiveRule.applyTo(win);

        //then
        assertThat(wonAmount).isEqualTo(TWENTY);

    }

}
