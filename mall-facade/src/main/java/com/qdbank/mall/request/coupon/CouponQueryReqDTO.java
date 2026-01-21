package com.qdbank.mall.request.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CouponLikeQueryReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 15:04
 * @Version 1.0
 **/
@Data
public class CouponQueryReqDTO  {




    /**
     * 优惠券类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券类型：0 积分兑换券 1 指定商品免费兑换券 2 指定商品现金优惠券 4 指定专区现金优惠券 5 全场通用现金优惠券")
    private Long couponType;


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
     * 券商品状态：0 待上架 1 已上架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "券商品状态：0待上架1已上架")
    private Long productStatus;


}
