package com.qdbank.mall.request.order;

import com.qdbank.mall.annotation.LockKey;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName CommonReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/23 18:49
 * @Version 1.0
 **/
@Data
public class OrderIDReqDTO implements LockKey {
    @NotBlank
    @ApiModelProperty(value = "订单号")
    private String orderSn;

    @Override
    public String keyName() {
        return orderSn;
    }
}
