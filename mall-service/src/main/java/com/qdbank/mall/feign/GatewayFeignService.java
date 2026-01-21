package com.qdbank.mall.feign;

import com.qdbank.mall.request.gatawayrequest.GatawayReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "gw-facade")
public interface GatewayFeignService {
    @RequestMapping(value = "/lb/tonglian/17070",method = RequestMethod.POST)
    public Object getIntegralByCustId(@RequestBody GatawayReqDTO gatawayReqDTO);
}
