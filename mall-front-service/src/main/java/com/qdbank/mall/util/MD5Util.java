package com.qdbank.mall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

@Slf4j
public class MD5Util {

    public static String wxMd5(Map params ,String pid)  throws Exception{
        String paramStr = getParamsByAscending(params);
        String message = URLEncoder.encode(paramStr,"GBK")+"&"+pid;
        log.debug("签名串明文[{}]",message);
        String md5 = DigestUtils.md5Hex(message);
        return md5;
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
            //值为空，或不存在，传空字符串
            if(!StringUtils.hasText(val)){
                val="";
            }
            param.append(entry.getKey()).append("=").append(val);
            param.append("&");
        }
        String result= param.toString();
        return result.substring(0,result.length()-1);
    }

}
