package com.qdbank.mall.time.impl;

import com.qdbank.mall.model.TimeDO;
import com.qdbank.mall.time.TimeService;
import com.qdbank.mall.util.TimeUtil;
import org.springframework.stereotype.Service;

/**
 * @ClassName TimeServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/19 16:38
 * @Version 1.0
 **/
@Service
public class TimeServiceImpl implements TimeService {
    @Override
    public void setTime(TimeDO time) {
        if(time.getStartTime() != null && time.getEndTime() != null){
            time.setStartTime(TimeUtil.dateZeroChange(time.getStartTime()));
            time.setEndTime(TimeUtil.dateEndChange(time.getEndTime()));
        }
    }
}
