package com.qdbank.mall.schedule;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.model.schedule.ScheduleJobLogDO;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.schedule.ScheduleJobLogLikeQueryReqDTO;
import com.qdbank.mall.request.schedule.ScheduleLikeQueryReqDTO;
import com.qdbank.mall.request.schedule.ScheduleReqDTO;
import com.qdbank.mall.request.schedule.UpdateScheduleReqDTO;
import com.qdbank.mall.response.schedule.ScheduleJobLogResDTO;
import com.qdbank.mall.response.schedule.ScheduleResDTO;


public interface ScheduleService {

    public PageInfo<ScheduleResDTO> list(ScheduleLikeQueryReqDTO scheduleReqDTO);

    /**
     * 保存定时任务
     */
    int saveJob(ScheduleReqDTO scheduleJob);

    /**
     * 更新定时任务
     */
    int update(UpdateScheduleReqDTO scheduleJob);


    /**
     * 立即执行
     */
    void run(CommonIDReqDTO commonIDReqDTO);

    /**
     * 暂停运行
     */
    int pause(CommonIDReqDTO commonIDReqDTO);

    /**
     * 恢复运行
     */
    int resume(CommonIDReqDTO commonIDReqDTO);

    /**
     * 删除定时任务
     * @param commonIDReqDTO
     * @return
     */
    int delete(CommonIDReqDTO commonIDReqDTO);

    public PageInfo<ScheduleJobLogResDTO> getJobLogs(ScheduleJobLogLikeQueryReqDTO commonIDReqDTO);

    /**
     * 任务执行日志入库
     * @param scheduleJobLogDO
     * @return
     */
    public  int insertJobLog(ScheduleJobLogDO scheduleJobLogDO);

    public int deleteJobLog();
}
