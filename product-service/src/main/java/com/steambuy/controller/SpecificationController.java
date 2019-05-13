package com.steambuy.controller;

import com.steambuy.common.model.Wrapper;
import com.steambuy.service.SpecificationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/spec")
@RestController
public class SpecificationController {

    @Resource
    private SpecificationService specificationService;

    @GetMapping("/{id}")
    public ResponseEntity<String> querySpecById(@PathVariable("id") Long id){
        String spec = specificationService.querySpecById(id);
        if(StringUtils.isBlank(spec)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return ResponseEntity.ok(spec);
    }
}
