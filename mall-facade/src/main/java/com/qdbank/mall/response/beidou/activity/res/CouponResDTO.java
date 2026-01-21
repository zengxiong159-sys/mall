package com.qdbank.mall.response.beidou.activity.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zengxiong
 * @Description 权益列表
 * @Date 2021/11/24 17:52
 */
@Data
public class CouponResDTO {

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
     * 优惠券名称
     */
    private String couponName;

    /**
     * 专区id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long prefectureId;

    /**
     * 专区名称
     */
    private String prefectureName;

    /**
     * 优惠券面值
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠券面值")
    private BigDecimal couponAmount;


}
