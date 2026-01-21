package com.qdbank.mall.constant.payment;

/**
 * 地址码表
 */
public enum MobileRechargeStatus {

        CHARGEING("1",	"充值中"),
        CHARGE_FAIL("2",	"充值失败"),
        CHARGE_SUCCESS("3",	"充值成功");



    public String code;
    public String name;

    MobileRechargeStatus(String code, String name){
        this.code=code;
        this.name=name;
    }

    public static MobileRechargeStatus getName(String code){
            for(MobileRechargeStatus mobileAddress : MobileRechargeStatus.values()){
                    if(mobileAddress.code.equals(code)){
                            return mobileAddress;
                    }
            }
            return null;
    }



}
