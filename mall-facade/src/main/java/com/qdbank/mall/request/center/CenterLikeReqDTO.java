package com.qdbank.mall.request.center;

import cn.hutool.core.date.DatePattern;
import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CenterLikeReqDTO extends PageParams {
    /**
     * 配置类型： 1 新闻中心 2 小程序隐私协议 3 商城隐私协议
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "配置类型：1新闻中心2小程序隐私协议3商城隐私协议")
    private Long configyType;

    /**
     * 标题
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态0停用1启用")
    private String status;
    /**
     * 广告开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间：开始时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    /**
     * 广告结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间：结束时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;
}
