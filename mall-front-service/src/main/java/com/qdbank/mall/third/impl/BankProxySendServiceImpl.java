package com.qdbank.mall.third.impl;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qdbank.alert.dto.AlertDTO;
import com.qdbank.alert.dto.WXAlertDTO;
import com.qdbank.alert.service.AlertServiceFactory;
import com.qdbank.mall.rpc.consumer.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.request.third.payment.*;
import com.qdbank.mall.response.third.ThirdResBody;
import com.qdbank.mall.response.third.payment.AccessSignResDTO;
import com.qdbank.mall.response.third.payment.PrePayResDTO;
import com.qdbank.mall.response.third.payment.QryPayResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.service.PrometheusMicrometerService;
import com.qdbank.mall.third.BankProxySendService;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.RSAUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.qdbank.mall.constant.DatePattern.NORM_DATETIME_FORMAT;

@Service
@Slf4j
//@ThirdComponent(desc = "行内前置服务 service")
@DefaultProperties(groupKey = "SC-Order-ProxySendServiceImpl-GK", threadPoolKey = "SC-Order-ProxySendServiceImpl-Pool", commandProperties = {
        @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"),
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")}, threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "10"),
        @HystrixProperty(name = "keepAliveTimeMinutes", value = "5"), @HystrixProperty(name = "maxQueueSize", value = "65535"), @HystrixProperty(name = "queueSizeRejectionThreshold", value = "35000")})
@RefreshScope
public class BankProxySendServiceImpl implements BankProxySendService {

    @Value(value = "${http.request.rsa.private.key}")
    private String privateKey;

    @Value(value = "${http.request.rsa.public.key}")
    private String publicKey;

    @Autowired
    @Qualifier("ThirdgroupTemplate")
    RestTemplate restTemplate;

    @Autowired
    private PrometheusMicrometerService prometheusMicrometerService;

    @Value(value = "${third.bank.url.accessSign:}")
    private String accessSignUrl;

    @Value(value = "${third.bank.url.saveOrderinfo:}")
    private String saveOrderinfoUrl;

    @Value(value = "${third.bank.url.orderType:}")
    private String orderTypeUrl;

    @Value(value = "${third.bank.url.refund:}")
    @Getter
    @Setter
    private String refundUrl;

    @Resource
    private AlertServiceFactory alertServiceFactory;

    @Value(value = "${pay.flag:}")
    private String  paymentFlag;
    @Value(value = "${refund.flag:}")
    private String refundFlag;
    @Value(value = "${order.flag:}")
    private String orderQueryFlag;

    String thirdEncryptKey="data";
    String thirdNormalKey="data";

    @Autowired
    private DubboService dubboService;

  //  @ThirdApiAccess(desc = "")
    @Override
    @HystrixCommand(commandKey = "accessSign", fallbackMethod = "accessSignFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public AccessSignResDTO accessSign(AccessSignReqDTO req) {
        //发送告警信息
        AlertDTO alertReqDTO = new WXAlertDTO();

        AccessSignResDTO accessSignRes = new AccessSignResDTO();
        try {
            //新逻辑
            if(req.getPayFlag() != null && req.getPayFlag().equals("0")  && "Y".equals(paymentFlag)){
                Map<String,Object> map = BeanUtil.beanToMap(req);
                Map resultMap = dubboService.call("CC800007",map);
                accessSignRes.setAccessSignid(resultMap != null && resultMap.get("id")!= null ? resultMap.get("id").toString() : "");
            }else {
                accessSignRes = this.post(accessSignUrl, req, AccessSignResDTO.class,false);
            }
        } catch (Exception e) {
            log.info("调用第三方异常:{}",e);
            alertReqDTO.setMessage("调用中台预授权接口发生异常");
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }

        if(!StringUtils.hasText(accessSignRes.getAccessSignid())){
            alertReqDTO.setMessage("【业务类】调用中台预授权接口成功,但准入标识流水号结果为空");
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }
        return accessSignRes;
    }


