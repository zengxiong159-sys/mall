package com.qdbank.mall.task.impl;

import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.task.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TradeDetailTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/27 11:29
 * @Version 1.0
 **/
@Service
@Slf4j
public class TradeDetailTaskImpl implements ITaskService {
    @Autowired
    @Qualifier("tradeDetailFinancialServiceImpl")
    private FinancialService financialService;
    @Override
    @Lock(name = "TradeDetailTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        log.info("TradeDetailTaskImpl 定时任务开始:{}",params);
        financialService.insertTradeRecord(ITaskService.getDate(params));
        log.info("TradeDetailTaskImpl 定时任务结束:{}",params);
    }
}
