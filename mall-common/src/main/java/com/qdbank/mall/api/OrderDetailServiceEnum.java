package com.qdbank.mall.api;

import com.qdbank.mall.exception.ApiException;
import lombok.Getter;

/**
 * @ClassName OrderDetailServiceEnum
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 13:49
 * @Version 1.0
 **/
@Getter
public enum  OrderDetailServiceEnum {
    //0实物1话费充值2油卡充值3视频会员充值4积分兑换券
    REAL_ORDER_DETAIL_SERVICE(0L,"orderRealDetailServiceImpl","实物订单"),
    MOBILE_RECHARGE_ORDER_DETAIL_SERVICE(1L,"orderMobileRechargeServiceImpl","话费充值订单"),
    COUPON_ORDER_DETAIL_SERVICE(4L,"orderCouponDetailServiceImpl","积分兑换订单");
    private Long productType;
    private String serviceName;
    private String desc;
    private OrderDetailServiceEnum(Long productType,String serviceName,String desc){
        this.productType = productType;
        this.serviceName = serviceName;
        this.desc = desc;
    }

    public static String getServiceName(Long productType){
        for(OrderDetailServiceEnum orderDetailServiceEnum : OrderDetailServiceEnum.values()){
            if(orderDetailServiceEnum.getProductType().equals(productType)) return orderDetailServiceEnum.getServiceName();
        }
        throw  new ApiException(ResultCode.TRADE_TYPE_ERROR);
    }
}
