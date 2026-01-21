package com.qdbank.mall.request.specificationattribute;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ParentSpecificationattributeReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/31 13:45
 * @since 1.0.0
 */
@Data
public class ParentSpecificationattributeReqDTO {
    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;
}
