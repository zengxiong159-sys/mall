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
public class PrePayReqDTO extends ThirdReqBody {


    @ApiModelProperty("订单号")
    private String orderid;

    @ApiModelProperty("支付现金金额")
    private String transAmt;

    @ApiModelProperty("支付积点数量")
    private String transe;

    @ApiModelProperty("积分调整方向 E|兑换,R|退货")
    private String trxndirect;

    @ApiModelProperty("商户号")
    private String merchantNumber;

    @ApiModelProperty("商户账号")
    private String businessNumber;

    @ApiModelProperty("商品名称")
    private String tradeName;

    @ApiModelProperty("支付方式  A纯积分 B现金加积分 C纯现金")
    private String flag;

    @ApiModelProperty("商品编号")
    private String commodityCode;

    @ApiModelProperty("商户名称")
    private String nameBusiness;

    @ApiModelProperty("交易类型")
    private String getType;

    @ApiModelProperty("准入标志流水号")
    private String accessSignid;



    @ApiModelProperty("存储标识")
    private String accessSignFlag;

    @ApiModelProperty("准入标志")
    private String accessSign;

    @ApiModelProperty("前台回调商城URL")
    private String url;
    private String queryType;
    @ApiModelProperty(value = "标识 0 走新逻辑")
    private String payFlag;

}
