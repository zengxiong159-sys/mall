package com.qdbank.mall.request.specificationattribute;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SpecificationattributeListReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/22 17:14
 * @since 1.0.0
 */
@Data
public class SpecificationattributeListReqDTO extends PageParams {
    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号",hidden = true)
    private Long merchantNo;
}
