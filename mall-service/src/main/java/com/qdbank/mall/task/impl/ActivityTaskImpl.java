package com.qdbank.mall.task.impl;

import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.mapper.activity.ActivityDOMapper;
import com.qdbank.mall.task.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ActivityTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/6/4 16:14
 * @Version 1.0
 **/
@Slf4j
@Service
public class ActivityTaskImpl implements ITaskService {
    @Autowired
    private ActivityDOMapper activityDOMapper;
    @Override
    @Lock(name = "ActivityTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        activityDOMapper.updateExpireStatus();
    }
}
