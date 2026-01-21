package com.qdbank.mall.task.impl;

import com.alibaba.fastjson.JSON;
import com.qdbank.alert.dto.AlertDTO;
import com.qdbank.alert.dto.WXAlertDTO;
import com.qdbank.alert.service.AlertServiceFactory;
import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.TaskServiceEnum;
import com.qdbank.mall.request.metrics.TaskMetricsUploadReqDTO;
import com.qdbank.mall.task.ITaskService;
import com.qdbank.mall.task.TaskService;
import com.qdbank.mall.util.HttpUtils;
import com.qdbank.mall.util.IpUtils;
import com.qdbank.mall.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

import static com.qdbank.mall.constant.DatePattern.NORM_DATETIME_FORMAT;

/**
 * @ClassName TaskServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/10/12 9:41
 * @Version 1.0
 **/
@Service
@Slf4j
@RefreshScope
public class TaskServiceImpl implements TaskService {

    @Value(value = "${es.index.schedule-job:}")
    private String scheduleJobEsIndex;
    @Resource
    private AlertServiceFactory alertServiceFactory;
    @Value(value = "${es.url:}")
    private String esUrl;

    @Override
    public void task(Map<String,String> params) {
        log.info("params:{}",params);

        if(params != null && "Y".equals(params.get("switch"))){
            //上送定时任务监控指标
            TaskMetricsUploadReqDTO taskMetricsUploadReqDTO = new TaskMetricsUploadReqDTO();
            String ip = IpUtils.getLocalHostAddress();

            TaskServiceEnum taskServiceEnum = TaskServiceEnum.getServiceName(params.get("type"));
            long startTime = System.currentTimeMillis();
            try {
                if(TaskServiceEnum.getServiceName(params.get("type")) == null){
                    log.info("type值不正确：{}",params.get("type"));
                    return;
                }
                ITaskService iTaskService = SpringUtil.getBean(taskServiceEnum.getServiceName(), ITaskService.class);
                if (iTaskService == null) {
                    log.info("serviceName错误：{}",taskServiceEnum.getServiceName());
                    return ;
                }
                log.info("params:{} taskClass :{}",params, taskServiceEnum.getServiceName());

                //设置监控指标
                taskMetricsUploadReqDTO.setIndex(scheduleJobEsIndex);
                taskMetricsUploadReqDTO.setJobName(taskServiceEnum.getServiceName());
                taskMetricsUploadReqDTO.setJobDesc(taskServiceEnum.getDesc());
                taskMetricsUploadReqDTO.setIp(ip);
                taskMetricsUploadReqDTO.setStartTime(NORM_DATETIME_FORMAT.format(new Date()));

                Date date = ITaskService.getDate(JSON.toJSONString(params));
                log.info("date:{}",date);

                iTaskService.run(JSON.toJSONString(params));
                taskMetricsUploadReqDTO.setEndTime(NORM_DATETIME_FORMAT.format(new Date()));
                taskMetricsUploadReqDTO.setDuration(System.currentTimeMillis() - startTime + "");
                taskMetricsUploadReqDTO.setHasError("N");
            } catch (Exception e) {
                taskMetricsUploadReqDTO.setErrorMsg(StringUtils.substring(e.toString(), 0, 1800));
                taskMetricsUploadReqDTO.setEndTime(NORM_DATETIME_FORMAT.format(new Date()));
                taskMetricsUploadReqDTO.setHasError("Y");
                taskMetricsUploadReqDTO.setDuration(System.currentTimeMillis() - startTime + "");
            } finally {
                //调用es接口上送定时任务监控指标数据
                try {
                    ResponseEntity<String> postEsResult = HttpUtils.getRestTemplate().postForEntity(esUrl, taskMetricsUploadReqDTO, String.class);
                    log.info("ScheduleJob:{} invoke es result:{}", taskServiceEnum.getDesc(), JSON.toJSONString(postEsResult));
                } catch (Exception e) {
                    log.error("ScheduleJob:{} invoke es occur error", taskServiceEnum.getDesc(), e);
                }
            }
        }
    }
}
