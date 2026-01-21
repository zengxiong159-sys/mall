package com.qdbank.mall.model.productpicurl;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ProductPicUrlDO extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 主图标识：0 主图 1 非主图
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主图标识：0主图1非主图")
    private Long mainFlag;

    /**
     * 图片url地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url地址")
    private String picUrl;

    /**
     * 文件名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件来源 0 规格图片 1 商品图册 2 商品详情图片
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件来源0规格图片1商品图册2商品详情图片")
    private String fileSource;
    /**
     * 文件组名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "文件组名")
    private String groupId;

    private static final long serialVersionUID = 1L;
}