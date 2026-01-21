package com.qdbank.mall.request.integralcoupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName UpdateIntegralCouponReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 10:41
 * @Version 1.0
 **/
@Data
public class UpdateIntegralCouponReqDTO {


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
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 库存id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "库存id")
    private Long skuId;
}
