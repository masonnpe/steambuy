package com.steambuy.common.model;

import lombok.ToString;

@ToString
public enum  CodeMsg {
    SUCCESS(0, "success"),
    SERVER_ERROR(500100, "服务端异常"),
    BIND_ERROR(500101, "参数校验异常：%s"),
    REQUEST_ILLEGAL(500102, "请求非法"),
    ACCESS_LIMIT_REACHED(500104, "访问太频繁！"),
    LOGIN_ERROR (500210, "用户未登录"),
    PASSWORD_EMPTY(500211, "登录密码不能为空"),
    MOBILE_EMPTY (500212, "手机号不能为空"),
    MOBILE_ERROR (500213, "手机号格式错误"),
    MOBILE_NOT_EXIST(500214, "手机号不存在"),
    PASSWORD_ERROR(500215, "密码错误"),
    ORDER_NOT_EXIST(500400, "订单不存在"),
    KILL_OVER(500500, "商品已经秒杀完毕"),
    REPEAT_KILL(500501, "不能重复秒杀"),
    KILL_FAIL(500502, "秒杀失败"),
    ILLEGAL_TYPE(500216,"参数类型不存在");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    CodeMsg( int code,String msg ) {
        this.code = code;
        this.msg = msg;
    }
}