package com.knife.config;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/14 14:47
 * @Version 1.0
 */
public class QpsException extends RuntimeException{

    public QpsException(String s) {
        super(s);
    }

    public QpsException() {
        super("server is busy");
    }
}
