package com.knife.config;
import com.knife.core.TokenFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author geey
 * @Date 2023/8/1 14:56
 * @Version 1.0
 */
@Service
public class TokenService extends TokenFactory<LoginUser> {

    public TokenService() {
        super("secret");
    }

    @Override
    public Class<LoginUser> getTClass() {
        return LoginUser.class;
    }
}
