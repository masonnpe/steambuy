package com.steambuy.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public enum RequestQueueList {

    INSTANCE;
    private List<ArrayBlockingQueue<Request>> queueList=new ArrayList<>();

    public ArrayBlockingQueue<Request> get(int index){
        return queueList.get(index);
    }

    public int size(){
        return queueList.size();
    }

    public void add(ArrayBlockingQueue<Request> queue){
        queueList.add(queue);
    }
}
