package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName FreightFeeReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 18:49
 * @Version 1.0
 **/
@Data
public class FreightFeeReqDTO {
    @NotNull
    @ApiModelProperty(value = "收货省市码值")
    private String receiverProvince;

    @Min(value = 1)
    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @NotNull
    @ApiModelProperty(value = "商品对应模板号；PS：前端显示用，订单生成另查")
    private Long freightTemplateId;


}
