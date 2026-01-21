package com.qdbank.mall.aspect;


import com.qdbank.mall.annotation.LockName;
import com.qdbank.mall.annotation.RateLimiter;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

@Component
@Aspect
@Slf4j
public class RateLimiterAspect {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 缺省锁名称
     */
    private final String DEFAULT_LOCK_NAME = "";
    @Resource
    private RedisScript<Long> redisScript;
    @Pointcut("@annotation(com.qdbank.mall.annotation.RateLimiter)")
    public void rateLimiterLockPoint() {
    }
    @Around("rateLimiterLockPoint()")
    public void rateLimiterLock(final ProceedingJoinPoint joinPoint) throws Throwable {
        RateLimiter rateLimiter = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(RateLimiter.class);
        String lockName = rateLimiter.key();
        int count = rateLimiter.count();
        int time = rateLimiter.time();
        log.info("lockName:{}",lockName);
        if (DEFAULT_LOCK_NAME.equals(lockName)) {
            lockName = getLockNameFromParameter(joinPoint);
        }
        if (DEFAULT_LOCK_NAME.equals(lockName)) {
            lockName = joinPoint.getSignature().toLongString();
        }
        List<String> keys = Collections.singletonList(lockName);
        try {
            Long number = stringRedisTemplate.execute(redisScript, keys, String.valueOf(count), String.valueOf(time));
            log.info("限制请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), keys.get(0));
            if (number == null || number.intValue() > count) {
                throw new ApiException(ResultCode.SEND_MESSAGE_LIMIT);
            }
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.info("限流异常：{}",e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
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
}
