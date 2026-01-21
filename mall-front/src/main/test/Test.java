import com.alibaba.fastjson.JSONObject;
import com.qdbank.mall.config.ThirdgroupTemplateConfig;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.request.third.payment.AccessSignReqDTO;
import com.qdbank.mall.request.third.payment.QryPayReqDTO;
import com.qdbank.mall.request.third.payment.RefundReqDTO;
import com.qdbank.mall.response.third.payment.AccessSignResDTO;
import com.qdbank.mall.response.third.payment.QryPayResDTO;
import com.qdbank.mall.util.JsonUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Test {

    public static void main(String[] args) {


        ThirdgroupTemplateConfig config = new ThirdgroupTemplateConfig();

        RestTemplate template=config.restTemplate(new RestTemplateBuilder());
        String url = "http://10.1.87.74:9080/ifp_middleground_service/getAccessSign.do";
        url="https://qdbservice.qdccb.com/ifp_middleground_service/getAccessSign.do";

    //    http://10.1.87.74:9080/ifp_middleground_service/getAccessSign.do

        AccessSignReqDTO accReq = new AccessSignReqDTO();
        /**
         * 00：获取支付页面
         * 01：退款
         * 02：获取订单状态
         * 03：预通知订单信息
         */

        accReq.setGetType("02");
        accReq.setFlag("B");
        accReq.setReqURL("dfsdfasdfasf");
        accReq.setChannel("mall");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        Map<String,Object> map = JsonUtil.parseObject(JsonUtil.toJSONString(accReq), Map.class);
        for(String key : map.keySet()){
         params.add(key, (String) map.get(key));//支持中文
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//仅需替换exchange方法
        ResponseEntity<JSONObject> response = template.postForEntity(url, requestEntity , JSONObject.class);
        JSONObject oo=response.getBody().getJSONObject("body");
        AccessSignResDTO result= oo.toJavaObject(AccessSignResDTO.class);
        System.out.println(response);
        System.out.println(result);







     /*   RefundReqDTO req = new RefundReqDTO();

        req.setCurrNum("RMB");
        req.setOrdId("QD202105311151420100000011");
  //      req.setOrdId("kp0015341167100376");
        req.setProdId("100031");
  //      req.setProdId("751357955");
       req.setMerchantNumber("10003");
 //       req.setMerchantNumber("");
        req.setGetType("01");

        req.setCustId("146");
  //      req.setCustId("14145");


        req.setAccessSign(result.getAccessSign());
        req.setAccessSignid(result.getAccessSignid());
        req.setAccessSignFlag("A");

        req.setOriTransDt("20210531");

        //渠道流水号
        req.setOriTransSer("10157603");
        //渠道日期
        //   req.setOriTransDt(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
        //积分账户类型
        //   req.setAcctType();
        //积分调整值
        req.setTranse("0");
        req.setTransAmt("29.98");
        //积分账户类型
        req.setAcctType("");
        req.setQueryType("C");


        HttpHeaders headers1 = new HttpHeaders();
//请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
         params= new LinkedMultiValueMap<String, String>();
         map = JsonUtil.parseObject(JsonUtil.toJSONString(req), Map.class);
        for(String key : map.keySet()){
            params.add(key, (String) map.get(key));//支持中文
        }
         requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        JSONObject __result =template.postForObject("http://wx.qdccb.cn/ifp_middleground_service/refund.do", requestEntity, JSONObject.class);
        System.out.println(__result.toJSONString());

        JSONObject ooo=__result.getJSONObject("body");
        String s = (String) ooo.get("data");*/
        //解密




       QryPayReqDTO req = new QryPayReqDTO();

        req.setGetType("02");
        req.setFlag("B");
        req.setChannel("mall");
        req.setOrdId("QD202107232034240007");
 //       req.setId(null);
        req.setAccessSign(result.getAccessSign());
        req.setAccessSignFlag("A");
        req.setAccessSignid(result.getAccessSignid());
        req.setPayFlage("C");

        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params1= new LinkedMultiValueMap<String, String>();
        Map<String,Object> map1 = JsonUtil.parseObject(JsonUtil.toJSONString(req), Map.class);
        for(String key : map1.keySet()){
            params1.add(key, (String) map1.get(key));//支持中文
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity1 = new HttpEntity<MultiValueMap<String, String>>(params1, headers1);
//仅需替换exchange方法
        String orderUrl = "http://10.1.87.74:9080/ifp_middleground_service/orderType.do";
        orderUrl="https://qdbservice.qdccb.com/ifp_middleground_service/orderType.do";
        ResponseEntity<JSONObject> response1 = template.postForEntity(orderUrl, requestEntity1 , JSONObject.class);
        JSONObject oo1=response1.getBody().getJSONObject("body");

        Map mm =oo1.toJavaObject(Map.class);
       /* for(Object key : mm.keySet()){
            System.out.println("@ApiModelProperty(\"\")");
            System.out.println("private String "+key.toString()+";");
            System.out.println();
        }*/

        System.out.println(oo1.toJSONString());
        QryPayResDTO result1= oo1.toJavaObject(QryPayResDTO.class);
        System.out.println(result1);
        System.out.println(result1.getAcctType());
/**
//_________________________________________________

        PrePayReqDTO prePayReq = new PrePayReqDTO();
        prePayReq.setAccessSign(result.getAccessSign());
        prePayReq.setAccessSignid(result.getAccessSignid());
        prePayReq.setAccessSignFlag("A");
        //商品id
        prePayReq.setCommodityCode("123");
        //商品名称
        prePayReq.setTradeName("青岛大虾");
        //商户号
        prePayReq.setMerchantNumber("QD");
        //商户名称
        prePayReq.setNameBusiness("青岛大虾123");

        //订单号
        prePayReq.setOrderid("123456");
        prePayReq.setGetType(Constant.THIRD_GET_TYPE_PAY);
        prePayReq.setChannel(Constant.THIRD_PAY_CHANNEL);
        prePayReq.setUrl("sdfsdf");
    //    prePayReq.setBusinessNumber("");
        Long payType =PaymentEnum.MONEY_SCORE.payType;
        PaymentEnum payTypeEnum =PaymentEnum.getPayEnumByPaytype(payType);
        //支付方式
        prePayReq.setFlag(payTypeEnum.value);
        if(payTypeEnum==PaymentEnum.MONEY_SCORE || payTypeEnum==PaymentEnum.SCORE){
            //积分兑换
            prePayReq.setTrxndirect("E");
            prePayReq.setTranse("100");
        }
        if(payTypeEnum==PaymentEnum.MONEY_SCORE || payTypeEnum==PaymentEnum.MONEY){
            //现金支付
            prePayReq.setTransAmt("10");
        }





        String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW/V7SE4xt/NPecY4iuHVXC8RwslL3dtEXkZzB9OdCqiePYo/YS3J98bsBxRCespifqpBKFY4nyFytZnNUTl1pq5MFgxaQiLKtNjrg3zoHPISjYHwdeMj5evjtVrL5qcpU/rF9ZBdd0/E2P82tp37qo44xPs4m8OFIF0S66hb0BQIDAQAB";
        String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJb9XtITjG38095xjiK4dVcLxHCyUvd20ReRnMH050KqJ49ij9hLcn3xuwHFEJ6ymJ+qkEoVjifIXK1mc1ROXWmrkwWDFpCIsq02OuDfOgc8hKNgfB14yPl6+O1WsvmpylT+sX1kF13T8TY/za2nfuqjjjE+zibw4UgXRLrqFvQFAgMBAAECgYBGl06tiNYu1fV4Gj8JnJO/jpDY34ZKjBJVoo5XX9h/Im48ayg1R5DPsSRtP2T1zSnt/CBZgppjnxk1OKDS2ZByDFSVTcK8ubvUcCg1w5y9wX9yB6Igx2Dsj8dqMQZXbgtYZj8LTMSztOQzXfk0JJJhPjRMTEAX1w/KIeCebj8aAQJBAOn5+ke005ErQBtYrfDwbfMjpIlFclPHzPckIIMxhf8/DOc2Q6dKioY13hhjwt4c9dldWFe8rBLf5na8S/DP/IkCQQClM7MdxWB9I5TXiUBVd/mNT/0LLk9cES7IG/gRh4fQwPe8YdAqBJENGPxuKO4ls7h+nU8Si384Cv8XBMEyg3SdAkBzbSH+fAOfazHOC9qLsWDcgOnr2nnDQR8pkQYFEspjGGy6J7gKcKiT+0Ec0SJSRwE2AWnSpr5Q9WoRi2T/YOvZAkALgTI0HI6/qD6xU+mbCiPi53Mj2DHGo5uya+A2uE2JCCc4g0dP5cmEM/1AXrFXBtEOYD4leHl/maRyIe6iae0xAkAJGEMfUoGYkRIi3Z3ZIjdR88UiZj8BVVQ0brZOFvl/cfuiIfz2hF5369/Ur3fj09og3ARhw7/zxRC2STx8ppYq";
        //加密
        String data = RSAUtil.encrypt(publicKey,privateKey,JsonUtil.toJSONString(prePayReq));
   //     Map map1 = new HashMap();
    //    map1.put("data",data);


        HttpHeaders headers1 = new HttpHeaders();
//请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers1.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params1= new LinkedMultiValueMap<String, String>();
            params1.add("data", data);//支持中文
        HttpEntity<MultiValueMap<String, String>> requestEntity1 = new HttpEntity<MultiValueMap<String, String>>(params1, headers1);


        JSONObject __result =template.postForObject("http://wx.qdccb.cn/ifp_middleground_service/saveOrderinfo.do", requestEntity1, JSONObject.class);
        System.out.println(__result.toJSONString());

        JSONObject ooo=__result.getJSONObject("body");
        String s = (String) ooo.get("data");
        //解密
        String  result11 =RSAUtil.decrypt(privateKey,publicKey,s);
        JSONObject j=JSONObject.parseObject(result11);
        JSONObject data_J =j.getJSONObject("data");
        PrePayResDTO resDTO= data_J.toJavaObject(PrePayResDTO.class);
        System.out.println(resDTO.getErrorCode());
*/

    }
}
