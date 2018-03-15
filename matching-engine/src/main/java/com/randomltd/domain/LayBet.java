package com.randomltd.domain;

import static com.randomltd.engine.Pairing.Side.Right;

public class LayBet extends Bet {

    public LayBet(Market market) {
        super(market);
    }

    @Override
    public Side side() {
        return Right;
    }

}
