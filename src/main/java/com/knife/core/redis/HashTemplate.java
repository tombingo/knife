package com.knife.core.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis-hash模板
 * @author geey
 * @date 2022/12/22 10:58
 */
public abstract class HashTemplate<R,K> {

    private final RedisTemplate redisTemplate;
    /**
     * key
     */
    private final String HASH_KEY;
    /**
     * 默认过期时间
     */
    private final long HASH_EXPIRED_MILLISECONDS;

    protected HashTemplate(RedisTemplate redisTemplate, String hash_key, long hash_expired_milliSecond) {
        this.redisTemplate = redisTemplate;
        this.HASH_KEY = hash_key;
        this.HASH_EXPIRED_MILLISECONDS = hash_expired_milliSecond;
    }
    /**
     * 读取数据
     * @param key
     * @return
     */
    public R get(K key) {
        Object o = redisTemplate.opsForHash().get(HASH_KEY, key);
        if (o == null) {
            R r = getSource(key);
            if( r != null){
                if(redisTemplate.hasKey(HASH_KEY)){
                    redisTemplate.opsForHash().put(HASH_KEY, key, r);
                }else{
                    redisTemplate.opsForHash().put(HASH_KEY, key, r);
                    redisTemplate.expire(HASH_KEY, HASH_EXPIRED_MILLISECONDS, TimeUnit.MILLISECONDS);
                }
            }
            return r;
        } else {
            return transform(o);
        }
    }
    public void delete( K key ){
        redisTemplate.opsForHash().delete(HASH_KEY,key);
    }

    /**
     * 原数据
     * @param key
     * @return
     */
    public abstract R getSource(K key);

    /**
     * 转换数据
     * @param o
     * @return
     */
    public abstract R transform(Object o);
}
