package com.thisisrandom.javatest.events;

import com.thisisrandom.javatest.Event;
import com.thisisrandom.javatest.impl.AbstractEvent;

public class TradeEvent extends AbstractEvent implements Event {

    private final double notional;

    public TradeEvent(String id, double notional) {
        super(id, null);
        this.notional = notional;
    }

    public double getNotional() {
        return notional;
    }
}
