package com.knife.core.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @program: pretty-cms
 * @description:
 * @author: GouYe
 * @create: 2021-09-28 16:38
 **/
@Configuration
public class DefaultRedisTemplate extends RedisTemplateFactory{

    @ConditionalOnMissingBean
    @Bean
    @Override
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return super.redisTemplate(redisConnectionFactory);
    }
}
