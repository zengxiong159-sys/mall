package com.qdbank.mall.service;

import com.alibaba.fastjson.JSON;
import com.qdbank.alert.dto.AlertDTO;
import com.qdbank.alert.dto.WXAlertDTO;
import com.qdbank.alert.service.AlertServiceFactory;
import com.qdbank.mall.domain.PrometheusHttpMetricLabel;
import com.qdbank.mall.util.HttpUtils;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Qdccb
 */
@Service
@Slf4j
public class PrometheusMicrometerService implements ApplicationContextAware {
    @Resource
    private AlertServiceFactory alertServiceFactory;
    /**
     * http请求耗时
     */
    private static final String HTTP_METRIC = "http_requests_duration";

    /**
     * 请求延时
     */
    private static final String HELP_METRIC = "Request latency in seconds.";

    private final ThreadPoolTaskExecutor monitorThreadPoolTaskExecutor;

    private final MeterRegistry registry;

    private static ApplicationContext applicationContext;

    public static PrometheusMicrometerService instance() {
        if (applicationContext != null) {
            return applicationContext.getBean(PrometheusMicrometerService.class);
        }
        return null;
    }

    public PrometheusMicrometerService(@Qualifier("monitorThreadPoolTaskExecutor") ThreadPoolTaskExecutor monitorThreadPoolTaskExecutor, MeterRegistry registry) {
        this.monitorThreadPoolTaskExecutor = monitorThreadPoolTaskExecutor;
        this.registry = registry;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public Timer.Sample startTimer() {
        try {
            return Timer.start(registry);
        } catch (Exception e) {
            log.error("PrometheusMicrometerService Timer.start error", e);
        }
        return null;
    }

    /**
     * 记录http请求耗时
     *
     * @param sample 计时器
     * @param label  指标数据
     */
    public void recordHttp(Timer.Sample sample, PrometheusHttpMetricLabel label) {
        monitorThreadPoolTaskExecutor.submit(
                () -> {
                    try {
                        log.info("PrometheusMicrometerService uri:{}, label {}", label.getUri(), label);
                        stop(sample, label, HTTP_METRIC);
                    } catch (Exception e) {
                        log.error("record error", e);
                    }
                });
    }

    private void stop(Timer.Sample sample, PrometheusHttpMetricLabel label, String metric) {
        final Timer.Builder builder =
                Timer.builder(metric)
                        .description(HELP_METRIC)
                        .tags("exception", label.getException() == null ? "none" : label.getException())
                        .tags("result", label.getResult())
                        .tags("type", label.getType())
                        .tags("uri", label.getUri())
                        .tags("method", label.getMethod())
                        .tags("httpStatus", label.getHttpStatus())
                        .tags("businessResCode", label.getBusinessResCode());
        sample.stop(builder.register(registry));
    }

    /**
     * 发起post请求
     *
     * @param request 请求参数DTO
     */
    public void sendPost(String url, Object request) {
        monitorThreadPoolTaskExecutor.submit(
                () -> {
                    try {
                        if(StringUtils.isNotBlank(url)){//上送ES数据
                            ResponseEntity<String> post = HttpUtils.getRestTemplate().postForEntity(url, request, String.class);
                            log.info("PrometheusMicrometerService:{} sendPost result:{}", JSON.toJSONString(post));
                        }else {//监控告警
                            alertServiceFactory.getDefaultService().alert((WXAlertDTO)request);
                        }

                    } catch (Exception e) {
                        log.error("PrometheusMicrometerService sendPost occur error", e);
                    }
                });
    }
}
