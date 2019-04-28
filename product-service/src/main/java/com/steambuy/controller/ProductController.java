package com.steambuy.controller;

import com.steambuy.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    UserFeignClient userFeignClient;

    @GetMapping("/product/{id}")
    public String hello(@PathVariable Long id){
        return userFeignClient.queryUserById(id);
    }
}
