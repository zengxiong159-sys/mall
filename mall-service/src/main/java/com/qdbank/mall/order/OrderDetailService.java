package com.qdbank.mall.order;

import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;

public interface OrderDetailService {
    public  OrderBaseDetailResDTO orderDetail(OrderDO orderDO);
}
