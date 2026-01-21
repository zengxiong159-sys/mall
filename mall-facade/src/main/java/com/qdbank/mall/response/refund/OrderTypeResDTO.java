package com.qdbank.mall.response.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OrderTypeResDTO
 *
 * @author shaoshihang
 * @date 2021/3/25 10:59
 * @since 1.0.0
 */
@Data
public class OrderTypeResDTO {

    /**
     * 数据列表
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "数据列表")
    private String data;
    /**
     * 状态 00:成功 99:失败 90:超时
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态")
    private String status;
    /**
     * 错误号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "错误号")
    private String errorCode;
    /**
     * 错误描述
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "错误描述")
    private String errorMsg;
}
