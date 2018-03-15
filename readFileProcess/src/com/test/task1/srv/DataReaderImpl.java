package com.test.task1.srv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.test.task1.domain.Event;

public class DataReaderImpl implements DataReader {

	private BlockingQueue<Event> queue;

	private String fileName;

	public DataReaderImpl(BlockingQueue<Event> queue, String fileName) {
		this.queue = queue;
		this.fileName = fileName;
	}

	@Override
	public void readData() throws IOException, InterruptedException {
		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader(DataReaderImpl.class.getClassLoader().getResource(fileName).getFile()))) {

			for (String line; (line = bufferedReader.readLine()) != null; ) {

				String[] parts = line.split(" ");
				System.out.println(Arrays.toString(parts));

				Event event = new Event(parts[0], parts[1], Integer.parseInt(parts[2]));
				boolean result = queue.offer(event, 1, TimeUnit.SECONDS);
				if(!result){
					throw new RuntimeException("file read timeout");
				}
			}

		}

	}

}
