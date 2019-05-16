package com.steambuy.request;

import com.steambuy.model.Stock;

public class UpdateDBStockRequest implements Request {
    @Override
    public void process() {

    }

    private Stock stock;

    public UpdateDBStockRequest(Stock stock) {
        this.stock = stock;
    }

    @Override
    public Long getId(){
        return stock.getSkuId();
    }
}
