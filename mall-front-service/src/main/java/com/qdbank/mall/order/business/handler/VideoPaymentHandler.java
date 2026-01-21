package com.qdbank.mall.order.business.handler;

import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.model.order.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 视频充值
 * @ClassName VideoPaymenServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Component("videoPayment")
@Slf4j
public  class VideoPaymentHandler extends VirtualPaymenHandler {


    @Override
    public ProductEnum getProductType() {
        return ProductEnum.VIDEO;
    }

    @Override
    public void paySuccess(OrderDO order) {

    }

    @Override
    public void payFail(OrderDO order) {

    }


}
