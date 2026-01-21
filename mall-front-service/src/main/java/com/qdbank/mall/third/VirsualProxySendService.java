package com.qdbank.mall.third;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.response.third.mobile.OrderFindResDTO;
import com.qdbank.mall.response.third.mobile.PhoneResDTO;
import com.qdbank.mall.response.third.virsual.QryOrderResDTO;

import java.util.Map;

public interface VirsualProxySendService {


    @HystrixCommand(commandKey = "chargeOrderProcess", fallbackMethod = "chargeOrderProcessFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    String chargeOrderProcess(OrderDO order,String productId);

    @HystrixCommand(commandKey = "orderFind", fallbackMethod = "orderFindFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    QryOrderResDTO orderFind(String orderSn);

    boolean valide(Map<String, String> _header);
}
