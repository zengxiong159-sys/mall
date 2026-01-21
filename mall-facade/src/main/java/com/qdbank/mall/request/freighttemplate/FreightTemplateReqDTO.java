package com.qdbank.mall.request.freighttemplate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName FreighttemplateReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 13:44
 * @Version 1.0
 **/
@Data
public class FreightTemplateReqDTO {

    /**
     * 模板名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "模板名称", required = true)
    @NotBlank(message = "模板名称不允许未空")
    @Size(min = 1, max = 20, message = "模板名称长度必须在1~20之间")
    private String templateName;

    /**
     * 是否包邮 0 包邮 1 不包邮
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否包邮 0 包邮 1 不包邮", required = true)
    private Long freeFlag;

    /**
     * 计费规则 0 按件计费 1 按重量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "计费规则 0 按件计费 1 按重量", required = true)
    private Long chargeRule;

    /**
     * 运送方式 0 快递 1 其他
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运送方式 0 快递 1 其他", required = true)
    private Long transferType;

    /**
     * 默认件数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "默认件数")
    private String defaultProductCount;

    /**
     * 默认金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "默认金额")
    @Digits(integer = 6, fraction = 2, message = "请输入有效的金额")
    private BigDecimal defaultCharge;

    /**
     * 增加件数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "增加件数")
    private String defaultAddProductCount;

    /**
     * 增加金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "增加金额")
    @Digits(integer = 6, fraction = 2, message = "请输入有效的金额")
    private BigDecimal defaultAddProductCharge;

    @ApiModelProperty(value = "指定地区设置运费")
    private String areaFreightTemplates;

    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键", hidden = true)
    private Long merchantNo;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updatedBy;
}
