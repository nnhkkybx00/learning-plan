//package com.dl.rediscache.config;
//
//import com.dl.rediscache.utils.JedisClient;
//import com.dl.rediscache.utils.impl.JedisClientSingle;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate redisTemplate(){
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        redisTemplate.setKeySerializer(stringRedisSerializer());
//        return redisTemplate;
//    }
//    @Bean
//    public StringRedisSerializer stringRedisSerializer(){
//        return new StringRedisSerializer();
//    }
//    @Bean
//    public JedisClient jedisClient() {
//        return new JedisClientSingle();
//    }
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName("localhost");
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setDatabase(0);
//        jedisConnectionFactory.setTimeout(3000);
//        jedisConnectionFactory.setUsePool(true);
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
//        return jedisConnectionFactory;
//    }
//    @Bean
//    public JedisPoolConfig jedisPoolConfig(){
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(10000);
//        jedisPoolConfig.setMaxIdle(10);
//        return jedisPoolConfig;
//    }
//}
