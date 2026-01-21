package com.qdbank.mall.response.center;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class CenterResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;
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
    @DateTimeFormat(pattern= DatePattern.NORM_DATETIME_PATTERN)
    private Date endTime;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")

    private Date createTime;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
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

    /**
     * 内容详情
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "内容详情")
    private String contentDetail;
}
