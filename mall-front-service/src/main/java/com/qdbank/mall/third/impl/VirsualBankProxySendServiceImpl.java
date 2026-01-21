package com.qdbank.mall.third.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qdbank.alert.dto.AlertDTO;
import com.qdbank.alert.dto.WXAlertDTO;
import org.springframework.beans.factory.annotation.Value;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.dao.prefecture.PrefectureDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.exception.MobileException;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.response.third.mobile.PhoneResDTO;
import com.qdbank.mall.response.third.virsual.QryOrderResDTO;
import com.qdbank.mall.service.PrometheusMicrometerService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.third.VirsualProxySendService;
import com.qdbank.mall.util.IpUtils;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.MD5Util;
import lombok.Data;
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

import java.util.*;

import static com.qdbank.mall.constant.DatePattern.NORM_DATETIME_FORMAT;
import static com.qdbank.mall.util.StringUtil.maskPhoneNo;

@Service
@Slf4j
@Data
//@ThirdComponent(desc = "行内前置服务 service")
@DefaultProperties(groupKey = "SC-Virsual-ProxySendServiceImpl-GK", threadPoolKey = "SC-Virsual-ProxySendServiceImpl-Pool", commandProperties = {
        @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"),
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")}, threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "10"),
        @HystrixProperty(name = "keepAliveTimeMinutes", value = "5"), @HystrixProperty(name = "maxQueueSize", value = "65535"), @HystrixProperty(name = "queueSizeRejectionThreshold", value = "35000")
  })
@RefreshScope
public class VirsualBankProxySendServiceImpl extends BaseServiceImpl implements VirsualProxySendService {

    @Autowired
    @Qualifier("mobileThirdgroupTemplate")
    RestTemplate restTemplate;

    @Autowired
    private PrometheusMicrometerService prometheusMicrometerService;


    /**
     * 环境域名
     */
    @Value(value = "${third.virsual.domain2}")
    String domain ;

    /**
     * 下单
     */
    String buyUrl ="/vip/wx/api/buy";

    /**
     * 查询
     */
    String qryOrderUrl ="/vip/wx/api/queryOrder";


    @Value(value = "${third.virsual.userName}")
    private String userName;

    @Value(value = "${third.virsual.passWord}")
    private String passWord;

    @Value(value = "${third.virsual.pid}")
    private String pid;

    @Autowired
    PrefectureDao prefectureDao;



    @Autowired
    MobileService mobileService;


   /* @Override
    @HystrixCommand(commandKey = "phone", fallbackMethod = "phoneFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
            , @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "0")
            ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "70")
      //      ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value="1")
    })
    public PhoneResDTO phone(String phone) {
        PhoneResDTO result = new PhoneResDTO();
        Map<String,String> body = new HashMap<>(1);
        //手机号码
        body.put("phone",phone);
        try {
            *//**
             * supplier_type
             * province_type
             *//*
            Map<String,String> wxPhone=  this.post(phoneUrl, body,false);
            result.setSupplierType(wxPhone.get("supplier_type"));
            result.setProvinceType(wxPhone.get("province_type"));
            *//*result.setSupplierType("CM");
            result.setProvinceType("10001");*//*
        } catch (Exception e) {
            if(e instanceof MobileException){
                throw e;
            }
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }
        return result;
    }*/

