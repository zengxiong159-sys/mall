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
public class ThirdNoticeReqDTO {


   /**
    * KH 订单号
    */
   @ApiModelProperty("KH 订单号")
   private String khOrderId;

   @ApiModelProperty("充值状态")
   private String status	;

   @ApiModelProperty("充值失败原因")
   private String errMsg;









}
