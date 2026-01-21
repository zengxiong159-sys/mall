package com.qdbank.mall.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * HttpUtils
 *
 * @author shaoshihang
 * @date 2021/3/23 11:48
 * @since 1.0.0
 */
@Slf4j
public class HttpUtils {
    //懒汉式加锁
    private volatile static RestTemplate restTemplate;
    private HttpUtils (){}
    public static RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            synchronized (RestTemplate.class) {
                if (restTemplate == null) {
                    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
                    requestFactory.setConnectTimeout(5*1000);
                    requestFactory.setReadTimeout(5*1000);
                    restTemplate=new RestTemplate(requestFactory);
                    for(HttpMessageConverter messageConverter :restTemplate.getMessageConverters()){
                        if(messageConverter instanceof StringHttpMessageConverter){
                            ((StringHttpMessageConverter) messageConverter).setDefaultCharset(Charset.forName("utf-8"));
                        }
                    }
                }
            }
        }
        return restTemplate;
    }




}
