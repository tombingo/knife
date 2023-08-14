package com.knife.core;

import java.io.Serializable;

/**
 * 响应体组装工具
 * @author 86151
 *
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = -3524059004688026872L;

    /**
     * @Description: 返回的错误码
     */
    private Object code;

    /**
     * @Description: 返回的错误信息
     */
    private String msg;

    /**
     * @Description: 返回体报文的出参，使用泛型兼容不同的类型
     */
    private T data;

    public R(Object code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Object code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 返回成功，传入返回体具体出參
     */
    public static R success(Object object) {
        return new R(0, "success", object);
    }

    /**
     * 返回成功，传入返回体具体出參
     */
    public static R ok(Object object) {
        return new R(0, "success", object);
    }

    /**
     * 提供给部分不需要出參的接口
     */
    public static R success() {
        return success(null);
    }

    /**
     * 提供给部分不需要出參的接口
     */
    public static R ok() {
        return success(null);
    }

    /**
     * 返回可描述的错误
     */
    public static R error(String msg) {
        return new R(-1, msg);
    }

    public static R error() {
        return new R(-1, "未知错误");
    }

    public static R error(int code, String msg) {
        return new R(code, msg);
    }

    /**
     * 为认证用户
     */
    public static R unauthenticated() {
        return new R(401, "身份未认证");
    }
}
