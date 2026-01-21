package com.qdbank.mall.request.third.mobile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName MobileNoticeReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class MobileNoticeReqDTO extends ThirdNoticeReqDTO{


   @ApiModelProperty("网信订单号")
   private String orderId;

   @ApiModelProperty("手机号")
   private String phone;

   @ApiModelProperty("充值类型 1:话费")
   private String type;

   @ApiModelProperty("结算时间，格式：yyyyMMddHHmmss")
   private String khSettleTime;

   @ApiModelProperty("结算价格，单位：元，保留四位小数")
   private String khSettlePrice;

   @ApiModelProperty("有透传信息的会在这个字段透传（不参与签名）")
   private String extContent;






}
