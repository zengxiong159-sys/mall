package com.qdbank.mall.response.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qdbank.mall.response.coupon.CouponResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderCountResDTO
 * @Author hongjh
 * @Date 2021/5/7 9:36
 * @Version 1.0
 **/
@Data
public class OrderCountResDTO {

    @ApiModelProperty(value = "商城待付款订单数量")
    private Long mallUnPaymentCount = 0L;

    @ApiModelProperty(value = "生活待付款订单数量")
    private Long vitualUnPaymentCount = 0L;

    @ApiModelProperty(value = "商城待收货订单数量")
    private Long mallUnReceiveCount = 0L;

    @ApiModelProperty(value = "待支付订单数量")
    private Long unPaymentCount = 0L;

}
