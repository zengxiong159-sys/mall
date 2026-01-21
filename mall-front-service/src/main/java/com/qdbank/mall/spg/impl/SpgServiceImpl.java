package com.qdbank.mall.spg.impl;

import cn.hutool.core.net.URLDecoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qdbank.mall.spg.SpgService;
import com.qdbank.mall.util.AesUtils;
import com.qdbank.mall.util.HttpUtils;
import com.qdbank.mall.util.RSASecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Random;
@Service
@Slf4j
public class SpgServiceImpl implements SpgService {
    @Value(value = "${spg.http.rsa.public.key}")
    private String rsaPublicKey;

    @Value(value = "${spg.http.url}")
    private String spgHttpReqUrl;

    private static final String RANDOM_AES_STR = "1234567890qwertyuiopasdfghjklzxcvbnm";
    @Override
    public String decryptData(String sessionKey,String actionid,String custId) {
        try {
            //随机生成aes key
            String aesKey = generateRandomAesKey();
            //使用rsa公钥加密aes key
            String spgEncKey = RSASecurityUtil.encryptRSADefault(rsaPublicKey, aesKey);
            sessionKey = URLDecoder.decode(sessionKey, Charset.forName("UTF-8"));
            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("wxAcc", "1");
            data.put("sessionKey", sessionKey);
            data.put("custId",custId);
            String dataStr = AesUtils.aesEncrypt(JSON.toJSONString(data), aesKey);
            body.put("encryptData", dataStr);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            headers.set("spgEncKey", spgEncKey);
            headers.set("actionid", actionid);
            headers.set("sessionKey", sessionKey);
            headers.set("encryptflag", "1");

            HttpEntity httpEntity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = HttpUtils.getRestTemplate();
            log.info("请求url:{} 参数：{}",spgHttpReqUrl,sessionKey);
            JSONObject jsonObject = restTemplate.postForObject(spgHttpReqUrl + "access", httpEntity, JSONObject.class);
            log.info("sessionKey:{}响应结果：{}",sessionKey,JSON.toJSONString(jsonObject));
            //AES解密
            String encryptData = jsonObject.getString("data");
            String decryptData = AesUtils.aesDecrypt(encryptData, aesKey);
            return decryptData;
        }catch (Exception e){
            log.info("请求小程序异常：{}",e);
            return null;
        }
    }
    /**
     * 生成16位随机数
     *
     * @return 随机数
     */
    private static String generateRandomAesKey() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 16; ++i) {
            sb.append(RANDOM_AES_STR.charAt(random.nextInt(36)));
        }
        return sb.toString();
    }
}
