package com.qdbank.mall.request.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UpdateMerchantReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 13:46
 * @Version 1.0
 **/
@Data
public class UpdateMerchantReqDTO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    @NotNull
    private Long merchantNo;
    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @Length(max = 50)
    @ApiModelProperty(value = "商户名称",required = true)
    private String merchantName;
    /**
     * 商户状态 0 未启用 1已启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户状态 0 未启用 1已启用",required = true)
    private Long status;

    /**
     * 账户名称：青岛银行对公账户名称
     *
     * @mbg.generated
     */
    @Length(max = 50)
    @ApiModelProperty(value = "账户名称：青岛银行对公账户名称",required = true)
    private String accountName;

    /**
     * 商户开户银行
     *
     * @mbg.generated
     */
    @Length(max = 50)
    @ApiModelProperty(value = "商户开户银行",required = true)
    private String bankName;

    /**
     * 开户银行卡号
     *
     * @mbg.generated
     */
    @Length(max = 30)
    @ApiModelProperty(value = "开户银行卡号")
    private String bankNo;

    /**
     * 管理员姓名
     *
     * @mbg.generated
     */
    @Length(max = 10)
    @ApiModelProperty(value = "管理员姓名",required = true)
    private String name;

    /**
     * 管理员身份证号
     *
     * @mbg.generated
     */
    @Length(max = 18)
    @ApiModelProperty(value = "管理员身份证号",required = true)
    private String idNo;

    /**
     * 管理员邮箱
     *
     * @mbg.generated
     */
    @Length(max = 50)
    @Email
    @ApiModelProperty(value = "管理员邮箱",required = true)
    private String email;

    /**
     * 管理员手机号
     *
     * @mbg.generated
     */
    @Length(max = 18)
    @ApiModelProperty(value = "管理员手机号",required = true)
    private String mobile;

    /**
     * 合同编号
     *
     * @mbg.generated
     */
    @Length(max = 30)
    @ApiModelProperty(value = "合同编号",required = true)
    private String agreementNo;

    /**
     * 合同有效期开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "合同有效期开始时间",required = true)
    private Date startTime;

    /**
     * 合同有效期结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "合同有效期结束时间",required = true)
    private Date endTime;

    /**
     * 备注
     *
     * @mbg.generated
     */
    @Length(max = 100)
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 原邮箱
     */
    @ApiModelProperty(value = "原邮箱")
    private String oldEmail;

    /**
     * 客服电话
     */
    @ApiModelProperty(value = "客服电话")
    private String supportPhone;

    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名")
    private String adminName;
}
