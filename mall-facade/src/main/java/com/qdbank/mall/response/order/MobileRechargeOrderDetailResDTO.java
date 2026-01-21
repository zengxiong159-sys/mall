package com.qdbank.mall.response.order;

import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.integralcoupon.IntegralCouponResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderProductInfoResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @ClassName MobileRechargeOrderDetailResDTO
 * @Description 话费充值订单详情
 * @Author ningyuehuai
 * @Date 2021/1/12 10:57
 * @Version 1.0
 **/
@Data
public class MobileRechargeOrderDetailResDTO extends OrderBaseInfoResDTO{
    @ApiModelProperty(value = "充值信息")
    private List<RechargeStatusDetailResDTO> rechargeStatus;
    @ApiModelProperty(value = "优惠信息")
    private CouponResDTO integralCouponInfo;
    @ApiModelProperty("商品信息")
    private OrderProductInfoResDTO productInfoResDTO;
}
