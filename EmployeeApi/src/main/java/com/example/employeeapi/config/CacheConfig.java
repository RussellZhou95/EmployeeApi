package com.example.employeeapi.config;

import com.example.employeeapi.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    //LettuceConnectionFactory not work when redis server is down for fall back method
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
//
//        return lettuceConnectionFactory;
//    }

    @Bean
    RedisTemplate<String, EmployeeDTO> redisTemplate() {
        jedisConnectionFactory().getConnection().flushDb();
        RedisTemplate<String, EmployeeDTO> redisTemplate = new RedisTemplate<String, EmployeeDTO>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
