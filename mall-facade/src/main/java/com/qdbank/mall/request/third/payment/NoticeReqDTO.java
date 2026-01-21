package com.qdbank.mall.request.third.payment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName NoticeReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class NoticeReqDTO {

   @ApiModelProperty("")
   private String orderid;

   @ApiModelProperty("")
   private String timestamp;

   @ApiModelProperty("状态：S成功；L失败")
   private String status;

   @ApiModelProperty("")
   private String oriTransSer;

   @ApiModelProperty("")
   private String oriTransDt;

   @ApiModelProperty("")
   private String custId;

   @ApiModelProperty("")
   private String acctType;

   @ApiModelProperty("")
   private String queryType;

   @ApiModelProperty("")
   private String transe;

   @ApiModelProperty("")
   private String prodId;

   @ApiModelProperty("")
   private String transAmt;





}
