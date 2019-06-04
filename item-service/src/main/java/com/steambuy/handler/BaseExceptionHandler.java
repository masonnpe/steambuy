package com.steambuy.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
//@RestControllerAdvice
//@Slf4j
//public class BaseExceptionHandler {
//
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity execute(Exception e){
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}
