package com.qdbank.mall.api;

import lombok.Getter;

@Getter
public enum ServiceEnum {

    INTEGRATION_COUPON_SERVICE(0L,"integrationCouponServiceImpl","积分兑换券服务"),
    PRODUCT_COUPON_SERVICE(1L,"productCouponServiceImpl","指定商品免费兑换券"),
    INTEGRATION_BATCH_COUPON_SERVICE(3L,"integrationBatchCouponServiceImpl","积分兑换批次券服务"),
    DESIGNATED_PREFECTURE_COUPON_SERVICE(4L,"designatedPrefectureCouponServiceImpl","指定专区现金优惠券服务"),
       ;
    private Long couponType;
    private String serviceName;
    private String desc;
    private ServiceEnum(Long couponType,String serviceName,String desc){
        this.couponType = couponType;
        this.serviceName = serviceName;
        this.desc = desc;
    }
    public static String getServiceName(Long couponType){
        for(ServiceEnum serviceEnum : ServiceEnum.values()){
            if(serviceEnum.getCouponType().equals(couponType)) return serviceEnum.getServiceName();
        }
        return null;
    }
}
