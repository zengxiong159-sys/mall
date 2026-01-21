# 状态机
##配置--com.qdbank.mall.config.order
RealOrderStateMachineConfig--RealOrderStatus, RealOrderHandlerEvent
MobileOrderStateMachineConfig--MobileReChargePayOrderStatus, MobileReChargePayOrderEvent
IntegralOrderStateMachineConfig--IntegralOrderStatus, IntegralOrderEvent


#事件处理--对应状态机配置事件触发的映射方法--com.qdbank.mall.order.event
RealOrderEventHandler
MobileReChargePayOrderEventHandler
IntegralOrderEventHandler


##业务实现类--通用业务--com.qdbank.mall.order.business.handler
RealPaymentHandler
MobileRechargePaymenHandler
IntegralPaymentHandler