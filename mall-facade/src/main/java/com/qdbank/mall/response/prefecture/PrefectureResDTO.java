package com.qdbank.mall.response.prefecture;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PrefectureResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 11:16
 * @Version 1.0
 **/
@Data
public class PrefectureResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 专区名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区名称")
    private String prefectureName;

    /**
     * 专区描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区描述")
    private String prefectureDecription;

    /**
     * 优先级 数字越小级别越高
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优先级 数字越小级别越高")
    private Long prefectureLevel;

    /**
     * 专区启用时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区启用时间")
    private Date startTime;

    @ApiModelProperty(value = "专区状态 0 停用 1启用")
    private Long status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 专区启用时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "专区启用结束时间")
    private Date endTime;
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
    @ApiModelProperty(value = "商品数量")
    private Long productCount;

    @ApiModelProperty(value = "关联商品")
    List<ProductSkuResDTO> productSkuDOS;

    @ApiModelProperty(value = "专区类型：默认0:常规专区 1:达标专区")
    private Integer prefectureType;
    @ApiModelProperty(value = "展示状态 Y 隐藏 N 展示")
    private String hidden;

}
