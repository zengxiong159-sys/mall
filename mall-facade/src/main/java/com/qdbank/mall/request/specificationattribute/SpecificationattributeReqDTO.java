package com.qdbank.mall.request.specificationattribute;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName SpecificationattributeReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 9:49
 * @Version 1.0
 **/
@Data
public class SpecificationattributeReqDTO {

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
    @NotBlank(message = "请输入属性名称")
    @Size(min = 1, max = 10, message = "规格属性名长度必须在1~10之间")
    private String attributeName;

    /**
     * 属性值
     *
     * @mbg.generated
     */
    @Length(max = 10)
    @ApiModelProperty(value = "属性值",required = true)
    private String attributeValue;

}
