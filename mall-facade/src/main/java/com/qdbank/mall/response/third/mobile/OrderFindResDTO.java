package com.qdbank.mall.response.third.mobile;

import com.qdbank.mall.response.third.ThirdResBody;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 准入标志
 * @ClassName AccessSignResDTO
 * @Description
 * @Author hongjh
 * @Date 2021/3/23 16:30
 * @Version 1.0
 **/
@Data
public class OrderFindResDTO {




    @ApiModelProperty("KH订单号")
    private String khOrderId;

    @ApiModelProperty("网信订单号")
    private String wxOrderId;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("充值状态")
    private String status;

    @ApiModelProperty("结算时间")
    private String khSettleTime;

    @ApiModelProperty("结算价格")
    private String khSettlePrice;

    @ApiModelProperty("充值失败原因")
    private String errMsg;








}
