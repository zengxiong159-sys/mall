package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CallBackReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/20 11:41
 * @Version 1.0
 **/
@Data
public class CallBackReqDTO {
    @ApiModelProperty(value = "订单编号")
    private String orderid;
    @ApiModelProperty(value = "时间戳")
    private String timestamp;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "渠道流水号")
    private String oriTransSer;
    @ApiModelProperty(value = "渠道日期")
    private String oriTransDt;
    @ApiModelProperty(value = "通联客户号")
    private String custId;
    @ApiModelProperty(value = "积分账户类型")
    private String acctType;
    @ApiModelProperty(value = "支付类型")
    private String queryType;
    @ApiModelProperty(value = "积分调整值")
    private String transe;
    @ApiModelProperty(value = "商品编号")
    private String prodId;
    @ApiModelProperty(value = "交易金额")
    private String transAmt;
}
