package com.qdbank.mall.task.impl;

import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.mapper.advertisement.AdvertisementDOMapper;
import com.qdbank.mall.task.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName AdvertisementTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/6/4 16:21
 * @Version 1.0
 **/
@Service
@Slf4j
public class AdvertisementTaskImpl implements ITaskService {
    @Autowired
    private AdvertisementDOMapper advertisementDOMapper;
    @Override
    @Lock(name = "AdvertisementTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        advertisementDOMapper.updateExpireStatus();
    }
}
