package com.kayan.lottery.domain.rules.main;

import com.google.common.base.Optional;
import com.kayan.lottery.domain.Game;
import com.kayan.lottery.domain.Match;
import com.kayan.lottery.domain.Win;

import java.math.BigDecimal;

public class AllBallsMatchMainRule extends AbstractMainRule {

    static final BigDecimal MULTIPLIER = new BigDecimal("10000");

    @Override
    public Optional<Win> applyTo(Game game) {
        Match match = findMatchesFor(
                game.getDraw().getDrawnBalls(),
                game.getPlayer().getGuessBalls());

        return (match.guessSize() == accuracyFactor(game.getDraw().getDrawnBalls().size()))
                ? Optional.of(new Win(match)) : Optional.<Win>absent();
    }

    @Override
    public BigDecimal applyTo(Win win) {
        return guessedNumbersPrize(win);
    }

    private BigDecimal guessedNumbersPrize(Win win) {
        BigDecimal total = BigDecimal.ZERO;
        for (Integer number : win.getMatch().getGuessedBalls()) {
            total = total.add(new BigDecimal(number).multiply(MULTIPLIER));
        }
        return total;
    }

    @Override
    public Integer getPriority() {
        return 0;
    }

    @Override
    int accuracyFactor(Integer drawnBallsAmount) {
        return drawnBallsAmount;
    }

}
