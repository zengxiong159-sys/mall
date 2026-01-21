package com.qdbank.mall.domain.submsg;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 订单状态提醒订阅消息kafka定义
 * @Date 2022/7/22 10:51
 */
@Data
public class OrderStatusMsgMQBO implements Serializable {

    private static final long serialVersionUID = 4599587232130151934L;

    /**
     * 推送标题
     */
    private String title;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 订单状态
     */
    private String orderStatusDesc;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 订单金额
     */
    private String orderAmount;

    /**
     * 提交时间
     */
    private String createTime;

    /**
     * 跳转链接
     */
    private String jumpUrl;

}
