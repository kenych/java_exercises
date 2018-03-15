package com.kayan.lottery.domain.rules.main;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Match;
import com.kayan.lottery.domain.Win;
import com.kayan.lottery.domain.rules.Rule;
import com.kayan.lottery.utils.Tuple2;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static com.kayan.lottery.TestUtils.*;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class AllBallsSameWinRuleTest {

    Rule allBallsMatchWinRule;

    @Before
    public void setUp() {
        allBallsMatchWinRule = new AllBallsMatchMainRule();
    }


    @Test
    public void testApplyTrueWhenBallsAreSame() throws Exception {

        //given
        Game game = gameAllMatches();

        //when
        Optional<Win> win = allBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isTrue();
    }

    @Test
    public void testApplyFalseWhenBallsAreNotSame() throws Exception {

        //given
        Game game = game3Matches();

        //when
        Optional<Win> win = allBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isFalse();
    }


    @Parameters
    private Object[] testDataProvider() {

        //given
        return new Object[]{
                new Tuple2<Win, BigDecimal>(
                        new Win(new Match().withGuessedBalls(BALLS_1_2_3_4_5_6)),
                        new BigDecimal("210000")),

                new Tuple2<Win, BigDecimal>(
                        new Win(new Match().withGuessedBalls(BALLS_1_2_3)),
                        new BigDecimal("60000")),

                new Tuple2<Win, BigDecimal>(
                        new Win(new Match().withGuessedBalls(BALLS_1)),
                        new BigDecimal("10000")),
        };

    }

    @Test
    @Parameters(method = "testDataProvider")
    public void testCalculateWonAmountFor(Tuple2<Win, BigDecimal> tuple2) throws Exception {
        //given
        Win win = tuple2.first;
        BigDecimal expectedWonAmount = tuple2.second;

        //when
        BigDecimal actualWonAmount = allBallsMatchWinRule.applyTo(win);

        //then
        assertThat(actualWonAmount).isEqualTo(expectedWonAmount);

    }
}
