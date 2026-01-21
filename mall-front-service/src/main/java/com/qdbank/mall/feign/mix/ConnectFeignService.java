package com.qdbank.mall.feign.mix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author zengxiong
 * @Description 外联调用
 * @Date 2023/3/27 20:32
 */
@FeignClient(value = "gw-provider-tonglian")
public interface ConnectFeignService {
    /**
     * 外联调用通联xml服务
     * @param serviceId 服务号
     * @param gwRequest 通联xml报文
     * @return
     */
    @PostMapping(value = "/tonglian/{serviceId}")
    String invokeTlXlmService(@PathVariable("serviceId") String serviceId, @RequestBody String gwRequest);
}