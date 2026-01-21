import cn.hutool.core.net.URLDecoder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qdbank.mall.MallFrontMainApplication;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.response.auth.AuthResDTO;
import com.qdbank.mall.response.spgacc001.BaseInfo;
import com.qdbank.mall.response.spgacc001.DataMap;
import com.qdbank.mall.response.spgacc001.SpgAcc001ResDTO;
import com.qdbank.mall.response.spgacc001.UserInfo;
import com.qdbank.mall.util.AesUtils;
import com.qdbank.mall.util.HttpUtils;
import com.qdbank.mall.util.JwtTokenUtil;
import com.qdbank.mall.util.RSASecurityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.Random;

/**
 * @Author zengxiong
 * @Description auth接口测试
 * @Date 2022/8/4 17:21
 */
@SpringBootTest(classes = MallFrontMainApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("sit")
public class TestAuth {
    private static final String RANDOM_AES_STR = "1234567890qwertyuiopasdfghjklzxcvbnm";

    @Value(value = "${spg.http.rsa.public.key}")
    private String rsaPublicKey;

    @Value(value = "${spg.http.url}")
    private String spgHttpReqUrl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void test() {
        AuthResDTO authResDTO = new AuthResDTO();
//        String sessionKey = URLDecoder.decode("BiEiv84kl%20RiuCI3b66Rlg%3D%3D", Charset.forName("UTF-8"));
        String sessionKey = "4DsGDc30f61Jp5qBd3bKOQ==";
        try {
            //随机生成aes key
            String aesKey = generateRandomAesKey();
            //使用rsa公钥加密aes key
            String spgEncKey = RSASecurityUtil.encryptRSADefault(rsaPublicKey, aesKey);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("wxAcc", "1");
            data.put("sessionKey", sessionKey);
            String dataStr = AesUtils.aesEncrypt(JSON.toJSONString(data), aesKey);
            body.put("encryptData", dataStr);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            headers.set("spgEncKey", spgEncKey);
            headers.set("actionid", "SPGACC001");
            headers.set("sessionKey", sessionKey);
            headers.set("encryptflag", "1");

            HttpEntity httpEntity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = HttpUtils.getRestTemplate();
            JSONObject jsonObject = restTemplate.postForObject(spgHttpReqUrl + "access", httpEntity, JSONObject.class);

            //AES解密
            String encryptData = jsonObject.getString("data");
            String decryptData = AesUtils.aesDecrypt(encryptData, aesKey);
            SpgAcc001ResDTO spgAcc001ResDTO = JSON.parseObject(decryptData, SpgAcc001ResDTO.class);
            if (spgAcc001ResDTO != null) {
                DataMap dataMap = spgAcc001ResDTO.getDataMap();
                BaseInfo baseInfo = dataMap.getBaseInfo();
                UserInfo userInfo = dataMap.getUserInfo();
                boolean custIdHasText = StringUtils.hasText(baseInfo.getCustId());

                Map<String, String> tokenMap = Maps.newHashMap();
                tokenMap.put("custId", baseInfo.getCustId());
                tokenMap.put("unionId", userInfo.getUnionId());
                tokenMap.put("timestamp", System.currentTimeMillis() + "");
                tokenMap.put("phone", baseInfo.getMobilePhone());
                tokenMap.put("authFlag", custIdHasText ? "1" : "0");
                authResDTO.setToken(jwtTokenUtil.generateFrontToken(tokenMap));
                authResDTO.setCustId(baseInfo.getCustId());
                authResDTO.setBindFlag(custIdHasText ? "1" : "0");
                authResDTO.setAvatarUrl(userInfo.getAvatarUrl());
                authResDTO.setPhone(baseInfo.getMobilePhone());
            }
            System.out.println("=========================================");
            System.out.println(JSON.toJSONString(authResDTO));
            System.out.println("=========================================");
        } catch (Exception e) {
            throw new ApiException(ResultCode.FORBIDDEN);
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
