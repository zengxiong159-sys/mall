package com.qdbank.mall.send;

import com.qdbank.mall.response.send.SendReturnResDTO;

import java.util.List;

public interface SendReturnService {
    /**
     * 根据订单号查询撤销发货记录流水
     * @param orderSN
     * @return
     */
    public List<SendReturnResDTO> querySendReturn(String orderSN);
}
