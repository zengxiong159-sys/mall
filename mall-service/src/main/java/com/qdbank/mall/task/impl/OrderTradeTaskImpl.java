package com.qdbank.mall.task.impl;

import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.task.ITaskService;
import com.qdbank.mall.trade.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName OrderTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 10:11
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrderTradeTaskImpl implements ITaskService {
    @Autowired
    private TradeService tradeService;
    @Override
    @Lock(name = "OrderTradeTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        log.info("OrderTradeTaskImpl 定时任务开始:{}",params);
        Long start = System.currentTimeMillis();
        tradeService.insertTLFileData(ITaskService.getDate(params));
        log.info("OrderTradeTaskImpl 定时任务结束:{} 耗时:{}",params,(System.currentTimeMillis() - start));

    }
}
