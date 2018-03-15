package com.kayan.lottery.domain.rules.main;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Match;
import com.kayan.lottery.domain.Win;

import java.math.BigDecimal;

public class LessThanThreeBallsMatchMainRule extends AbstractMainRule {

    static final int ACCURACY = 3;


    @Override
    public Optional<Win> applyTo(Game game) {
        Match match = findMatchesFor(
                game.getDraw().getDrawnBalls(),
                game.getPlayer().getGuessBalls());

        return (match.guessSize() < accuracyFactor())
                ? Optional.of(new Win(game.getDraw())) : Optional.<Win>absent();
    }


    @Override
    public BigDecimal applyTo(Win win) {
        return missedNumbersPrize(win);
    }


    private BigDecimal missedNumbersPrize(Win win) {
        BigDecimal total = BigDecimal.ZERO;

        for (Integer aBall : win.getDraw().getDrawnBalls()) {
            total = total.add(new BigDecimal(aBall));
        }

        return total;
    }

    @Override
    public Integer getPriority() {
        return 2;
    }

    private int accuracyFactor() {
        return accuracyFactor(null);
    }

    @Override
    int accuracyFactor(Integer drawnBallsAmount) {
        return ACCURACY;
    }

}
