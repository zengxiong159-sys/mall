package com.qdbank.mall.domain.submsg;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 优惠券到账提醒订阅消息kafka定义
 * @Date 2022/7/26 15:09
 */
@Data
public class CouponToAcctMsgMQBO implements Serializable {

    private static final long serialVersionUID = 4769155633814919184L;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 到账时间
     */
    private String sendTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 跳转链接
     */
    private String jumpUrl;

}
