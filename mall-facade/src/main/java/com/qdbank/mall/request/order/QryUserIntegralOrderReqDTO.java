package com.qdbank.mall.request.order;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @ClassName UserOrderReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/18 15:53
 * @Version 1.0
 **/
@Data
public class QryUserIntegralOrderReqDTO extends PageParams {


    @ApiModelProperty(value = "状态  -1->全部； 0->待支付；  1->待发货；3->已完成；5->退款")
    private Long status;
}
