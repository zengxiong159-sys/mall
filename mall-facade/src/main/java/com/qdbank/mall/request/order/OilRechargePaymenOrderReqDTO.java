package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 手机充值请求
 * @ClassName OilRechargePaymenOrderReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/21 12:05
 * @Version 1.0
 **/
@Data
public class OilRechargePaymenOrderReqDTO extends VirtualPaymenOrderReqDTO{




    private static final long serialVersionUID = 1L;
}
