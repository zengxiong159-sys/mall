package com.qdbank.mall.model.trade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class TradeTotalDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户号")
    private String merchantNo;

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 订单类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单类型")
    private String orderType;

    /**
     * 订单笔数汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单笔数汇总")
    private String orderCount;

    /**
     * 商品实际售价折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品实际售价折算价")
    private String productPrice;

    /**
     * 积分汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分汇总")
    private String integrationCount;

    /**
     * 积分兑换券汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分兑换券汇总")
    private String couponCount;

    /**
     * 指定商品兑换券汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品兑换券汇总")
    private String productCouponCount;

    /**
     * 现金汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "现金汇总")
    private String orderCash;

    /**
     * 运费汇总
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费汇总")
    private String freightAmount;

    /**
     * 现金+运费
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "现金+运费")
    private String cashFreightAmount;

    /**
     * 交易日期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "交易日期")
    private String paymentTime;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private String couponType;

    /**
     * 发放方式: 0:用户兑换 1:行方发放
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "发放方式:0:用户兑换1:行方发放")
    private String distributeWay;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;
    private static final long serialVersionUID = 1L;

}