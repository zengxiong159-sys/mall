package com.qdbank.mall.order.impl;

import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderDetailService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.recharge.RechargeService;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderCouponDetailResDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName OrderCouponDetailServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 11:48
 * @Version 1.0
 * 积分兑换券订单详情服务
 **/
@Service
public class OrderCouponDetailServiceImpl implements OrderDetailService {
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private OrderService orderService;
    @Override
    public OrderBaseDetailResDTO orderDetail(OrderDO orderDO) {
        //订单信息
        OrderCouponDetailResDTO orderCouponDetailResDTO = new OrderCouponDetailResDTO();
        orderCouponDetailResDTO.setProductType(4L);
        BeanUtils.copyProperties(orderDO,orderCouponDetailResDTO);
        //充值记录信息
        orderCouponDetailResDTO.setResDTOList(rechargeService.getRechargeStatus(orderDO.getOrderSn()));
        //商品信息
        orderCouponDetailResDTO.setCouponResDTO(orderService.getCouponInfo(orderDO.getExchangeCouponId()));
        return orderCouponDetailResDTO;
    }
}
