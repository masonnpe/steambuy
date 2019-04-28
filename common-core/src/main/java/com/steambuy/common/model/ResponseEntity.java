package com.steambuy.common.model;

import lombok.Data;

@Data
public class ResponseEntity<T> {

    private int code;
    private String msg;
    private T data;

    /**
     *  成功时候的调用
     * */
    public static  <T> ResponseEntity<T> success(T data){
        ResponseEntity<T> result = new ResponseEntity<T>(data);
        result.code = CodeMsg.SUCCESS.getCode();
        return new ResponseEntity<T>(data);
    }

    /**
     *  失败时候的调用
     * */
    public static  <T> ResponseEntity<T> error(CodeMsg codeMsg){
        return new ResponseEntity<T>(codeMsg);
    }

    private ResponseEntity(T data) {
        this.data = data;
    }

    private ResponseEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseEntity(CodeMsg codeMsg) {
        if(codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}
