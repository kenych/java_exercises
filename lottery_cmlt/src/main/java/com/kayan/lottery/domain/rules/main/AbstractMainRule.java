package com.kayan.lottery.domain.rules.main;

import com.kayan.lottery.domain.Match;
import com.kayan.lottery.domain.rules.Rule;

import java.util.List;

public abstract class AbstractMainRule implements Rule {

    abstract int accuracyFactor(Integer drawnBallsAmount);

    Match findMatchesFor(List<Integer> guessBalls, List<Integer> drawBalls) {

        Match match = new Match();
        for (int i = 0; i < guessBalls.size(); i++) {
            if (equals(guessBalls, drawBalls, i)) {
                match.addGuessed(drawBalls.get(i));
            } else {
                match.addMissed(drawBalls.get(i));
            }
        }

        return match;
    }

    private boolean equals(List<Integer> guessBalls, List<Integer> drawBalls, int i) {
        return guessBalls.get(i).equals(drawBalls.get(i));
    }

    @Override
    public boolean isIncentive() {
        return false;
    }


}
