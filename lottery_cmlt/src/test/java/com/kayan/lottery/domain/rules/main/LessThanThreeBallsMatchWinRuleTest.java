package com.kayan.lottery.domain.rules.main;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Draw;
import com.kayan.lottery.domain.Game;
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
public class LessThanThreeBallsMatchWinRuleTest {

    Rule lessThanThreeBallsMatchWinRule;

    @Before
    public void setUp() {
        lessThanThreeBallsMatchWinRule = new LessThanThreeBallsMatchMainRule();
    }

    @Test
    public void testGameWinsWhenLessThan3BallsAreSame() throws Exception {

        //given
        Game game = gameLessThan3Matches();
        //when
        Optional<Win> win = lessThanThreeBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isTrue();
    }

    @Test
    public void testGameLostWhen3BallsAreSame() throws Exception {

        //given
        Game game = game3Matches();

        //when
        Optional<Win> win = lessThanThreeBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isFalse();
    }

    @Test
    public void testGameLostWhenMoreThan3BallsAreSame() throws Exception {

        //given
        Game game = gameMoreThan3Matches();

        //when
        Optional<Win> win = lessThanThreeBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isFalse();
    }

    @Parameters
    private Object[] testDataProvider() {

        Tuple2 win1 = new Tuple2<Win, BigDecimal>(
                new Win(new Draw(NOW, BALLS_1_2_3)),
                new BigDecimal(6));


        Tuple2 win2 = new Tuple2<Win, BigDecimal>(
                new Win(new Draw(NOW, BALLS_4_5_6)),
                new BigDecimal(15));

        return new Object[]{
                win1,
                win2
        };

    }


    @Test
    @Parameters(method = "testDataProvider")
    public void testCalculateWonAmount(Tuple2<Win, BigDecimal> tuple2) throws Exception {
        //given
        Win win = tuple2.first;
        BigDecimal expectedWonAmount = tuple2.second;

        //when
        BigDecimal wonAmount = lessThanThreeBallsMatchWinRule.applyTo(win);

        //then
        assertThat(wonAmount).isEqualTo(expectedWonAmount);

    }
}
