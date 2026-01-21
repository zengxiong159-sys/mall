package com.qdbank.mall.request.specificationattribute;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName SpecificationExtendReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/14 15:50
 * @Version 1.0
 **/
@Data
public class SpecificationExtendReqDTO {
   @ApiModelProperty("父级Id")
   private Long parentId;

   @ApiModelProperty("父级规格属性名称")
   private String parentAttributeName;

   @ApiModelProperty("规格属性名称,逗号分隔")
   private String attributeName;

}
