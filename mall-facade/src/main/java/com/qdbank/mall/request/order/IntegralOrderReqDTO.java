package com.qdbank.mall.request.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName IntegralOrderReqDTO
 * @Description 积分兑换请求数据
 * @Author hongjh
 * @Date 2021/1/23 12:05
 * @Version 1.0
 **/
@Data
public class IntegralOrderReqDTO extends CommonOrderReqDTO{


    /**
     * 用户优惠券编号
     *
     * @mbg.generated
     */
    @NotNull
    @ApiModelProperty(value = "待兑换优惠券编号")
    private Long exchangeCouponId;







    private static final long serialVersionUID = 1L;
}
