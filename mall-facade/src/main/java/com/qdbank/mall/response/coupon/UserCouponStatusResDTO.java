package com.qdbank.mall.response.coupon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserCouponStatusResDTO implements Serializable {
    private static final long serialVersionUID = 3047369014841209793L;

    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
     * "用户使用券状态 :0 待使用1 已使用2 已过期3 已作废"
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'用户使用券状态:0待使用1已使用2已过期3已作废'")
    private Long status;

    /**
     * 券商品状态：0 待上架 1 已上架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券商品状态：0待上架1已上架")
    private Long productStatus;

    /**
     * "指定商品类型0 实物 1 话费充值 2 油卡充值 3 视频会员充值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "'指定商品类型 0实物 1话费充值 2油卡充值 3视频会员充值")
    private Long productType;

    /**
     * 指定商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    /**
     * 指定商品规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品规格编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productSkuId;

    /**
     * 专区id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "专区id")
    private Long prefectureId;
    /**
     * 主活动id
     */
    @ApiModelProperty(value = "主活动id")
    private String mainActivityId;

    /**
     * 子活动id
     */
    @ApiModelProperty(value = "子活动id")
    private String subActivityId;
    /**
     * 专区关联商品数量
     */
    @ApiModelProperty(value = "专区关联商品数量")
    private Long productCount;
}