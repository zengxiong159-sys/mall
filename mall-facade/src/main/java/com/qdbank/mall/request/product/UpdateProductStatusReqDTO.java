package com.qdbank.mall.request.product;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateProductStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 15:09
 * @Version 1.0
 **/
@Data
public class UpdateProductStatusReqDTO {
    @NotNull
    @ApiModelProperty(value = "商品编号")
    private Long productId;
    /**
     * 上架状态 0 待入库 1 已入库 2 未上架 3 已上架
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "上架状态 0 待入库 1 已入库  2 已上架")
    private Long publishStatus;

    @ApiModelProperty(hidden = true)
    private String system;
}
