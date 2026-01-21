package com.qdbank.mall.external.impl;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.external.ExternalService;
import com.qdbank.mall.model.external.ExternalDO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @Author sunhaoran
 * @Date 2022/5/13 11:29
 * @Version 1.0
 */
@Slf4j
@Service
public class ExternalServiceImpl implements ExternalService {

    @Value(value = "${mgt.http.url}")
    private String url;

    @Autowired
    @Qualifier("ThirdgroupTemplate")
    private RestTemplate popRestTemplate;

    public ExternalDO callMgtService(String commonReq, String childUrl) {
        ExternalDO externalDO = new ExternalDO();
        try {
            long l = System.currentTimeMillis();
            log.info("callMgtService request {},service url {}", commonReq,childUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("traceId", MDC.get("traceId"));
            RequestEntity<String> requestEntity = new RequestEntity<>(commonReq, httpHeaders, HttpMethod.POST, new URI(url.concat(childUrl)));
            ResponseEntity<String> responseEntity = popRestTemplate.exchange(requestEntity, String.class);
            String body = responseEntity.getBody();
            log.info("callMgtService response {},coast time{}", body,System.currentTimeMillis()-l);
            externalDO = JSON.parseObject(body, ExternalDO.class);
        } catch (Exception e) {
            externalDO.setMsg("mgtService inner error");
            log.error("req {},mgt inner error", commonReq, e);
        }
        return externalDO;
    }

}
