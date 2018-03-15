package com.kayan.lottery.domain.rules.main;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Match;
import com.kayan.lottery.domain.Win;
import com.kayan.lottery.utils.Tuple4;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class AbstractMainRuleTest {

    AbstractMainRule abstractMainRule;


    @Before
    public void setUp() {
        abstractMainRule = new AbstractMainRule() {
            @Override
            public Integer getPriority() {
                return null;
            }

            @Override
            public Optional<Win> applyTo(Game gamGame) {
                return Optional.absent();
            }

            @Override
            public BigDecimal applyTo(Win win) {
                return null;
            }

            @Override
            int accuracyFactor(Integer drawnBallsAmount) {
                return 0;
            }
        };
    }

    @Parameters
    private Object[] testData() {

        //given
        return new Object[]{
                new Tuple4<List<Integer>, List<Integer>, Integer, Integer>(newArrayList(1, 2, 3), newArrayList(1, 2, 3), 3, 0),
                new Tuple4<List<Integer>, List<Integer>, Integer, Integer>(newArrayList(1, 2, 3), newArrayList(1, 2, 0), 2, 1),
                new Tuple4<List<Integer>, List<Integer>, Integer, Integer>(newArrayList(1, 2, 3), newArrayList(1, 0, 0), 1, 2),
                new Tuple4<List<Integer>, List<Integer>, Integer, Integer>(newArrayList(1, 2, 3), newArrayList(4, 5, 6), 0, 3),
        };

    }

    @Test
    @Parameters(method = "testData")
    public void testFindMatchesFor(Tuple4<List<Integer>, List<Integer>, Integer, Integer> tuple4) throws Exception {
        //given
        List<Integer> guessBalls = tuple4.first;
        List<Integer> drawBalls = tuple4.second;
        Integer expectedGuessSize = tuple4.third;
        Integer expectedMissSize = tuple4.forth;

        //when
        Match match = abstractMainRule.findMatchesFor(guessBalls, drawBalls);

        //then
        assertThat(match.guessSize()).isEqualTo(expectedGuessSize);
        assertThat(match.missSize()).isEqualTo(expectedMissSize);

    }


}
