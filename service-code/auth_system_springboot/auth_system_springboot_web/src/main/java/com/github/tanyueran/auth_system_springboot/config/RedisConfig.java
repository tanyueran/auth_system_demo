package com.github.tanyueran.auth_system_springboot.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.tanyueran.auth_system_springboot.vo.UserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean(value = "myRedisTemplate")
    @Primary
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

    @Bean
    public RedisCacheConfiguration userDetailRedisCacheConfiguration() {
        RedisSerializer<UserDetail> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<UserDetail>(
                UserDetail.class);
        RedisSerializationContext.SerializationPair<UserDetail> serializationPair = RedisSerializationContext.SerializationPair
                .fromSerializer(jackson2JsonRedisSerializer);

        return RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializationPair);
    }

    @Bean
    public RedisCacheManager userDetailRedisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(userDetailRedisCacheConfiguration());
        RedisCacheManager cm = builder.build();
        return cm;
    }

}
