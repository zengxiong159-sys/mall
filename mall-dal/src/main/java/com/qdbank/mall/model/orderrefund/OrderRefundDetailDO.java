package com.qdbank.mall.model.orderrefund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * OrderRefundDetailDO
 *
 * @author shaoshihang
 * @date 2021/3/11 14:12
 * @since 1.0.0
 */
@Data
public class OrderRefundDetailDO extends OrderRefundDO{

    /**
     * 订单状态：(根据商品类型区分）实物订单：0 待支付1 待发货2 已发货3 4 已关闭  话费订单：0 待支付1 充值中3 已完成4 已关闭 积分券兑换订单：0 待支付2 待使用3 已使用4 已关闭5 已过期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单状态：(根据商品类型区分）实物订单：0待支付1待发货2已发货34已关闭话费订单：0待支付1充值中3已完成4已关闭积分券兑换订单：0待支付2待使用3已使用4已关闭5已过期")
    private Long status;

    /**
     * 商品折算价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品折算价")
    private BigDecimal productPrice;


    /**
     * 订单实付款(折算价)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单实付款(折算价)")
    private BigDecimal payAmount;

    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;
    /**
     * 规格图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格图片URL")
    private String skuPicUrl;
    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    /**
     * 收货人电话
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;
    /**
     * 详细地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "详细地址")
    private String receiverDetailAddress;
    /**
     * 物流公司名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "物流公司名称")
    private String deliveryCompanyName;
    /**
     * 物流单号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "物流单号")
    private String deliverySn;
    /**
     * 优惠券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;
    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;
    /**
     * 批次号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    /**
     * "
     券商品编号

     "
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'券商品编号'")
    private Long couponId;
    /**
     * 优惠券面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值")
    private BigDecimal couponAmount;
}