    @Override
    @HystrixCommand(commandKey = "orderType", fallbackMethod = "orderTypeFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public  QryPayResDTO orderType(QryPayReqDTO req) {
        //发送告警信息
        AlertDTO alertReqDTO = new WXAlertDTO();

        QryPayResDTO res;
        try {
            //不加密
            if(!"Y".equals(orderQueryFlag)){
                res=  this.post(orderTypeUrl, req, QryPayResDTO.class,false);
            }else {
                Map<String,Object> map = BeanUtil.beanToMap(req);
                res = BeanUtil.mapToBean(dubboService.call("CC800004",map),QryPayResDTO.class,true);
                res.setRespStatus(res.getStatus());
            }
        } catch (Exception e) {
            alertReqDTO.setMessage("调用中台订单查询接口发生异常,订单号:" + req.getOrdId());
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }

        //单个交易错误信息处理
        if(!Constant.THIRD_SUCCESS_CODE.equals(res.getErrorCode())){
            alertReqDTO.setMessage("【业务类】调用中台订单查询接口失败,订单号:" + req.getOrdId() + ",原因:" + res.getErrorMsg());
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(res.getErrorCode()+":"+res.getErrorMsg());
        }
        return res;
    }

    @Override
   @HystrixCommand(commandKey = "refund", fallbackMethod = "refundFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public  RefundResDTO refund(RefundReqDTO req) {
        //发送告警信息
        AlertDTO alertReqDTO = new WXAlertDTO();
        RefundResDTO res;
        try {
            //加密
            if(!"Y".equals(refundFlag)){
                res=  this.post(refundUrl, req, RefundResDTO.class,false);
            }else{
                Map<String,Object> map = BeanUtil.beanToMap(req);
                res = BeanUtil.mapToBean(dubboService.call("CC800003",map),RefundResDTO.class,true);
            }
            log.info("订单号：{}调用退款接口响应结果:{}",req.getOrdId(),JSON.toJSONString(res));
        } catch (Exception e) {
            log.info("订单号：{}调用退款接口CC800003异常:{}",req.getOrdId(),e);
            alertReqDTO.setMessage("调用中台退款接口发生异常,订单号:" + req.getOrdId());
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }

        //单个交易错误信息处理
        if(!Constant.THIRD_SUCCESS_CODE.equals(res.getErrorCode())){
            alertReqDTO.setMessage("【业务类】调用中台退款接口失败,订单号:" + req.getOrdId() + ",原因:" + res.getErrorMsg());
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(res.getErrorCode() + ":" + res.getErrorMsg());
        }
        return res;
    }

    @Override
    @HystrixCommand(commandKey = "saveOrderinfo", fallbackMethod = "saveOrderinfoFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public  PrePayResDTO saveOrderinfo(PrePayReqDTO prePayReq) {
        //发送告警信息
        AlertDTO alertReqDTO = new WXAlertDTO();

        PrePayResDTO accessSignRes ;
        try {
            if(prePayReq.getPayFlag() != null && prePayReq.getPayFlag().equals("0")){
                if("Y".equals(paymentFlag)){
                    Map<String,Object> map = BeanUtil.beanToMap(prePayReq);
                    Map resultMap = dubboService.call("CC800009",map);
                    accessSignRes = BeanUtil.mapToBean(resultMap,PrePayResDTO.class,true);
                    accessSignRes.setErrorCode("1700000");
                }else {
                    accessSignRes = this.post(saveOrderinfoUrl, prePayReq, PrePayResDTO.class,true);
                }
            }else{
                accessSignRes = this.post(saveOrderinfoUrl, prePayReq, PrePayResDTO.class,true);
            }

        } catch (Exception e) {
            alertReqDTO.setMessage("调用中台下单接口发生异常,订单号:" + prePayReq.getOrderid());
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }

        //单个交易错误信息处理
        if(!"1700000".equals(accessSignRes.getErrorCode())){
            alertReqDTO.setMessage("【业务类】调用中台下单接口失败,订单号:" + prePayReq.getOrderid() + ",原因:" + accessSignRes.getErrorMsg());
            prometheusMicrometerService.sendPost(null, alertReqDTO);
            throw new ApiException(accessSignRes.getErrorCode() + ":" + accessSignRes.getErrorMsg());
        }

        return accessSignRes;
    }


