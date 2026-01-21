package com.qdbank.mall.constant.payment.event;

/**
 * @author hongjh
 * @date 2021/3/31
 * 订单的流程操作事件
 */
public enum RealOrderHandlerEvent implements OrderEventAware {

        CREATE("创建订单","订单创建事件"),

        PAY("支付申请","支付申请事件"),

        NOTIC("支付结果通知","支付完成通知事件"),

        DELIVER_GOODS("发货事件",""),

        USER_CHECK("用户确认",""),

        APPLY_REFUND("申请退款","支付成功后的申请退款操作"),

        CANCEL_REFUND("撤销事件",""),

        APPROVAL("审批事件",""),

        CANCEL("取消订单","关闭订单"),

        TIMEOUT_CANCEL("超时取消订单","超时关闭订单"),

        REFUND("退款事件","退款操作"),

        ;

        public String eventName;
        public String desc;

         RealOrderHandlerEvent(String eventName, String desc){
                this.eventName=eventName;
                this.desc =desc;
        }





}
