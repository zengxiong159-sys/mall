package com.qdbank.mall.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ClassName CommonStringReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/5 10:26
 * @Version 1.0
 **/
@Data
public class CommonStringReqDTO {
    @ApiModelProperty(value = "json字符串数组")
    @NotBlank
    private String jsonStr;
    @ApiModelProperty(value = "商品标识：默认0:常规商品 1:达标专属礼")
    @NotNull
    private Integer  identification;
    @ApiModelProperty(value = "商品编号")
    @NotNull
    private Long productId;

    @ApiModelProperty(value = "最多抵扣积分量")
    @JsonSerialize(using = ToStringSerializer.class)
    @Digits(integer = 8, fraction = 0, message = "请输入有效的数字")
    private Long maxIntegralDeduct;

    @ApiModelProperty(value = "最小抵扣积分量")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long minIntegralDeduct;

}
