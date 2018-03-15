package com.thisisrandom.javatest;

public interface Processor {

    boolean interestedIn(Event event);

    void process(Event event);
}
