package com.kayan.lottery.domain.rules.incentive;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Draw;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Win;
import com.kayan.lottery.domain.rules.Rule;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.kayan.lottery.TestUtils.TEN;
import static com.kayan.lottery.TestUtils.THIRTY;
import static org.fest.assertions.api.Assertions.assertThat;

public class February29IncentiveRuleTest {

    public static final int FEBRUARY = 2;

    Rule february29IncentiveRule;

    @Before
    public void setUp() {
        february29IncentiveRule = new February29IncentiveRule();
    }

    @Test
    public void testApplyWins() throws Exception {
        //given
        Game game = new Game(new Draw(new DateTime().
                withYear(2016).
                withMonthOfYear(FEBRUARY).
                withDayOfMonth(29), null), null).won(new Win());

        //when
        Optional<Win> win = february29IncentiveRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isTrue();

    }

    @Test
    public void testApplyLost() throws Exception {
        //given
        Game game = new Game(new Draw(new DateTime().
                withYear(2016).
                withMonthOfYear(FEBRUARY).
                withDayOfMonth(1), null), null).won(new Win());

        //when
        Optional<Win> win = february29IncentiveRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isFalse();


    }

    @Test
    public void testCalculateWonAmount() throws Exception {
        //given
        Win win = new Win().withAmount(TEN);

        //when
        BigDecimal wonAmount = february29IncentiveRule.applyTo(win);

        //then
        assertThat(wonAmount).isEqualTo(THIRTY);

    }

}
