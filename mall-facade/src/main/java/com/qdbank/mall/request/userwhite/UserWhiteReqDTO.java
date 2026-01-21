package com.qdbank.mall.request.userwhite;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UserWhiteReqDTO {
    @ApiModelProperty(value = "用户白名单文件")
    private MultipartFile file;
}
