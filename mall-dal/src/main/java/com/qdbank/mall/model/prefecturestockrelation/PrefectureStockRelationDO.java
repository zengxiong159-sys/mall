package com.qdbank.mall.model.prefecturestockrelation;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class PrefectureStockRelationDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 专区id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区id")
    private Long prefectureId;

    /**
     * 商品id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;
    @ApiModelProperty(value = "商品优先级")
    private Long productLevel;

    private static final long serialVersionUID = 1L;
}