package com.qdbank.mall.request.specificationattribute;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName DeleteSpecificationReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class DeleteSpecificationReqDTO {
    @ApiModelProperty(value = "规格编号")
    Long id;

    @ApiModelProperty("规格属性名称")
    private String attributeName;
}
