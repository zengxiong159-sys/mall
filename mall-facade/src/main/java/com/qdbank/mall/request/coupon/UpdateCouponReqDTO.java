package com.qdbank.mall.request.coupon;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName UpdateCouponReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 17:11
 * @Version 1.0
 **/
@Data
public class UpdateCouponReqDTO {
    /**
     * 券商品编号
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "券商品编号")
    private Long couponId;

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
     * 商品售价中积分量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分量")
    private Long productIntegration;

    /**
     * 优惠券面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值")
    private BigDecimal couponAmount;

    /**
     * 优惠券描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券描述")
    private String couponDescription;
    /**
     * "指定商品类型
     0 话费
     "
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品类型: 0 实物 1 话费充值 2 油卡充值 3 视频会员充值")
    private Long productType;

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
     * 指定商品规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "指定商品规格编号")
    private Long productSkuId;



    /**
     * 优惠券过期天数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券过期天数")
    private Long expireDays;


    /**
     * 优惠券有效期开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券有效期开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    /**
     * 优惠券有效期结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券有效期结束时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;

    /**
     * 关联积分券商品ID
     */
    @ApiModelProperty(value = "关联积分券商品ID")
    private Long relCouponId;

    /**
     * 指定积分兑换券商品编号
     */
    @ApiModelProperty(value = "指定专区编号")
    private Long prefectureId;

    /**
     * 专区类型 0:常规专区 1:达标专区
     */
    @ApiModelProperty(value = "专区类型")
    private Integer prefectureType;

    /**
     * 白名单获取方式 0:白名单 1:北斗
     */
    @ApiModelProperty(value = "白名单获取方式")
    private Integer obtainWay;

}
