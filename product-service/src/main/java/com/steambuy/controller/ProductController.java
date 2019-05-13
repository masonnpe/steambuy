package com.steambuy.controller;

import com.steambuy.bo.SpuBo;
import com.steambuy.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProductController {

//    @GetMapping("/spu/{id}")
//    public ResponseEntity<SpuBo> queryGoodsById(@PathVariable("id") Long id){
//        SpuBo spuBo=this.goodsService.queryGoodsById(id);
//        if (spuBo == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(spuBo);
//    }
}
