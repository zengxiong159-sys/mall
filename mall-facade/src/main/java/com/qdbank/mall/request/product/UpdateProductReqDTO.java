package com.qdbank.mall.request.product;

import com.qdbank.mall.request.productpicurl.ProductDetailPicReqDTO;
import com.qdbank.mall.request.skustock.SkustockReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @ClassName UpdateProductReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/14 18:51
 * @Version 1.0
 **/
@Data
public class UpdateProductReqDTO {
    @NotNull
    @ApiModelProperty(value = "商品编号")
    private  Long productId;

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
    @Size(max = 60, message = "商品描述最多不能超过60位")
    @ApiModelProperty(value = "商品描述")
    private String productDescription;

    /**
     * 运费模板编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费模板编号",required = true)
    private Long freightTemplateId;
    @ApiModelProperty(value = "规格信息",required = true)
    List<SkustockReqDTO> skustocks;
    @ApiModelProperty(value = "原始规格信息",required = true)
    List<SkustockReqDTO> oldSkustocks;
    @ApiModelProperty(value = "规格修改标识 0 新增 1修改")
    private Long updateFlag;
    @Length(max = 5000)
    @ApiModelProperty(value = "商品详情",required = true)
    private String productDetail;


    @ApiModelProperty("规格属性信息")
    String attributeNames;
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

}
