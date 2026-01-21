package com.qdbank.mall.request.order;

import lombok.Data;

/**
 * @ClassName OrderUpdateStatusReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/8/10 15:24
 * @Version 1.0
 **/
@Data
public class OrderUpdateStatusReqDTO {
    /**状态**/
    private String status;
    /**订单编号**/
    private String ordId;
}
