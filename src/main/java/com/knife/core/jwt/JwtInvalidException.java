package com.knife.core.jwt;

import com.knife.core.BusinessException;

/**
 * @program: pretty-cms
 * @description:
 * @author: GouYe
 * @create: 2021-09-24 16:00
 **/
public class JwtInvalidException extends BusinessException {

    public JwtInvalidException(String message) {
        super(message);
    }

    public JwtInvalidException() {
        super("身份验证不通过");
    }
}
