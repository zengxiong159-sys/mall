package com.qdbank.mall.request.third.payment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName RefundReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class RefundReqDTO extends ThirdReqBody {

    @ApiModelProperty("读卡方式")
    private String inptTpye 	;
    @ApiModelProperty("交易金额")
    private String transAmt	;
    @ApiModelProperty("币种")
    private String currNum	;
    @ApiModelProperty("核准代码")
    private String apprvCd	;
    @ApiModelProperty("订单号")
    private String ordId	;
    @ApiModelProperty("商品编号")
    private String prodId ;
    @ApiModelProperty("交易类型")
    private String getType;
    @ApiModelProperty("准入标志流水号")
    private String accessSignid;
    @ApiModelProperty("存储标识")
    private String accessSignFlag	;
    @ApiModelProperty("准入标志")
    private String accessSign	;
    @ApiModelProperty("渠道流水号")
    private String oriTransSer	;
    @ApiModelProperty("渠道日期")
    private String oriTransDt	;
    @ApiModelProperty("通联客户号")
    private String custId	;
    @ApiModelProperty("积分账户类型")
    private String acctType	;
    @ApiModelProperty("支付类型")
    private String queryType	;
    @ApiModelProperty("积分调整值")
    private String transe	;
    @ApiModelProperty("商户号")
    private String merchantNumber	;
    private String oriOrdId;


}
