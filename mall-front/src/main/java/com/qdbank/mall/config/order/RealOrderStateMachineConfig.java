package com.qdbank.mall.config.order;

import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.action.real.*;
import com.qdbank.mall.order.choice.real.RealNoticChoiceGuard;
import com.qdbank.mall.order.choice.real.ReviewedChoiceGuard;
import lombok.extern.slf4j.Slf4j;
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
//@EnableStateMachine(name = "realorderMachine")
@EnableStateMachineFactory(name = "realStateMachineFactory")
@Slf4j
public class RealOrderStateMachineConfig extends StateMachineConfigurerAdapter<RealOrderStatus, RealOrderHandlerEvent> {


    /**
     * 配置状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<RealOrderStatus, RealOrderHandlerEvent> states) throws Exception {
        states
                .withStates()
                .initial(RealOrderStatus.INIT)
                .choice(RealOrderStatus.PAY_NOTIC_CHOICE)
        //        .choice(OrderStatus.PAY_SUCCESS)
                .choice(RealOrderStatus.REVIEWED_CHOICE)
                .states(EnumSet.allOf(RealOrderStatus.class));
    }

    /**
     * 配置状态转换事件关系
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<RealOrderStatus, RealOrderHandlerEvent> transitions) throws Exception {

        transitions
                //待支付-支付中
                .withExternal()
                .source(RealOrderStatus.INIT).target(RealOrderStatus.PREPARE_PAY)
                .event(RealOrderHandlerEvent.CREATE)
                //待支付-支付中
                .and()
                .withExternal()
                .source(RealOrderStatus.PREPARE_PAY).target(RealOrderStatus.PAY_WAIT)
                .event(RealOrderHandlerEvent.PAY)

                //待支付-关闭
                .and()
                .withExternal()
                .source(RealOrderStatus.PREPARE_PAY).target(RealOrderStatus.CANCEL)
                .event(RealOrderHandlerEvent.CANCEL)

                //待支付-超时关闭
                .and()
                .withExternal()
                .source(RealOrderStatus.PREPARE_PAY).target(RealOrderStatus.TIMEOUT_CANCEL)
                .event(RealOrderHandlerEvent.TIMEOUT_CANCEL)

                //通知：支付中--支付通知
                .and()
                .withExternal()
                .source(RealOrderStatus.PREPARE_PAY).target(RealOrderStatus.PAY_NOTIC_CHOICE)
                .event(RealOrderHandlerEvent.NOTIC)

                .and()
                .withExternal()
                .source(RealOrderStatus.CANCEL).target(RealOrderStatus.REFUND)
                .event(RealOrderHandlerEvent.NOTIC)

                .and()
                .withExternal()
                .source(RealOrderStatus.TIMEOUT_CANCEL).target(RealOrderStatus.REFUND)
                .event(RealOrderHandlerEvent.NOTIC)

                //分支处理：支付成功（分支）-支付失败（关闭订单）
                .and()
                .withChoice()
                .source(RealOrderStatus.PAY_NOTIC_CHOICE)
                .first(RealOrderStatus.PAY_SUCCESS, new RealNoticChoiceGuard(),stayDeliverGoods())
                .last(RealOrderStatus.PAY_FAIL, payFail())

                //分支处理：是否关闭-关闭（退款）|  未关闭，待发货
               /* .and()
                .withChoice()
                .source(OrderStatus.PAY_SUCCESS)
                .first(OrderStatus.STAY_DELIVER_GOODS, new RealPaySuccessCloseChoiceGuard() ,stayDeliverGoods())
                .last(OrderStatus.REFUND, realRefund())*/

               //发货
                .and()
                .withExternal()
                .source(RealOrderStatus.STAY_DELIVER_GOODS).target(RealOrderStatus.DELIVER_GOODS)
                .event(RealOrderHandlerEvent.DELIVER_GOODS)

                //用户确认
                .and()
                .withExternal()
                .source(RealOrderStatus.DELIVER_GOODS).target(RealOrderStatus.HAS_FINISH)
                .event(RealOrderHandlerEvent.USER_CHECK)

                //待发货--退款
                .and()
                .withExternal()
                .source(RealOrderStatus.STAY_DELIVER_GOODS).target(RealOrderStatus.REVIEWED)
                .event(RealOrderHandlerEvent.APPLY_REFUND)

                //已发货--退款
                .and()
                .withExternal()
                .source(RealOrderStatus.HAS_FINISH).target(RealOrderStatus.REVIEWED)
                .event(RealOrderHandlerEvent.APPLY_REFUND)

                //审核
                .and()
                .withExternal()
                .source(RealOrderStatus.REVIEWED).target(RealOrderStatus.REVIEWED_CHOICE)
                .event(RealOrderHandlerEvent.APPROVAL)

                //审核结果
                .and()
                .withChoice()
                .source(RealOrderStatus.REVIEWED_CHOICE)
                .first(RealOrderStatus.REVIEWED_PASS, new ReviewedChoiceGuard() ,reviewedSucess())
                .last(RealOrderStatus.REVIEWED_FAIL,reviewedFail())

                //撤销
                .and()
                .withExternal()
                .source(RealOrderStatus.REVIEWED).target(RealOrderStatus.REVOCATION)
                .event(RealOrderHandlerEvent.CANCEL_REFUND)

        ;

    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<RealOrderStatus, RealOrderHandlerEvent> config) throws Exception {
        config.withConfiguration().machineId(Constant.REAL_ORDER_MACHINE);
        super.configure(config);
    }

    @Bean
    public PayFailAction payFail(){
        return new PayFailAction ();
    }

    @Bean
    public ReviewedFailAction reviewedFail(){
        return new ReviewedFailAction ();
    }

    @Bean
    public ReviewedSucessAction reviewedSucess(){
        return new ReviewedSucessAction ();
    }

    @Bean
    public StayDeliverGoodsAction stayDeliverGoods(){
        return new StayDeliverGoodsAction ();
    }


    @Bean
    public StateMachinePersister<RealOrderStatus, RealOrderHandlerEvent, OrderDO> persister() {
        /**
         * 状态反转---
         */
        return new DefaultStateMachinePersister<>(new StateMachinePersist<RealOrderStatus, RealOrderHandlerEvent, OrderDO>() {


            @Override
            public void write(StateMachineContext<RealOrderStatus, RealOrderHandlerEvent> context, OrderDO order) {
                /*
                order.setStatus(context.getState().status);*/
            }

            @Override
            public StateMachineContext<RealOrderStatus, RealOrderHandlerEvent> read(OrderDO order) {
                return new DefaultStateMachineContext<>(RealOrderStatus.getOrderStatus(order.getStatus(),order.getCloseType(),order.getRefundStatus()), null, null, null,null, Constant.REAL_ORDER_MACHINE);
            }
        });
    }


}
