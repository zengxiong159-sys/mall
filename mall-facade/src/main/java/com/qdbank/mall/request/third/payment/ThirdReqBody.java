package com.qdbank.mall.request.third.payment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Response
 *
 * @author hongjh
 * @date 2021/3/23 13:50
 * @since 1.0.0
 */
@Data
public class ThirdReqBody {

    @ApiModelProperty("第三方标识")
    private String channel;


}
