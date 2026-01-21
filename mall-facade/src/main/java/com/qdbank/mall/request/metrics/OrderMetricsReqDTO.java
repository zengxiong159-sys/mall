package com.qdbank.mall.request.metrics;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 订单监控上送指标
 * @Date 2021/7/9 15:43
 */
@Data
public class OrderMetricsReqDTO implements Serializable {
    private static final long serialVersionUID = -5214323650131463277L;

    /**
     * es索引
     */
    private String index;

    /**
     * 订单编号(异常订单无数据)
     */
    private String orderSn;

    /**
     * 订单商品类型(0:实物 1:话费充值 2:油卡充值 3:视频会员充值 4:积分兑换券)
     */
    private String orderProductType;

    /**
     * 订单商品类型描述(0:实物 1:话费充值 2:油卡充值 3:视频会员充值 4:积分兑换券)
     */
    private String orderProductTypeDesc;

    /**
     * 订单指标监控类型(1:下单 2:退款 3:系统异常 4:支付成功 5:支付失败 6:用户取消 7:超时取消 )
     */
    private String orderMetricsType;

    /**
     * 订单指标监控类型对应描述
     */
    private String orderMetricsTypeDesc;

    /**
     * 订单退款指标监控类型(1:用户主动退款 2:系统自动退款 3:极端场景退款)
     */
    private String orderRefundMetricsType;

    /**
     * 订单指标监控类型退款类型对应描述
     */
    private String orderRefundMetricsTypeDesc;

    /**
     * 错误原因(仅针对异常订单)
     */
    private String errorMsg;
}
