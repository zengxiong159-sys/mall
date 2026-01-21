package com.qdbank.mall.trade.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.mapper.trade.TradeFileDataDOMapper;
import com.qdbank.mall.model.trade.TradeFileDataDO;
import com.qdbank.mall.model.trade.TradeFileDataDOExample;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.refund.OrderRefundService;
import com.qdbank.mall.trade.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TradeServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 10:46
 * @Version 1.0
 **/
@Service
@Slf4j
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TradeFileDataDOMapper tradeFileDataDOMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRefundService orderRefundService;
    @Override
    public void insertTLFileData(Date date) {
        //先删除表中现有数据，支持定时任务重跑
        tradeFileDataDOMapper.deleteByCreateTime(date);
        List<TradeFileDataDO> orderList = orderService.selectByPaymentTime(date);
        List<TradeFileDataDO> orderRefundList = orderRefundService.selectHandFinishTime(date);
        if(CollUtil.isEmpty(orderList) && CollUtil.isEmpty(orderRefundList)) return;
        List<TradeFileDataDO> unioAll = new ArrayList<>();
        if(CollUtil.isNotEmpty(orderList)) unioAll.addAll(orderList);
        if(CollUtil.isNotEmpty(orderRefundList)) unioAll.addAll(orderRefundList);
        for(TradeFileDataDO tradeFileDataDO : unioAll){
            //固定商户名称
            tradeFileDataDO.setMerName("青岛银行积分商城");
            tradeFileDataDOMapper.insert(tradeFileDataDO);
        }
        log.info("日期：{} 通联对账交易记录条数:{}",DateUtil.format(date, DatePattern.NORM_DATE_PATTERN), unioAll.size());
    }
}
