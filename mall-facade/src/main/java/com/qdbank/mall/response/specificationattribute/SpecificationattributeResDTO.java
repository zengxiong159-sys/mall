package com.qdbank.mall.response.specificationattribute;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SpecificationattributeResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 9:49
 * @Version 1.0
 **/
@Data
public class SpecificationattributeResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;


    /**
     * 商户编号 商户表主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商户编号 商户表主键")
    private Long merchantNo;

    /**
     * 父id
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
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

    /**
     * 存放子级
     */
    @ApiModelProperty(value = "存放子级")
    List<SpecificationattributeResDTO> specificationattributeResDTOList;
}
