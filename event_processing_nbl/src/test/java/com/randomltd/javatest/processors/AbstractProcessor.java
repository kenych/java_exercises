package com.thisisrandom.javatest.processors;

import com.thisisrandom.javatest.Event;
import com.thisisrandom.javatest.Orchestrator;
import com.thisisrandom.javatest.Processor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractProcessor implements Processor {

    private final Orchestrator orchestrator;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    protected AbstractProcessor(Orchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public final void process(Event event) {
        threadPool.submit(new EventTask(event));
    }

    protected abstract Event processInternal(Event event);

    private class EventTask implements Runnable {
        private final Event input;

        private EventTask(Event input) {
            this.input = input;
        }

        @Override
        public void run() {
            Event output = processInternal(input);
            output.setFirstParentId(input.getFirstParentId());
            output.markProcessed();
            orchestrator.receive(output);
        }
    }

}
