package com.qdbank.mall.response.productpicurl;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductPicUrlResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2020/12/12 10:46
 * @Version 1.0
 **/
@Data
public class ProductPicUrlResDTO {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "商品编号")
    private Long productId;

    /**
     * 主图标识：0 主图 1 非主图
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主图标识：0 主图 1 非主图")
    private Long mainFlag;

    /**
     * 图片url地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片url地址")
    private String picUrl;

}
