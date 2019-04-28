package com.steambuy.controller;

import com.steambuy.common.model.CodeMsg;
import com.steambuy.common.model.ResponseEntity;
import com.steambuy.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @Autowired
    CheckService checkService;

    public ResponseEntity<Boolean> check(@PathVariable String data,@PathVariable String type){
        Boolean check = checkService.check(data, type);
        return ResponseEntity.success(check);
    }
}
