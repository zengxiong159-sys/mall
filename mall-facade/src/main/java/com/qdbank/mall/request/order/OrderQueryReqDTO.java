package com.qdbank.mall.request.order;

import lombok.Data;

/**
 * @ClassName OrderQueryReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/8/9 14:42
 * @Version 1.0
 **/
@Data
public class OrderQueryReqDTO {
    private String orderid;
    private String queryType = "B";
}
