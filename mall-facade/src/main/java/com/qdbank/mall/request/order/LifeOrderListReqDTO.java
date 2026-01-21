package com.qdbank.mall.request.order;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @ClassName UserOrderReqDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/1/18 15:53
 * @Version 1.0
 **/
@Data
public class LifeOrderListReqDTO extends PageParams {

    @Pattern(regexp = "-1||0||3||4")
    @ApiModelProperty(value = "状态  -1->全部； 0->待支付；3->已完成；4->已关闭")
    private String status;
}
