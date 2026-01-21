package com.qdbank.mall.response.order.orderdetail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName OrderRealDetailResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 9:41
 * @Version 1.0
 * 实物订单详情
 **/
@Data
public class OrderRealDetailResDTO extends OrderBaseDetailResDTO {
    @ApiModelProperty("优惠券信息")
    private OrderCouponInfoResDTO couponResDTO;
    @ApiModelProperty("商品信息")
    private OrderProductInfoResDTO productInfoResDTO;
    @ApiModelProperty("退款信息")
    private List<OrderRefundResDTO> orderRefundResDTOs;
}
