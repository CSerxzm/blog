package com.xzm.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtils<T> {

    public void setValueList(RedisTemplate redisTemplate,String key,List<T> value){
        redisTemplate.opsForList().rightPushAll(key,value);
        redisTemplate.expire(key,60, TimeUnit.SECONDS);
    }

    public List<T> getValueList(RedisTemplate redisTemplate,String key){
        List<T> result = redisTemplate.opsForList().range(key,0,-1);
        return result;
    }

    public boolean isEmpty(RedisTemplate redisTemplate,String key){
        Boolean result = redisTemplate.hasKey(key);
        return  ! result;
    }

    public void removeValue(RedisTemplate redisTemplate,String key){
        redisTemplate.delete(key);
    }

    //单个操作
    public void setValue(RedisTemplate redisTemplate,String key,T value){
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key,600, TimeUnit.SECONDS);
    }

    public T getValue(RedisTemplate redisTemplate,String key){
        T result = (T) redisTemplate.opsForValue().get(key);
        return result;
    }

}
