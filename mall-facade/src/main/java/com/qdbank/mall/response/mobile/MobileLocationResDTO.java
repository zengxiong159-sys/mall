package com.qdbank.mall.response.mobile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName MobileSkuResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/5/7 13:57
 * @Version 1.0
 **/
@Data
public class MobileLocationResDTO {

    @ApiModelProperty(value = "手机号码")
    private String rechargeMobile;

    @ApiModelProperty(value = "号码归属地")
    private String mobileAddress;

    @ApiModelProperty(value = "归属地省份码值描述")
    private String mobileAddressDesc;


    @ApiModelProperty(value = "运营商码值CM:移动 CU:联通 CT:电信 SW:三网")
    private String supplierType;



}
