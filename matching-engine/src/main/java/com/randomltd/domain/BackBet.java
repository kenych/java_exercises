package com.randomltd.domain;

import static com.randomltd.engine.Pairing.Side.Left;

public class BackBet extends Bet {

    public BackBet(Market market) {
        super(market);
    }

    @Override
    public Side side() {
        return Left;
    }
}
