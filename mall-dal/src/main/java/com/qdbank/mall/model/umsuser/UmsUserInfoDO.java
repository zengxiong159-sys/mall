package com.qdbank.mall.model.umsuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class UmsUserInfoDO implements Serializable {
    /**
     * 用户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户编号")
    private Long id;

    /**
     * 手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

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
     * 用户类型： 0 行员 1 白名单 2 非行员
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户类型：0行员1白名单2非行员")
    private String userType;

    private static final long serialVersionUID = 1L;
}