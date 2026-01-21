package com.qdbank.mall.third;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.request.third.payment.AccessSignReqDTO;
import com.qdbank.mall.request.third.payment.PrePayReqDTO;
import com.qdbank.mall.request.third.payment.QryPayReqDTO;
import com.qdbank.mall.request.third.payment.RefundReqDTO;
import com.qdbank.mall.response.third.mobile.OrderFindResDTO;
import com.qdbank.mall.response.third.mobile.PhoneResDTO;
import com.qdbank.mall.response.third.payment.AccessSignResDTO;
import com.qdbank.mall.response.third.payment.PrePayResDTO;
import com.qdbank.mall.response.third.payment.QryPayResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;

import java.util.Map;

public interface MobileProxySendService {


    //  @ThirdApiAccess(desc = "")
      @HystrixCommand(commandKey = "phone", fallbackMethod = "phoneFallback", commandProperties = {
              @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
      PhoneResDTO phone(String phone) ;

    @HystrixCommand(commandKey = "chargeOrderProcess", fallbackMethod = "chargeOrderProcessFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    String chargeOrderProcess(OrderDO order);

    @HystrixCommand(commandKey = "orderFind", fallbackMethod = "orderFindFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    OrderFindResDTO orderFind(String orderSn);

    boolean valide(Map<String, String> _header, Map<String, String> _body);
}
