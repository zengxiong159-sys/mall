package com.qdbank.mall.order;

import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.StateMachineError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.constant.payment.metrics.MetricsEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsHolder;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * @author hongjh
 */
@Component
@Slf4j
public class RealStateMachineUtil {

    @Resource
    @Qualifier("realStateMachineFactory" )
    StateMachineFactory<RealOrderStatus, RealOrderHandlerEvent> stateMachineFactory;

    @Autowired
    private StateMachinePersister<RealOrderStatus, RealOrderHandlerEvent,OrderDO> persister;

    @Autowired
    OrderService orderService;


    public Message initMessage(RealOrderHandlerEvent event, Map<String,Object> params){
        MessageBuilder messageBuilder =MessageBuilder.withPayload(event);
        if(!CollectionUtils.isEmpty(params)){
            for(String key : params.keySet()){
                messageBuilder.setHeader(key,params.get(key));
            }
        }
        messageBuilder.setHeader(Constant.STATE_MACHINE_ERROR,new StateMachineError());
        return messageBuilder.build();
    }


    public void execute(OrderDO order,Message<RealOrderHandlerEvent> message) {
        if(order==null && message.getPayload()== RealOrderHandlerEvent.CREATE){
            order = new OrderDO();
        }
        //获取客户号
        if(message.getPayload()!= RealOrderHandlerEvent.TIMEOUT_CANCEL&& message.getPayload()!= RealOrderHandlerEvent.NOTIC
                && message.getPayload()!= RealOrderHandlerEvent.APPROVAL
                && message.getPayload()!=RealOrderHandlerEvent.CANCEL_REFUND
        ){
            String custNo = UserContextHolder.getUserDetails().getCustNo();
            if(!custNo.equals(order.getCustNo()+"")){
                log.error("非本人操作[{}]-[{}]",custNo,order.getCustNo());
                throw new ApiException(ResultCode.NOT_BR_ERROR);
            }
        }

        StateMachine stateMachine =getStateMachine(order);
        String before  =stateMachine.getState().getId().toString();
        log.info("状态机状态执行前[{}],[{}]",order.getOrderSn(),stateMachine.getState().getId());

        try{
            stateMachine.sendEvent(message);
            String after  =stateMachine.getState().getId().toString();
            if(before.equals(after)){
                throw new ApiException(ResultCode.STATE_MACHINE_STATUS_FOUND);
            }
        }finally {
            StateMachineError error = (StateMachineError) message.getHeaders().get(Constant.STATE_MACHINE_ERROR);
            log.info("状态机状态执行后[{}],[{}],[{}]",order.getOrderSn(),stateMachine.getState().getId(),error.isHasError());
            if(error.isHasError()){
                Exception e = error.getException();
                if(e instanceof ApiException){
                    throw (ApiException)e;
                }else{
                    log.error("系统异常",e);
                    throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
                }
            }else{
                //正常运行
                Object obj=stateMachine.getState().getId();
                if(obj !=null && obj instanceof RealOrderStatus){
                    RealOrderStatus status = (RealOrderStatus) stateMachine.getState().getId();
                    if(status!=null && status.metricsHolder!=null){
                        if(RealOrderStatus.REFUND==status){
                            orderService.sendPost(order,new MetricsHolder(MetricsEnum.PAY_SUCESS,null),null);
                        }
                        orderService.sendPost(order,status.metricsHolder,null);
                    }
                }
            }
            stateMachine.stop();
        }
    }


    private StateMachine getStateMachine(OrderDO order){
    //    StateMachine machine = stateMachineThreadLocal.get();
        StateMachine machine = null;
        if (null == machine){
            machine = stateMachineFactory.getStateMachine(Constant.REAL_ORDER_MACHINE);
        }
        try {
            machine.start();
            //订单状态初始化至状态机
            persister.restore(machine,order);
        } catch (Exception e) {
            log.error("初始化状态机异常",e);
        }
        return machine;
    }




}
