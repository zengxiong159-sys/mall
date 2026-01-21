package com.qdbank.mall.model.areafreighttemplate;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AreafreighttemplateDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 运费模板编号->运费模板表主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费模板编号->运费模板表主键")
    private Long freightTemplateId;

    /**
     * 省份编号,逗号分隔
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "省份编号,逗号分隔")
    private String provinceIds;

    /**
     * 是否支持配送 0 支持 1 不支持
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否支持配送 0 支持 1 不支持")
    private Long transferFlag;

    /**
     * 件/单
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "件/单")
    private String countUnit;

    /**
     * 件/元
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "件/元")
    private String chargeUnit;

    /**
     * 续件
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "续件")
    private String addCount;

    /**
     * 续费
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "续费")
    private String addCharge;



    private static final long serialVersionUID = 1L;
}