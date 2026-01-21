package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName OrderCountReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/9/5 17:34
 * @Version 1.0
 **/
@Data
public class OrderCountReqDTO {
    @ApiModelProperty(value = "通联客户号")
    @NotBlank(message = "通联客户号不能为空")
    private String custNo;
}
