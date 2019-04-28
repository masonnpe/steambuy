package com.steambuy.handler;

import com.steambuy.common.model.CodeMsg;
import com.steambuy.common.model.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity execute(Exception e){
        if(e instanceof BindException){

        }
        return ResponseEntity.error(CodeMsg.ACCESS_LIMIT_REACHED);
    }
}
