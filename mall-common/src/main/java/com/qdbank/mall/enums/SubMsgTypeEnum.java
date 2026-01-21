package com.qdbank.mall.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zengxiong
 * @Description 订阅消息类型枚举
 * @Date 2021/8/13 17:28
 */
@Getter
public enum SubMsgTypeEnum {

    /**
     * 订单状态提醒
     */
    ORDER_STATUS_MSG(1, "订单状态提醒"),

    /**
     * 优惠券到账提醒
     */
    COUPON_TO_ACCOUNT_MSG(2, "优惠券到账提醒"),

    /**
     * 优惠券到期提醒
     */
    COUPON_EXPIRED_MSG(3, "优惠券到期提醒");

    private Integer code;
    private String desc;

    SubMsgTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举信息
     *
     * @param code 码值
     * @return 枚举信息
     */
    public static SubMsgTypeEnum getEnumByCode(Integer code) {
        return Arrays.stream(SubMsgTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst().orElse(null);
    }
}
