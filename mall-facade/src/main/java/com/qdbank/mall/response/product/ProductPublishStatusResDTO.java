package com.qdbank.mall.response.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ProductPublishStatusResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/7/25 11:30
 * @Version 1.0
 **/
@Data
public class ProductPublishStatusResDTO {
    /**
     * 上架状态 0 待入库 1 已入库 2 未上架 3 已上架
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上架状态 0 待入库 1 已入库 2已上架")
    private Long publishStatus;
}
