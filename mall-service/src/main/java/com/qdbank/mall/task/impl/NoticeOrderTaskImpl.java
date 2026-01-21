package com.qdbank.mall.task.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.feign.MallFeignService;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.task.ITaskService;
import com.qdbank.mall.third.MallFrontService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NoticeOrderTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/6 9:29
 * @Version 1.0
 **/
@Slf4j
@Service
@RefreshScope
public class NoticeOrderTaskImpl implements ITaskService {
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private MallFeignService mallFeignService;
    @Override
    @Lock(name = "NoticeOrderTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        log.info("NoticeOrderTaskImpl 定时任务开始：{}",params);
        List<OrderDO> orderDOList = orderDOMapper.selectNotNoticeOrders();
        if(CollUtil.isNotEmpty(orderDOList)){
            orderDOList.stream().forEach(orderDO -> {
                Map<String,String> orderSNMap = new HashMap<>();
                orderSNMap.put("orderSn",orderDO.getOrderSn());
                try {
                    OrderIDReqDTO orderIDReqDTO = new OrderIDReqDTO();
                    orderIDReqDTO.setOrderSn(orderDO.getOrderSn());
                    CommonResult commonResult = mallFeignService.qryOrder(orderIDReqDTO);
                    log.info("订单号:{}异步通知补单结果:{}",orderDO.getOrderSn(), JSON.toJSONString(commonResult));
                }catch (Exception e){
                    log.error("订单号:{}异步通知补单异常:{}",orderDO.getOrderSn(),e);
                }
            });
        }
        log.info("NoticeOrderTaskImpl 定时任务结束,记录条数{}",orderDOList == null ? "0" : orderDOList.size());
    }
}
