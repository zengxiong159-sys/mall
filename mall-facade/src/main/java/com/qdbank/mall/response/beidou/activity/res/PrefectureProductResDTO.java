package com.qdbank.mall.response.beidou.activity.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zengxiong
 * @Description
 * @Date 2021/11/30 10:48
 */
@Data
public class PrefectureProductResDTO {

    /**
     * 商品id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品主图URL
     */
    private String mailPicUrl;

    /**
     * 商品原价
     */
    private BigDecimal originalPrice;

    /**
     * 商品兑换价
     */
    private BigDecimal exchangePrice;

    /**
     * 跳转链接
     */
    private String jumpUrl;

}
