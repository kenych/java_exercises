package com.kayan.lottery.domain.rules.incentive;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Win;

import java.math.BigDecimal;

public class February29IncentiveRule extends AbstractIncentiveRule {

    static final int FEBRUARY = 2;


    @Override
    public Optional<Win> applyTo(Game game) {
        return game.getDraw().getDrawDate().getMonthOfYear() == FEBRUARY
                && game.getDraw().getDrawDate().getDayOfMonth() == 29
                ? Optional.of(game.getWin()) : Optional.<Win>absent();
    }


    @Override
    public BigDecimal applyTo(Win win) {
        return win.getAmount().multiply(new BigDecimal(3));
    }


    @Override
    public Integer getPriority() {
        return 0;
    }


}
