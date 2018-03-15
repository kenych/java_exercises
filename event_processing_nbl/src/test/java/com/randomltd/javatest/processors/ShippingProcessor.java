package com.thisisrandom.javatest.processors;

import com.thisisrandom.javatest.Event;
import com.thisisrandom.javatest.Orchestrator;
import com.thisisrandom.javatest.events.ShippingEvent;
import com.thisisrandom.javatest.events.TradeEvent;

import static com.thisisrandom.javatest.util.TestIdGenerator.shipEventId;

public class ShippingProcessor extends AbstractProcessor {

    public ShippingProcessor(Orchestrator orchestrator) {
        super(orchestrator);
    }

    @Override
    public boolean interestedIn(Event event) {
        return event instanceof TradeEvent;
    }

    @Override
    protected Event processInternal(Event event) {
        String parId = event.getId();
        if (event instanceof TradeEvent)
            //this was returning Margin, deliberately or by accident, so I had to fix
//        return new MarginEvent(shipEventId(parId), parId, calculateTradeShipping(event));

            return new ShippingEvent(shipEventId(parId), parId, calculateTradeShipping(event));
        throw new IllegalArgumentException("unknown event for shipping " + event);
    }

    private double calculateTradeShipping(Event te) {
        return ((TradeEvent) te).getNotional() * 0.2;
    }
}
