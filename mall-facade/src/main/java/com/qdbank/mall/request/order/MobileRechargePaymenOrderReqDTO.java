package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 手机充值请求
 * @ClassName MobileRechargePaymenOrderReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/21 12:05
 * @Version 1.0
 **/
@Data
public class MobileRechargePaymenOrderReqDTO extends VirtualPaymenOrderReqDTO{


    /**
     * 充值手机号
     *
     * @mbg.generated
     */
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$",message = "请填写正确的手机号码")
    @ApiModelProperty(value = "充值手机号")
    private String rechargeMobile;

    /**
     * 号码归属地
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "号码归属地")
    private String mobileAddress;

    @NotNull
    @ApiModelProperty(value = "归属地省份码值描述")
    private String mobileAddressDesc;


    @Pattern(regexp = "CM||CU||CT||SW")
    @ApiModelProperty(value = "运营商码值CM:移动 CU:联通 CT:电信 SW:三网")
    private String supplierType;



    /**
     * 商品规格id
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "手机充值规格id")
    private Long mobileSkuId;





    private static final long serialVersionUID = 1L;
}
