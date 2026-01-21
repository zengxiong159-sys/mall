package com.qdbank.mall.task.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.download.DownloadDOExample;
import com.qdbank.mall.service.impl.FileDfsService;
import com.qdbank.mall.task.ITaskService;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DownloadTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/1/25 16:04
 * @Version 1.0
 **/
@Service
@Slf4j
public class DownloadTaskImpl implements ITaskService {
    @Autowired
    private DownloadDOMapper downloadDOMapper;
    @Autowired
    private FileDfsService fileDfsService;
    @Override
    @Lock(name = "DownloadTaskImpl",leaseTime =60,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        log.info("DownloadTaskImpl 定时任务开始:{}",params);
        Long start = System.currentTimeMillis();
        DownloadDOExample downloadDOExample = new DownloadDOExample();
        downloadDOExample.createCriteria().andCreateTimeLessThanOrEqualTo(TimeUtil.dateZeroChange(DateUtil.offsetDay(ITaskService.getDate(params),- 7))).andStatusEqualTo(1L);
        List<DownloadDO> downloadDOS = downloadDOMapper.selectByExample(downloadDOExample);
        if(CollUtil.isNotEmpty(downloadDOS)){
            downloadDOS.stream().forEach(downloadDO -> {
                fileDfsService.deleteFile(downloadDO.getFileGroup(),downloadDO.getFileUrl());
                downloadDO.setUpdateTime(new Date());
                downloadDO.setStatus(2L);
                downloadDOMapper.updateByPrimaryKeySelective(downloadDO);
            });
        }
        log.info("DownloadTaskImpl 定时任务结束 耗时:{}",(System.currentTimeMillis() - start));
    }
}
