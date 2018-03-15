package com.thisisrandom.javatest;

import com.thisisrandom.javatest.events.MarginEvent;
import com.thisisrandom.javatest.events.RiskEvent;
import com.thisisrandom.javatest.events.ShippingEvent;
import com.thisisrandom.javatest.events.TradeEvent;
import com.thisisrandom.javatest.impl.CompositeEvent;
import com.thisisrandom.javatest.processors.MarginProcessor;
import com.thisisrandom.javatest.processors.RiskProcessor;
import com.thisisrandom.javatest.processors.ShippingProcessor;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.thisisrandom.javatest.util.TestIdGenerator.tradeEventId;
import static com.thisisrandom.javatest.util.TestIdGenerator.tradeEventIdWithPrefix;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SimpleOrchestratorTest {

    static Logger log = Logger.getLogger("test");

    static {
        log.setLevel(Level.FINE);
    }

    Orchestrator orchestrator;
    TestPublisher testPublisher;

    @Before
    public void setup() {
        testPublisher = new TestPublisher();
        orchestrator = new OrchestratorImpl();
        orchestrator.setup(testPublisher);

        orchestrator.register(new MarginProcessor(orchestrator));
        orchestrator.register(new ShippingProcessor(orchestrator));
        orchestrator.register(new RiskProcessor(orchestrator));
    }

    @Test
    public void testSimpleUninterestingEvent() {
        MarginEvent marginEvent = new MarginEvent("marginEvt", null, 4.5);

        orchestrator.receive(marginEvent);
        CompositeEvent ce = (CompositeEvent) testPublisher.getLastEvent();
        assertEquals(marginEvent, ce.getParent());
        assertEquals(0, ce.size());
    }

    @Test
    public void tradeEventShouldTriggerAllProcessors() {
        TradeEvent te = new TradeEvent(tradeEventId(), 1000.0);
        orchestrator.receive(te);

        CompositeEvent ce = (CompositeEvent) testPublisher.getLastEvent();
        assertEquals(te, ce.getParent());
        assertEquals(5, ce.size());

        RiskEvent re1 = ce.getChildById("tradeEvt-riskEvt");
        assertNotNull(re1);
        assertEquals(50.0, re1.getRiskValue(), 0.01);

        MarginEvent me1 = ce.getChildById("tradeEvt-marginEvt");
        assertNotNull(me1);
        assertEquals(10.0, me1.getMargin(), 0.01);

        ShippingEvent se1 = ce.getChildById("tradeEvt-shipEvt");
        assertNotNull(se1);
        assertEquals(200.0, se1.getShippingCost(), 0.01);

        RiskEvent re2 = ce.getChildById("tradeEvt-shipEvt-riskEvt");
        assertNotNull(re2);
        assertEquals(10.0, re2.getRiskValue(), 0.01);

        MarginEvent me2 = ce.getChildById("tradeEvt-shipEvt-marginEvt");
        assertNotNull(me2);
        assertEquals(2.0, me2.getMargin(), 0.01);
    }

    @Test
    public void shippingEventShouldTriggerOnly2Processors() {

        ShippingEvent se = new ShippingEvent("ship2", 500.0);
        orchestrator.receive(se);
        CompositeEvent ce = (CompositeEvent) testPublisher.getLastEvent();
        assertEquals(se, ce.getParent());
        assertEquals(2, ce.size());

        RiskEvent re2 = ce.getChildById("ship2-riskEvt");
        assertNotNull(re2);

        //this was incorrectly set to 50 so I had to fix
        assertEquals(25, re2.getRiskValue(), 0.01);

        MarginEvent me2 = ce.getChildById("ship2-marginEvt");
        assertNotNull(me2);
        //this was incorrectly set to 10 so I had to fix
        assertEquals(5, me2.getMargin(), 0.01);
    }

    @Test
    public void testAddAllJobsAndRunConcurrently() {
        int maxIterations = 500;

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final CountDownLatch countDownLatch = new CountDownLatch(maxIterations);

        List<String> tradeEventIds = new LinkedList<>();
        prepareAndRun(maxIterations, executor, countDownLatch, tradeEventIds);
        assertResults(tradeEventIds);
    }

    private void assertResults(List<String> tradeEventIds) {
        for (String tradeEventId : tradeEventIds) {
            log.fine("looking for:" + tradeEventId);
            CompositeEvent ce = (CompositeEvent) testPublisher.getByInitiatorId(tradeEventId);

            log.info("published: " + ce);
            assertEquals(5, ce.size());

            RiskEvent re1 = ce.getChildById(tradeEventId + "-riskEvt");
            assertNotNull(re1);
            assertEquals(50.0, re1.getRiskValue(), 0.01);

            MarginEvent me1 = ce.getChildById(tradeEventId + "-marginEvt");
            assertNotNull(me1);
            assertEquals(10.0, me1.getMargin(), 0.01);

            ShippingEvent se1 = ce.getChildById(tradeEventId + "-shipEvt");
            assertNotNull(se1);
            assertEquals(200.0, se1.getShippingCost(), 0.01);

            RiskEvent re2 = ce.getChildById(tradeEventId + "-shipEvt-riskEvt");
            assertNotNull(re2);
            assertEquals(10.0, re2.getRiskValue(), 0.01);

            MarginEvent me2 = ce.getChildById(tradeEventId + "-shipEvt-marginEvt");
            assertNotNull(me2);
            assertEquals(2.0, me2.getMargin(), 0.01);
        }
    }

    private void prepareAndRun(int maxIterations, ExecutorService executor, final CountDownLatch countDownLatch, List<String> tradeEventIds) {
        log.info("be patient adding test jobs:" + maxIterations);
        for (int i = 0; i < maxIterations; i++) {
            String tradeEventId = tradeEventIdWithPrefix();
            final TradeEvent te = new TradeEvent(tradeEventId, 1000.0);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    orchestrator.receive(te);
                }
            });
            tradeEventIds.add(tradeEventId);
            if (i == maxIterations - 1) {
                log.info("added all jobs, now it is going to start.");
            }
            countDownLatch.countDown();
        }
    }


}
