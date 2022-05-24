package com.example.spring_security_demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(keySerializer());
        template.setValueSerializer(valueSerializer());

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(keySerializer());
        template.setHashValueSerializer(valueSerializer());

        template.afterPropertiesSet();
        return template;
    }

    // 创建redis的key序列化规则
    private RedisSerializer<?> keySerializer() {
        return new StringRedisSerializer();
    }

    // 值使用jackson进行序列化
    private RedisSerializer<?> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
        // return new JdkSerializationRedisSerializer();
    }
}


