package com.qdbank.mall.model.usercoupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class ExportUserCouponDO implements Serializable {

    /**
     * 通联核心客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心客户号")
    private String custNo;

    /**
     * 批次号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    /**
     * "优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private String couponTypeValue;

    @ApiModelProperty(value = "优惠券编号")
    private Long userCouponId;
    /**
     * 优惠券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * 优惠券面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值")
    private BigDecimal couponAmount;

    /**
     * 优惠券发放时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券发放时间")
    private Date sendTime;

    /**
     *
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'用户使用券状态:0 待使用 1已使用 2已过期 3 已作废")
    private String statusValue;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;


    /**
     * 指定商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品编号")
    private Long productId;

    /**
     * 指定商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品名称")
    private String productName;

    /**
     * 指定积分兑换券id
     */
    @ApiModelProperty(value = "指定积分兑换券id")
    private Long relCouponId;

    /**
     * 指定专区id
     */
    @ApiModelProperty(value = "指定专区id")
    private Long prefectureId;

    /**
     * 券类型
     */
    @ApiModelProperty(value = "券类型")
    private Long couponType;

    /**
     * 子活动id
     */
    @ApiModelProperty(value = "子活动id")
    private String subActivityId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}