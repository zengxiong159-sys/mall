package com.qdbank.mall.constant.payment.event;

public enum MobileReChargePayOrderEvent implements OrderEventAware {




        CREATE("创建订单","订单创建事件"),
        PAY("支付申请","支付申请事件"),
        NOTIC("支付结果通知","支付完成通知事件"),
        APPLY_REFUND("申请退款","支付成功后的申请退款操作"),
        CANCEL("取消订单","关闭订单"),
        TIMEOUT_CANCEL("超时取消订单","超时关闭订单"),

        CHARGE("充值事件","充值操作"),
        CHARGE_NOTICE("重置事件","重置操作"),
                ;

        public String eventName;
        public String desc;

        MobileReChargePayOrderEvent(String eventName, String desc){
                this.eventName=eventName;
                this.desc =desc;
        }





}
