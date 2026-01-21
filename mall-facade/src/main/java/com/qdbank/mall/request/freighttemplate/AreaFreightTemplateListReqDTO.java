package com.qdbank.mall.request.freighttemplate;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * AreaFreightTemplateListReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/17 13:55
 * @since 1.0.0
 */
@Data
public class AreaFreightTemplateListReqDTO extends PageParams {

    /**
     * 状态 0 启用 1 禁用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态 0 启用 1 禁用 2所有")
    private Long status;

    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long merchantNo;

}
