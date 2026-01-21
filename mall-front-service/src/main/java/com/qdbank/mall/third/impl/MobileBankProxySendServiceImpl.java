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
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.exception.MobileException;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.response.third.mobile.OrderFindResDTO;
import com.qdbank.mall.response.third.mobile.PhoneResDTO;
import com.qdbank.mall.service.PrometheusMicrometerService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.third.MobileProxySendService;
import com.qdbank.mall.util.IpUtils;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.RSASignature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.qdbank.mall.util.StringUtil.maskPhoneNo;

@Service
@Slf4j
//@ThirdComponent(desc = "行内前置服务 service")
@DefaultProperties(groupKey = "SC-Mobile-ProxySendServiceImpl-GK", threadPoolKey = "SC-Mobile-ProxySendServiceImpl-Pool", commandProperties = {
        @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"),
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")}, threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "10"),
        @HystrixProperty(name = "keepAliveTimeMinutes", value = "5"), @HystrixProperty(name = "maxQueueSize", value = "65535"), @HystrixProperty(name = "queueSizeRejectionThreshold", value = "35000")
  })
@RefreshScope
public class MobileBankProxySendServiceImpl extends BaseServiceImpl implements MobileProxySendService {

    /**
     * 本地私钥
     */
    @Value(value = "${third.mobile.rsa.local.privateKey}")
    private String privateKey;

    /**
     * 他行公钥
     */
    @Value(value = "${third.mobile.rsa.other.publicKey}")
    private String publicKey;

    @Autowired
    @Qualifier("mobileThirdgroupTemplate")
    RestTemplate restTemplate;

    @Autowired
    private PrometheusMicrometerService prometheusMicrometerService;

    @Value(value = "${third.mobile.appid}")
    private String appid;


    @Autowired
    MobileService mobileService;

    /**
     * 环境域名
     */
    @Value(value = "${third.mobile.domain2}")
    private String mobileurl ;

    private static final String phoneUrl = "/tariffe/wx/phone/wxPhone.do";

    private static final String orderFindUrl = "/tariffe/wx/order/wxOrderFind.do";

    private static final String chargeOrderProcessUrl = "/tariffe/wx/charge/wxChargeOrderProcess.do";


