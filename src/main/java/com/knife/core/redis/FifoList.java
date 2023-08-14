package com.knife.core.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 先进先出的redisList
 * @author geey
 * @date 2022/12/14 17:28
 * @version 1.1.2
 */
public abstract class FifoList<T> {

    /**
     * redis连接
     */
    private final RedisTemplate redisTemplate;
    /**
     * 阻塞等待时长
     */
    private final Integer TIME_OUT_SECONDS;
    /**
     * 存储的键名
     */
    private final String KEY;

    public FifoList(RedisTemplate redisTemplate, String KEY, Integer TIME_OUT_SECONDS) {
        //禁止不超时阻塞
        if (TIME_OUT_SECONDS <= 0) {
            throw new RuntimeException("阻塞等待时长TIME_OUT 必须大于0！");
        }
        this.redisTemplate = redisTemplate;
        this.TIME_OUT_SECONDS = TIME_OUT_SECONDS;
        this.KEY = KEY;
    }

    /**
     * 消费数据
     * @return
     */
    public Object pop()  {
        try {
            Object obj = redisTemplate.opsForList().rightPop(KEY, TIME_OUT_SECONDS, TimeUnit.SECONDS);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 存储数据
     * @param value
     * @return
     */
    public Long push(T value) {
        try {
            //执行 leftPush 命令后，列表的长度。
            Long size = redisTemplate.opsForList().leftPush(KEY, value);
            return size;
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public Long size(){
        return redisTemplate.opsForList().size(KEY);
    }
}
