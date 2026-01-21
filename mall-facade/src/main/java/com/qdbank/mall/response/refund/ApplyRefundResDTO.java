package com.qdbank.mall.response.refund;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ApplyRefundResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/19 9:51
 * @Version 1.0
 **/
@Data
public class ApplyRefundResDTO {
    /**
     * 银联账务日期
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "银联账务日期")
    private String cupdDt;
    /**
     * 检索参考号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "检索参考号")
    private String etRefNo;
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
