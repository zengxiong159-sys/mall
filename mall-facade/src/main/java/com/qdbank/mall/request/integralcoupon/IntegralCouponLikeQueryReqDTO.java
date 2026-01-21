package com.qdbank.mall.request.integralcoupon;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName IntegralcouponReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 10:06
 * @Version 1.0
 **/
@Data
public class IntegralCouponLikeQueryReqDTO extends PageParams {

    /**
     * 积分券名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分券名称")
    private String couponName;


    /**
     * 商品类型 0 话费 1 油卡 2 视频会员
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品类型 0 话费 1 油卡 2 视频会员")
    private Long productType;


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

}
