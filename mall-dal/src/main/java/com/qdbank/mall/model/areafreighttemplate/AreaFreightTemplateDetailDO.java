package com.qdbank.mall.model.areafreighttemplate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * AreaFreightTemplateDetail
 *
 * @author shaoshihang
 * @date 2021/3/22 10:21
 * @since 1.0.0
 */
@Data
public class AreaFreightTemplateDetailDO {
    /**
     * 模板id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "模板id")
    private Long freightTemplateId;
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long merchantNo;
}
