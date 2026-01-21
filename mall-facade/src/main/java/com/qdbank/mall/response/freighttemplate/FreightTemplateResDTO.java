package com.qdbank.mall.response.freighttemplate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName FreighttemplateResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 13:43
 * @Version 1.0
 **/
@Data
public class FreightTemplateResDTO {
    /**
     * 模板编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "模板编号")
    private Long freightTemplateId;

    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商户编号")
    private Long merchantNo;

    /**
     * 模板名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "模板名称")
    private String templateName;

    /**
     * 是否包邮 0 包邮 1 不包邮
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否包邮 0 包邮 1 不包邮")
    private Long freeFlag;

    /**
     * 计费规则 0 按件计费 1 按重量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "计费规则 0 按件计费 1 按重量")
    private Long chargeRule;

    /**
     * 运送方式 0 快递 1 其他
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运送方式 0 快递 1 其他")
    private Long transferType;

    /**
     * 默认件数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "默认件数")
    private String defaultProductCount;

    /**
     * 默认金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "默认金额")
    private BigDecimal defaultCharge;

    /**
     * 增加件数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "增加件数")
    private String defaultAddProductCount;

    /**
     * 增加金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "增加金额")
    private BigDecimal defaultAddProductCharge;

    /**
     * 状态 0 启用 1 禁用
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态 0 启用 1 禁用")
    private Long status;

    @ApiModelProperty(value = "关联商品标识 0 未关联商品 1 关联商品")
    private Long relationFlag;
    @ApiModelProperty(value = "是否待入库 0否  1是")
    private Long publishStatus;

    @ApiModelProperty(value = "指定区域模板设置")
    private List<AreaFreightTemplateResDTO> areaFreightTemplates;
    @Data
    public static class AreaFreightTemplateResDTO{
        /**
         * 主键
         *
         * @mbg.generated
         */
        @JsonSerialize(using = ToStringSerializer.class)
        @ApiModelProperty(value = "主键")
        private Long id;

        /**
         * 运费模板编号
         *
         * @mbg.generated
         */
        @JsonSerialize(using = ToStringSerializer.class)
        @ApiModelProperty(value = "运费模板编号")
        private Long freightTemplateId;

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

    }
}
