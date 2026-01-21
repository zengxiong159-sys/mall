package com.qdbank.mall.component;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.util.StringUtils;

@Slf4j
public class RepeatLoginManage {

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    private String   REDIS_KEY ="LOGIN-REPEAT";

    private String   REDIS_TOKEN_KEY ="LOOUT-TIMEOUT";

    @Value(value = "${jwt.expiration}")
    private Long expiration;

    @Autowired
    RedisService redisService;




    /*@Around("@annotation(refreshUserToken)")
    public Object handlerToken(final ProceedingJoinPoint joinPoint, RefreshUserToken refreshUserToken) throws Throwable {
        Object[] args =joinPoint.getArgs();
        UserDetails userDetails  =UserContextHolder.getUserDetails();
        log.info("用户信息[{}]", JsonUtil.toJSONString(userDetails));
        if(userDetails==null || !StringUtils.hasText(userDetails.getCustNo())){
            Signature s = joinPoint.getSignature();
            if(s instanceof MethodSignature){
                MethodSignature ms = (MethodSignature)s;
                Method m = ms.getMethod();
                log.error("客户内容为空，无法操作[{}]-[{}]", ResultCode.USER_ERROR.getCode(),m.getName());
            }else{
                log.error("客户内容为空，无法操作[{}]",ResultCode.USER_ERROR.getCode());
            }
            throw new ApiException(ResultCode.USER_ERROR);
        }
        return joinPoint.proceed();
    }*/

    /**
     * 设置登出token标识并设置超时时间
     * @param token
     */
    public void setTokenTimeOutExpir(String token){
        redisService.set(getTokenExpireKey(token),"1",expiration);
    }

    /**
     * 校验
     * @param token
     * @return
     */
    public void checkOut(String token){
        String result = (String) redisService.get(getTokenExpireKey(token));
        if("1".equals(result)){
            throw new AccountExpiredException("token 已失效");
        }
    }


    public void checkRepeat(String userName,String token){
        String key = getKey(userName);
        String _token = (String) redisService.get(key);
        //redis 里存在对用token且不想等，说明被踢
        if(StringUtils.hasText(_token) && !_token.equals(token) ){
            //当前token设置登出失效
            setTokenTimeOutExpir(token);
            throw new AccountExpiredException("用户互踢");
        }
    }

    public void clearToken(String userName){
        redisService.del(getKey(userName));
    }

    public void setToken(String userName,String token){
        redisService.set(getKey(userName),token,expiration);
    }

    private String getTokenExpireKey(String token){
        return REDIS_DATABASE + ":" + REDIS_TOKEN_KEY + ":" + token;
    }

    private String getKey(String userName){
        return REDIS_DATABASE + ":" + REDIS_KEY + ":" + userName;
    }


}
