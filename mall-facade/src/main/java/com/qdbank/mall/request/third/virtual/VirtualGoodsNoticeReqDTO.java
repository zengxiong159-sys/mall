package com.qdbank.mall.request.third.virtual;

import com.qdbank.mall.request.third.mobile.ThirdNoticeReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName VirtualGoodsNoticeReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class VirtualGoodsNoticeReqDTO  {

   @ApiModelProperty("用户侧订单号")
   private String cusOrderId	;
   @ApiModelProperty("类型")
   private String type;
   @ApiModelProperty("要充值的账号")
   private String accountId;
   @ApiModelProperty("充值状态")
   private String orderState	;
   @ApiModelProperty("修改时间")
   private String succTime	;
   @ApiModelProperty("失败原因")
   private String resultState	;
   @ApiModelProperty("商品ID号")
   private String kactivityId	;

   @ApiModelProperty("签名")
   private String sign	;







}
