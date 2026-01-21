package com.qdbank.mall.response.integralcoupon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName IntegralcouponResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 10:03
 * @Version 1.0
 **/
@Data
public class IntegralCouponResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 积分券类型 0 积分兑换券
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券类型 0 积分兑换券")
    private Long couponType;

    /**
     * 积分券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券名称")
    private String couponName;

    /**
     * 规则描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规则描述")
    private String couponRuleDescription;

    /**
     * 积分券价格
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券价格")
    private BigDecimal couponPrice;

    /**
     * 券过期时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券过期时间")
    private Long expireDate;

    /**
     * 积分图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分图片URL")
    private String picUrl;

    /**
     * 商品类型 0 话费 1 油卡 2 视频会员
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型 0 话费 1 油卡 2 视频会员")
    private Long productType;

    /**
     * 商品id
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 库存id
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "库存id")
    private Long skuId;

    /**
     * 已兑换总量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "已兑换总量")
    private Long convertTotal;

    /**
     * 已使用总量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "已使用总量")
    private Long useTotal;

    /**
     * 已过期总量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "已过期总量")
    private Long expireTotal;

    /**
     * 积分券状态 0 待上架 1 已上架 2 已下架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券状态 0 待上架 1 已上架 2 已下架")
    private Long status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
}
