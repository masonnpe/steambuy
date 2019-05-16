package com.steambuy.request;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

@Slf4j
public class RequestCallable implements Callable<Boolean> {

    private ArrayBlockingQueue<Request> queue;

    public RequestCallable(ArrayBlockingQueue<Request> queue) {
        this.queue=queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true){
                Request request = queue.take();
                request.process();
                return true;
            }

        }catch (Exception e){
            log.info("queue error",e);
        }
        return true;
    }

}
