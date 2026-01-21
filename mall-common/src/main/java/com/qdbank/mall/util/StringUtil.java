package com.qdbank.mall.util;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * StringUtil
 *
 * @author shaoshihang
 * @date 2021/3/15 9:04
 * @since 1.0.0
 */
public class StringUtil extends StringUtils {

    public static String objectToString(Object object){
        if (object == null || "".equals(object)){
            return "";
        }else {
            return object.toString().trim();
        }
    }

    static  DecimalFormat df = new DecimalFormat("#0.00");
    public static String formateAmt(BigDecimal bd){
        if(bd==null){
            return "0.00";
        }
        bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
        String result = df.format(bd);
        return result;
    }

    /**
     * 手机号掩码
     * @param phoneNo   手机号
     * @return  掩码结果
     */
    public static String maskPhoneNo(String phoneNo){
        if(StringUtils.isEmpty(phoneNo) || phoneNo.trim().length() != 11){
            return phoneNo;
        }
        return phoneNo.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
