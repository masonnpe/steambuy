package com.steambuy.request;

public class CacheRefreshRequest implements Request {

    private Long id;

    @Override
    public void process() {

    }

    public CacheRefreshRequest(Long id) {
        this.id = id;
    }
    @Override
    public Long getId(){
        return id;
    }
}
