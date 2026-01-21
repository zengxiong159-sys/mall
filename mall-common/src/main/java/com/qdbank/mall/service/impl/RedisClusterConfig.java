//package com.qdbank.mall.service.impl;
//
//import lombok.Data;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * RedisClusterConfig
// *
// * @author shaoshihang
// * @date 2021/3/15 15:39
// * @since 1.0.0
// */
//@Configuration
//@Data
//public class RedisClusterConfig {
//    @Value("${spring.redis.cluster.nodes}")
//    private String nodes;
//    @Value("${spring.redis.password}")
//    private String password;
//
//
//
//
//    @Bean//初始化方法构造一个jedisCluster对象
//    public RedissonClient init(){
//        Config config = new Config();
//        config.useClusterServers().setScanInterval(2000);
//        config.useClusterServers().setPassword(password);
//        String[] nodeArray = nodes.split(",");
//        for (String node : nodeArray){
//            String[] array = node.split(":");
//            String ip = array[0];
//            String port = array[1];
//            config.useClusterServers().addNodeAddress("redis://"+ip+":"+port);
//        }
//        RedissonClient redisson = Redisson.create(config);
//
//        return redisson;
//    }
//}
