package com.qdbank.mall.model.freighttemplate;

import com.qdbank.mall.model.areafreighttemplate.AreafreighttemplateDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * FreighttemplateListDO
 *
 * @author shaoshihang
 * @date 2021/3/16 17:23
 * @since 1.0.0
 */
@Data
public class FreighttemplateListDO extends FreighttemplateDO{
    /**
     * 地区设置模板
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "地区设置模板")
    private List<AreafreighttemplateDO> areafreighttemplateDO;
}
