package com.thisisrandom.javatest.impl;

import com.thisisrandom.javatest.Event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CompositeEvent extends AbstractEvent implements Event {

    private final String id;
    private final Event parent;
    private final ConcurrentMap<String, Event> children = new ConcurrentHashMap<>();
    private AtomicInteger totalPendingEvents = new AtomicInteger();

    public CompositeEvent(String id, Event parent) {
        super(id, null);
        this.id = id;
        this.parent = parent;
    }

    public void addPendingEvents(int pendingEvents) {
        this.totalPendingEvents.addAndGet(pendingEvents);
    }

    public boolean allProcessed() {
        return totalPendingEvents.get() == 0;
    }

    public String getId() {
        return id;
    }

    public Event getParent() {
        return parent;
    }

    public CompositeEvent addChild(Event child) {
        children.put(child.getId(), child);
        totalPendingEvents.decrementAndGet();
        return this;
    }

    @SuppressWarnings("unchecked")
    public <E extends Event> E getChildById(String id) {
        return (E) children.get(id);
    }

    public int size() {
        return children.size();
    }

    @Override
    public String toString() {
        return "CompositeEvent{" +
                "children=" + getChildren() +
                "}";
    }

    private String getChildren() {
        String result = "";
        for (String s : children.keySet()) {
            result += "\n" + s + "=" + children.get(s);
        }
        return result;
    }
}
