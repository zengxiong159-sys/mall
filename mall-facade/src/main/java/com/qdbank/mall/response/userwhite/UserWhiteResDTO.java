package com.qdbank.mall.response.userwhite;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserWhiteResDTO {
    /**
     * 用户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 姓名： 0 男 1 女
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "姓名")
    private String userName;

    /**
     * 性别
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "性别")
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

    @ApiModelProperty(value = "生效按钮是否隐藏： 0 隐藏 1 展示")
    private Integer hidden;

    @ApiModelProperty(value = "备注信息")
    private String remark;
}
