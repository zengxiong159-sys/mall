package com.qdbank.mall.order.impl;

import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderDetailService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.recharge.RechargeService;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderCouponInfoResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderMobileRechargeDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderProductInfoResDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @ClassName OrderMobileRechargeServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 11:54
 * @Version 1.0
 * 话费充值订单详情服务
 **/
@Service
public class OrderMobileRechargeServiceImpl implements OrderDetailService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RechargeService rechargeService;
    @Override
    public OrderBaseDetailResDTO orderDetail(OrderDO orderDO) {
        //订单信息
        OrderMobileRechargeDetailResDTO orderMobileRechargeDetailResDTO = new OrderMobileRechargeDetailResDTO();
        BeanUtils.copyProperties(orderDO,orderMobileRechargeDetailResDTO);

        orderMobileRechargeDetailResDTO.setRechargeMobile(orderDO.getRechargeMobile()+"");

        //商品信息
        OrderProductInfoResDTO orderProductInfoResDTO = new OrderProductInfoResDTO(); //orderService.getProductInfo(orderDO);
        orderProductInfoResDTO.setProductName(orderDO.getProductName());
        orderProductInfoResDTO.setProductSpData(orderDO.getProductName());
        orderProductInfoResDTO.setMerchantName(orderDO.getMerchantName());
        orderProductInfoResDTO.setProductCount(orderDO.getProductCount());
        orderProductInfoResDTO.setProductCash(orderDO.getProductCash());
        orderProductInfoResDTO.setProductIntegration(orderDO.getOrderIntegration());
        orderMobileRechargeDetailResDTO.setProductInfoResDTO(orderProductInfoResDTO);
        //优惠券信息
        orderMobileRechargeDetailResDTO.setCouponResDTO(orderService.getCouponInfoByUserCouponId(orderDO.getUserCouponId()));
        //充值记录信息
        orderMobileRechargeDetailResDTO.setProductType(1L);
        orderMobileRechargeDetailResDTO.setResDTOList(rechargeService.getRechargeStatus(orderDO.getOrderSn()));
        return orderMobileRechargeDetailResDTO;
    }
}
