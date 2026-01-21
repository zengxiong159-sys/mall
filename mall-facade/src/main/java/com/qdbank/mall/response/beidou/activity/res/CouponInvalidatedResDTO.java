package com.qdbank.mall.response.beidou.activity.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Author zengxiong
 * @Description
 * @Date 2021/12/13 11:44
 */
@Data
public class CouponInvalidatedResDTO {

    /**
     * 券商品编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long couponId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 是否已失效
     */
    private Boolean invalidated;

}
