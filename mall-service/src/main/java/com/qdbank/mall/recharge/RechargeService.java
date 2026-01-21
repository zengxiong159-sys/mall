package com.qdbank.mall.recharge;

import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.response.order.RechargeStatusDetailResDTO;

import java.util.List;

public interface RechargeService {
    /**
     * 获取订单兑换状态记录
     * @param orderSn
     * @return
     */
    List<RechargeStatusDetailResDTO>  getRechargeStatus(String orderSn);
}
