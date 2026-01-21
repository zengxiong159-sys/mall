package com.qdbank.mall.model.userwhite;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class UserWhiteDO extends BaseDO implements Serializable {
    /**
     * 用户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户编号")
    private Long id;

    /**
     * 姓名： 0 男 1 女
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "姓名：0男1女")
    private String userName;

    /**
     * 性别
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "性别: M 男 F 女")
    private String gender;

    /**
     * 状态 0 启用 1 停用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态0 停用 1启用")
    private String status;

    /**
     * 手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 身份证号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "身份证号")
    private String idNo;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    private static final long serialVersionUID = 1L;


}