package com.qdbank.mall.response.third.mobile;

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
public class PhoneResDTO {


    @ApiModelProperty("运营商类型")
    private String supplierType;

    @ApiModelProperty("身份")
    private String provinceType;








}
