package com.qdbank.mall.request.metrics;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 定时任务监控上送指标
 * @Date 2021/5/21 14:40
 */
@Data
public class TaskMetricsUploadReqDTO implements Serializable {
    private static final long serialVersionUID = -6541906434901584222L;

    /**
     * 索引
     */
    private String index;

    /**
     *  任务名称
     */
    private String jobName;

    /**
     *  任务描述
     */
    private String jobDesc;

    /**
     *  服务器ip
     */
    private String ip;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     *  耗时ms
     */
    private String duration;

    /**
     *  错误信息
     */
    private String errorMsg;

    /**
     * 失败标识 Y:失败 N:成功
     */
    private String hasError;
}
