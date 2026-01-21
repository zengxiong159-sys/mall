package com.qdbank.mall.model.orderrefund;

import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderRefundListDO
 *
 * @author shaoshihang
 * @date 2021/3/10 13:41
 * @since 1.0.0
 */
@Data
public class OrderRefundListDO extends OrderRefundDO{

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private SkustockDO skustockDO;

    /**
     * 申请开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "申请开始时间")
    private String ApplicationStartTime;

    /**
     * 申请结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "申请结束时间")
    private String ApplicationEndTime;
    /**
     * 订单信息
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单信息")
    private OrderDO orderDO;

    @ApiModelProperty(value = "退款失败状态 0:失败，1:正常")
    private String errorStatus;


}
