package com.qdbank.mall.domain.submsg;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 订阅消息BO
 * @Date 2022/7/25 14:50
 */
@Data
public class SubMsgCommonBO implements Serializable {
    private static final long serialVersionUID = 3845193043662543355L;

    /**
     * 消息类型 1.订单状态提醒 2.优惠券到账提醒 3.优惠券到期提醒 4.新活动通知
     */
    private Integer type;

    /**
     * 消息id
     */
    private String msgCode;

    /**
     * 通联核心客户号
     */
    private String custNo;

    /**
     * 消息内容
     */
    private Object content;

}
