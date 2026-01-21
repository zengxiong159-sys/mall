package com.qdbank.mall.response.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName GenerateOrderResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/1/12 9:36
 * @Version 1.0
 **/
@Data
public class GenerateOrderResDTO {

    /**
     * 跳转地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "跳转地址")
    private String jumpUrl;




}
