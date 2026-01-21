

package com.qdbank.mall.util;

import java.text.DecimalFormat;

/**
 * 常量
 *
 * @author ningyuehuai
 */
public class Constant {
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";

    /**
     * 订阅消息kafka topic
     */
    public static final String SUB_MSG_TOPIC = "TP_SPG_SUBSCRIBE_MSG";

    /**
     * 订阅消息msg code前缀
     */
    public static final String SUB_MSG_PREFIX = "SPG";

    /**
     * 红包卡券跳转地址
     */
    public static final String MALL_COUPON_JUMP_URL = "/red-envelopes/mycoupon?type=Mall";

    /**
     * 优惠券到账订阅消息备注默认文案
     */
    public static final String COUPON_TO_ACCOUNT_MSG_REMARK = "优惠券存在有效期，请尽快使用，避免过期!";

    /**
     * 格式化金额
     */
    public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("0.00#");

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }


    }


