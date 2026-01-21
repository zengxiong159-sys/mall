package com.qdbank.mall.constant.payment.event;

public enum IntegralOrderEvent implements OrderEventAware {




        CREATE("创建订单","订单创建事件"),

        PAY("支付申请","支付申请事件"),

        NOTIC("支付结果通知","支付完成通知事件"),

        CANCEL("取消订单","关闭订单"),

        TIMEOUT_CANCEL("超时取消订单","超时关闭订单"),

        REFUND("退款事件","退款操作"),
        USE("使用事件","使用操作"),
        RESET("重置事件","重置操作"),
        EXPIR("失效事件","失效操作"),

                ;

        public String eventName;
        public String desc;

        IntegralOrderEvent(String eventName,String desc){
                this.eventName=eventName;
                this.desc =desc;
        }





}
