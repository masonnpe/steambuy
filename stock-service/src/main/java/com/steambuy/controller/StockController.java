package com.steambuy.controller;

import com.steambuy.model.Stock;
import com.steambuy.request.Request;
import com.steambuy.request.UpdateDBStockRequest;
import com.steambuy.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    RoutingService routingService;

    public ResponseEntity<Boolean> updateStock(Stock stock){
        try {
            Request request = new UpdateDBStockRequest(stock);
            routingService.routing(request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Boolean> getStock(Long skuid){
        return ResponseEntity.ok().build();
    }
}
