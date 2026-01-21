package com.qdbank.mall.request.third.payment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName PrePayReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class QryPayReqDTO extends ThirdReqBody {

    @ApiModelProperty("请求标志")
    private String flag	;

    @ApiModelProperty("订单号")
    private String ordId 	;

    @ApiModelProperty("流水号")
    private String id	;
    @ApiModelProperty("交易类型")
    private String getType	;

    @ApiModelProperty("准入标志流水号")
    private String accessSignid	;

    @ApiModelProperty("存储标识")
    private String accessSignFlag 	;

    @ApiModelProperty("准入标志")
    private String accessSign	;
    @ApiModelProperty("支付类型")
    private String payFlage	;



}
