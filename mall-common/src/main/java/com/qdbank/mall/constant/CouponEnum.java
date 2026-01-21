package com.qdbank.mall.constant;

/**
 * 优惠券类型
 */
public enum CouponEnum {

    COUPON_TYPE_EXCHANGE(0L,CouponGroup.EXCHANGE,"积分兑换券"),
    COUPON_TYPE_APPOINT(1L,CouponGroup.APPOINT,"指定免费兑换券"),
    COUPON_TYPE_CASH_APPOINT(2L,CouponGroup.APPOINT,"指定现金兑换券"),
    PREFECTURE_CASH_COUPON(4L,CouponGroup.APPOINT,"指定专区现金优惠券"),
    GENERAL_CASH_COUPON(5L,CouponGroup.APPOINT,"全场通用现金优惠券")
    ;

    public  enum CouponGroup{
        EXCHANGE("0","积分兑换券类"),
        APPOINT("1","指定优惠券类"),
        ;

        public String desc;
        public String groupType;

        CouponGroup(String groupType,String desc){
            this.groupType=groupType;
            this.desc=desc;
        }
    }

    public Long couponType;
    public String desc;
    public CouponGroup group;

    CouponEnum(Long couponType,CouponGroup group,String desc){
        this.couponType=couponType;
        this.group=group;
        this.desc=desc;
    }


    public static CouponEnum getCouponType(Long couponType){
        for(CouponEnum coupon : CouponEnum.values()){
            if(coupon.couponType.equals(couponType)){
                return coupon;
            }
        }
        return null;
    }


}

