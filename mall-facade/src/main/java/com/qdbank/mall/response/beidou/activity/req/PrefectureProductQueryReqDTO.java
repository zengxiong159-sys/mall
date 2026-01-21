package com.qdbank.mall.response.beidou.activity.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author zengxiong
 * @Description 专区商品列表查询请求DTO
 * @Date 2021/11/29 13:43
 */
@Data
public class PrefectureProductQueryReqDTO {

    /**
     * 券id
     */
    @NotNull
    private Long couponId;

}
