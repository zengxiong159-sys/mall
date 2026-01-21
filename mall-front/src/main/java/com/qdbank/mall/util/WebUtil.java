package com.qdbank.mall.util;

import org.springframework.util.CollectionUtils;

import java.util.Map;

public class WebUtil {

    public static String formateUrl(String url, Map<String,String> params){
        StringBuilder sb = new StringBuilder(url);
        if(!CollectionUtils.isEmpty(params)){
            sb.append("?");
            for(String key : params.keySet()){
                Object obj=params.get(key);
                sb.append(key).append("=");
                if(obj != null){
                    sb.append(obj.toString());
                }
                sb.append("&");
            }
            return sb.substring(0,sb.length()-1);
        }
        return sb.toString();
    }

}
