package com.knife.config.interceptor;
import com.knife.config.Access;
import com.knife.config.LoginUser;
import com.knife.core.AccessContext;
import com.knife.config.TokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 对用户的身份进行验证
 * @Author geey
 * @Date 2023/8/2 14:21
 * @Version 1.0
 */
@Component
@Log4j2
public class AuthenticateIntercept implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    /**
     * 验证身份，通过后将访问用户的信息存入内存
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Access access  =  AccessContext.getAccessMeta();
        String token  = request.getHeader("auth");
        LoginUser loginUser =  tokenService.decrypt(token);
        access.setUser(loginUser);
        AccessContext.setAccessMeta(access);
        return true;
    }
}
