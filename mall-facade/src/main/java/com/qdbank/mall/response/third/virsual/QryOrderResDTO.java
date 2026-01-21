package com.qdbank.mall.response.third.virsual;

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
public class QryOrderResDTO {

    @ApiModelProperty(" 用户侧订单号	为用户提交的订单号(唯一)")
    private String cusOrderId	;
    @ApiModelProperty("    类型	充值帐号类型:1:平台帐号,2:手机号")
    private String type	        ;
    @ApiModelProperty("    要充值的账号	为用户提交的账号(唯一)")
    private String accountId	;
    @ApiModelProperty("    充值状态	1充值中，2失败，3成功")
    private String orderState	;
    @ApiModelProperty(" 修改时间	修改时间")
    private String succTime	    ;
    @ApiModelProperty("    失败原因	失败原因(如果成功则是操作成功)")
    private String resultState	;
    @ApiModelProperty("	商品ID号	只有卡密直充订单才会返回")
    private String kactivityId  ;

}
