package com.knife.core;

/**
 * 消费者异常-对外app
 *
 * @author 86151
 * */
public class BusinessException extends RuntimeException {

    private final Integer code;

    private final String msg;

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code  = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code  = -1;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
