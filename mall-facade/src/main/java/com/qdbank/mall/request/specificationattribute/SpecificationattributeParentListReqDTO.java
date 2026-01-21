package com.qdbank.mall.request.specificationattribute;

import com.qdbank.mall.request.CommonIDReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SpecificationattributeParentListReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/22 17:25
 * @since 1.0.0
 */
@Data
public class SpecificationattributeParentListReqDTO extends CommonIDReqDTO {
    /**
     * 商户编号 商户表主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号 商户表主键")
    private Long merchantNo;
}
