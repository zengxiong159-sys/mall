package com.qdbank.mall.model.user;

import com.qdbank.mall.model.BaseDO;
import com.qdbank.mall.model.role.RoleDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserInfoDO extends BaseDO implements Serializable {
    /**
     * ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 用户名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 图标
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 邮箱
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 昵称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 备注信息
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注信息")
    private String note;



    /**
     * 最后登录时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "最后登录时间")
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Long status;

    /**
     * 部门编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "部门编号")
    private Long deptno;

    /**
     * 手机号码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    @ApiModelProperty(value = "部门名称")
    private String deptname;

    @ApiModelProperty(value = "角色名称列表")
    private List<RoleDO> roleNames;
    private static final long serialVersionUID = 1L;

}