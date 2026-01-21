package com.qdbank.mall.ods;


import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface OdsService {
    @Transactional
    public void insertOdsOrder(Date date);
    @Transactional
    public void insertOdsRefundOrder(Date date);
    @Transactional
    public void insertOdsSmsCoupon(Date date);
    @Transactional
    public void insertUserCoupon(Date date);
    @Transactional
    public void insertProduct(Date date);
    @Transactional
    public void insertSkuStock(Date date);
}
