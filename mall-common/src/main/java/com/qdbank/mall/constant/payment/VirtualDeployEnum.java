package com.qdbank.mall.constant.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author hongjh
 * 关闭枚举
 *
 */
@AllArgsConstructor
@Getter
public enum VirtualDeployEnum {

    /**
     */

    UP("1",0L),
    DOWN("0",1L),
    ;

    private String wxStatus;
    private Long bankStatus;


    public static VirtualDeployEnum getEnumByWxStatus(String wxStatus){
        for(VirtualDeployEnum paymentEnum : VirtualDeployEnum.values()){
            if(paymentEnum.wxStatus.equals(wxStatus)){
                return paymentEnum;
            }
        }
        return null;
    }



}
