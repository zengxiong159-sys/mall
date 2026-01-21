package com.qdbank.mall.model.schedule;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScheduleDO extends BaseDO implements Serializable {


    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long jobId;

    /**
     * bean名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "bean名称")
    private String beanName;

    /**
     * 任务参数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "任务参数")
    private String params;

    /**
     * cron表达式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    /**
     * 任务状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "任务状态")
    private Long status;

    /**
     * 备注
     *
     * @mbg.generated
     */
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