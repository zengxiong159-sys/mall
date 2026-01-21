package com.qdbank.mall.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "wechat-oms")
public interface FeignService {
    @GetMapping(value = "/oms/role/feign/timeout")
    public String paymentFeignTimeout();
}
//