//package com.example.jpademo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * 完成对Redis的整合的一些配置
// */
//@Configuration
//public class RedisConfig {
//    /**
//     * 1.创建JedisPoolConfig对象。在该对象中完成一些连接池配置
//     */
//    @Bean
//    public JedisPoolConfig jedisPoolConfig(){
//        JedisPoolConfig config = new JedisPoolConfig();
//        //最大空闲数
//        config.setMaxIdle(10);
//        //最小空闲数
//        config.setMinIdle(5);
//        //最大连接数
//        config.setMaxActive(20);
//        //没有任何配置
//        return config;
//    }
//
//    /**
//     * 2.创建JedisConnectionFactory:配置redis连接信息
//     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config){
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        //关联连接池配置对象
//        factory.setPoolConfig(config);
//        //配置连接Redis的信息
//        factory.setHostName("");
//        factory.setPort(6379);
//        //指定数据库，默认操作0数据库
//        factory.setDatabase(0);
//        return factory;
//    }
//
//    /**
//     * 3.创建RedisTemplate: 用于执行Redis操作的方法
//     */
//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
//        RedisTemplate<String,Object> template = new RedisTemplate<>();
//        //关联
//        template.setConnectionFactory(factory);
//        return template;
//    }
//}
