package com.qdbank.mall.request.third.payment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 准入标志
 * @ClassName AccessSignReqDTO
 * @Description
 * @Author hongjh
 * @Date 2021/3/23 16:30
 * @Version 1.0
 **/
@Data
public class AccessSignReqDTO extends ThirdReqBody {


    @ApiModelProperty("请求标志")
    private String flag;

    @ApiModelProperty("交易类型")
    private String getType;

    @ApiModelProperty("支付页面完成后跳转第三方url")
    private String reqURL;
    @ApiModelProperty(value = "标识 0 走新逻辑")
    private String payFlag;





}