  //  @ThirdApiAccess(desc = "")
    @Override
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
            /**
             * supplier_type
             * province_type
             */
            Map<String,String> wxPhone=  this.post(phoneUrl, body,false);
            result.setSupplierType(wxPhone.get("supplier_type"));
            result.setProvinceType(wxPhone.get("province_type"));
            /*result.setSupplierType("CM");
            result.setProvinceType("10001");*/
        } catch (Exception e) {
            if(e instanceof MobileException){
                throw e;
            }
            throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
        }
        return result;
    }

    @Override
    @HystrixCommand(commandKey = "chargeOrderProcess", fallbackMethod = "chargeOrderProcessFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
            , @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,value = "0")
            ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,value = "70")
            //      ,@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,value="1")
    })
    public String chargeOrderProcess(OrderDO order) {
        Long productSkuId = order.getProductSkuId();
        //获取规格信息
        MobileSkuDO mobileSkuDO =mobileService.qryMobileSkuByMobileSkuId(productSkuId);
        Map<String,String> body = new HashMap<>();
        //产品id
        body.put("product_id",mobileSkuDO.getSupplyProductId());
        body.put("phone",order.getRechargeMobile()+"");
        //话费类型
        body.put("type","1");
        body.put("kh_order_id",order.getOrderSn());
        body.put("ext_content","");

        String orderId;
        try {
            Map<String,String> wxPhone=  this.post(chargeOrderProcessUrl, body,false);
            orderId = wxPhone.get("order_id");
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
    public OrderFindResDTO orderFind(String orderSn) {
        Map<String,String> body = new HashMap<>();
        //产品id
        body.put("kh_order_id",orderSn);

        OrderFindResDTO res = new OrderFindResDTO();
        try {
            Map<String,String> orderFind=  this.post(orderFindUrl, body,true);
            /**
             * kh_order_id	String	True	KH订单号
             * wx_order_id	String	True	网信订单号
             * phone	String	True	手机号
             * status	String	True	充值状态	详见充值状态列表
             * kh_settle_time	String	True	结算时间，格式：yyyyMMddHH24mmss	充值成功时，此值不可为空
             * kh_settle_price	String	True	结算价格，单位：元，保留四位小数	充值成功时，此值不可为空
             */
            res.setPhone(orderFind.get("phone"));
            res.setStatus(orderFind.get("status"));
            res.setKhOrderId(orderFind.get("kh_order_id"));
            res.setKhSettlePrice(orderFind.get("kh_settle_price"));
            res.setKhSettleTime(orderFind.get("kh_settle_time"));
            res.setWxOrderId(orderFind.get("wx_order_id"));
            res.setErrMsg(orderFind.get("err_msg"));
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
        Map<String,String> header = new HashMap();
        //应用id
        header.put("app_id",appid);
        //请求序列号
        header.put("req_no",super.generateId()+"");
        //请求发起时的时间戳
        header.put("timestamp",System.currentTimeMillis()+"");
        //签名随机串
        header.put("sign_nonce",header.get("req_no"));

        //step 1 生成签名
        Map signMap = new HashMap();
        signMap.putAll(header);
        signMap.putAll(body);
        String signStr = getParamsByAscending(signMap);
        signStr = signStr.substring(0,signStr.length()-1);
        String sign = RSASignature.sign(signStr,privateKey, "UTF-8");
        header.put("sign",sign);
        log.info("请求前mobile 签名串[{}]-[{}]",signStr,sign);

        //step 2 发送
        Map req = new HashMap();
        req.put("header",header);
        req.put("body",body);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<String>(JsonUtil.toJSONString(req), headers);

            //返回数组特殊处理
            JSONObject _response = null;
            String requestUrl = mobileurl+url;
            log.info("不加密：mobile third requst[{}]-->[{}]",requestUrl,JsonUtil.toJSONString(req));
            if(isArray){
                JSONArray _responseArray = restTemplate.postForObject(requestUrl, requestEntity , JSONArray.class);
                _response=_responseArray.getJSONObject(0);
            }else{
                _response  = restTemplate.postForObject(requestUrl, requestEntity , JSONObject.class);
            }

            JSONObject res_header=_response.getJSONObject("header");
            JSONObject res_body=_response.getJSONObject("body");

            //step 3 响应返回
            log.info("third response-->[{}]", _response.toJSONString());
            //step 3.1 错误码判断
            String errorCode = res_body.getString("code");
            if(!"200".equals(errorCode)){
                if("210012".equals(errorCode)){
                    throw new MobileException(ResultCode.PHONE_NUM_ERROR);
                }else{
                    throw new MobileException(errorCode,res_body.getString("msg"));
                }
            }

            Map<String,String> _header = res_header.toJavaObject(Map.class);
            Map<String,String> _body = res_body.toJavaObject(Map.class);

            //校验签名
            boolean signq = this.valide(_header,_body);

            //验签失败
            if(!signq){
                throw new ApiException(ResultCode.THIRD_SYSTEM_ERROR);
            }

            return _body;
        } catch (Exception e) {
            if(e instanceof MobileException){
                throw e;
            }
            log.error("三方接口: 请求参数[{}]", req,e);
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
    public boolean valide(Map<String, String> _header, Map<String, String> _body){
        //响应的sign
        String _sign = _header.get("sign");
        //step 3.2 响应验签
        Map _signMap  = new HashMap();
        _signMap.putAll(_header);
        _signMap.putAll(_body);
        _signMap.remove("sign");

        String mzSignStr = getParamsByAscending(_signMap);
        mzSignStr = mzSignStr.substring(0,mzSignStr.length()-1);
        log.info("请求后mobile 签名串[{}]-[{}]",mzSignStr,_sign);
        boolean signq = RSASignature.doCheck(mzSignStr, _sign, publicKey, "UTF-8");
        return signq;
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

    public String chargeOrderProcessFallback(OrderDO order) {return null;}
    public String chargeOrderProcessFallback(OrderDO order,Throwable exc) throws Throwable{
        log.error("报错[{}]-[{}]",new Object[]{order.getOrderSn(),exc.getMessage()});
        String errorMsg ="调用网信充值下单接口发生异常,订单号:" + order.getOrderSn();
        alert(exc,errorMsg);
        throw exc;
    }

    public OrderFindResDTO orderFindFallback(String orderSn) {return null;}
    public OrderFindResDTO orderFindFallback(String orderSn,Throwable exc) throws Throwable {
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