    @Override
    @HystrixCommand(commandKey = "chargeOrderProcess", fallbackMethod = "chargeOrderProcessFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
            , @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "0")
            ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "70")
            //      ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value="1")
    })
    public String chargeOrderProcess(OrderDO order,String productId) {



        //获取规格信息
        Map<String,String> body = new HashMap<>();

       //	订单号	Yes	Yes	String	订单号:由请求方系统生成并填写上送，最长支持32位长度
       body.put("cusOrderId",order.getOrderSn());
       //商品ID	Yes	Yes	String	商品ID由网信会员平台分配，含价格/规格等信息
       body.put("kactivityId",productId);
       //充值帐号类型	Yes	Yes	String	充值帐号类型:1:平台帐号, 2:手机号(卡密则可为空) (除腾讯视频会员，腾讯QQ币为1，其余会员均为2)
       body.put("accountIdType","1");
       //要充值的帐号	Yes	Yes	String	要充值的帐号 (卡密则可为空) (除腾讯视频会员和腾讯QQ币为QQ账号，其余会员均为注册手机号)
       body.put("accountId",order.getRechargeMobile()+"");
       // 充值类型	Yes	Yes	String	要下单的服务类型：1：直充 2：卡密 3：卡密直冲 4：QQ币充值
       body.put("chargetype","1");
       //充值数量	Yes	Yes	String	充值数量 (QQ币充值填写大于0整数，其他服务类型填写1即可)
       body.put("amount","1");

        // 扩展信息	No	No	String	不参与验签
        body.put("extContent","");


        String orderId;
        try {
            Map<String,String> wxPhone=  this.post(buyUrl, body,false);
      //      orderId = wxPhone.get("order_id");
            orderId = order.getOrderSn();
        } catch (Exception e) {
            if(e instanceof MobileException){
                throw e;
            }
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }
        return orderId;
    }

    @Override
    @HystrixCommand(commandKey = "orderFind", fallbackMethod = "orderFindFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
            , @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "0")
            ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "70")
            //      ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value="1")
    })
    public QryOrderResDTO orderFind(String orderSn) {
        Map<String,String> body = new HashMap<>();
        //订单号:由请求方系统生成并填写上送，最长支持32位长度
        body.put("cusOrderId",orderSn);
        //要查单的服务类型：1：直充 2：卡密 3：卡密直冲 4：QQ充值
        body.put("chargetype","1");

        QryOrderResDTO res = new QryOrderResDTO();
        try {
            Map orderFind=  this.post(qryOrderUrl, body,false);
            res.setOrderState(orderFind.get("status").toString());
            res.setCusOrderId(orderFind.get("orderId").toString());
        } catch (Exception e) {
            if(e instanceof MobileException){
                throw e;
            }
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }
        return res;
    }




    /**
     * 对接网信手机
     * @param url
     * @param body
     * @return
     */
    public Map<String, String> post(String url, Map<String,String> body,boolean isArray){

        //	客户帐号
        body.put("userName",userName);
        //用户+密码
        body.put("passWord",passWord);



        //step 1 生成签名
        Map signMap = new HashMap();
        signMap.putAll(body);
        //去除拓展字段
        signMap.remove("extContent");

        String md5 = "";
        try {
            md5=MD5Util.wxMd5(signMap,pid);
        } catch (Exception e) {
            log.error("生成签名错误",e);
        }

        body.put("sign",md5);
        body.put("pid",pid);
        log.info("请求前网信虚拟商品 签名串-[{}]",md5);

        //step 2 发送
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
            for (Map.Entry<String,String> entry:
                    body.entrySet()) {
                params.add(entry.getKey(),entry.getValue());
            }

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

            //返回数组特殊处理
            JSONObject _response = null;
            String requestUrl = domain+url;
            log.info("不加密：virsual third requst[{}]-->[{}]",requestUrl,JsonUtil.toJSONString(body));
            if(isArray){
                JSONArray _responseArray = restTemplate.postForObject(requestUrl, requestEntity , JSONArray.class);
                _response=_responseArray.getJSONObject(0);
            }else{
                _response  = restTemplate.postForObject(requestUrl, requestEntity , JSONObject.class);
            }

            JSONObject data=_response.getJSONObject("data");

            //step 3 响应返回
            log.info("virsual third response-->[{}]", _response.toJSONString());
            //step 3.1 错误码判断
            String httpCode = _response.getString("httpCode");
            String retCode = _response.getString("retCode");
            if(!"200".equals(httpCode) || !"0".equals(retCode)){
                throw new MobileException(retCode,_response.getString("retMsg"));
            }

            Map<String,String> _body = data.toJavaObject(Map.class);

           /* //校验签名
            boolean signq = this.valide(_header,_body);

            //验签失败
            if(!signq){
                throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
            }*/

            return _body;
        } catch (Exception e) {
            if(e instanceof MobileException){
                throw e;
            }
            log.error("三方接口: 请求参数[{}]", body,e);
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }
    }


    /**
     * 待签名字段排序
     * @param params
     * @return
     */
    public static String getParamsByAscending(Map<String, String> params) {// 升序
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
                params.entrySet());
        Collections.sort(entryList,
                Comparator.comparing(Map.Entry::getKey));
        StringBuffer param = new StringBuffer();
        for (Map.Entry<String, String> entry : entryList) {
            String val = entry.getValue();
            //存在值做处理
            if(StringUtils.hasText(val)){
                param.append(entry.getKey()).append("=").append(entry.getValue());
                param.append("&");
            }
        }
        return param.toString();
    }


    @Override
    public boolean valide(Map<String, String> signMap)  {
       //响应的sign
        String _sign = signMap.get("sign");
        //step 3.2 响应验签
        Map _signMap  = new HashMap();
        signMap.remove("sign");

        String md5= null;
        try {
            md5 = MD5Util.wxMd5(signMap,pid);
        } catch (Exception e) {
           log.error("生成签名失败",e);
           return false;
        }
        log.info("请求后mobile 签名串[{}]-[{}]",md5,_sign);
        return md5.equals(_sign);
    }





    public PhoneResDTO phoneFallback(String phone) {
        return null;
    }
    public PhoneResDTO phoneFallback(String phone,Throwable exc) throws Throwable {
        log.error("报错[{}]-[{}]",new Object[]{phone,exc.getMessage()});
        String errorMsg = "调用网信归属地查询接口发生异常,手机号:" + maskPhoneNo(phone);
        alert(exc,errorMsg);
        throw exc;
    }

    public String chargeOrderProcessFallback(OrderDO order,String productId) {return null;}
    public String chargeOrderProcessFallback(OrderDO order,String productId,Throwable exc) throws Throwable{
        log.error("报错[{}]-[{}]",new Object[]{order.getOrderSn(),exc.getMessage()});
        String errorMsg ="调用网信充值下单接口发生异常,订单号:" + order.getOrderSn();
        alert(exc,errorMsg);
        throw exc;
    }

    public  QryOrderResDTO orderFindFallback(String orderSn) {return null;}
    public QryOrderResDTO orderFindFallback(String orderSn,Throwable exc) throws Throwable {
        log.error("报错[{}]-[{}]",new Object[]{orderSn,exc.getMessage()});
        String errorMsg = "调用网信订单查询接口发生异常,订单号:" + orderSn;
        alert(exc,errorMsg);
        throw exc;
    }

    long jg = System.currentTimeMillis();
    public void alert(Throwable exc,String msg){
        if("Hystrix circuit short-circuited and is OPEN".equals(exc.getMessage())){
            long now = System.currentTimeMillis();
            if((now-jg)/(1000*60)>5){
                log.info("通知告警-熔断器打开");
                AlertDTO alertReqDTO = new WXAlertDTO();
                alertReqDTO.setMessage(msg);
                prometheusMicrometerService.sendPost(null, alertReqDTO);
                jg=now;
            }

        }
    }




}
