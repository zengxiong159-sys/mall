package com.qdbank.mall.task.impl;

import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.paramsconfig.ParamsConfigDOMapper;
import com.qdbank.mall.paramsconfig.ParamsConfigService;
import com.qdbank.mall.response.paramsconfig.ParamsConfigResDTO;
import com.qdbank.mall.task.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName OrderStatusTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/6/10 15:58
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrderStatusTaskImpl implements ITaskService {
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private ParamsConfigService paramsConfigService;
    private static final String DELIVERY_TIME_OUT = "deliveryTimeOut";
    @Override
    @Lock(name = "OrderStatusTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        log.info("OrderStatusTaskImpl 定时任务开始：{}",params);
        ParamsConfigResDTO paramsConfigResDTO = paramsConfigService.queryByParamName(DELIVERY_TIME_OUT);
        int count = orderDOMapper.updateOrderStatus(Integer.valueOf(paramsConfigResDTO.getParamValue()));
        if(count > 0){
            log.info("更新自动收货记录条数：{}",count);
        }
        log.info("OrderStatusTaskImpl 定时任务结束：{}",params);
    }
}
