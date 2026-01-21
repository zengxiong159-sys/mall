package com.qdbank.mall.model.activity;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ActivityDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 活动名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动名称")
    private String activityName;

    /**
     * 优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级")
    private Long activityLevel;

    /**
     * 活动开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;
    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;
    /**
     * 活动调换url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动调换url")
    private String jumpUrl;

    /**
     * 图片url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url")
    private String picUrl;

    /**
     * 活动状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动状态 0 停用 1 启用")
    private Long status;
    @ApiModelProperty(value = "文件组ID")
    private String groupId;
    private static final long serialVersionUID = 1L;
}