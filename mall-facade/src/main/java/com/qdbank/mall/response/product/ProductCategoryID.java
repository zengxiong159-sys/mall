package com.qdbank.mall.response.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductCategoryID
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/7 10:48
 * @Version 1.0
 **/
@Data
public class ProductCategoryID {
    /**
     * 一级分类
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "一级分类")
    private Long categoryId1;

    /**
     * 二级分类
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "二级分类")
    private Long categoryId2;

    /**
     * 三级分类
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "三级分类")
    private Long categoryId3;

    /**
     * 四级分类
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "四级分类")
    private Long categoryId4;

}
