package com.qdbank.mall.request.order;

import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * @ClassName UserOrderReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/18 15:53
 * @Version 1.0
 **/
@Data
public class QryUserRealOrderReqDTO extends PageParams {


    @Pattern(regexp = "-1||0||1||2||3||5")
    @ApiModelProperty(value = "状态  -1->全部； 0->待支付；  1->待发货；3->已完成；")
    private String status;
}
