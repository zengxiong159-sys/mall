package com.qdbank.mall.response.third.payment;

import com.qdbank.mall.response.third.ThirdResBody;
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
public class AccessSignResDTO extends ThirdResBody {


    @ApiModelProperty("返回相应交易url")
    private String requrl;
    @ApiModelProperty("准入标志")
    private String accessSign;
    @ApiModelProperty("准入标志流水号")
    private String accessSignid;
    @ApiModelProperty("支付页面返回url")
    private String url;
    @ApiModelProperty(value = "准入流水号")
    private String id;







}
