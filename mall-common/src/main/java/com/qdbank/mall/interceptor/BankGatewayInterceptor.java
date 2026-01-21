package com.qdbank.mall.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.filter.HttpRequestWrapper;
import com.qdbank.mall.util.ContextHolder;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qdccb
 */
@Slf4j
@Order(1)
@RefreshScope
public class BankGatewayInterceptor implements HandlerInterceptor {

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
         /*   log.info("请求方式："+request.getHeader(HttpHeaders.CONTENT_TYPE));
            if (!MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getHeader(HttpHeaders.CONTENT_TYPE))) {
                log.info("received old request");
                return true;
            }*/
            HttpRequestWrapper wrapper = (HttpRequestWrapper) request;
            String body = wrapper.getBody();
            checkParameter(body);
            Map<String, String> paramMap = changeDataMap(body);
            String data =  paramMap.get("data");
            checkParameter(data);
            data = RSAUtil.decrypt(privateKey, publicKey, data);
            checkParameter(data);

            Map<String,Object> _body1 = JsonUtil.parseObject(data,Map.class);

            JSONObject jsonObject  = (JSONObject) _body1.get("data");
            Map<String,String> _body =jsonObject.toJavaObject(Map.class);
            Map<String, String[]> parameterMap = new HashMap<>();
            if(!CollectionUtils.isEmpty(_body)){
                for(String key : _body.keySet()){
                  parameterMap.put(key, new String[]{_body.get(key)});
                }
            }
            wrapper.setParameterMap(parameterMap);
            wrapper.setBody(jsonObject.toJSONString());
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
        if (data == null || data.equals("") ) {
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
            String[] keyValue = s.split("=");
            if(keyValue != null && keyValue.length == 2){
                bodyHashMap.put( keyValue[0],keyValue[1]);
            }
        }
        return bodyHashMap;
    }
}
