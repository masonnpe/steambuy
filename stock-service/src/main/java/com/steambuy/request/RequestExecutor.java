package com.steambuy.request;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum  RequestExecutor {
    INSTACNE;
    @Value("${request.pool.size}")
    private int poolSize;

    @Value("${request.queue.size}")
    private int queueSize;

    private ExecutorService service= Executors.newFixedThreadPool(poolSize);

    RequestExecutor() {
        RequestQueueList queueList=RequestQueueList.INSTANCE;
        for (int i = 0; i < poolSize; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(queueSize);
            queueList.add(queue);
            service.submit(new RequestCallable(queue));
        }
    }
}
