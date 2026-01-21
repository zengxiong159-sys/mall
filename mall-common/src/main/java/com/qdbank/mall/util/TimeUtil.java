package com.qdbank.mall.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * TimeUtil
 *
 * @author shaoshihang
 * @date 2021/3/22 11:39
 * @since 1.0.0
 */
public class TimeUtil {

    public static boolean judgeThreeMonths(Date startTime,Date endTime){
        SimpleDateFormat year =new SimpleDateFormat("yyyy");
        SimpleDateFormat months=new SimpleDateFormat("MM");
        SimpleDateFormat day=new SimpleDateFormat("dd");
        int ye = Integer.parseInt(year.format(startTime));
        int mo = Integer.parseInt(months.format(startTime));
        int da = Integer.parseInt(day.format(startTime));
        int ye1 = Integer.parseInt(year.format(endTime));
        int mo1 = Integer.parseInt(months.format(endTime));
        int da1 = Integer.parseInt(day.format(endTime));
        LocalDate afterDate = LocalDate.of(ye, mo, da);
        LocalDate beforeDate = LocalDate.of(ye1, mo1, da1);
        long betweenMONTHS = ChronoUnit.MONTHS.between(beforeDate, afterDate);
        if (betweenMONTHS > 3){
            return true;
        }
        if (betweenMONTHS == 3 && (da1 + 1 != da)){
            return true;
        }
        return false;
    }
    /**
     * 时间处理
     * @param date
     * @return
     */
    public static Date dateChange(Date date,int hour,int min,int second){
        if (date == null) return  date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }
    /**
     * 时间处理
     * @param date
     * @return
     */
    public static Date dateStartChange(Date date){
       return dateChange(date,23,00,00);
    }
    public static Date dateEndChange(Date date){
        return dateChange(date,23,59,59);
    }
    public static Date dateZeroChange(Date date){
        return dateChange(date,00,00,00);
    }

    /**
     * 时间跨度判断-是否超过
     * @param begin
     * @param end
     * @param type  类型
     * @param num
     * @return
     */
    public static boolean overDate(Date begin,Date end ,int type ,int num){
        if(begin.compareTo(end) >0){
            throw new RuntimeException("时间不对");
        }
        Calendar cc =Calendar.getInstance();
        cc.setTime(begin);
        //设置时间
        cc.add(type,num);
        return cc.getTime().compareTo(end) <=0 ;
    }


    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.parse(date);
    }


}
