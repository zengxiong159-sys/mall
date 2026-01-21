package com.qdbank.mall.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.qdbank.mall.account.AccountService;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bind.WechatBindService;
import com.qdbank.mall.enums.WechatBindStatus;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.request.auth.Auth2ReqDTO;
import com.qdbank.mall.request.auth.AuthReqDTO;
import com.qdbank.mall.response.account.AccountInfo;
import com.qdbank.mall.response.auth.AuthResDTO;
import com.qdbank.mall.response.bind.WechatBindInfo;
import com.qdbank.mall.response.spgacc001.BaseInfo;
import com.qdbank.mall.response.spgacc001.DataMap;
import com.qdbank.mall.response.spgacc001.SpgAcc001ResDTO;
import com.qdbank.mall.response.spgacc001.UserInfo;
import com.qdbank.mall.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName AuthController
 * @Author hongjh
 * @Date 2021/1/11 10:09
 * @Version 1.0
 **/
@RestController
@Api(tags = "AuthController", description = "授权controller")
@RequestMapping("/auth")
@Slf4j
@RefreshScope
public class AuthController {

    private static final String RANDOM_AES_STR = "1234567890qwertyuiopasdfghjklzxcvbnm";
    @Autowired
    private WechatBindService wechatBindService;
    @Value(value = "${spg.http.rsa.public.key}")
    private String rsaPublicKey;

    @Value(value = "${spg.http.url}")
    private String spgHttpReqUrl;

    @Value(value = "${http.request.rsa.private.key}")
    private String privateKey;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountService accountService;

    @ApiOperation("授权信息查询")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AuthResDTO> auth (@Validated @RequestBody AuthReqDTO authReqDTO) {
        String data = authReqDTO.getEncryptData();
        String key = authReqDTO.getEncryptKey();
        String result   =decrypt(key,data);
        log.info("转信息[{}]",result);

        Map map = JsonUtil.parseObject(result, Map.class);
        //获取客户号
        String custId = (String) map.get("custId");
        AuthResDTO authResDTO =JsonUtil.parseObject(result, AuthResDTO.class);
        authResDTO.setBindFlag(StringUtils.hasText(custId)?"1":"0");

        String token =  jwtTokenUtil.generateFrontToken(map);
        authResDTO.setToken(token);
        authResDTO.setCustId(custId);
        return CommonResult.success(authResDTO);
    }

    private String decrypt(String key,String data){
        String result = null;
        try {
            key = RSAUtil.decryptRSADefault(privateKey,key);
            result= new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes()).decryptStr(data);
        } catch (Exception e) {
            log.error("解密异常",e);
            throw new ApiException(ResultCode.FORBIDDEN);
        }
        return result;
    }

    @ApiOperation("授权信息查询")
    @RequestMapping(value = "/auth2", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AuthResDTO> auth2(@Validated @RequestBody Auth2ReqDTO auth2ReqDTO) {
        AuthResDTO authResDTO = new AuthResDTO();
        String sessionKey = URLDecoder.decode(auth2ReqDTO.getSessionKey(), Charset.forName("UTF-8"));
        String openId = wechatBindService.queryOpenId(sessionKey);
        log.info("sessionKey:{} 获取openId:{}",sessionKey,openId);
        WechatBindInfo wechatBindInfo = wechatBindService.queryBindInfo(openId);
        log.info("openId:{} 查询绑定信息：{}",openId,JSON.toJSONString(wechatBindInfo));
        String custId = "";
        String unionId = "";
        String phone = "";
        if(WechatBindStatus.BIND.getStatus().equals(wechatBindInfo.getIsBind())){
            phone = wechatBindInfo.getWechatBindDetailInfo().getMobilePhoneNo();
            unionId = wechatBindInfo.getWechatBindDetailInfo().getUnionId();
            List<AccountInfo> accountInfos = accountService.queryAccountInfo(wechatBindInfo.getWechatBindDetailInfo().getIdCde());
            if(CollectionUtil.isNotEmpty(accountInfos)){
                custId = accountInfos.get(0).getCustId();
            }
        }
        Map<String, String> tokenMap = Maps.newHashMap();
        tokenMap.put("custId",  custId);
        tokenMap.put("unionId", unionId);
        tokenMap.put("timestamp", System.currentTimeMillis() + "");
        tokenMap.put("phone", phone);
        tokenMap.put("authFlag",StringUtils.hasText(custId)  ? "1" : "0");
        authResDTO.setToken(jwtTokenUtil.generateFrontToken(tokenMap));
        authResDTO.setCustId(custId);
        authResDTO.setBindFlag(StringUtils.hasText(custId) ? "1" : "0");
        authResDTO.setPhone(phone);
        return CommonResult.success(authResDTO);
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
