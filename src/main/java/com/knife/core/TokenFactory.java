package com.knife.core;

import cn.hutool.core.date.DateField;
import com.alibaba.fastjson2.JSONObject;
import com.knife.core.jwt.Jwt;

/**
 * 凭证获取方式
 * @author 86151
 */
public abstract  class TokenFactory<T> {

    /**
     * token默认有效日期是2小时
     */
    private Integer expiredSeconds = 7200;

    private String secret;

    public TokenFactory(String secret) {
        this.secret = secret;
    }

    public TokenFactory(Integer expiredSeconds, String secret) {
        this.expiredSeconds = expiredSeconds;
        this.secret = secret;
    }

    /**
     * 加密
     * @param t
     * @return
     */
   public Jwt encrypt(T t){
       String s = JSONObject.toJSONString(t);
       return Jwt.create(s,secret, DateField.SECOND,expiredSeconds);
   }

    /**
     * 解密
     * @param token
     * @return
     */
    public T  decrypt(String token){
        Jwt.verify(token,secret);
        String info =  Jwt.getPayLoad(token);
        return  JSONObject.parseObject(info,getTClass());
    }

    public abstract Class<T> getTClass();
}
