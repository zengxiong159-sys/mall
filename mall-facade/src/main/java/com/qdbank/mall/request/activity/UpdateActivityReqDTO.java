package com.qdbank.mall.request.activity;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName ActivityReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 16:52
 * @Version 1.0
 **/
@Data
public class UpdateActivityReqDTO {
    @NotNull
    @ApiModelProperty(value = "活动编号",required = true)
    private Long id;
    /**
     * 活动名称
     *
     * @mbg.generated
     */
    @Length(max = 24)
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
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;
    /**
     * 活动开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动结束时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;
    /**
     * 活动跳转url
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "活动跳转url")
    private String jumpUrl;

    @ApiModelProperty("图片更新标识：0 不更新 1更新")
    private int picUpdateFlag = 0;
}
