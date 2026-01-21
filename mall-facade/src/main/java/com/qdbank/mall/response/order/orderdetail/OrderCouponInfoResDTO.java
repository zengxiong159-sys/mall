package com.qdbank.mall.response.order.orderdetail;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName CouponInfoResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 9:46
 * @Version 1.0
 * 订单使用优惠券信息
 **/
@Data
public class OrderCouponInfoResDTO {
    /**
     * 券商品编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "券商品编号")
    private Long couponId;

    /**
     * 批次号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    /**
     * 优惠券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * "优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

    /**
     * 优惠券面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值")
    private BigDecimal couponAmount;
}
