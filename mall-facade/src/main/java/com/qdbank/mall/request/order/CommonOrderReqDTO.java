package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName SendOrderReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/1/23 12:05
 * @Version 1.0
 **/
@Data
public class CommonOrderReqDTO {

    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户优惠券编号")
    private Long userCouponId;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;


    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;

    @ApiModelProperty(value = "标识 0 走新逻辑")
    private String payFlag;

    private static final long serialVersionUID = 1L;
}
