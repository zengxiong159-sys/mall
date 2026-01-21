package com.qdbank.mall.request.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import sun.nio.cs.ext.MacArabic;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName MerchantReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 13:43
 * @Version 1.0
 **/
@Data
public class MerchantReqDTO implements Serializable {

    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 50)
    @ApiModelProperty(value = "商户名称",required = true)
    private String merchantName;


    /**
     * 账户名称：青岛银行对公账户名称
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 50)
    @ApiModelProperty(value = "账户名称：青岛银行对公账户名称",required = true)
    private String accountName;

    /**
     * 商户开户银行
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 50)
    @ApiModelProperty(value = "商户开户银行",required = true)
    private String bankName;

    /**
     * 开户银行卡号
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 30)
    @ApiModelProperty(value = "开户银行卡号",required = true)
    private String bankNo;

    /**
     * 管理员姓名
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 10)
    @ApiModelProperty(value = "管理员姓名",required = true)
    private String adminName;

    /**
     * 管理员身份证号
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 18)
    @ApiModelProperty(value = "管理员身份证号",required = true)
    private String idNo;

    /**
     * 管理员邮箱
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 50)
    @Email
    @ApiModelProperty(value = "管理员邮箱",required = true)
    private String email;

    /**
     * 管理员手机号
     *
     * @mbg.generated
     */
    @NotBlank
    @Length(max = 18)
    @ApiModelProperty(value = "管理员手机号",required = true)
    private String mobile;
    @NotBlank
    @Length(max = 15)
    @ApiModelProperty(value = "客服电话",required = true)
    private String supportPhone;
    /**
     * 合同编号
     *
     * @mbg.generated
     */
    @NotBlank
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
    @ApiModelProperty(value = "商户状态")
    private Long status;

}
