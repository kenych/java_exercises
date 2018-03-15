package com.randomltd.domain;

import com.randomltd.engine.Pairing;

public abstract class Bet implements Pairing<String> {

    protected Market market;

    public Bet(Market market) {
        this.market = market;
    }

    @Override
    public String criteria() {
        return market.getId();
    }
}
