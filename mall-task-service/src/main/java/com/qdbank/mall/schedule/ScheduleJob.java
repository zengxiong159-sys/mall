

package com.qdbank.mall.schedule;


import com.alibaba.fastjson.JSON;
import com.qdbank.alert.dto.AlertDTO;
import com.qdbank.alert.dto.WXAlertDTO;
import com.qdbank.alert.service.AlertServiceFactory;
import com.qdbank.mall.model.schedule.ScheduleDO;
import com.qdbank.mall.model.schedule.ScheduleJobLogDO;
import com.qdbank.mall.request.metrics.TaskMetricsUploadReqDTO;
import com.qdbank.mall.util.HttpUtils;
import com.qdbank.mall.util.IpUtils;
import com.qdbank.mall.util.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;
import java.util.Date;

import static com.qdbank.mall.constant.DatePattern.NORM_DATETIME_FORMAT;
import static com.qdbank.mall.constant.DatePattern.PURE_DATETIME_FORMAT;


/**
 * 定时任务
 *
 * @author ningyuehuai
 */
@DisallowConcurrentExecution
public class ScheduleJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ScheduleDO scheduleJob = (ScheduleDO) context.getMergedJobDataMap()
                .get(ScheduleDO.JOB_PARAM_KEY);
        //数据库保存执行记录
        ScheduleJobLogDO log = new ScheduleJobLogDO();
        log.setJobId(scheduleJob.getJobId());
        log.setLogId(Long.valueOf(PURE_DATETIME_FORMAT.format(new Date())));
        log.setBeanName(scheduleJob.getBeanName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        //任务开始时间
        long startTime = System.currentTimeMillis();

        //上送定时任务监控指标
        TaskMetricsUploadReqDTO taskMetricsUploadReqDTO = new TaskMetricsUploadReqDTO();
        String ip = IpUtils.getLocalHostAddress();
        try {
            String scheduleJobEsIndex = SpringContextUtils.applicationContext.getEnvironment().getProperty("es.index.schedule-job");
            taskMetricsUploadReqDTO.setIndex(scheduleJobEsIndex);
            taskMetricsUploadReqDTO.setJobName(scheduleJob.getBeanName());
            taskMetricsUploadReqDTO.setJobDesc(scheduleJob.getRemark());
            taskMetricsUploadReqDTO.setIp(ip);
            taskMetricsUploadReqDTO.setStartTime(NORM_DATETIME_FORMAT.format(new Date()));

            //执行任务
            logger.debug("任务准备执行，任务ID：" + scheduleJob.getJobId());

            Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            method.invoke(target, scheduleJob.getParams());

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes(times);
            //任务状态    0：成功    1：失败
            log.setStatus(0L);
            log.setIp(ip);

            //设置监控指标
            taskMetricsUploadReqDTO.setEndTime(NORM_DATETIME_FORMAT.format(new Date()));
            taskMetricsUploadReqDTO.setDuration(times + "");
            taskMetricsUploadReqDTO.setHasError("N");
            logger.debug("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            log.setTimes(times);

            //任务状态    0：成功    1：失败
            log.setStatus(1L);
            String errMsg = StringUtils.substring(e.toString(), 0, 1800);
            log.setError(errMsg);
            log.setIp(ip);
            taskMetricsUploadReqDTO.setErrorMsg(errMsg);
            taskMetricsUploadReqDTO.setEndTime(NORM_DATETIME_FORMAT.format(new Date()));
            taskMetricsUploadReqDTO.setHasError("Y");
            taskMetricsUploadReqDTO.setDuration(times + "");

            //发送告警信息
            AlertDTO alertReqDTO = new WXAlertDTO();
            alertReqDTO.setMessage("定时任务:" + scheduleJob.getBeanName() + "执行发生异常");
            try {
                AlertServiceFactory alertServiceFactory = SpringContextUtils.getApplicationContext().getBean(AlertServiceFactory.class);
                alertServiceFactory.getDefaultService().alert(alertReqDTO);
            } catch (Exception ex) {
                logger.error("ScheduleJob:{} invoke alert occur error", scheduleJob.getBeanName(), ex);
            }
        } finally {
            ScheduleService scheduleService = (ScheduleService) SpringContextUtils.getBean("scheduleServiceImpl");
            scheduleService.insertJobLog(log);

            //调用es接口上送定时任务监控指标数据
            try {
                String url = SpringContextUtils.applicationContext.getEnvironment().getProperty("es.url");
                ResponseEntity<String> postEsResult = HttpUtils.getRestTemplate().postForEntity(url, taskMetricsUploadReqDTO, String.class);
                logger.info("ScheduleJob:{} invoke es result:{}", scheduleJob.getBeanName(), JSON.toJSONString(postEsResult));
            } catch (Exception e) {
                logger.error("ScheduleJob:{} invoke es occur error", scheduleJob.getBeanName(), e);
            }
        }
    }
}
