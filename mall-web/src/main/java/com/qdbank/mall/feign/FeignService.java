package com.qdbank.mall.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(value = "wechat-cms")
public interface FeignService {
    @GetMapping(value = "/cms/admin/feign/timeout")
    public String paymentFeignTimeout();
}
//