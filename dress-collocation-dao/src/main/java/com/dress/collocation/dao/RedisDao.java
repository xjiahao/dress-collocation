package com.dress.collocation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Description: redis操作类
 * Created by xuejiahao on 2016/10/23.
 */
@Component
public class RedisDao {

    @Autowired
    RedisTemplate<Serializable,Object> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        if(!exists(key)) return null;
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 批量移除
     * @param keys
     */
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 删除
     *
     * @param key
     */
    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value,Long expireTime) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value,expireTime,TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }









}
