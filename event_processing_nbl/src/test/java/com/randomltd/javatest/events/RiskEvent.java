package com.thisisrandom.javatest.events;

import com.thisisrandom.javatest.Event;
import com.thisisrandom.javatest.impl.AbstractEvent;

public class RiskEvent extends AbstractEvent implements Event {


    private final double riskValue;

    public RiskEvent(String id, String parentId, double riskValue) {
        super(id, parentId);
        this.riskValue = riskValue;
    }

    public double getRiskValue() {
        return riskValue;
    }
}
