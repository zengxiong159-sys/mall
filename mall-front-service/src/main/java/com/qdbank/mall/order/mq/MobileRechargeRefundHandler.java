package com.qdbank.mall.order.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.core.rocketMQ.config.RocketMQProperties;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import com.qdbank.mall.order.business.handler.VirtualPaymenHandler;
import com.qdbank.mall.request.third.mobile.MobileNoticeReqDTO;
import com.qdbank.mall.request.third.mobile.ThirdNoticeReqDTO;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 话费充值失败退款操作
 * MobileRechargeHandler
 *
 * @author hongjh
 * @date 2021/5/24 18:24
 * @since 1.0.0
 */

@Component
@Slf4j
public class MobileRechargeRefundHandler  implements BsnMqAwar{



    @Autowired
    DefaultMQProducer defaultMQProducer;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    MobileRechargePaymenHandler mobileRechargePaymenHandler;


    @Autowired
    private RocketMQProperties properties;


    @Override
    public String getTopicHandlerType(){
        return   properties.getBsn().getRechargeRefund();
    }



     @Override
     public ConsumeConcurrentlyStatus handler(byte[] msg) {
            //单条
            ConsumeConcurrentlyStatus result = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            String _json = null;
            try{
                _json =new String(msg);
                log.info("退款订单[{}]",_json);
                MobileNoticeReqDTO req = JsonUtil.parseObject(_json,MobileNoticeReqDTO.class);
                //处理退款订单
                handlerRefundOrder(req);
            }catch (Exception e){
                log.error("执行出现异常[{}]",_json,e);
                result = ConsumeConcurrentlyStatus.RECONSUME_LATER;;
            }
            return result;
    }

    private void handlerRefundOrder(ThirdNoticeReqDTO req) {
        //step 1 查询订单信息
        OrderDO order = orderService.qryOrderBySn(req.getKhOrderId());

        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        ProductEnum productEnum =ProductEnum.getProductByType(order.getProductType());

        VirtualPaymenHandler virtualPaymenHandler=SpringContextUtils.getBean(productEnum.handlerName, VirtualPaymenHandler.class);

        virtualPaymenHandler.handlerNotic(order,req);
    }

    /**
     * 设置超时时间
     * @param req
     */
    public void refund(ThirdNoticeReqDTO req){
        try{
            String _json = JsonUtil.toJSONString(req);
            Message msg = new Message(properties.getBsn().getRechargeRefund(), "*", _json.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //1s、 5s、 10s、 30s、 1m、 2m、 3m、 4m、 5m、 6m、 7m、 8m、 9m、 10m、 20m、 30m、 1h、 2h
            //定时发送
            msg.setDelayTimeLevel(4);
            // 发送消息到一个Broker
            SendResult sendResult = defaultMQProducer.send(msg);
            // 通过sendResult返回消息是否成功送达
            log.info("退款推送结果[{}]-[{}]",_json, JsonUtil.toJSONString(sendResult));
        }catch (Exception e){
            log.error("MQ 发送异常",e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }


}
