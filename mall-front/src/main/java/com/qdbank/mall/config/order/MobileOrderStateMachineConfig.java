package com.qdbank.mall.config.order;

import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.action.mobile.*;
import com.qdbank.mall.order.choice.mobile.MobileChargeNoticChoiceGuard;
import com.qdbank.mall.order.choice.mobile.MobileNoticChoiceGuard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * @author hongjh
 *
 */
@Configuration
@Scope("prototype")
@EnableStateMachineFactory(name = "mobileStateMachineFactory")
public class MobileOrderStateMachineConfig extends StateMachineConfigurerAdapter<MobileReChargePayOrderStatus, MobileReChargePayOrderEvent> {
    /**
     * 配置状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(MobileReChargePayOrderStatus.INIT)
                .choice(MobileReChargePayOrderStatus.PAY_NOTIC_CHOICE)
                .choice(MobileReChargePayOrderStatus.CHARGIN_CHOICE)
                .states(EnumSet.allOf(MobileReChargePayOrderStatus.class));
    }
    /**
     * 配置状态转换事件关系
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> transitions) throws Exception {
        transitions
                //待支付-支付中
                .withExternal()
                .source(MobileReChargePayOrderStatus.INIT).target(MobileReChargePayOrderStatus.PREPARE_PAY)
                .event(MobileReChargePayOrderEvent.CREATE)
                //待支付-支付中
                .and()
                .withExternal()
                .source(MobileReChargePayOrderStatus.PREPARE_PAY).target(MobileReChargePayOrderStatus.PAY_WAIT)
                .event(MobileReChargePayOrderEvent.PAY)

                //待支付-关闭
                .and()
                .withExternal()
                .source(MobileReChargePayOrderStatus.PREPARE_PAY).target(MobileReChargePayOrderStatus.CANCEL)
                .event(MobileReChargePayOrderEvent.CANCEL)

                //待支付-超时关闭
                .and()
                .withExternal()
                .source(MobileReChargePayOrderStatus.PREPARE_PAY).target(MobileReChargePayOrderStatus.TIMEOUT_CANCEL)
                .event(MobileReChargePayOrderEvent.TIMEOUT_CANCEL)

                //通知：支付中--支付通知
                .and()
                .withExternal()
                //TODO 支付状态
                .source(MobileReChargePayOrderStatus.PREPARE_PAY).target(MobileReChargePayOrderStatus.PAY_NOTIC_CHOICE)
                .event(MobileReChargePayOrderEvent.NOTIC)

                //分支处理：支付成功（分支）-支付失败（关闭订单）
                .and()
                .withChoice()
                .source(MobileReChargePayOrderStatus.PAY_NOTIC_CHOICE)
                .first(MobileReChargePayOrderStatus.PAY_SUCCESS, new MobileNoticChoiceGuard(),createMoile())
                .last(MobileReChargePayOrderStatus.PAY_FAIL, mobilePayFail())

                .and()
                .withExternal()
                .source(MobileReChargePayOrderStatus.CANCEL).target(MobileReChargePayOrderStatus.REFUND)
                .event(MobileReChargePayOrderEvent.NOTIC)

                .and()
                .withExternal()
                .source(MobileReChargePayOrderStatus.TIMEOUT_CANCEL).target(MobileReChargePayOrderStatus.REFUND)
                .event(MobileReChargePayOrderEvent.NOTIC)

                //充值通知：充值中--充值通知
                .and()
                .withExternal()
                .source(MobileReChargePayOrderStatus.CHARGIN).target(MobileReChargePayOrderStatus.CHARGIN_CHOICE)
                .event(MobileReChargePayOrderEvent.CHARGE_NOTICE)

                //分支处理：充值成功（分支）-充值失败（退款订单）
                .and()
                .withChoice()
                .source(MobileReChargePayOrderStatus.CHARGIN_CHOICE)
                .first(MobileReChargePayOrderStatus.CHARGIN_SUCCESS, new MobileChargeNoticChoiceGuard(),chargeSuccess())
                .last(MobileReChargePayOrderStatus.CHARGIN_FAIL, chargeFaile())


               ;


    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> config) throws Exception {
        config.withConfiguration().machineId(Constant.INTEGRAL_ORDER_MACHINE);
        super.configure(config);
    }


    @Bean
    public  MobileRechargeAction createMoile(){
        return new MobileRechargeAction();
    }

    @Bean
    public  RechargeSuccessAction chargeSuccess(){
        return new RechargeSuccessAction();
    }

    @Bean
    public  RechargeFailAction chargeFaile(){
        return new RechargeFailAction();
    }


    @Bean
    public  MobilePayFailAction mobilePayFail(){
        return new MobilePayFailAction();
    }


    @Bean
    public StateMachinePersister<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent, OrderDO> mobilPersister() {
        /**
         * 状态反转---
         */
        return new DefaultStateMachinePersister<>(new StateMachinePersist<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent, OrderDO>() {


            @Override
            public void write(StateMachineContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> context, OrderDO order) {
            }

            @Override
            public StateMachineContext<MobileReChargePayOrderStatus,MobileReChargePayOrderEvent> read(OrderDO order) {
                return new DefaultStateMachineContext<>(MobileReChargePayOrderStatus.getOrderStatus(order.getStatus(),order.getCloseType(),order.getRefundStatus()), null, null, null,null, Constant.MOBILE_RECHARGEPAY_ORDER_MACHINE);
            }
        });
    }


}
