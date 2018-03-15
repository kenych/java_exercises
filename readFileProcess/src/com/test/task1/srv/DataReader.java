package com.test.task1.srv;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataReader {
	void readData() throws  IOException, InterruptedException;
}
