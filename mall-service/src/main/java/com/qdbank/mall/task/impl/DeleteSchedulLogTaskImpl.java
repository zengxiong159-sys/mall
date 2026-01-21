package com.qdbank.mall.task.impl;

import com.qdbank.mall.mapper.schedule.ScheduleJobLogDOMapper;
import com.qdbank.mall.task.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DeleteSchedulLogTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/6/4 16:40
 * @Version 1.0
 **/
@Service
@Slf4j
public class DeleteSchedulLogTaskImpl implements ITaskService {
    @Autowired
    private ScheduleJobLogDOMapper scheduleJobLogDOMapper;
    @Override
    public void run(String params) {
        scheduleJobLogDOMapper.deleteJogLog();
    }
}
