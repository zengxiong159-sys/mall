package com.qdbank.mall.response.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName IntegralCouponOrderDetailResDTO
 * @Description 积分兑换订单详情
 * @Author ningyuehuai
 * @Date 2021/1/12 11:01
 * @Version 1.0
 **/
@Data
public class IntegralCouponOrderDetailResDTO {
    @ApiModelProperty(value = "订单基础信息包含：基础信息、物流信息、费用详情")
    private OrderBaseInfoResDTO baseInfo;
    @ApiModelProperty(value = "充值信息")
    private List<RechargeStatusDetailResDTO> rechargeStatus;
}
