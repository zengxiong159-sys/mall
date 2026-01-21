package com.qdbank.mall.model.usercoupon;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserCouponDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1053696325713842662L;

    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户优惠券编号")
    private Long userCouponId;

    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;

    /**
     * 订单编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    /**
     * 通联核心客户号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "通联核心客户号")
    private String custNo;

    /**
     * 券商品编号
     *
     * @mbg.generated
     */
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
     * 积分券售价(积分值)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券售价(积分值)")
    private Long orderIntegration;

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * "用户使用券状态 :0 待使用1 已使用2 已过期3 已作废"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'用户使用券状态:0待使用1已使用2已过期3已作废'")
    private Long status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 优惠券过期时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券过期时间")
    private Date expireDate;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 发放开始时间
     */
    @ApiModelProperty(value = "发放开始时间")
    private Date startSendTime;

    /**
     * 发放结束时间
     */
    @ApiModelProperty(value = "发放结束时间")
    private Date endSendTime;

    /**
     * 有效期开始时间
     */
    @ApiModelProperty(value = "有效期开始时间")
    private Date startTime;

    /**
     * 有效期结束时间
     */
    @ApiModelProperty(value = "有效期结束时间")
    private Date endTime;

    /**
     * 券批次发放时间
     */
    @ApiModelProperty(value = "券批次发放时间")
    private Date sendTime;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private String orderId;

    /**
     * (订单表)商品类型：0实物 1话费充值 2油卡充值 3视频会员充值 4积分兑换券
     */
    @ApiModelProperty(value = "(订单表)商品类型：0实物 1话费充值 2油卡充值 3视频会员充值 4积分兑换券")
    private Long productType;

    /**
     * 核销订单号
     */
    @ApiModelProperty(value = "核销订单号")
    private String writeOffOrderSn;

    /**
     * 专区id
     */
    @ApiModelProperty(value = "专区id")
    private Long prefectureId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 商品规格id
     */
    @ApiModelProperty(value = "商品id")
    private Long productSkuId;

    /**
     * 子活动id
     */
    @ApiModelProperty(value = "子活动id")
    private String subActivityId;

    /**
     * 子活动id
     */
    @ApiModelProperty(value = "优惠券面值")
    private String couponAmount;

}