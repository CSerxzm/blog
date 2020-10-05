package com.xzm.conf;

import com.xzm.bean.Blog;
import com.xzm.bean.Tag;
import com.xzm.bean.Type;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig{
    @Bean
    public RedisTemplate<Object, Blog> blogRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Blog> template = new RedisTemplate<Object, Blog>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Blog> ser = new Jackson2JsonRedisSerializer<Blog>(Blog.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Primary
    @Bean
    public RedisCacheManager blogCacheManager(RedisTemplate<Object, Blog> blogRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(blogRedisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Tag> tagRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Tag> template = new RedisTemplate<Object, Tag>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Tag> ser = new Jackson2JsonRedisSerializer<Tag>(Tag.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheManager tagCacheManager(RedisTemplate<Object, Tag> tagRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(tagRedisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Type> typeRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Type> template = new RedisTemplate<Object, Type>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Type> ser = new Jackson2JsonRedisSerializer<Type>(Type.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheManager typeCacheManager(RedisTemplate<Object, Type> typeRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(typeRedisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
