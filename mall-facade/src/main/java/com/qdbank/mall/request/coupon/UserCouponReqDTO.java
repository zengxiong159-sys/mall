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
public class UserCouponReqDTO extends PageParams{

    @NotNull
    @ApiModelProperty(value = "用户使用券状态 :0 待使用1 已使用2 已过期3 已作废",required = true)
    private Long status;

    @ApiModelProperty(value = "商品id",required = true)
    private Long productId;

    @ApiModelProperty(value = "规格id",required = true)
    private Long productSkuId;




}