    public <T extends ThirdResBody>T post(String url, ThirdReqBody req, Class<T> clazz, boolean encryptFlag){
        req.setChannel(Constant.THIRD_PAY_CHANNEL);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
            ThirdResBody finalResult ;
            //加密流程
            if(encryptFlag){
                //加密
                String data =RSAUtil.encrypt(publicKey,privateKey,JsonUtil.toJSONString(req));
                //加密字段
                params.add(thirdEncryptKey, data);
                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

                log.info("加密 third requst [{}]-->[{}]",url,JsonUtil.toJSONString(params));
                JSONObject _result =restTemplate.postForObject(url, requestEntity, JSONObject.class);
                //解密
                JSONObject ooo=_result.getJSONObject("body");
                String s = (String) ooo.get(thirdNormalKey);
                String  result11 =RSAUtil.decrypt(privateKey,publicKey,s);
                JSONObject j=JSONObject.parseObject(result11);
                JSONObject data_J =j.getJSONObject(thirdEncryptKey);

                finalResult= data_J.toJavaObject(clazz);
            }else{
                //不加密形式，普通url get请求
                Map<String,Object> map = JsonUtil.parseObject(JsonUtil.toJSONString(req), Map.class);
                for(String key : map.keySet()){
                    params.add(key, (String) map.get(key));//支持中文
                }
                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
                log.info("不加密：third requst[{}]-->[{}]",url,JsonUtil.toJSONString(map));
                JSONObject _response = restTemplate.postForObject(url, requestEntity , JSONObject.class);
                JSONObject oo=_response.getJSONObject("body");
                finalResult= oo.toJavaObject(clazz);
            }

            log.info("third response-->[{}]", JsonUtil.toJSONString(finalResult));
           /* if(Constant.THIRD_SUCCESS_CODE.equals(finalResult.getErrorCode())){
                throw new ApiException(finalResult.getErrorCode()+":"+finalResult.getErrorCode());
            }*/
            return (T) finalResult;
        } catch (Exception e) {
            log.error("三方接口: 请求参数[{}]", req,e);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }
    }


    public AccessSignResDTO accessSignFallback(AccessSignReqDTO req) {
        return null;
    }
    public AccessSignResDTO accessSignFallback(AccessSignReqDTO req,Throwable exc) throws Throwable {
        log.error("报错-[{}]",new Object[]{exc.getMessage()});
        throw exc;
    }

    public PrePayResDTO saveOrderinfoFallback(PrePayReqDTO req) {
        return null;
    }
    public PrePayResDTO saveOrderinfoFallback(PrePayReqDTO req,Throwable exc) throws Throwable {
        log.error("报错[{}]-[{}]",new Object[]{req.getOrderid(),exc.getMessage()});
        throw exc;
    }


    public QryPayResDTO orderTypeFallback(QryPayReqDTO req) {
        return null;
    }
    public QryPayResDTO orderTypeFallback(QryPayReqDTO req,Throwable exc) throws Throwable {
        log.error("报错[{}]-[{}]",new Object[]{req.getOrdId(),exc.getMessage()});
        throw exc;
    }

    public RefundResDTO refundFallback(RefundReqDTO req) {
        return null;
    }
    public RefundResDTO refundFallback(RefundReqDTO req,Throwable exc) throws Throwable {
        log.error("报错[{}]-[{}]",new Object[]{req.getOrdId(),exc.getMessage()});
        throw exc;
    }






}
