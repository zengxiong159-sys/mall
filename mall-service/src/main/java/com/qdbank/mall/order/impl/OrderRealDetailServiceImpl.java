package com.qdbank.mall.order.impl;

import cn.hutool.core.collection.CollUtil;
import com.qdbank.mall.mapper.orderrefund.OrderRefundDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDOExample;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.order.OrderDetailService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderProductInfoResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderRealDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderRefundResDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderRealDetailServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 10:15
 * @Version 1.0
 * 实物订单详情服务接口
 **/
@Slf4j
@Service
public class OrderRealDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderRefundDOMapper orderRefundDOMapper;
    @Autowired
    private OrderService orderService;

    @Autowired
    private SkustockDOMapper skustockDOMapper;
    @Autowired
    private ReplaceService replaceService;
    @Override
    public OrderBaseDetailResDTO orderDetail(OrderDO orderDO) {
        //订单信息
        OrderRealDetailResDTO orderRealDetailResDTO = new OrderRealDetailResDTO();
        BeanUtils.copyProperties(orderDO,orderRealDetailResDTO);
        //商品信息
        OrderProductInfoResDTO orderProductInfoResDTO = orderService.getProductInfo(orderDO);

        //商品图片信息显示为当前订单的商品规格图片
        SkustockDO skustockDO = skustockDOMapper.selectByPrimaryKey(orderDO.getProductSkuId());
        if(skustockDO != null && StringUtils.isNotBlank(skustockDO.getSkuPicUrl())){
            orderProductInfoResDTO.setMailPicUrl(replaceService.replaceUrl(skustockDO.getSkuPicUrl()));
        }
        orderRealDetailResDTO.setProductInfoResDTO(orderProductInfoResDTO);
        //优惠券信息
        orderRealDetailResDTO.setCouponResDTO(orderService.getCouponInfoByUserCouponId(orderDO.getUserCouponId()));
        //退款信息
        List<OrderRefundDO> orderRefundDOs = this.getRefundDO(orderDO.getOrderSn());
        List<OrderRefundResDTO> orderRefundResDTOS = new ArrayList<>();
        if(CollUtil.isNotEmpty(orderRefundDOs)){
            for(OrderRefundDO orderRefundDO : orderRefundDOs){
                OrderRefundResDTO orderRefundResDTO = new OrderRefundResDTO();
                BeanUtils.copyProperties(orderRefundDO,orderRefundResDTO);
                orderRefundResDTOS.add(orderRefundResDTO);
            }
        }
        orderRealDetailResDTO.setOrderRefundResDTOs(orderRefundResDTOS);
        orderRealDetailResDTO.setProductType(0L);
        return orderRealDetailResDTO;
    }

    /**
     * 获取退款信息
     * @param orderSn
     * @return
     */
    private List<OrderRefundDO> getRefundDO(String orderSn){
        //退款信息
        OrderRefundDOExample orderRefundDOExample = new OrderRefundDOExample();
        orderRefundDOExample.createCriteria().andOrderSnEqualTo(orderSn).andRefundTypeNotEqualTo(2L);
        List<OrderRefundDO> orderRefundDOS = orderRefundDOMapper.selectByExample(orderRefundDOExample);
        return orderRefundDOS;
    }
}

