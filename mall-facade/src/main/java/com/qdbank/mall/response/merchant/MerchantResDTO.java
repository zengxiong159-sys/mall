package com.qdbank.mall.response.merchant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName MerchantResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 13:57
 * @Version 1.0
 **/
@Data
public class MerchantResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long merchantNo;


    /**
     * 商户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 商户状态 0 未启用 1已启用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户状态 0 未启用 1已启用")
    private Long status;

    /**
     * 账户名称：青岛银行对公账户名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "账户名称：青岛银行对公账户名称")
    private String accountName;

    /**
     * 商户开户银行
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户开户银行")
    private String bankName;

    /**
     * 开户银行卡号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "开户银行卡号")
    private String bankNo;

    /**
     * 管理员姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员姓名")
    private String adminName;

    /**
     * 管理员身份证号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员身份证号")
    private String idNo;

    /**
     * 管理员邮箱
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员邮箱")
    private String email;

    /**
     * 管理员手机号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "管理员手机号")
    private String mobile;

    @ApiModelProperty(value = "客服电话",required = true)
    private String supportPhone;
    /**
     * 合同编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "合同编号")
    private String agreementNo;

    /**
     * 合同有效期开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "合同有效期开始时间")
    private Date startTime;

    /**
     * 合同有效期结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "合同有效期结束时间")
    private Date endTime;

    /**
     * 备注
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;

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
}
