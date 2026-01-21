package com.qdbank.mall.order;

import com.qdbank.mall.request.order.OrderQueryReqDTO;
import com.qdbank.mall.request.order.OrderUpdateStatusReqDTO;
import com.qdbank.mall.request.order.PayReqDTO;
import com.qdbank.mall.response.order.OrderQueryResDTO;
import com.qdbank.mall.response.order.OrderUpdateStatusResDTO;
import com.qdbank.mall.response.order.PayResDTO;

public interface PayService {
    /**
     * 订单支付接口
     * @param payReqDTO
     * @return
     */
    public PayResDTO payOrder(PayReqDTO payReqDTO);

    /**
     * 订单信息查询
     * @param orderQueryReqDTO
     * @return
     */
    public OrderQueryResDTO orderQuery(OrderQueryReqDTO orderQueryReqDTO);

    /**
     * 修改订单状态
     * @param orderUpdateStatusReqDTO
     * @return
     */
    public OrderUpdateStatusResDTO updateStatus(OrderUpdateStatusReqDTO orderUpdateStatusReqDTO);

    /**
     * 批量生成文件
     */
    public void batchMakeFile();
}
