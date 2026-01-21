package com.qdbank.mall.model.freighttemplate;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FreighttemplateDO extends BaseDO implements Serializable {
    /**
     * 模板编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "模板编号")
    private Long freightTemplateId;

    /**
     * 商户编号-》商户表主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号-》商户表主键")
    private Long merchantNo;

    /**
     * 模板名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "模板名称")
    private String templateName;

    /**
     * 是否包邮 0 包邮 1 不包邮
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否包邮 0 包邮 1 不包邮")
    private Long freeFlag;

    /**
     * 计费规则 0 按件计费 1 按重量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "计费规则 0 按件计费 1 按重量")
    private Long chargeRule;

    /**
     * 运送方式 0 快递 1 其他
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运送方式 0 快递 1 其他")
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
    private BigDecimal defaultAddProductCharge;

    /**
     * 状态 0 启用 1 禁用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态 0 启用 1 禁用")
    private Long status;



    private static final long serialVersionUID = 1L;


}