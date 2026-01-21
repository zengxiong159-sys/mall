package com.qdbank.mall.request.center;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class CenterReqDTO {
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 配置类型： 1 新闻中心 2 小程序隐私协议 3 商城隐私协议
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "配置类型：1新闻中心2小程序隐私协议3商城隐私协议")
    @NotNull(message = "配置类型不能为空")
    private Long configyType;

    /**
     * 标题
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "标题")
    @Length(max = 100,message = "标题不能超过100个字符")
    @NotNull(message = "标题不能为空")
    private String title;

    /**
     * 状态 0 停用 1 启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态0停用1启用")
    @NotNull(message = "状态不能为空")
    private String status;

    /**
     * 生效开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "生效开始时间")
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date startTime;

    /**
     * 结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern=DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;
    /**
     * 内容详情
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "内容详情")
    private String contentDetail;
}
