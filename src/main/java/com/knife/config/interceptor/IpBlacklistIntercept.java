package com.knife.config.interceptor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knife.config.Access;
import com.knife.core.AccessContext;
import com.knife.core.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * ip黑名单
 *
 * @Author geey
 * @Date 2023/8/3 14:55
 * @Version 1.0
 */
@Component
public class IpBlacklistIntercept implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;
    //访问记录前缀
    private final String KEY_PREFIX = "ip_access_";
    //黑名单前缀
    private final String BLACK_KEY_PREFIX = "ip_access_black_";
    //统计窗口时间，秒
    private final long WINDOW_SECOND = 1;
    //窗口内最大访问数
    private final int MAX_ACCESS_NUMBER = 5;
    //小黑屋时间，分
    private final int BLACK_MINUTES = 5;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Access access = AccessContext.getAccessMeta();
        String ip = access.getIp();
        /**
         * 检查黑名单
         */
        String blackKey = BLACK_KEY_PREFIX + ip;
        if (redisTemplate.hasKey(blackKey)) {
            throw new BusinessException("you are in blackList");
        }
        /**
         * 记录并执行黑名单逻辑
         */
        String accessKey = KEY_PREFIX + ip;
        if (redisTemplate.hasKey(accessKey)) {
            //访问次数
            long accessNumber = redisTemplate.opsForValue().increment(accessKey);
            if (accessNumber > MAX_ACCESS_NUMBER) {
                //加入黑名单
                redisTemplate.opsForValue().set(blackKey, 1);
                redisTemplate.expire(blackKey, BLACK_MINUTES, TimeUnit.MINUTES);
                //清空统计数据
                redisTemplate.delete(accessKey);
            }
        } else {
            redisTemplate.opsForValue().increment(accessKey);
            redisTemplate.expire(accessKey, WINDOW_SECOND, TimeUnit.SECONDS);
        }
        return true;
    }
}
