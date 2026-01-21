package com.qdbank.mall.aspect;


import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.bo.StateMachineError;
import com.qdbank.mall.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class StateExceptionAspect {



    @Around("@annotation(onTransition)")
    public Object errorHandler(final ProceedingJoinPoint joinPoint, StateError onTransition) throws Throwable {
        Object[] args =joinPoint.getArgs();
        Message message = null;
        if(args!=null){
            for(Object arg : args){
                if(arg instanceof Message){
                    message = (Message) arg;
                }else if(arg instanceof StateContext){
                    StateContext context= (StateContext) arg;
                    message= context.getMessage();
                }
            }
        }
        try{
            return joinPoint.proceed();
        }catch (Exception e){
            //将错误异常、通过上下文包裹，由上层处理
            StateMachineError error = (StateMachineError) message.getHeaders().get(Constant.STATE_MACHINE_ERROR);
            error.setHasError(true);
            error.setException(e);
            log.error("出现异常",e);
            throw e;
        }
    }






}
