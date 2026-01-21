package com.qdbank.mall.constant.payment.metrics;

import lombok.Data;

@Data
public class MetricsHolder {

    private MetricsEnum metricsEnum;

    private OrderRefundMetricsEnum orderRefundMetricsEnum;

    public MetricsHolder(MetricsEnum metricsEnum,OrderRefundMetricsEnum orderRefundMetricsEnum){
        this.metricsEnum=metricsEnum;
        this.orderRefundMetricsEnum=orderRefundMetricsEnum;
    }

}
