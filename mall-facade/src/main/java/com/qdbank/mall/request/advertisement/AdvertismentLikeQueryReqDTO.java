package com.qdbank.mall.request.advertisement;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName AdvertismentReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 9:53
 * @Version 1.0
 **/
@Data
public class AdvertismentLikeQueryReqDTO  extends PageParams {

    /**
     * 广告模块编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告模块编号 0 首页顶通 1 快捷入口 2 积分兑换顶通")
    private Long moduleId;

    /**
     * 广告名称
     *
     * @mbg.generated
     */
    @Length(max = 20)
    @ApiModelProperty(value = "广告名称")
    private String advertiseName;

    /**
     * 广告状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告状态 0 停用 1 启用")
    private Long status;

    /**
     * 广告开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告开始时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    /**
     * 广告结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "广告结束时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;
}
