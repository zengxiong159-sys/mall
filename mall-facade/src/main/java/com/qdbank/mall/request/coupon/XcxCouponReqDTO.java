package com.qdbank.mall.request.coupon;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CouponReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 15:06
 * @Version 1.0
 **/
@Data
public class XcxCouponReqDTO {


    @NotBlank
    @ApiModelProperty(value = "客户号")
    private String userId;

    @NotBlank
    @ApiModelProperty(value = "类型")
    private String type;


}
