package com.steambuy.service;

import com.steambuy.request.Request;
import com.steambuy.request.RequestQueueList;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

@Service
public class RoutingService {

    public void routing(Request request){
        String key = String.valueOf(request.getId());
        int h;
        int hash=(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        int index=(RequestQueueList.INSTANCE.size() - 1) & hash;
        ArrayBlockingQueue<Request> queue = RequestQueueList.INSTANCE.get(index);
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
