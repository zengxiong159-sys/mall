package com.qdbank.mall.util;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qdccb
 */
public class MetricsUtils {
    private static final Logger logger = LoggerFactory.getLogger(MetricsUtils.class);

    private static final Map<String, AtomicDouble> DICT = new ConcurrentHashMap<>();
    private static final MeterRegistry REGISTRY = SpringContextUtils.getApplicationContext().getBean(MeterRegistry.class);

    public static void count(String name, String amount, String... tags) {
        try {
            REGISTRY.counter(name, tags).increment(Double.parseDouble(amount));
        } catch (Exception e) {
            logger.error("MetricsUtils count error", e);
        }
    }

    public static void gauge(String biz, String name, double num) {
        try {
            AtomicDouble value;
            if (!DICT.containsKey(biz)) {
                value = new AtomicDouble();
                DICT.put(biz, value);
            }
            value = DICT.get(biz);
            value.getAndAdd(num);
            REGISTRY.gauge(name, value);
        } catch (Exception e) {
            logger.error("MetricsUtils gauge error", e);
        }
    }
}
