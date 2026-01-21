

package com.qdbank.mall.task;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qdbank.mall.util.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 定时任务接口，所有定时任务都要实现该接口
 *
 * @author ningyuehuai
 */
public interface ITaskService {

    /**
     * 执行定时任务接口
     *
     * @param params   参数，多参数使用JSON数据
     */
    void run(String params);
    static Date getDate(String params){
        Date date = new Date();
        if(!StringUtil.isEmpty(params)){
            JSONObject jsonObject = JSON.parseObject(params);
            String dateStr = (String)jsonObject.get("date");
            if(StringUtils.isNotBlank(dateStr)){
                date = DateUtil.parse(dateStr, DatePattern.NORM_DATE_PATTERN);
            }
        }
        return date;
    }
}