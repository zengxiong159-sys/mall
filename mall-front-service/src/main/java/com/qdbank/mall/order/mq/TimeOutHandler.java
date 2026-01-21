package com.qdbank.mall.order.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.common.ParamsService;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.core.rocketMQ.config.RocketMQProperties;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OrderRefundErrorMq
 *
 * @author hongjh
 * @date 2021/4/25 18:24
 * @since 1.0.0
 */

@Component
@Slf4j
public class TimeOutHandler  implements BsnMqAwar  {



    @Autowired
    DefaultMQProducer defaultMQProducer;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    private RocketMQProperties properties;

    @Autowired
    ParamsService paramsService;


    @Override
    public String getTopicHandlerType(){
      return   properties.getBsn().getTimeOut();
    }

    @Override
    public ConsumeConcurrentlyStatus handler(byte[] msg) {
        String orderSn = null;
        try{
            orderSn =new String(msg);
            log.info("超时订单[{}]",orderSn);
            //处理超时订单
            handlerTimeOutOrder(orderSn);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }catch (Exception e) {
            log.error("执行出现异常[{}]", orderSn, e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }


    public void handlerTimeOutOrder(String orderSn) {
        //step 1 查询订单信息
        OrderDO order = orderService.qryNotNoticOrderByOrderSn(orderSn);

        //只针对待支付订单做处理
        if(order==null || 0L!=(order.getStatus())){
            return;
        }


        ProductEnum productEnum =ProductEnum.getProductByType(order.getProductType());
        //实物订单通知
        if(ProductEnum.PAYMENT_IN_KIND == productEnum){
            paymentService.handlerOrder(order, RealOrderHandlerEvent.TIMEOUT_CANCEL,null);
        }else if(ProductEnum.INTEGRAL==productEnum){
            //积分兑换
            paymentService.handlerOrder(order, IntegralOrderEvent.TIMEOUT_CANCEL,null);
        }else if(ProductEnum.MOBILE_RECHARGE==productEnum || ProductEnum.OIL_CARD==productEnum){
            //话费充值
            paymentService.handlerOrder(order, MobileReChargePayOrderEvent.TIMEOUT_CANCEL,null);
        }
    }

    /**
     * 设置超时时间
     * @param orderSn
     */
    public void setOrderTimeOut(String orderSn){
        try{
            Message msg = new Message(properties.getBsn().getTimeOut(), "*", orderSn.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //1s、 5s、 10s、 30s、 1m、 2m、 3m、 4m、 5m、 6m、 7m、 8m、 9m、 10m、 20m、 30m、 1h、 2h
            //定时发送
            msg.setDelayTimeLevel(MQHandler.getDelay(paramsService.getOrderTimeOut()));
            // 发送消息到一个Broker
            SendResult sendResult = defaultMQProducer.send(msg);
            // 通过sendResult返回消息是否成功送达
            log.info("推送结果[{}]", JsonUtil.toJSONString(sendResult));
        }catch (Exception e){
            log.error("MQ 发送异常",e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }


}
