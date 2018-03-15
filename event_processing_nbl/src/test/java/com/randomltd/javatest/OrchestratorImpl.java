package com.thisisrandom.javatest;

import com.thisisrandom.javatest.impl.CompositeEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrchestratorImpl implements Orchestrator {

    static Logger log = Logger.getLogger("test");

    static {
        log.setLevel(Level.FINE);
    }

    private List<Processor> processors = new LinkedList<>();
    private Publisher publisher;
    ConcurrentMap<String, CompositeEvent> map = new ConcurrentHashMap<>();

    @Override
    public void register(Processor processor) {
        processors.add(processor);
    }

    @Override
    public void receive(Event event) {
        log.fine("received event " + event);

        CompositeEvent compositeEvent = createOrGetComposite(event);

        handleInterestingEvents(event, compositeEvent);

        handleProcessedEvents(event, compositeEvent);

        publishIfNeeded(compositeEvent);
    }

    private void handleInterestingEvents(Event event, CompositeEvent compositeEvent) {
        List<Processor> interestedProcessors = getInterestedProcessors(event);

        if (interestedProcessors.size() > 0) {
            compositeEvent.addPendingEvents(interestedProcessors.size());
            dispatchEvents(event, interestedProcessors);
        }
    }

    private void handleProcessedEvents(Event event, CompositeEvent compositeEvent) {
        if (event.isProcessed()) {
            log.fine("processed event will be added: " + event);
            compositeEvent.addChild(event);
        }
    }

    private void publishIfNeeded(CompositeEvent compositeEvent) {
        if (compositeEvent.allProcessed()) {
            log.fine("all processed:" + compositeEvent.size());
            publisher.publish(compositeEvent);
            map.remove(compositeEvent.getId());
        }
    }

    private void dispatchEvents(Event event, List<Processor> interestedProcessors) {
        for (Processor interestedProcessor : interestedProcessors) {
            interestedProcessor.process(event);
        }
    }

    private List<Processor> getInterestedProcessors(Event event) {
        List<Processor> interestedProcessors = new LinkedList<>();

        for (Processor processor : processors) {
            if (processor.interestedIn(event)) {
                interestedProcessors.add(processor);
                log.fine("processor: " + processor + " interested in " + event);
            }
        }

        return interestedProcessors;
    }

    private CompositeEvent createOrGetComposite(Event event) {
        CompositeEvent compositeEvent = map.get(event.getFirstParentId());
        if (compositeEvent == null) {
            compositeEvent = new CompositeEvent(event.getId(), event);
            log.fine("created new " + compositeEvent);
            map.put(event.getFirstParentId(), compositeEvent);
        }
        return compositeEvent;
    }

    @Override
    public void setup(Publisher publisher) {
        this.publisher = publisher;
    }

}
