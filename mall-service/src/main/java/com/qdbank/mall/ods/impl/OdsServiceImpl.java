package com.qdbank.mall.ods.impl;

import com.qdbank.mall.mapper.ods.OdsDOMapper;
import com.qdbank.mall.ods.OdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName OdsServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/23 16:33
 * @Version 1.0
 **/
@Service
public class OdsServiceImpl implements OdsService {
    @Autowired
    private OdsDOMapper odsDOMapper;
    @Override
    public void insertOdsOrder(Date date) {
        odsDOMapper.deleteOdsOrder(date);
        odsDOMapper.insertOdsOrder(date);
    }

    @Override
    public void insertOdsRefundOrder(Date date) {
        odsDOMapper.deleteOdsOrderRefund(date);
        odsDOMapper.insertOdsRefundOrder(date);
    }

    @Override
    public void insertOdsSmsCoupon(Date date) {
        odsDOMapper.deleteOdsCoupon(date);
        odsDOMapper.insertOdsSmsCoupon(date);
    }

    @Override
    public void insertUserCoupon(Date date) {
        odsDOMapper.deleteOdsUserCoupon(date);
        odsDOMapper.insertUserCoupon(date);
    }

    @Override
    public void insertProduct(Date date) {
        odsDOMapper.deleteOdsProduct(date);
        odsDOMapper.insertProduct(date);
    }

    @Override
    public void insertSkuStock(Date date) {
        odsDOMapper.deleteOdsSkuStock(date);
        odsDOMapper.insertSkuStock(date);
    }
}
