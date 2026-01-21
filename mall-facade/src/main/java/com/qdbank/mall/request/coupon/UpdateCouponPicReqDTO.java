package com.qdbank.mall.request.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UpdateCouponPicReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/6 11:01
 * @Version 1.0
 **/
@Data
public class UpdateCouponPicReqDTO extends UpdateCouponReqDTO{

    @ApiModelProperty(value = "白名单文件",required = true)
    @NotNull
    private MultipartFile file;
}
