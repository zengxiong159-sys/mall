package com.qdbank.mall.request.orderimport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @ClassName OrderImportReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/26 13:52
 * @Version 1.0
 **/
@Data
public class OrderImportReqDTO {
    @ApiModelProperty(value = "存量数据文件")
    @NotNull
    private MultipartFile file;
    @ApiModelProperty(value = "存量数据类型：0 订单数据 1 退款数据 2 用户持券信息数据")
    @NotNull
    private Integer type;
}
