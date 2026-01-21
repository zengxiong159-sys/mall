package com.qdbank.mall.request.integral;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName IntegralBalance
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/23 16:24
 * @Version 1.0
 **/
@Data
public class IntegralBalanceReqDTO {
    @ApiModelProperty(value = "客户号")
    private String custId;
    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;
    @ApiModelProperty(value = "账户类型")
    private String acctType;


}
