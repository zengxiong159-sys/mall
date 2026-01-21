package com.qdbank.mall.feign;

import com.qdbank.mall.request.gatawayrequest.GatawayReqDTO;
import com.qdbank.mall.response.feign.FeignResponse;
import com.qdbank.mall.response.feign.integral.IntegralResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "gw-facade")
public interface GatewayFeignService {
    @RequestMapping("/lb/tonglian/17070")
    public FeignResponse<IntegralResDTO> getIntegralByCustId(@RequestBody GatawayReqDTO gatawayReqDTO);
}
