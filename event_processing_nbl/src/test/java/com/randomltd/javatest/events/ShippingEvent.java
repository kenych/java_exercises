package com.thisisrandom.javatest.events;

import com.thisisrandom.javatest.Event;
import com.thisisrandom.javatest.impl.AbstractEvent;

public class ShippingEvent extends AbstractEvent implements Event {


    private final double shippingCost;

    public ShippingEvent(String id, double shippingCost) {
        this(id, null, shippingCost);
    }

    public ShippingEvent(String id, String parentId, double shippingCost) {
        super(id, parentId);
        this.shippingCost = shippingCost;
    }

    public double getShippingCost() {
        return shippingCost;
    }

}
