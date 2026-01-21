package com.qdbank.mall.response.beidou.activity.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author zengxiong
 * @Description 通用加密请求参数DTO
 * @Date 2022/1/12 10:55
 */
@Data
public class CommonReqDTO {

    /**
     * 加密数据
     */
    @NotEmpty
    private String data;
}
