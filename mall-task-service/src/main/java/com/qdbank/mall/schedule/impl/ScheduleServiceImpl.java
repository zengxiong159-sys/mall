package com.qdbank.mall.schedule.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.mapper.schedule.ScheduleDOMapper;
import com.qdbank.mall.mapper.schedule.ScheduleJobLogDOMapper;
import com.qdbank.mall.model.schedule.ScheduleDO;
import com.qdbank.mall.model.schedule.ScheduleDOExample;
import com.qdbank.mall.model.schedule.ScheduleJobLogDO;
import com.qdbank.mall.model.schedule.ScheduleJobLogDOExample;

import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.schedule.ScheduleJobLogLikeQueryReqDTO;
import com.qdbank.mall.request.schedule.ScheduleLikeQueryReqDTO;
import com.qdbank.mall.request.schedule.ScheduleReqDTO;
import com.qdbank.mall.request.schedule.UpdateScheduleReqDTO;
import com.qdbank.mall.response.schedule.ScheduleJobLogResDTO;
import com.qdbank.mall.response.schedule.ScheduleResDTO;
import com.qdbank.mall.schedule.ScheduleHelper;
import com.qdbank.mall.schedule.ScheduleService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ScheduleServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 16:13
 * @Version 1.0
 **/
@Service
public class ScheduleServiceImpl extends BaseServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleDOMapper scheduleDOMapper;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleJobLogDOMapper scheduleJobLogDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @PostConstruct
    public void init(){
        List<ScheduleDO> scheduleJobList = scheduleDOMapper.selectByExample(null);
        for(ScheduleDO scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleHelper.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleHelper.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleHelper.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }
    @Override
    public PageInfo<ScheduleResDTO> list(ScheduleLikeQueryReqDTO scheduleReqDTO) {
        PageHelper.startPage(scheduleReqDTO.getPageNum(),scheduleReqDTO.getPageSize());
        ScheduleDOExample scheduleDOExample = new ScheduleDOExample();
        scheduleDOExample.setOrderByClause("create_time desc");
        ScheduleDOExample.Criteria criteria = scheduleDOExample.createCriteria();
        if(StringUtils.isNotBlank(scheduleReqDTO.getBeanName())){
            criteria.andBeanNameLike("%"+scheduleReqDTO.getBeanName()+"%");
        }
        List<ScheduleDO> scheduleDOS = scheduleDOMapper.selectByExample(scheduleDOExample);
        List<ScheduleResDTO> list = new ArrayList<>();
        for(ScheduleDO scheduleDO : scheduleDOS){
            ScheduleResDTO scheduleResDTO = new ScheduleResDTO();
            BeanUtils.copyProperties(scheduleDO,scheduleResDTO);
            list.add(scheduleResDTO);
        }
        return super.getPageInfo(scheduleDOS,list);
    }

    @Override
    public int saveJob(ScheduleReqDTO scheduleJob) {
        ScheduleDO scheduleDO = new ScheduleDO();
        BeanUtils.copyProperties(scheduleJob,scheduleDO);
        scheduleDO.setJobId(super.generateId());
        scheduleDO.setStatus(Long.valueOf(Constant.ScheduleStatus.NORMAL.getValue()));
        umsAdminService.injectUserValue(scheduleDO);
        ScheduleHelper.createScheduleJob(scheduler,scheduleDO);
        return scheduleDOMapper.insert(scheduleDO);
    }

    @Override
    public int update(UpdateScheduleReqDTO scheduleJob) {
        ScheduleDO scheduleDO = new ScheduleDO();
        BeanUtils.copyProperties(scheduleJob,scheduleDO);
        ScheduleHelper.updateScheduleJob(scheduler,scheduleDO);
        umsAdminService.injectUpdateUserValue(scheduleDO);
        return scheduleDOMapper.updateByPrimaryKeySelective(scheduleDO);
    }

    @Override
    public void run(CommonIDReqDTO commonIDReqDTO) {
        ScheduleHelper.run(scheduler, scheduleDOMapper.selectByPrimaryKey(commonIDReqDTO.getId()));
    }

    @Override
    public int pause(CommonIDReqDTO commonIDReqDTO) {
        ScheduleHelper.pauseJob(scheduler, commonIDReqDTO.getId());
        ScheduleDO scheduleDO = new ScheduleDO();
        scheduleDO.setJobId(commonIDReqDTO.getId());
        scheduleDO.setStatus(Long.valueOf(Constant.ScheduleStatus.PAUSE.getValue()));
        umsAdminService.injectUpdateUserValue(scheduleDO);
       return scheduleDOMapper.updateByPrimaryKeySelective(scheduleDO);
    }

    @Override
    public int resume(CommonIDReqDTO commonIDReqDTO) {
        ScheduleHelper.resumeJob(scheduler, commonIDReqDTO.getId());
        ScheduleDO scheduleDO = new ScheduleDO();
        scheduleDO.setJobId(commonIDReqDTO.getId());
        scheduleDO.setStatus(Long.valueOf(Constant.ScheduleStatus.NORMAL.getValue()));
        umsAdminService.injectUpdateUserValue(scheduleDO);
        return scheduleDOMapper.updateByPrimaryKeySelective(scheduleDO);
    }

    @Override
    public int delete(CommonIDReqDTO commonIDReqDTO) {
        ScheduleHelper.deleteScheduleJob(scheduler,commonIDReqDTO.getId());
        return scheduleDOMapper.deleteByPrimaryKey(commonIDReqDTO.getId());
    }

    @Override
    public PageInfo<ScheduleJobLogResDTO> getJobLogs(ScheduleJobLogLikeQueryReqDTO scheduleJobLogLikeQueryReqDTO) {
        PageHelper.startPage(scheduleJobLogLikeQueryReqDTO.getPageNum(),scheduleJobLogLikeQueryReqDTO.getPageSize());
        ScheduleJobLogDOExample scheduleJobLogDOExample = new ScheduleJobLogDOExample();
        scheduleJobLogDOExample.setOrderByClause("create_time desc");
        if(scheduleJobLogLikeQueryReqDTO.getJobId() != null) scheduleJobLogDOExample.createCriteria().andJobIdEqualTo(scheduleJobLogLikeQueryReqDTO.getJobId());
        List<ScheduleJobLogDO> list = scheduleJobLogDOMapper.selectByExample(scheduleJobLogDOExample);
        PageInfo<ScheduleJobLogResDTO> pageInfo = new PageInfo (list);
        List<ScheduleJobLogResDTO> logResDTOS = new ArrayList<>();
        for(ScheduleJobLogDO scheduleJobLogDO : list){
            ScheduleJobLogResDTO scheduleJobLogResDTO = new ScheduleJobLogResDTO();
            BeanUtils.copyProperties(scheduleJobLogDO,scheduleJobLogResDTO);
            logResDTOS.add(scheduleJobLogResDTO);
        }
        pageInfo.setList(logResDTOS);
        return pageInfo;
    }
    @Override
    public int insertJobLog(ScheduleJobLogDO scheduleJobLogDO) {
        return scheduleJobLogDOMapper.insert(scheduleJobLogDO);
    }

    @Override
    public int deleteJobLog() {
        return scheduleJobLogDOMapper.deleteJogLog();
    }
}
