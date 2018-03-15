package com.thisisrandom.javatest.processors;

import com.thisisrandom.javatest.Event;
import com.thisisrandom.javatest.Orchestrator;
import com.thisisrandom.javatest.events.RiskEvent;
import com.thisisrandom.javatest.events.ShippingEvent;
import com.thisisrandom.javatest.events.TradeEvent;

import static com.thisisrandom.javatest.util.TestIdGenerator.riskEventId;

public class RiskProcessor extends AbstractProcessor {

    public RiskProcessor(Orchestrator orchestrator) {
        super(orchestrator);
    }

    @Override
    public boolean interestedIn(Event event) {
        return event instanceof TradeEvent || event instanceof ShippingEvent;
    }

    @Override
    protected Event processInternal(Event event) {
        String parId = event.getId();
        if (event instanceof TradeEvent)
            return new RiskEvent(riskEventId(parId), parId, calculateTradeRisk(event));
        else if (event instanceof ShippingEvent)
            return new RiskEvent(riskEventId(parId), parId, calculateShippingRisk(event));
        throw new IllegalArgumentException("unknown event for risk " + event);
    }

    private double calculateShippingRisk(Event se) {
        return ((ShippingEvent) se).getShippingCost() * 0.05;
    }

    private double calculateTradeRisk(Event te) {
        return ((TradeEvent) te).getNotional() * 0.05;
    }
}
