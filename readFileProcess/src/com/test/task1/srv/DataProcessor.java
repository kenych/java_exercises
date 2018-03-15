package com.test.task1.srv;

import java.util.concurrent.ExecutionException;

public interface DataProcessor {
     void processData() throws InterruptedException, ExecutionException;
}
