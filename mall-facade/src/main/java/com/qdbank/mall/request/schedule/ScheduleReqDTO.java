package com.qdbank.mall.request.schedule;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ScheduleReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 15:59
 * @Version 1.0
 **/
@Data
public class ScheduleReqDTO {
    @ApiModelProperty("bean名称")
    private String beanName;
    @ApiModelProperty("参数值：createTime:yyyy-MM-dd 格式")
    private String params;
    @ApiModelProperty(value = "时间表达式")
    private String cronExpression;
    @ApiModelProperty(value = "备注")
    private String remark;
}
