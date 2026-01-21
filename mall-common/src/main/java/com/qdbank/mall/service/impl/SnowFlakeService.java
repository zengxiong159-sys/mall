package com.qdbank.mall.service.impl;

import com.qdbank.mall.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Qdccb
 */
@Service
@Slf4j
public class SnowFlakeService implements InitializingBean {

    /**
     * SnowFlake 算法需要机器id，用 redis set存储机器 Inet4Address hashcode来实现机器id
     */
    protected RedisTemplate<String, String> redisTemplate;
    private static String IP;
    private SnowFlake snowFlake;
    @Value("${redis.key.orderId}")
    private String REDIS_KEY_ORDER_ID;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${spring.application.name}")
    private String machineRedisList;
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initMachine();
    }

    /**
     * 初始化把服务器ip hashcode 放入 redis set
     */
    private void initMachine() {
        if (redisTemplate != null) {
            //ip地址去重
            ListOperations<String, String> listOperations = redisTemplate.opsForList();
            List<String> list = listOperations.range("SnowFlake_".concat(machineRedisList).concat("_machine_list_"), 0, -1);
            IP = getLocalHost();
            if (CollectionUtils.isEmpty(list)) {
                listOperations.rightPush("SnowFlake_".concat(machineRedisList).concat("_machine_list_"), IP);
            }else {
                Set<String> stringSet = new HashSet<>(list);
                stringSet.add(IP);
                if (list.size() != stringSet.size()) {
                    listOperations.trim("SnowFlake_".concat(machineRedisList).concat("_machine_list_"), 1, -list.size());
                    listOperations.rightPushAll("SnowFlake_".concat(machineRedisList).concat("_machine_list_"), stringSet);
                }
            }
//            redisTemplate.opsForList().rightPush(MACHINE_REDIS_LIST, getLocalHost());
        }
    }

    /**
     * 雪花算法获取 id。依赖 redis ，redis 不可用时候返回uuid
     *
     * @return id
     */
    public String nextId() {
        try {
            if (initSnowFlakeIfAbsent()) {
                return String.valueOf(snowFlake.nextId());
            }
        } catch (Exception e) {
            log.error("SnowFlake ops error", e);
        }
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取数据库ID
     * @return
     */
    public Long getId(){
        try {
            if (initSnowFlakeIfAbsent()) {
                return snowFlake.nextId();
            }
        } catch (Exception e) {
            log.error("SnowFlake ops error", e);
        }
        return Long.valueOf(this.generateOrderSN());
    }

    private boolean initSnowFlakeIfAbsent() {
        if (redisTemplate!=null && snowFlake == null) {
            int index = getMachineId();
            if (index != -1) {
                synchronized (this) {
                    snowFlake = new SnowFlake(1L, index);
                }
            }
        }
        return snowFlake != null;
    }

    private int getMachineId() {
    List<String> list = redisTemplate.opsForList().range("SnowFlake_".concat(machineRedisList).concat("_machine_list_"), 0, -1);
    if (CollectionUtils.isEmpty(list)) {
      return 0;
    }
    list = list.stream().distinct().collect(Collectors.toList());
    return list.indexOf(IP);
    }

    private static String getLocalHost() {
        return IpUtils.getLocalHostAddress();
    }

    /**
     * 生成订单流水号
     * @return
     */
    public String generateOrderSN(){
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = REDIS_DATABASE+":"+ REDIS_KEY_ORDER_ID + date;
        Long increment = redisTemplate.opsForValue().increment(key, 1);
        if(increment==1L){
         redisTemplate.expire(key,24, TimeUnit.HOURS);
        }
        if(increment == 9000L){
            redisTemplate.opsForValue().set(key,"1");
        }
        String orderSNPre = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        sb.append(orderSNPre);
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 4) {
            sb.append(String.format("%04d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }
}
