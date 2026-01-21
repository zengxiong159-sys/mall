package com.qdbank.mall.interceptor;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.util.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Qdccb
 */
@ControllerAdvice(basePackages = "com.qdbank.mall.controller")
@Slf4j
public class ResponseBodyAnalysis implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class clazz, ServerHttpRequest httpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        String requestURI = null;
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (null != requestAttributes) {
                HttpServletRequest httpServletRequest =
                        ((ServletRequestAttributes) requestAttributes).getRequest();
                requestURI = httpServletRequest.getRequestURI();
            }
            if (body instanceof CommonResult) {
                CommonResult responseDTO = (CommonResult) body;
                String aesKey = ContextHolder.getAesKey();
                if (StringUtils.isEmpty(aesKey)) {
                    log.info("request URI {}, response data: {}", requestURI, JSON.toJSONString(body));
                    responseDTO.setEnc(Constant.NOT_ENCRYPT);
                    return responseDTO;
                }
                if (Constant.ENCRYPT.equals(responseDTO.getEnc()) && responseDTO.getCode() == ResultCode.SUCCESS.getCode()) {
                    Object obj = responseDTO.getData();
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(obj);
                    if (StringUtils.isEmpty(jsonString)) {
                        return responseDTO;
                    }
                    String encrypt = new AES(Mode.ECB, Padding.PKCS5Padding, aesKey.getBytes()).encryptBase64(jsonString);
                    responseDTO.setData(encrypt);
                }
            }
        } catch (Exception e) {
            log.error("encrypt response data error", e);
        } finally {
            ContextHolder.removeAesKey();
            ContextHolder.removeRequestFlag();
        }
        return body;
    }

    @Override
    public boolean supports(MethodParameter arg0, Class arg1) {
        return true;
    }

}