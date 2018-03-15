package com.test.task1.srv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.test.task1.domain.Event;
import com.test.task1.domain.PriceResut;
import com.test.task1.domain.Result;
import com.test.task1.domain.RiskResut;

public class DataProcessorImpl implements DataProcessor {

	private BlockingQueue<Event> queue;

	public volatile boolean jobFinished = false;

	private int jobsDone = 0;
	
	public List<Future<String>> list = new ArrayList<>(2);

	private LoadBalancer<String> loadBalancer = new LoadBalancer<>(new Sequence(4));

	ThreadPool threadPool = new ThreadPool(4);

	ExecutorService executorService = Executors.newFixedThreadPool(10);

	public DataProcessorImpl(BlockingQueue<Event> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void processData() throws InterruptedException, ExecutionException {

		while (!jobFinished) {

			final Event event = queue.poll(1, TimeUnit.SECONDS);

			if (event != null) {

				

				list.add(executorService.submit(new Callable<String>() {

					@Override
					public String call() throws Exception {
						return threadPool.getThread(loadBalancer.getNode(event.getId())).addJob(event);
					}
				}));

				// list.add(executorService.submit(new Callable<Result>() {
				//
				// @Override
				// public RiskResut call() throws Exception {
				// // TODO Auto-generated method stub
				// Thread.sleep(100);
				// return new RiskResut();
				// }
				//
				// }));
				//
				// list.add(executorService.submit(new Callable<Result>() {
				//
				// @Override
				// public PriceResut call() throws Exception {
				// // TODO Auto-generated method stub
				// Thread.sleep(100);
				// return new PriceResut();
				// }
				//
				// }));
				
				

			} else {
				System.out.println("no jobs");
			}

		}

	}

}
