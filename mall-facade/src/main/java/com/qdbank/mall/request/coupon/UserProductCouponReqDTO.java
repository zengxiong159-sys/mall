package com.qdbank.mall.request.coupon;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UserCouponReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/18 16:12
 * @Version 1.0
 **/
@Data
public class UserProductCouponReqDTO  {

    @NotNull
    @ApiModelProperty(value = "商品id",required = true)
    private Long productId;

    @ApiModelProperty(value = "规格id",required = true)
    private Long productSkuId;

}
