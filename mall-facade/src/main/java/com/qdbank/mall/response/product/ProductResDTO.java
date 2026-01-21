package com.qdbank.mall.response.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qdbank.mall.response.productpicurl.ProductPicUrlResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/14 11:09
 * @Version 1.0
 **/
@Data
public class ProductResDTO extends ProductSkuResDTO{


    /**
     * 运费模板编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "运费模板编号")
    private Long freightTemplateId;

    @ApiModelProperty(value = "运费模板名称")
    private String templateName;
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
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;


    /**
     * 商品详情
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品详情")
    private String productDetail;

    @ApiModelProperty(value = "商品图册")
    List<ProductPicUrlResDTO> picUrls;

    @ApiModelProperty(value = "商品详情图片URL",required = true)
    private List<ProductDetailPictureResDTO> detailPicUrls;


    @ApiModelProperty(value = "运费金额")
    private BigDecimal freightAmount;

    @ApiModelProperty(value = "客服号码")
    private String customerServicePhone;

    @ApiModelProperty(value = "规格异常标识 Y:异常 N:正常")
    private String attributeExceptionFlag;

    @ApiModelProperty(value = "商品标识：默认0:常规商品 1:达标专属礼")
    private Integer  identification;
}
