package com.qdbank.mall.constant;

import lombok.AllArgsConstructor;

/**
 *
 * @author hongjh
 * 支付枚举
 *
 */
@AllArgsConstructor
public enum ProductEnum {


    PAYMENT_IN_KIND(0L,"paymentInKind","实物",null),
    MOBILE_RECHARGE(1L,"mobileRechargePayment","话费充值",100031L),
    OIL_CARD(2L,"oilCardPayment","油卡充值",100032L),
    VIDEO(3L,"videoPayment","视频会员充值",100033L),
    INTEGRAL(4L,"integralPayment","积分兑换券",null),
    ;

    public Long productType;
    public String handlerName;
    public String desc;
    public Long productId;



    /**
     * 通过商品类型获取枚举
     * @param productType
     * @return
     */
    public static ProductEnum getProductByType(Long productType){
        for(ProductEnum productEnum : ProductEnum.values()){
            if(productEnum.productType .equals(productType)){
                return productEnum;
            }
        }
        return null;
    }



}
