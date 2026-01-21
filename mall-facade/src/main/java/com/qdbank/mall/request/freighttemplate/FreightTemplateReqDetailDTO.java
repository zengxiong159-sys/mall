package com.qdbank.mall.request.freighttemplate;

import com.qdbank.mall.request.CommonIDReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * FreightTemplateReqDetailDTO
 *
 * @author shaoshihang
 * @date 2021/3/22 10:18
 * @since 1.0.0
 */
@Data
public class FreightTemplateReqDetailDTO extends CommonIDReqDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long merchantNo;
}
