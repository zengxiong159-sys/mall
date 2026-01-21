package com.qdbank.mall.request.activity;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ActivityReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 16:52
 * @Version 1.0
 **/
@Data
public class ActivityReqDTO implements Serializable{



    /**
     * 活动名称
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 24)
    @ApiModelProperty(value = "活动名称",required = true)
    private String activityName;

    /**
     * 优先级
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "优先级",required = true)
    private Long activityLevel;



    @ApiModelProperty("活动时间：开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;
    @ApiModelProperty("活动时间：结束时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;

    /**
     * 活动跳转url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动跳转url")
    private String jumpUrl;

    /**
     * 图片url
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "活动图片",required = true)
    private MultipartFile file;
}
