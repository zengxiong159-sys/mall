package com.qdbank.mall.third;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qdbank.mall.request.third.payment.*;
import com.qdbank.mall.response.third.payment.*;

public interface BankProxySendService {


      /**
       * 获取准入标识
       * @param req
       * @return
       */
      AccessSignResDTO accessSign(AccessSignReqDTO req);

    @HystrixCommand(commandKey = "orderType", fallbackMethod = "orderTypeFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    QryPayResDTO orderType(QryPayReqDTO req);

    @HystrixCommand(commandKey = "refund", fallbackMethod = "refundFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    RefundResDTO refund(RefundReqDTO req);

    /**
       *预下单
       * @param prePayReq
       * @return
       */
      PrePayResDTO saveOrderinfo(PrePayReqDTO prePayReq);
}
