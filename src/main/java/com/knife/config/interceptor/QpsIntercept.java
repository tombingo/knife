package com.knife.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * qps防护
 *
 * @Author geey
 * @Date 2023/8/14 9:29
 * @Version 1.0
 */
@Component
public class QpsIntercept implements HandlerInterceptor {
    private String KEY_PREFIX = "qps_limiting_";
    private String QPS = "4";
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 滑动窗口计数器限流
     *
     * @param key
     * @return
     */
    public boolean acquire(String key) {
        long now = System.currentTimeMillis();
        key = KEY_PREFIX + key;
        String oldest = String.valueOf(now - 1_000);
        String score = String.valueOf(now);
        String scoreValue = score;
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        //lua文件存放在resources目录下
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis-limiting.lua")));
        return redisTemplate.execute(redisScript, Arrays.asList(key), oldest, score, QPS, scoreValue).toString().equals("1");
    }
}
