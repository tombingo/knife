package com.knife.core.jwt;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 对hutool-jwt的自定义封装
 * @author: geey
 * @create: 2021-09-26 14:35
 **/
@Data
public class Jwt implements Serializable {

    private static final String PAYLOAD_NAME = "id";
    private static final long serialVersionUID = 9048197339736255076L;

    /**
     * 加密后的身份凭证
     */
    private  String token;

    /**
     * 失效时间
     */
    private  Long tokenExpired;

    public Jwt(String token, Long tokenExpired) {
        this.token = token;
        this.tokenExpired = tokenExpired;
    }

    /**
     * 创建token
     *
     * @param info        playLoad信息
     * @param millisecond 有效期/ms
     * @param secret      秘钥
     * @return 生成的令牌实体
     */
    public static Jwt create
    (
            String info, int millisecond, String secret
    ) {
        return create(info, secret,DateField.MILLISECOND,millisecond);
    }

    /**
     * 创建token
     * @param info
     * @param secret
     * @param expire
     * @param dateField
     * @return
     */
    public static Jwt create
    (
            String info, String secret, DateField dateField, int expire
    ) {
        Date nowDate = DateUtil.date();
        Date expireAt = DateUtil.offset(nowDate,dateField,expire);
        String token = JWT.create()
                .setExpiresAt(expireAt)
                .setIssuedAt(nowDate)
                .setNotBefore(nowDate)
                .setKey(secret.getBytes())
                .setPayload(PAYLOAD_NAME, info)
                .sign();
        return new Jwt(token, expireAt.getTime()-nowDate.getTime());
    }

    /**
     * 取出token中携带的信息
     * 不校验
     *
     * @param token 令牌
     * @return 解密后的字符串
     */
    public static String getPayLoad(String token) {
        Object o = JWT.of(token).getPayload(PAYLOAD_NAME);
        return o.toString();
    }

    public static String getPayLoad(String token, String secret) {
        verify(token, secret);
        return getPayLoad(token);
    }

    /**
     * 验证token,{算法}、{失效时间}
     * 不解密具体内容
     */
    public static void verify(String token, String secret) {
        JWTValidator jwtValidator;
        try {
            JWT jwt = JWT.of(token).setKey(secret.getBytes());
            jwtValidator = JWTValidator.of(jwt);
            jwtValidator.validateAlgorithm();
        } catch (Exception e) {
            throw new JwtInvalidException();
        }
        try {
            jwtValidator.validateDate();
        } catch (Exception e) {
            throw new JwtExpireException();
        }

    }
}
