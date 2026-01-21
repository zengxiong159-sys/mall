package com.qdbank.mall.request.order;

import com.qdbank.mall.request.CommonIDReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OrderDetailsReqDTO
 *
 * @author shaoshihang
 * @date 2021/3/18 11:55
 * @since 1.0.0
 */
@Data
public class OrderDetailsReqDTO extends CommonIDReqDTO {
    /**
     * 商户编号
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商户编号",hidden = true)
    private Long merchantNo;
}
