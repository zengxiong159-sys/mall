package com.qdbank.mall.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import redis.clients.jedis.JedisCluster;

import java.util.List;
@Slf4j
public class LuaRedisCallBack implements RedisCallback<Long> {


    private final String sha;

    private final List<String> keys;

    private final List<String> params;

    private final String script;

    public LuaRedisCallBack(String sha, List<String> keys, List<String> params, String script) {
        this.sha = sha;
        this.keys = keys;
        this.params = params;
        this.script = script;
    }

    @Override
    public Long doInRedis(RedisConnection connection) throws DataAccessException {
        Object nativeConnection = connection.getNativeConnection();
        // 集群模式
        if (nativeConnection instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) nativeConnection;
            long v;
            try {
                v = (long) jedisCluster.evalsha(sha, keys, params);
            } catch (Exception ex) {
                log.error("eval sha error, use eval instead ", ex);
                //fall back to eval
                v = (long) jedisCluster.eval(script, keys, params);
            }
            return v;
        }
        // 不存在单机模式
        return -1L;
    }
}
