package com.qdbank.mall.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName JsonUtil
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/5 10:03
 * @Version 1.0
 **/
@Slf4j
public class JsonUtil {
    public static <T> List<T> json2List(String jsonStr,Class clazz){
        try {
            JSONArray jsonArray = JSONArray.parseArray(jsonStr);
            List<T> list = jsonArray.toJavaList(clazz);
            return list;
        }catch (Exception e){
            log.error("转换异常:"+e);
        }
        return  null;
    }

    /**
     * 暂时统一处理
     * @param o
     * @return
     */
    public static String toJSONString(Object o){
       return JSONObject.toJSONString(o);
    }

    /**
     * 暂时统一处理
     * @param
     * @return
     */
    public static <T> T parseObject(String  s,Class<T> clazz){
        return JSONObject.parseObject(s,clazz);
    }
}
