package com.knife.core.redis;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * @program: pretty-cms
 * @description: 自行配置redis
 * @author: GouYe
 * @create: 2021-10-08 17:35
 **/
public class RedisTemplateFactory {

    /**
     * 创建系统默认的redis连接
     */

    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // key序列化,参照StringRedisTemplate内部实现指定序列化器
        redisTemplate.setKeySerializer(keySerializer());

        // key序列化
        redisTemplate.setHashKeySerializer(keySerializer());

        // value序列化
        redisTemplate.setValueSerializer(valueSerializer());

        // key haspMap序列化
        redisTemplate.setHashValueSerializer(valueSerializer());

        return redisTemplate;
    }

    /**
     * 自主创建redis连接
     */
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionBuilder redisConnectionBuilder) {
        // 基本配置
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisConnectionBuilder.getHost());
        configuration.setPort(redisConnectionBuilder.getPort());
        configuration.setDatabase(redisConnectionBuilder.getDatabase());
        if (StrUtil.isNotBlank(redisConnectionBuilder.getPassword())) {
            configuration.setPassword(
                    RedisPassword.of(redisConnectionBuilder.getPassword()));
        }

        // 连接池配置
        GenericObjectPoolConfig<Object> genericObjectPoolConfig =
                new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(redisConnectionBuilder.getMaxActive());
        genericObjectPoolConfig.setMaxWaitMillis(redisConnectionBuilder.getMaxWait());
        genericObjectPoolConfig.setMaxIdle(redisConnectionBuilder.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisConnectionBuilder.getMinIdle());

        // lettuce pool
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder = LettucePoolingClientConfiguration.builder();
        builder.poolConfig(genericObjectPoolConfig);
        builder.commandTimeout(Duration.ofSeconds(redisConnectionBuilder.getTimeout()));
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration, builder.build());

        lettuceConnectionFactory.afterPropertiesSet();
        return  redisTemplate(lettuceConnectionFactory);
    }

    /**
     * 定义键使用的序列化器
     */
    public RedisSerializer<Object> keySerializer() {
        return new KeySerializer();
    }

    /**
     * 定义value使用的fastJson序列化器
     */
    public RedisSerializer<Object> valueSerializer() {
        return new FastJsonSerializer(Object.class);
    }
}
