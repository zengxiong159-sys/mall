package com.qdbank.mall.request.oil;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @ClassName MerchantReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 13:43
 * @Version 1.0
 **/
@Data
public class OilLocationReqDTO implements Serializable {


    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$",message = "请填写正确的手机号码")
    @ApiModelProperty(value = "手机号码",required = true)
    private String rechargeMobile;




}
