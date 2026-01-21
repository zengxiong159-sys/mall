package com.qdbank.mall.user.impl;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.user.RedisKeyDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RedisKeyDeleteServiceImpl implements RedisKeyDeleteService {
    private static final int COUNT = 3000;
    private static final Duration MINUTES = Duration.ofMinutes(30);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void deletePattern(String pattern) {
        log.info("delete pattern : {}",pattern);
        scanAndDelete(pattern);
    }

    public void scanAndDelete(String pattern) {
        long start = System.currentTimeMillis();
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                try {
                    final RedisClusterConnection connection = redisTemplate.getConnectionFactory().getClusterConnection();
                    JedisCluster cluster = (JedisCluster) connection.getNativeConnection();
                    Collection<JedisPool> pools = cluster.getClusterNodes().values();
                    Set<String> set = new HashSet<>();

                    for (JedisPool pool : pools) {
                        Jedis resource = pool.getResource();
                        ScanParams scanParams = new ScanParams();
                        scanParams.match(pattern + "*");
                        scanParams.count(COUNT);
                        ScanResult<String> scan = resource.scan("0", scanParams);
                        do {
                            final List<String> result = scan.getResult();
                            if (result == null || result.isEmpty()) {
                                break;
                            }
                            set.addAll(result);
                            if (set.size() >= COUNT) {
                                //批量删除
                                executeDelete(pattern,set);
                                set.clear();
                            }
                            final String cursor = scan.getCursor();
                            log.info("{} cursor {} ",pattern,cursor);
                            scan = resource.scan(cursor, scanParams);
                        } while (!"0".equals(scan.getCursor()));

                        if (set.size() > 0) {
                            //批量删除
                            executeDelete(pattern,set);
                        }
                    }
                } catch (Exception e) {
                    log.error("doInRedis error", e);
                }
                return null;
            }
        });
        log.info("scan and delete finish , cost {} 秒", (System.currentTimeMillis() - start) / 1000);
    }

    /**
     * 管道批量删除
     */
    private void executeDelete(String pattern, Set<String> keys) {
        log.info("namespace {} ops count: {}",pattern, keys.size());
        for (String key : keys) {
            redisTemplate.delete(keys);
        }
    }
}
