package com.qdbank.mall.response.mobile;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName MobileSkuResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/5/7 13:57
 * @Version 1.0
 **/
@Data
public class UserMobileSkuResDTO {




    @ApiModelProperty(value = "历史充值手机号；若无，使用客户绑定手机号码")
    private MobileLocationResDTO historyRechargeMobile;


    @ApiModelProperty(value = "规格列表")
    private List<MobileSkuResDTO> mobileSkus;







}
