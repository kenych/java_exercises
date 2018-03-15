package com.kayan.lottery.domain.rules.main;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Match;
import com.kayan.lottery.domain.Win;

import java.math.BigDecimal;

public class ThreeAndMoreBallsMatchMainRule extends AbstractMainRule {

    static final BigDecimal MULTIPLIER = new BigDecimal("1000");
    static final int ACCURACY = 3;


    @Override
    public Optional<Win> applyTo(Game game) {
        Match match = findMatchesFor(
                game.getDraw().getDrawnBalls(),
                game.getPlayer().getGuessBalls());

        return (match.guessSize() >= accuracyFactor())
                ? Optional.of(new Win(match)) : Optional.<Win>absent();
    }

    @Override
    public BigDecimal applyTo(Win win) {
        return guessedNumbersPrize(win).add(missedNumbersPrize(win));
    }

    private BigDecimal guessedNumbersPrize(Win win) {
        return new BigDecimal(win.getMatch().guessSize()).multiply(MULTIPLIER);
    }

    private BigDecimal missedNumbersPrize(Win win) {
        BigDecimal total = BigDecimal.ONE;
        for (Integer missedBall : win.getMatch().getMissedBalls()) {
            total = total.multiply(new BigDecimal(missedBall));
        }
        return total;
    }

    @Override
    public Integer getPriority() {
        return 1;
    }

    private int accuracyFactor() {
        return accuracyFactor(null);
    }

    @Override
    int accuracyFactor(Integer drawnBallsAmount) {
        return ACCURACY;
    }

}
