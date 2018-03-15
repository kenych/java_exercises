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
public class ThreeAndMoreBallsMatchWinRuleTest {

    Rule threeAndMoreBallsMatchWinRule;

    @Before
    public void setUp() {
        threeAndMoreBallsMatchWinRule = new ThreeAndMoreBallsMatchMainRule();
    }


    @Test
    public void testGameWinsWhen3BallsAreSame() throws Exception {
        //given
        Game game = game3Matches();

        //when
        Optional<Win> win = threeAndMoreBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isTrue();
    }

    @Test
    public void testGameWinsWhenMoreThan3BallsAreSame() throws Exception {

        //given
        Game game = gameMoreThan3Matches();

        //when
        Optional<Win> win = threeAndMoreBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isTrue();
    }

    @Test
    public void testGameLostWhenLessThan3BallsAreNotSame() throws Exception {

        //given
        Game game = gameLessThan3Matches();

        //when
        Optional<Win> win = threeAndMoreBallsMatchWinRule.applyTo(game);

        //then
        assertThat(win.isPresent()).isFalse();
    }


    @Parameters
    private Object[] testDataProvider() {

        Tuple2 winWith3BallsGuessed = new Tuple2<Win, BigDecimal>(
                new Win(new Match().
                        withGuessedBalls(BALLS_1_2_3).
                        withMissedBalls(BALLS_4_5_6)),
                new BigDecimal(3120));


        Tuple2 winWith3AndMoreBallsGuessed = new Tuple2<Win, BigDecimal>(
                new Win(new Match().
                        withGuessedBalls(BALLS_1_2_3_1_1_1).
                        withMissedBalls(BALLS_1_2_3)),
                new BigDecimal(6006));

        return new Object[]{
                winWith3BallsGuessed,
                winWith3AndMoreBallsGuessed
        };

    }

    @Test
    @Parameters(method = "testDataProvider")
    public void testCalculateWonAmount(Tuple2<Win, BigDecimal> tuple2) throws Exception {
        //given
        Win win = tuple2.first;
        BigDecimal expectedWonAmount = tuple2.second;

        //when
        BigDecimal wonAmount = threeAndMoreBallsMatchWinRule.applyTo(win);

        //then
        assertThat(wonAmount).isEqualTo(expectedWonAmount);

    }
}
