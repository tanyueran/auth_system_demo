package com.github.tanyueran.auth_system_springboot.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.tanyueran.auth_system_springboot.vo.UserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean(value = "myRedisTemplate")
    public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        FastJsonRedisSerializer s = new FastJsonRedisSerializer<Object>(Object.class);
        template.setDefaultSerializer(s);
        return template;
    }

    @Bean(value = "userDetailRedisTemplate")
    public RedisTemplate<Object, Object> userDetailRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        FastJsonRedisSerializer s = new FastJsonRedisSerializer<UserDetail>(UserDetail.class);
        template.setDefaultSerializer(s);
        return template;
    }

}
