package com.qdbank.mall.model.skustock;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class SkustockDO extends BaseDO implements Serializable {
    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 市场价                  
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    /**
     * 建议售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "建议售价")
    private BigDecimal advicePrice;

    /**
     * 商品售价
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价")
    private BigDecimal productPrice;

    /**
     * 商品售价中现金金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中现金金额")
    private BigDecimal productCash;

    /**
     * 商品售价中积分值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品售价中积分值")
    private Long productIntegration;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 限购开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "限购开始时间")
    private Date promotionStartTime;

    /**
     * 限购结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "限购结束时间")
    private Date promotionEndTime;

    /**
     * 每人限购数量
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "每人限购数量")
    private Long promotionPerLimit;

    /**
     * 积分结算标识：0 不结算 1 需要结算
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "积分结算标识：0不结算1需要结算")
    private Long integrationPayFlag;

    /**
     * 商品库存
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品库存")
    private Long productStock;

    /**
     * 规格图片URL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格图片URL")
    private String skuPicUrl;

    /**
     * 商品锁定库存(已下单商品数量)
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品锁定库存(已下单商品数量)")
    private Long productLockStock;

    /**
     * 商品属性:JSON格式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品属性:JSON格式")
    private String productSpData;

    /**
     * 状态标识：0上架 1下架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态标识：0上架 1下架")
    private Long status;

    /**
     * 文件组名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件组名")
    private String groupId;
    private static final long serialVersionUID = 1L;

    /**
     * 商品属性(含父id数据),如:[{"形状_0":"方角_形状id"},{"重量_0":"500g_重量id"}]
     */
    @ApiModelProperty(value = "商品属性含id数据:JSON格式")
    private String productSpDataIncludePid;

    /**
     * 最多抵扣积分量
     */
    @ApiModelProperty(value = "最多抵扣积分量")
    private Long maxIntegralDeduct;
    @ApiModelProperty(value = "最少抵扣积分量")
    private Long minIntegralDeduct;
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "销量")
    private Long salCount;

}