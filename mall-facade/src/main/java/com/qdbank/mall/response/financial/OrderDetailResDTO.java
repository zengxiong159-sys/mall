package com.qdbank.mall.response.financial;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName OrderDetailResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/19 13:47
 * @Version 1.0
 **/
@Data
public class OrderDetailResDTO {
    /**
     * 订单主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单流水号")
    private String orderId;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private String merchantNo;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private String productId;

    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private String productSkuId;


    @ApiModelProperty(value = "商品实际售价折算价")
    private String productPrice = "0";
    /**
     * 支付现金
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付现金：实际支付现金(商品售价-券面值金额)部分不包含运费")
    private String orderCash = "0";

    /**
     * 订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单积分")
    private String orderIntegration = "0";

    /**
     * 运费金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费金额")
    private String freightAmount = "0";

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private String couponType ;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型名称")
    private String couponTypeDesc ;

    /**
     * 优惠券面值金额
     *
     * @mbg.generated
     */

    @ApiModelProperty(value = "积分兑换券面值")
    private String integrationCouponAmount = "0";

    @ApiModelProperty(value = "指定商品免费兑换券面值")
    private String productCouponAmount = "0";

    /**
     * 积分结算标识：否 不结算 是 需要结算
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分结算标识：否 不结算 是 需要结算")
    private String integrationPayFlag;

    /**
     * 支付完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付完成时间")
    private String paymentTime;
    @ApiModelProperty(value = "指定商品免费兑换券批次号")
    private String batchNo;
    @ApiModelProperty(value = "订单类型")
    private String orderType;
    @ApiModelProperty(value = "现金+运费=需结算现金")
    private String cashFreightAmount = "0";
    @ApiModelProperty(value = "优惠金额")
    private String discountAmount;
}
