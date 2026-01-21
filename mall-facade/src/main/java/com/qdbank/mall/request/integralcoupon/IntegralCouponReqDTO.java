package com.qdbank.mall.request.integralcoupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName IntegralcouponReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 10:06
 * @Version 1.0
 **/
@Data
public class IntegralCouponReqDTO {
    /**
     * 积分券类型 0 积分兑换券
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 1)
    @ApiModelProperty(value = "积分券类型 0 积分兑换券 默认积分兑换券")
    private Long couponType = 0L;

    /**
     * 积分券名称
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 20,min = 1)
    @ApiModelProperty(value = "积分券名称",required = true)
    private String couponName;

    /**
     * 规则描述
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 100,min = 1)
    @ApiModelProperty(value = "规则描述",required = true)
    private String couponRuleDescription;

    /**
     * 积分券值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分值",required = true)
    private Long couponValue;

    /**
     * 面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "面值",required = true)
    private BigDecimal faceValue;

    /**
     * 券过期时间
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 3)
    @ApiModelProperty(value = "券过期时间",required = true)
    private Long expireDate;

    /**
     * 积分图片
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "积分券图片",required = true)
    private MultipartFile file;

    /**
     * 商品类型 0 话费 1 油卡 2 视频会员
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 1)
    @ApiModelProperty(value = "商品类型 0 话费 1 油卡 2 视频会员",required = true)
    private Long productType;

    /**
     * 商品id
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "商品id",required = true)
    private Long productId;

    /**
     * 库存id
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "库存id",required = true)
    private Long skuId;


}
