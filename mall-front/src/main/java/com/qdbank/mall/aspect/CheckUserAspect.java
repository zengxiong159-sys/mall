package com.qdbank.mall.aspect;


import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class CheckUserAspect {



    @Around("@annotation(checkUser)")
    public Object errorHandler(final ProceedingJoinPoint joinPoint, CheckUser checkUser) throws Throwable {
        Object[] args =joinPoint.getArgs();
        UserDetails userDetails  =UserContextHolder.getUserDetails();
        log.info("用户信息[{}]",JsonUtil.toJSONString(userDetails));
        if(userDetails==null || !StringUtils.hasText(userDetails.getCustNo())){
            Signature s = joinPoint.getSignature();
            if(s instanceof MethodSignature ){
                MethodSignature ms = (MethodSignature)s;
                Method m = ms.getMethod();
                log.error("客户内容为空，无法操作[{}]-[{}]",ResultCode.USER_ERROR.getCode(),m.getName());
            }else{
                log.error("客户内容为空，无法操作[{}]",ResultCode.USER_ERROR.getCode());
            }
            throw new ApiException(ResultCode.USER_ERROR);
        }
        return joinPoint.proceed();
    }






}
