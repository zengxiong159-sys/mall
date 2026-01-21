package com.qdbank.mall.config.order;

import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.constant.payment.orderstatus.IntegralOrderStatus;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.action.integral.CreateCouponAction;
import com.qdbank.mall.order.action.integral.IntegralPayFailAction;
import com.qdbank.mall.order.choice.integral.IntegralNoticChoiceGuard;
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
@EnableStateMachineFactory(name = "integralStateMachineFactory")
public class IntegralOrderStateMachineConfig extends StateMachineConfigurerAdapter<IntegralOrderStatus, IntegralOrderEvent> {
    /**
     * 配置状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<IntegralOrderStatus, IntegralOrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(IntegralOrderStatus.INIT)
                .choice(IntegralOrderStatus.PAY_NOTIC_CHOICE)
         //       .choice(IntegralOrderStatus.PAY_SUCCESS)
                .states(EnumSet.allOf(IntegralOrderStatus.class));
    }
    /**
     * 配置状态转换事件关系
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<IntegralOrderStatus, IntegralOrderEvent> transitions) throws Exception {
        transitions
                //待支付-支付中
                .withExternal()
                .source(IntegralOrderStatus.INIT).target(IntegralOrderStatus.PREPARE_PAY)
                .event(IntegralOrderEvent.CREATE)
                //待支付-支付中
                .and()
                .withExternal()
                .source(IntegralOrderStatus.PREPARE_PAY).target(IntegralOrderStatus.PAY_WAIT)
                .event(IntegralOrderEvent.PAY)

                //待支付-关闭
                .and()
                .withExternal()
                .source(IntegralOrderStatus.PREPARE_PAY).target(IntegralOrderStatus.CANCEL)
                .event(IntegralOrderEvent.CANCEL)

                //待支付-超时关闭
                .and()
                .withExternal()
                .source(IntegralOrderStatus.PREPARE_PAY).target(IntegralOrderStatus.TIMEOUT_CANCEL)
                .event(IntegralOrderEvent.TIMEOUT_CANCEL)

                //通知：支付中--支付通知
                .and()
                .withExternal()
                //TODO 支付状态
                .source(IntegralOrderStatus.PREPARE_PAY).target(IntegralOrderStatus.PAY_NOTIC_CHOICE)
                .event(IntegralOrderEvent.NOTIC)

                //分支处理：支付成功（分支）-支付失败（关闭订单）
                .and()
                .withChoice()
                .source(IntegralOrderStatus.PAY_NOTIC_CHOICE)
                .first(IntegralOrderStatus.PAY_SUCCESS, new  IntegralNoticChoiceGuard(),createCoupon())
                .last(IntegralOrderStatus.PAY_FAIL, integralPayFail())

                .and()
                .withExternal()
                .source(IntegralOrderStatus.CANCEL).target(IntegralOrderStatus.REFUND)
                .event(IntegralOrderEvent.NOTIC)

                .and()
                .withExternal()
                .source(IntegralOrderStatus.TIMEOUT_CANCEL).target(IntegralOrderStatus.REFUND)
                .event(IntegralOrderEvent.NOTIC)


              /*  .and()
                .withChoice()
                .source(IntegralOrderStatus.PAY_SUCCESS)
                .first(IntegralOrderStatus.PREPARE_USE, new  IntegralPaySuccessCloseChoiceGuard(), createCoupon())
                .last(IntegralOrderStatus.REFUND, integralRefund())*/



                //待使用--使用
                .and()
                .withExternal()
                .source(IntegralOrderStatus.PREPARE_USE).target(IntegralOrderStatus.HAS_FINISH)
                .event(IntegralOrderEvent.USE)

                //待使用--过期
                .and()
                .withExternal()
                .source(IntegralOrderStatus.PREPARE_USE).target(IntegralOrderStatus.EXPIRE)
                .event(IntegralOrderEvent.EXPIR)

                //使用--待使用
                .and()
                .withExternal()
                .source(IntegralOrderStatus.HAS_FINISH).target(IntegralOrderStatus.PREPARE_USE)
                .event(IntegralOrderEvent.RESET);


    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<IntegralOrderStatus, IntegralOrderEvent> config) throws Exception {
        config.withConfiguration().machineId(Constant.INTEGRAL_ORDER_MACHINE);
        super.configure(config);
    }


    @Bean
    public  CreateCouponAction createCoupon(){
        return new CreateCouponAction();
    }

    @Bean
    public  IntegralPayFailAction integralPayFail(){
        return new IntegralPayFailAction();
    }


    @Bean
    public StateMachinePersister<IntegralOrderStatus, IntegralOrderEvent, OrderDO> integralPersister() {
        /**
         * 状态反转---
         */
        return new DefaultStateMachinePersister<>(new StateMachinePersist<IntegralOrderStatus, IntegralOrderEvent, OrderDO>() {


            @Override
            public void write(StateMachineContext<IntegralOrderStatus, IntegralOrderEvent> context, OrderDO order) {
            }

            @Override
            public StateMachineContext<IntegralOrderStatus, IntegralOrderEvent> read(OrderDO order) {
                return new DefaultStateMachineContext<>(IntegralOrderStatus.getOrderStatus(order.getStatus(),order.getCloseType(),order.getRefundStatus()), null, null, null,null, Constant.INTEGRAL_ORDER_MACHINE);
            }
        });
    }


}
