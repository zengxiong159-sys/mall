package com.qdbank.mall.model.orderreport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderExportDetailDO
 * @Description 订单明细
 * @Author ningyuehuai
 * @Date 2021/3/14 9:03
 * @Version 1.0
 **/
@Data
public class OrderExportDetailDO {
    /**
     * 订单主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单流水号")
    private Long orderId;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;

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
    private Long productId;

    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;


    @ApiModelProperty(value = "商品实际售价折算价")
    private BigDecimal productPrice;
    /**
     * 支付现金
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付现金：实际支付现金(商品售价-券面值金额)部分不包含运费")
    private BigDecimal orderCash;

    /**
     * 订单积分
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单积分")
    private Long orderIntegration;

    /**
     * 运费金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

    /**
     * 优惠券面值金额
     *
     * @mbg.generated
     */

    @ApiModelProperty(value = "积分兑换券面值")
    private BigDecimal integrationCouponAmount;

    @ApiModelProperty(value = "指定商品免费兑换券面值")
    private BigDecimal productCouponAmount;

    /**
     * 积分结算标识：0 不结算 1 需要结算
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分结算标识：0不结算1需要结算")
    private String integrationPayFlag;

    /**
     * 支付完成时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付完成时间")
    private Date paymentTime;
    @ApiModelProperty(value = "指定商品免费兑换券批次号")
    private String batchNo;
    @ApiModelProperty(value = "订单类型")
    private String orderType;
    @ApiModelProperty(value = "现金+运费=需结算现金")
    private BigDecimal cashAndFreightAmount;
    private BigDecimal discountAmount;
}
