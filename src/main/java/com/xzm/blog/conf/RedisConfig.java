package com.xzm.blog.conf;

import com.xzm.blog.bean.Blog;
import com.xzm.blog.bean.Comment;
import com.xzm.blog.bean.Tag;
import com.xzm.blog.bean.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

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
    public RedisCacheManager blogCacheManager(RedisTemplate<Object, Blog> blogRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(blogRedisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(60);
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
    public RedisCacheManager tagCacheManager(RedisTemplate<Object, Tag> tagRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(tagRedisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(60);
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
    public RedisCacheManager typeCacheManager(RedisTemplate<Object, Type> typeRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(typeRedisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(60);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Comment> commentRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Comment> template = new RedisTemplate<Object, Comment>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Comment> ser = new Jackson2JsonRedisSerializer<Comment>(Comment.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheManager commentCacheManager(RedisTemplate<Object, Comment> commentRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(commentRedisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(60);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Integer> integerRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Integer> template = new RedisTemplate<Object, Integer>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Integer> ser = new Jackson2JsonRedisSerializer<Integer>(Integer.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheManager integerCacheManager(RedisTemplate<Object, Integer> integerRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(integerRedisTemplate);
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(60);
        return cacheManager;
    }
}
