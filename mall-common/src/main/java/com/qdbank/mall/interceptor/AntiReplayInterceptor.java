package com.qdbank.mall.interceptor;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.filter.HttpRequestWrapper;
import com.qdbank.mall.util.ContextHolder;
import com.qdbank.mall.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Qdccb
 */
@Slf4j
@Order(1)
@RefreshScope
public class AntiReplayInterceptor implements HandlerInterceptor {

    private static final int TIMEOUT = 300;

    private static final String NONCE_CACHE_PREFIX = "MALL_NONCE_";

    @Value(value = "${http.request.rsa.private.key}")
    private String privateKey;

    @Value(value = "${http.request.rsa.public.key}")
    private String publicKey;
    @Value("${spring.application.name}")
    private String applicationName;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String ERROR_PAGE = "/error";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CommonResult responseDTO = new CommonResult();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (ERROR_PAGE.equals(request.getRequestURI())) {
            log.info("we got an error page");
            handleErrorPage(responseDTO, response);
            return false;
        }
        responseDTO.setCode(ResultCode.VALIDATE_FAILED.getCode());
        responseDTO.setMessage(ResultCode.VALIDATE_FAILED.getMessage());
        try {
            log.info("请求方式："+request.getHeader(HttpHeaders.CONTENT_TYPE));
            if (!MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getHeader(HttpHeaders.CONTENT_TYPE))) {
                log.info("received old request");
                return true;
            }
            HttpRequestWrapper wrapper = (HttpRequestWrapper) request;
            String body = wrapper.getBody();
            checkParameter(body);
            Map<String, Object> paramMap = new ObjectMapper().readValue(body, new TypeReference<HashMap<String, Object>>() {
            });
            String timestamp = (String) paramMap.get("timestamp");
            checkParameter(timestamp);
            long t = System.currentTimeMillis();
            boolean timeout = t - Long.parseLong(timestamp) >= TIMEOUT * 1000L;
            if (timeout) {
                //时间戳不合法
                log.info("request time:{} invalid, server time:{}", timestamp, t);
            }
            String nonce = (String) paramMap.get("nonce");
            checkParameter(nonce);
            //检查redis中是否存在nonce
            Boolean absent = redisTemplate.opsForValue().setIfAbsent(NONCE_CACHE_PREFIX+applicationName+ nonce, nonce);
            if (null != absent && !absent) {
                //重复请求
                log.info("request nonce already exist:{}", nonce);
                response.getWriter().print(JSON.toJSONString(responseDTO));
                return false;
            }
            redisTemplate.expire(NONCE_CACHE_PREFIX +applicationName+ nonce, TIMEOUT, TimeUnit.SECONDS);
            //解密AES key
            String key = (String) paramMap.get("aesKey");
            log.info("RSAkey:"+key);
            checkParameter(key);
            String aesKey = RSAUtil.decrypt(privateKey, publicKey, key);
            String str = timestamp + aesKey + nonce;
            if (!verifySign((String) paramMap.get("signature"), str)) {
                log.info("verify signature failed");
                response.getWriter().print(JSON.toJSONString(responseDTO));
                return false;
            }
            String paramsflag = (String) paramMap.get("paramsflag");
            //data里有请求参数进行data解密
            if(StringUtils.isNotBlank(paramsflag) && paramsflag.equals("Y")){
                //解密业务参数
                String data = (String) paramMap.get("data");
                checkParameter(data);
                data = new AES(Mode.ECB, Padding.PKCS5Padding, aesKey.getBytes()).decryptStr(data);
                log.info("url:{}, request data:{}", request.getRequestURI(), data);
                checkParameter(data);
                Map<String, String[]> parameterMap = new HashMap<>();
                cast2Map(data, parameterMap);
                wrapper.setParameterMap(parameterMap);
                Map<String,String> dataMap = this.changeDataMap(data);
                wrapper.setBody(JSON.toJSONString(dataMap));
            }
            ContextHolder.setAesKey(aesKey);
            ContextHolder.setRequestFlag(true);
            return true;
        } catch (Exception e) {
            log.error("pre handle request data error", e);
            response.getWriter().print(JSON.toJSONString(responseDTO));
            return false;
        }
    }

    private void handleErrorPage(CommonResult commonResult, HttpServletResponse response) throws Exception{
        int status = response.getStatus();
        HttpStatus httpStatus = HttpStatus.resolve(status);
        if (null == httpStatus) {
            commonResult.setCode(ResultCode.SYSTEM_EXCEPTION.getCode());
            commonResult.setMessage(ResultCode.SYSTEM_EXCEPTION.getMessage());
        }else {
            String reasonPhrase = httpStatus.getReasonPhrase();
            commonResult.setCode(status);
            commonResult.setMessage(reasonPhrase);
        }
        response.getWriter().print(JSON.toJSONString(commonResult));
    }

    private void checkParameter(String data) {
        if (!org.springframework.util.StringUtils.hasText(data)) {
            throw new NullPointerException();
        }
    }

    private void cast2Map(String data, Map<String, String[]> parameterMap) {
        String[] split = data.split("&");
        for (String s : split) {
            int index = s.indexOf("=");
            parameterMap.put(s.substring(0, index), new String[]{s.substring(index + 1)});
        }
    }

    /**
     * 验签
     */
    private boolean verifySign(String sign, String data) {
        String md5Hex = DigestUtils.md5Hex(data);
        return md5Hex.equals(sign);
    }

    /**
     * 将请求data转换为map
     * @param data
     * @return
     */
    private Map<String,String> changeDataMap(String data){
        Map<String,String> bodyHashMap = new HashMap<String,String>();
        String[] split = data.split("&");
        for (String s : split) {
            int index=StringUtils.indexOf(s,"=");
            if(index>0){
                String key=StringUtils.substring(s,0,index);
                String value=StringUtils.substring(s,index+1,s.length());
                bodyHashMap.put(key,value);
            }
        }
        return bodyHashMap;
    }
}
