package com.qdbank.mall.aspect;

import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.annotation.LockKey;
import com.qdbank.mall.annotation.LockName;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.UUID;

/**
 * Lock Aspect<br>
 *
 * @author hongjh
 */
@Slf4j
@Aspect
@Component
public class LockAspect {

    /**
     * Lock在redis中的key前缀
     */
    private final String LOCK_PREFIX = "wechatmall:lock:";
    /**
     * 缺省锁名称
     */
    private final String DEFAULT_LOCK_NAME = "";

    @Autowired
    private RedisTemplate<String, String> redisStringTemplate;

    private RedisSerializer<String> serializer = new StringRedisSerializer();

    @Pointcut("@annotation(com.qdbank.mall.annotation.Lock)")
    public void khtLockPoint() {
    }

    @Around("khtLockPoint()")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        Lock lock = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Lock.class);
        String lockName = lock.name();
        log.info("lockName:{}",lockName);
        if (DEFAULT_LOCK_NAME.equals(lockName)) {
            lockName = getLockNameFromParameter(joinPoint);
        }
        if (DEFAULT_LOCK_NAME.equals(lockName)) {
            lockName = joinPoint.getSignature().toLongString();
        }
        boolean locked = tryLocking(lockName, lock);
        if (locked) {
            try {
                return joinPoint.proceed();
            } finally {
                if (lock.unLockAfterDone()) {
                    unlock(lockName);
                }
            }
        } else {
            log.info("skip this invoking because of failure to get lock");
            return null;
        }
    }

    /**
     * 从横切的方法参数中获取锁的名称，只支持简单对象参数名称，不支持bean对象或bean对象里面的属性作为引用参数
     *
     * @param joinPoint
     * @return 引用的方法参数值
     */
    private String getLockNameFromParameter(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Annotation[][] paramAnnotations = ((MethodSignature) (joinPoint.getSignature())).getMethod().getParameterAnnotations();
        int paramIndex = -1;
        StringBuilder sb = new StringBuilder(DEFAULT_LOCK_NAME);
        for (int i = 0; i < paramAnnotations.length; i++) {
            for (int j = 0; j < paramAnnotations[i].length; j++) {
                Object obj = paramAnnotations[i][j];
                if (obj instanceof LockName) {
                    LockName name = (LockName) obj;
                    paramIndex = i;
                    //暂时处理
                    Object param=args[paramIndex];
                   /* if(param instanceof LockKey){
                        LockKey key = (LockKey)param;
                        return name.value()+"_"+key.keyName();
                    }*/
                 //   return name.value()+"_"+param.toString();
                    if(param!=null){
                        sb.append(param.toString());
                    }
                    sb.append("_");
                }
            }
        }
        log.info("锁名字：[{}]",sb.toString());
        return sb.toString(); // 返回缺省锁名称
    }

    /**
     * 尝试获取得锁
     *
     * @param lockName 锁名称
     * @param lock     加锁时的注解参数
     * @return 加锁成功返回true，否则返回false
     */
    private boolean tryLocking(String lockName, Lock lock) {
        String lockKey = LOCK_PREFIX + lockName;
        // RedisCallback方式调用原生接口
        return redisStringTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                String uuid = UUID.randomUUID().toString();
                byte[] key = serializer.serialize(lockKey);
                connection.set(key, serializer.serialize(uuid), Expiration.from(lock.leaseTime(), lock.timeUnit()), SetOption.SET_IF_ABSENT);
                byte[] value = connection.get(key);
                if (uuid.equals(serializer.deserialize(value))) {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 释放锁
     *
     * @param lockName 锁名称
     */
    private void unlock(String lockName) {
        String lockKey = LOCK_PREFIX + lockName;
        redisStringTemplate.delete(lockKey);
    }
}