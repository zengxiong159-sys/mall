package com.qdbank.mall.task.impl;

import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.task.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName PrefectureTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/6/4 16:16
 * @Version 1.0
 **/
@Service
@Slf4j
public class PrefectureTaskImpl implements ITaskService {
    @Autowired
    private PrefectureDOMapper prefectureDOMapper;
    @Override
    @Lock(name = "PrefectureTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        prefectureDOMapper.updateExpireStatus();
    }
}
