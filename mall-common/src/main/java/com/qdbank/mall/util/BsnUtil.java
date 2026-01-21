package com.qdbank.mall.util;


import com.qdbank.mall.constant.payment.PaymentTypeEnum;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * TimeUtil
 *
 * @author hongjh
 * @date 2021/6/2 11:39
 * @since 1.0.0
 */
public class BsnUtil {

    static BigDecimal basicCoupon = new BigDecimal(100);

    /**
     * 算积分
     * @param couponAmount
     * @return
     */
    public static Long getCouponIntegration (BigDecimal couponAmount){
        if (couponAmount==null) {
            return 0L;
        }
        return (basicCoupon.multiply(basicCoupon)).longValue();
    }

    /**
     * 算积分
     * @param integration
     * @return
     */
    public static BigDecimal integraToAmt (Long integration){
        if (integration==null || integration==0) {
            return new BigDecimal(0);
        }
        return (new BigDecimal(integration)).divide(basicCoupon);
    }




    /**
     * 通过金额和积分确定支付类型
     * @param amt
     * @param integration
     * @return
     */
    public static PaymentTypeEnum initPayType(BigDecimal amt , Long integration){
        amt = amt==null?BigDecimal.ZERO:amt;
        integration = integration==null?0:integration;

        if(amt.compareTo(BigDecimal.ZERO) >0 && integration >0){
            return PaymentTypeEnum.MONEY_SCORE;
        }else if(amt.compareTo(BigDecimal.ZERO) >0){
            return PaymentTypeEnum.MONEY;
        }else if(integration > 0){
            return PaymentTypeEnum.SCORE;
        }
        return null;
    }


    public static BigDecimal convertBigdecimal(Object obj1){
        BigDecimal b1 = null;
        if(obj1 == null){
            b1 = BigDecimal.ZERO;
        }else if(obj1 instanceof BigDecimal){
            b1 = (BigDecimal) obj1;
        }else{
            b1 = new BigDecimal(obj1.toString());
        }
        return b1;
    }

    public static Long convertLong(Object obj1){
        Long b1 = null;
        if(obj1 == null){
            b1 = 0L;
        }else if(obj1 instanceof Long){
            b1 = (Long) obj1;
        }else{
            b1 = new Long(obj1.toString());
        }
        return b1;
    }






}
