package com.qdbank.mall.response.order.orderdetail;

import com.qdbank.mall.response.order.RechargeStatusDetailResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName OrderCouponDetailRespDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/27 18:46
 * @Version 1.0 积分兑换券订单详情
 **/
@Data
public class OrderCouponDetailResDTO extends OrderBaseDetailResDTO {
    @ApiModelProperty("优惠券信息")
    private OrderCouponInfoResDTO couponResDTO;
    @ApiModelProperty("兑换记录信息")
    private List<RechargeStatusDetailResDTO> resDTOList;
}
