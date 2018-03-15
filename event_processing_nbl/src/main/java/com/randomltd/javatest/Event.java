package com.thisisrandom.javatest;

public interface Event {

    String getId();

    String getFirstParentId();

    void setFirstParentId(String firstParentId);

    boolean isProcessed();

    void markProcessed();
}
