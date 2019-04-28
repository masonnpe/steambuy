package com.steambuy.controller;

import com.github.pagehelper.PageInfo;
import com.steambuy.common.model.ResponseEntity;
import com.steambuy.model.SeckillProduct;
import com.steambuy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/list/page/{pagenum}")
    public ResponseEntity list(@PathVariable int pagenum, @RequestParam(defaultValue = "12",required = false) int pagesize){
        PageInfo<SeckillProduct> pageInfo = productService.selectByPageNum(pagenum, pagesize);
        redisTemplate.opsForValue().set("das","haha",5, TimeUnit.MINUTES);
        return ResponseEntity.success(pageInfo);
    }
}
