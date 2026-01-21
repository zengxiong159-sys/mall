package com.qdbank.mall.response.schedule;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ScheduleJobLogResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/31 9:43
 * @Version 1.0
 **/
@Data
public class ScheduleJobLogResDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("日志ID")
    private Long logId;
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("任务ID")
    private Long jobId;
    @ApiModelProperty("bean名称")
    private String beanName;
    @ApiModelProperty("参数值")
    private String params;
    @ApiModelProperty("任务执行状态：0 成功 1 失败")
    private Long status;
    @ApiModelProperty("错误信息")
    private String error;
    @ApiModelProperty("任务耗时")
    private Long times;
    @ApiModelProperty("执行时间")
    private Date createTime;
    @ApiModelProperty("任务执行的机器IP")
    private String ip;
}
