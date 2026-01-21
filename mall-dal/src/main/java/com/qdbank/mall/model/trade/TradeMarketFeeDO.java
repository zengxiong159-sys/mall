package com.qdbank.mall.model.trade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class TradeMarketFeeDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

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
     * 指定商品免费兑换券金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品免费兑换券金额")
    private String productCoupon;

    /**
     * 商品券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品券名称")
    private String couponName;

    /**
     * 日期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "日期")
    private String paymentTime;

    /**
     * 指定商品免费兑换券批次号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品免费兑换券批次号")
    private String batchNo;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;
}