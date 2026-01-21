package com.qdbank.mall.request.freighttemplate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AreaFreightTemplateReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/16 16:51
 * @since 1.0.0
 */
@Data
public class AreaFreightTemplateReqDTO {
    @ApiModelProperty("指定区域模板id")
    private Long id;
    /**
     * 省份编号，逗号分隔
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "省份编号，逗号分隔")
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
    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
}
