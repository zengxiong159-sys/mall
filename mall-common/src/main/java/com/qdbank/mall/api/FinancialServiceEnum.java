package com.qdbank.mall.api;

import lombok.Getter;

@Getter
public enum FinancialServiceEnum {
    TRADE_TOTAL_SERVICE(2L,"tradeTotalFinancialServiceImpl","商城交易汇总服务"),
    TRADE_DETAIL_SERVICE(3L,"tradeDetailFinancialServiceImpl","商城交易汇总服务"),
    COUPON_TOTAL_SERVICE(4L,"couponTotalFinancialServiceImpl","积分交易汇总服务"),
    MARKET_TOTAL_SERVICE(5L,"marketTotalFinancialServiceImpl","营销费用月度汇总服务");
    private Long couponType;
    private String serviceName;
    private String desc;
    private FinancialServiceEnum(Long couponType,String serviceName,String desc){
        this.couponType = couponType;
        this.serviceName = serviceName;
        this.desc = desc;
    }
    public static String getServiceName(Long couponType){
        for(FinancialServiceEnum serviceEnum : FinancialServiceEnum.values()){
            if(serviceEnum.getCouponType().equals(couponType)) return serviceEnum.getServiceName();
        }
        return null;
    }
}
