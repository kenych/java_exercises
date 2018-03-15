package com.test.task1.srv;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import com.test.task1.domain.Event;

public class WorkerThread extends Thread{
	
	BlockingQueue<Event> queue = new LinkedBlockingQueue<>();
	BlockingQueue<String> queueRes = new LinkedBlockingQueue<>();
	
	public String addJob(Event event){
		
		queue.add(event);
		return queueRes.poll();
	}
	
	@Override
	public void run() {
		
		Event event;
		try {
			while((event = queue.poll(10, TimeUnit.SECONDS))!=null){
				queueRes.add(Thread.currentThread().getId()+" is processing: "+ event);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
