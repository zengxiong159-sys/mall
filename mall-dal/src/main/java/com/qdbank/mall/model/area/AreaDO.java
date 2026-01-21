package com.qdbank.mall.model.area;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
public class AreaDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 区域名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "区域名称")
    private String addressName;

    /**
     * 父级ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    /**
     * 排序
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "排序")
    private Long viewOrder;

    private static final long serialVersionUID = 1L;

}