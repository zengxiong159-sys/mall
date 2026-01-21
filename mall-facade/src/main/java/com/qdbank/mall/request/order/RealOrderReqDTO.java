package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实物支付请求
 * @ClassName PaymentInKindOrderReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/1/23 12:05
 * @Version 1.0
 **/
@Data
public class RealOrderReqDTO extends CommonOrderReqDTO{

    private static final long serialVersionUID = 1L;


    /**
     * 商品编号
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "商品编号")
    private Long productId;


    /**
     * 规格编号
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "规格编号")
    private Long productSkuId;

    /**
     * 商品数量
     *
     * @mbg.generated
     */
    @Min(value = 1)
    @ApiModelProperty(value = "商品数量")
    private Long productCount;




    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    @NotNull
    @Length(max = 100)
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;

    /**
     * 收货人电话
     *
     * @mbg.generated
     */
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$",message = "请填写正确的手机号码")
    @ApiModelProperty(value = "收货人电话")
    private String receiverPhone;

    /**
     * 省份直辖市
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "省份直辖市")
    private String receiverProvince;

    /**
     * 城市
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "城市")
    private String receiverCity;

    /**
     * 区县
     *
     * @mbg.generated
     */
    @NotBlank
    @ApiModelProperty(value = "区县")
    private String receiverRegion;

    /**
     * 详细地址
     *
     * @mbg.generated
     */
    @NotEmpty
    @Length(max = 100)
    @ApiModelProperty(value = "详细地址")
    private String receiverDetailAddress;

    /**
     * 订单备注
     *
     * @mbg.generated
     */
    @Length(max = 200)
    @ApiModelProperty(value = "订单备注")
    private String note;
    @ApiModelProperty(value = "积分抵扣")
    private BigDecimal integralDeduct;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmount;
}
