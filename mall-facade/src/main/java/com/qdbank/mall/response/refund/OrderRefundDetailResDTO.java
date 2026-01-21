package com.qdbank.mall.response.refund;

import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.order.OrderBaseInfoResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName OrderRefundDetailResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/17 20:26
 * @Version 1.0
 **/
@Data
public class OrderRefundDetailResDTO {
    @ApiModelProperty(value = "订单基础信息包含：基础信息、物流信息、费用详情")
    private OrderBaseInfoResDTO baseInfo;
    @ApiModelProperty(value = "商品信息")
    private ProductResDTO productInfo;
    @ApiModelProperty(value = "优惠信息")
    private CouponResDTO integralCouponInfo;
}
