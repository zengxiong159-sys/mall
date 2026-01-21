package com.qdbank.mall.response.area;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName FreightTemplateResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/1/21 9:49
 * @Version 1.0
 **/
@Data
public class FreightFeeResDTO {



    /**
     * 是否包邮 0 包邮 1 不包邮
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否包邮 0 包邮 1 不包邮")
    private Long freeFlag;


    /**
     * 是否包邮 0 包邮 1 不包邮
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "最终邮费金额")
    private BigDecimal freightAmount;

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
     * 运送方式 0 快递 1 其他
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "0 支持 1不支持")
    private Long transferFlag;

    /**
     * 指定地区命中
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "0未命中 1命中")
    private Long hitFlag;

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





}
