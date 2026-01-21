package com.qdbank.mall.common;

import com.qdbank.mall.constant.ProductEnum;

import java.util.Date;

public interface ParamsService {

    String qrySupperMobileNo(Long merchantNo);

    /**
     * 通过code获取参数信息
     * @param code
     * @param value
     * @return
     */
    String qryMsgByCode(Long code, String value);


     String handlerSuppiorMobile(ProductEnum productEnum, Long merchantNo);


    Integer getOrderTimeOut();

    Integer getAppalyRefundTimeOut();

    boolean isRefundTimeOut(Date receiveDate);

    Integer getOrderDelivery();
}
