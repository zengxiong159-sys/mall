package com.qdbank.mall.response.order.orderdetail;

import com.qdbank.mall.response.order.RechargeStatusDetailResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName OrderMobileRechargeDetailResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 10:12
 * @Version 1.0
 * 话费充值订单详情
 **/
@Data
public class OrderMobileRechargeDetailResDTO extends OrderBaseDetailResDTO {
    @ApiModelProperty("优惠券信息")
    private OrderCouponInfoResDTO couponResDTO;
    @ApiModelProperty("商品信息")
    private OrderProductInfoResDTO productInfoResDTO;
    @ApiModelProperty("充值记录信息")
    private List<RechargeStatusDetailResDTO> resDTOList;
}
