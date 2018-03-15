package com.thisisrandom.javatest.events;

import com.thisisrandom.javatest.Event;
import com.thisisrandom.javatest.impl.AbstractEvent;

public class MarginEvent extends AbstractEvent implements Event {

    private final double margin;

    public MarginEvent(String id, String parentId, double margin) {
        super(id, parentId);
        this.margin = margin;
    }

    public double getMargin() {
        return margin;
    }
}
