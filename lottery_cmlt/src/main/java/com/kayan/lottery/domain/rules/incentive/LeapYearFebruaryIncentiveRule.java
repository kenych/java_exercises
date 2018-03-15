package com.kayan.lottery.domain.rules.incentive;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Win;

import java.math.BigDecimal;

public class LeapYearFebruaryIncentiveRule extends AbstractIncentiveRule {


    static final int FEBRUARY = 2;


    @Override
    public Optional<Win> applyTo(Game game) {
        return isLeapYearFebruary(game)
                ? Optional.of(game.getWin()) : Optional.<Win>absent();
    }

    boolean isLeapYearFebruary(Game game) {
        return game.getDraw().getDrawDate().year().isLeap()
                && game.getDraw().getDrawDate().getMonthOfYear() == FEBRUARY;
    }

    @Override
    public BigDecimal applyTo(Win win) {
        return win.getAmount().multiply(new BigDecimal(2));
    }


    @Override
    public Integer getPriority() {
        return 1;
    }


}
