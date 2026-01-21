package com.qdbank.mall.request.product;

import com.qdbank.mall.request.productpicurl.ProductDetailPicReqDTO;
import com.qdbank.mall.request.skustock.SkustockReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
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
public class ProductReqDTO implements Serializable {
    private static final long serialVersionUID = 2858756903288565382L;

    /**
     * 商品主图
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品图片RUL",required = true)
    private List<ProductPictureReqDTO> picUrls;

    @ApiModelProperty(value = "商品详情图片URL",required = true)
    private List<ProductDetailPicReqDTO> detailPicUrls;
    /**
     * 一级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "一级分类",required = true)
    private Long categoryId1;

    /**
     * 二级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "二级分类")
    private Long categoryId2;

    /**
     * 三级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "三级分类")
    private Long categoryId3;

    /**
     * 四级分类
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "四级分类")
    private Long categoryId4;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品名称",required = true)
    @NotBlank(message = "请输入商品名称")
    @Size(min = 1, max = 45, message = "商品名称长度必须在1~45之间")
    private String productName;


    /**
     * 商品描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品描述")
    @Size(max = 60, message = "商品描述最多不能超过60位")
    private String productDescription;

    /**
     * 运费模板编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费模板编号",required = true)
    private Long freightTemplateId;

    @ApiModelProperty(value = "规格信息",required = true)
    List<SkustockReqDTO>  skustocks;

    @ApiModelProperty("规格属性信息")
    private String attributeNames;

    @ApiModelProperty(value = "商品详情",required = true)
    private String productDetail;

    /**
     * 商户主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户主键")
    private Long merchantNo;

    /**
     * 商户姓名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户姓名")
    private String merchantName;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date updateTime;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createdBy;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人",hidden = true)
    private String updatedBy;

}
