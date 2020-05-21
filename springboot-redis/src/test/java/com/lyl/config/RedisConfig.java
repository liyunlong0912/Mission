package com.lyl.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *
 */
@Configuration
public class RedisConfig {

    // 编写我们自己的RedisTemplate
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 使用<String,Object>类型
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // json序列化配置
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash采用String序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value采用jackson方式序列化
        template.setValueSerializer(objectJackson2JsonRedisSerializer);
        // hash的value采用jackson方式序列化
        template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
