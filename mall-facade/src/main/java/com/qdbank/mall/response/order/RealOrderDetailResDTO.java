package com.qdbank.mall.response.order;

import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.refund.OrderRefundListResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName RealOrderDetailResDTO
 * @Description 实物订单详情信息
 * @Author ningyuehuai
 * @Date 2021/1/12 10:55
 * @Version 1.0
 **/
@Data
public class RealOrderDetailResDTO extends OrderBaseInfoResDTO{
    @ApiModelProperty(value = "商品信息")
    private ProductResDTO productInfo;
    @ApiModelProperty(value = "优惠信息")
    private CouponResDTO integralCouponInfo;
    @ApiModelProperty(value = "退款信息")
    private List<OrderRefundListResDTO> orderRefundResDTOs;
    @ApiModelProperty(value = "撤销发货记录标识 0 无撤销数据 1 有撤销数据")
    private Integer sendReturnDataFlag = 0;
}
