package com.qdbank.mall.aspect;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.domain.PrometheusHttpMetricLabel;
import com.qdbank.mall.service.PrometheusMicrometerService;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zengxiong
 * @Description prometheus请求监控
 * @Date 2021/5/27 14:46
 */
@Component
@Aspect
@Slf4j
public class PrometheusMetricsAspect {
    /**
     * 监控指标-响应结果标识:失败
     */
    private static final String LABEL_RESULT_FAILURE = "false";

    /**
     * 监控指标-响应结果标识:成功
     */
    private static final String LABEL_RESULT_SUCCESS = "true";

    /**
     * 响应结果成功code
     */
    private static final long BUSINESS_SUCCESS_CODE = 200;

    @Autowired
    private PrometheusMicrometerService prometheusMicrometerTimerService;

    @Around("execution(* com.qdbank.mall.controller..*(..)) && !@annotation(com.qdbank.mall.annotation.ExcludeHandler) ")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Timer.Sample sample = prometheusMicrometerTimerService.startTimer();
        Object proceedObj = pjp.proceed();
        if (sample != null) {
            HttpServletResponse response = attributes.getResponse();
            int status = response.getStatus();
            String uri = request.getRequestURI();

            //设置label信息
            PrometheusHttpMetricLabel label = new PrometheusHttpMetricLabel();
            label.setUri(uri);
            label.setType(request.getScheme());
            label.setMethod(request.getMethod());
            label.setHttpStatus(String.valueOf(status));

            //首先判断服务是否可用,再根据响应结果code码判断业务是否异常
            if (status != HttpStatus.SC_OK) {
                label.setResult(LABEL_RESULT_FAILURE);
                label.setException(uri + " service unavailable");
                prometheusMicrometerTimerService.recordHttp(sample, label);
            } else {
                try {
                    if (proceedObj instanceof CommonResult) {
                        CommonResult commonResult = (CommonResult) proceedObj;
                        //判断业务响应code码
                        long businessResCode = commonResult.getCode();
                        label.setBusinessResCode(String.valueOf(businessResCode));
                        if (businessResCode != BUSINESS_SUCCESS_CODE) {
                            label.setResult(LABEL_RESULT_FAILURE);
                            label.setException(commonResult.getMessage());
                        } else {
                            label.setResult(LABEL_RESULT_SUCCESS);
                        }
                        prometheusMicrometerTimerService.recordHttp(sample, label);
                    } else {
                        log.info("PrometheusMetricsAspect proceedObj instanceof CommonResult occur error");
                    }
                } catch (Exception e) {
                    log.error("PrometheusMetricsAspect http monitor occur error", e);
                }
            }
        }
        return proceedObj;
    }
}
