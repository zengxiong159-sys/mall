package com.qdbank.mall.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

/**
 * @Author zengxiong
 * @Description 密码操作工具类
 * @Date 2021/7/15 16:55
 */
public class PasswordCheckUtil {
    /**
     * 判断是否是复杂密码
     * 必须包含数字、大写、小写、特殊字符,且密码长度大于8位
     *
     * @param password 密码
     * @return 是否是复杂密码 true:是 false:否
     */
    public static boolean isComplicatedPwd(String password) {
        Map<String, String> map = Maps.newHashMap();
        for (int i = 0; i < password.length(); i++) {
            int A = password.charAt(i);
            if (A >= 48 && A <= 57) {
                map.put("数字", "数字");
            } else if (A >= 65 && A <= 90) {
                map.put("大写", "大写");
            } else if (A >= 97 && A <= 122) {
                map.put("小写", "小写");
            } else {
                map.put("特殊", "特殊");
            }
        }
        Set<String> sets = map.keySet();
        int pwdSize = sets.size();
        int pwdLength = password.length();
        if (pwdSize >= 4 && pwdLength >= 8) {
            return true;
        } else {
            return false;
        }
    }
}
