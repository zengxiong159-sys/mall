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
 * @ClassName TradeTotalCouponTaskImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/27 11:30
 * @Version 1.0
 **/
@Service
@Slf4j
public class TradeTotalCouponTaskImpl implements ITaskService {
    @Autowired
    @Qualifier("couponTotalFinancialServiceImpl")
    private FinancialService financialService;
    @Override
    @Lock(name = "TradeTotalCouponTaskImpl",leaseTime =30,timeUnit = TimeUnit.SECONDS)
    public void run(String params) {
        log.info("TradeTotalCouponTaskImpl 定时任务开始:{}",params);
        Long start = System.currentTimeMillis();
        financialService.insertTradeRecord(ITaskService.getDate(params));
        log.info("TradeTotalCouponTaskImpl 定时任务结束:{} 耗时:{}",params,(System.currentTimeMillis() - start));

    }
}
