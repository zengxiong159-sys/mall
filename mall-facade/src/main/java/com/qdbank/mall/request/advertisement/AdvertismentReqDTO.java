package com.qdbank.mall.request.advertisement;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName AdvertismentReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 9:53
 * @Version 1.0
 **/
@Data
public class AdvertismentReqDTO {

    /**
     * 广告模块编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告模块编号 0 首页顶通 1 快捷入口 2 积分兑换顶通",required = true)
    private Long moduleId;

    /**
     * 广告名称
     *
     * @mbg.generated
     */
    @Length(max = 20,min = 1)
    @ApiModelProperty(value = "广告名称",required = true)
    private String advertiseName;

    /**
     * 广告描述
     *
     * @mbg.generated
     */
    @Length(max = 100)
    @ApiModelProperty(value = "广告描述")
    private String advertiseDescription;

    /**
     * 优先级
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级",required = true)
    private Long advertismentLevel;

    @ApiModelProperty("广告时间：开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;
    @ApiModelProperty("广告时间：结束时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;

    /**
     * 广告跳转链接
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告跳转链接")
    private String jumpUrl;

    /**
     * 图片
     *
     * @mbg.generated
     */
    private MultipartFile file;
}
