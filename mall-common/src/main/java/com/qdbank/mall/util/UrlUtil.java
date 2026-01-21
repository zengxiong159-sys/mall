package com.qdbank.mall.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

/**
 * @Author zengxiong
 * @Description URL处理工具类
 * @Date 2021/7/6 11:30
 */
@Slf4j
public class UrlUtil {
    private static final String ENCODE = "UTF-8";

    /**
     * url解码
     *
     * @param str url地址
     * @return 解码结果
     */
    public static String getUrlDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            log.error("url解码异常:{}", e);
        }
        return result;
    }

    /**
     * url编码
     *
     * @param str url地址
     * @return 编码结果
     */
    public static String getUrlEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            log.error("url编码异常:{}", e);
        }
        return result;
    }
}
