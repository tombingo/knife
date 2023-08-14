package com.knife.core.jwt;

import com.knife.core.BusinessException;

/**
 * @author geey
 * @date 2022/9/23 14:52
 */
public class JwtExpireException extends BusinessException {

    public JwtExpireException(String message) {
        super(message);
    }

    public JwtExpireException() {
        super("身份凭证已过期");
    }
}
