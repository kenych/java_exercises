package com.thisisrandom.javatest.impl;

import com.thisisrandom.javatest.Event;

public abstract class AbstractEvent implements Event {
    protected boolean processed = false;
    protected String id;
    protected String parentId;
    protected String firstParentId;

    public AbstractEvent(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    @Override
    public boolean isProcessed() {
        return processed;
    }

    public void markProcessed() {
        processed = true;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getFirstParentId() {
        return firstParentId != null ? firstParentId : id;
    }

    public void setFirstParentId(String firstParentId) {
        this.firstParentId = firstParentId;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "processed=" + processed +
                ", id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", firstParentId='" + firstParentId + '\'' +
                '}';
    }
}
