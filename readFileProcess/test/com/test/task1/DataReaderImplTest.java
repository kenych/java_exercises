package com.test.task1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.test.task1.domain.Event;
import com.test.task1.srv.DataProcessor;
import com.test.task1.srv.DataProcessorImpl;
import com.test.task1.srv.DataReader;
import com.test.task1.srv.DataReaderImpl;

public class DataReaderImplTest {

	@Test
	public void test() throws IOException, InterruptedException, ExecutionException {

		final BlockingQueue<Event> queue = new ArrayBlockingQueue<>(1);

		final DataProcessorImpl dataProcessor = new DataProcessorImpl(queue);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					dataProcessor.processData();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		Thread.sleep(3000);

		DataReader dataReader = new DataReaderImpl(queue, "data.txt");
		dataReader.readData();
		

		for(Future<String> f: dataProcessor.list){
			System.out.println(f.get());
		}
	}

}
