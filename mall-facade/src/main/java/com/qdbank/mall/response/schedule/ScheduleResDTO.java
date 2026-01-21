package com.qdbank.mall.response.schedule;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ScheduleResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 16:02
 * @Version 1.0
 **/
@Data
public class ScheduleResDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("任务id")
    private Long jobId;
    @ApiModelProperty("bean名称")
    private String beanName;
    @ApiModelProperty("参数值：createTime:yyyy-MM-dd 格式")
    private String params;
    @ApiModelProperty(value = "时间表达式")
    private String cronExpression;
    @ApiModelProperty(value = "定时任务状态 0 正常 1 暂停")
    private Long status;
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
}
