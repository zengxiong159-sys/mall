package com.qdbank.mall.model.order;

import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * OrderDetailsDO
 *
 * @author shaoshihang
 * @date 2021/3/9 16:56
 * @since 1.0.0
 */
@Data
public class OrderDetailsDO extends OrderDO{

    /**
     * 退货申请
     */
    @ApiModelProperty(value = "模板名称")
    private OrderRefundDO orderRefundDO;
    /**
     * 优惠券详情
     */
    @ApiModelProperty(value = "优惠券详情")
    private CouponDO couponDO;
    /**
     * 商品
     */
    @ApiModelProperty(value = "商品")
    private ProductDO productDO;
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private SkustockDO skustockDO;

    /**
     * 充值记录
     */
    private List<RechargeStatusDO> rechargeStatusDOS;

}
