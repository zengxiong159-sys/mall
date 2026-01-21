package com.qdbank.mall.model.order;

import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName OrderCouponDetailDO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/27 14:22
 * @Version 1.0
 * 积分兑换订单详情
 **/
@Data
public class OrderCouponDetailDO extends OrderDO{
    @ApiModelProperty(value = "兑换记录")
    private List<RechargeStatusDO> rechargeStatusDOS;
}
