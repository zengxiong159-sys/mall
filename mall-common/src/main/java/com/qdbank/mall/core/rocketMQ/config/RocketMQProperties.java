package com.qdbank.mall.core.rocketMQ.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongjh
 * rocketMQ 配置参数
 */
@Data
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQProperties {
    private boolean isEnable = false;
    private String namesrvAddr ;
    private String groupName = "default";
    private int producerMaxMessageSize = 1024;
    private int producerSendMsgTimeout = 2000;
    private int producerRetryTimesWhenSendFailed = 2;
    private int consumerConsumeThreadMin = 5;
    private int consumerConsumeThreadMax = 30;
    private int consumerConsumeMessageBatchMaxSize = 1;

    private Bsn bsn;

    @Data
    public static class  Bsn{

        private  String timeOut;

        private  String rechargeRefund;
    }

}


