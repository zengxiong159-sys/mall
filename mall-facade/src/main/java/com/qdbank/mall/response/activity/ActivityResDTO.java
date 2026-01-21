package com.qdbank.mall.response.activity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ActivityResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 16:55
 * @Version 1.0
 **/
@Data
public class ActivityResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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

    /**
     * 活动结束时间
     *
     * @mbg.generated
     */
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
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

}
