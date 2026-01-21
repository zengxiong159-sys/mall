package com.qdbank.mall.response.coupon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zengxiong
 * @Description 积分兑换批次券指定积分兑换券商品信息
 * @Date 2021/8/17 15:38
 */
@Data
public class RelCouponInfo implements Serializable {
    private static final long serialVersionUID = -4378070057090438904L;

    /**
     * 积分兑换券商品编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "积分兑换券商品编号")
    private Long couponId;

    /**
     * 积分兑换券名称
     */
    @ApiModelProperty(value = "积分兑换券名称")
    private String couponName;

    /**
     * 商品类型
     */
    @ApiModelProperty(value = "商品类型: 0 实物 1 话费充值 2 油卡充值 3 视频会员充值")
    private Long productType;

    /**
     * 积分兑换券面值
     */
    @ApiModelProperty(value = "积分兑换券面值")
    private BigDecimal couponAmount;

    /**
     * 积分兑换券有效期开始时间
     */
    @ApiModelProperty(value = "积分兑换券有效期开始时间")
    private Date startTime;

    /**
     * 优惠券有效期结束时间
     */
    @ApiModelProperty(value = "优惠券有效期结束时间")
    private Date endTime;

    /**
     * 优惠券规则描述
     */
    @ApiModelProperty(value = "优惠券规则描述")
    private String couponDescription;
}
