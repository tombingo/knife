package com.knife.config.interceptor;

import com.knife.config.Permission;
import com.knife.config.PermissionEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 推荐使用aop
 * 权限验证
 * @Author geey
 * @Date 2023/8/3 15:17
 * @Version 1.0
 */
@Deprecated
@Component
public class VerifyPermissionIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(handler instanceof HandlerMethod){
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
//
//        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
