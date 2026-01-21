package com.qdbank.mall.model.specificationattribute;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SpecificationattributeDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商户编号 商户表主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号 商户表主键")
    private Long merchantNo;

    /**
     * 父id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;

    /**
     * 属性名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "属性名称")
    private String attributeName;

    /**
     * 属性值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "属性值")
    private String attributeValue;
    @ApiModelProperty("规格属性状态标识 0 未上架 1 已上架")
    private Long status;
}