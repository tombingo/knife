package com.knife.config.interceptor;
import com.google.common.util.concurrent.RateLimiter;
import com.knife.core.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * qps防护
 *
 * @Author geey
 * @Date 2023/8/14 9:29
 * @Version 1.0
 */
@Component
public class QpsIntercept implements HandlerInterceptor {

    /**
     * 限流器
     */
    private  RateLimiter limiter =  RateLimiter.create(200);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!limiter.tryAcquire()){
            throw new BusinessException("服务器繁忙，请稍后再试");
        }
        limiter.acquire();
        return true;
    }
}
