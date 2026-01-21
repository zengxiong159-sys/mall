package com.qdbank.mall.request.syslog;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * SysLogEntityReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/29 10:14
 * @since 1.0.0
 */
@Data
public class SysLogEntityReqDTO {
    /**
     * 系统日志id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "系统日志id")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 用户操作
     */
    @ApiModelProperty(value = "用户操作")
    private String operation;
    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法")
    private String method;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    private String params;
    /**
     * 执行时长(毫秒)
     */
    @ApiModelProperty(value = "执行时长(毫秒)")
    private Long time;
    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    private String ip;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date createDate;
}
