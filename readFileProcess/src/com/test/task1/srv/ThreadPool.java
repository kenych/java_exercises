package com.test.task1.srv;

public class ThreadPool {
	
	private int threadSize;
	
	private WorkerThread[]threads = new WorkerThread[4];

	public ThreadPool(int threadSize) {
		super();
		this.threadSize = threadSize;
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new WorkerThread();
			threads[i].start();
		}
	}
	
	public WorkerThread getThread(int id){
		return threads[id];
		
	}
	
	

}
