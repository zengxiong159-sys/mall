package com.qdbank.mall.constant.payment.orderstatus;

import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsHolder;
import com.qdbank.mall.constant.payment.metrics.OrderRefundMetricsEnum;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public enum VideoOrderStatus implements OrderStatusAware {

    INIT(null, CloseTypeEnum.INIT.closeType, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"初始化",null),
    PREPARE_PAY(new Long[]{0L},CloseTypeEnum.INIT.closeType, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"待支付",new MetricsHolder(MetricsEnum.CREATE_ORDER,null)),
    PAY_WAIT(null,null,null,false,"支付中-过渡",null),

    PAY_NOTIC_CHOICE(null,null,null,false,"支付通知选择-过度",null),

    PAY_SUCCESS(null,null,null,false,"支付成功-过度",new MetricsHolder(MetricsEnum.PAY_SUCESS,null)),
    PAY_FAIL(new Long[]{4L},2L, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"支付失败",new MetricsHolder(MetricsEnum.PAY_ERROR,null)),

    REFUND(null,null,null,false,"支付成功后，已取消退款",new MetricsHolder(MetricsEnum.REFUND, OrderRefundMetricsEnum.ERROR)),

    CHARGIN(new Long[]{6L},CloseTypeEnum.INIT.closeType, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"充值中",null),
    CHARGIN_CHOICE(null,null,null,false,"充值通知选择-过度",null),
    CHARGIN_SUCCESS(null,null,null,false,"充值成功-过度",null),
    CHARGIN_FAIL(new Long[]{4L},2L, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"充值失败",new MetricsHolder(MetricsEnum.REFUND,OrderRefundMetricsEnum.SYSTEM)),

    HAS_FINISH(new Long[]{3L},CloseTypeEnum.INIT.closeType, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"已完成",null),

    CANCEL(new Long[]{4L},0L, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"已取消",new MetricsHolder(MetricsEnum.USER_OUT_CLOSE,null)),
    TIMEOUT_CANCEL(new Long[]{4L},1L, com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus,true,"超时已取消",new MetricsHolder(MetricsEnum.TIME_OUT_CLOSE,null)),
    ;

    public static enum Status{
        PREPARE_PAY(0L,"待支付"),
        CHARGING(6L,"充值中"),
        HAS_FINISH(3L,"已完成"),
        HAS_CLOSE(4L,"已关闭"),
        ;
        public Long status;
        public String msg;

        Status(Long status,String msg){
            this.status=status;
            this.msg=msg;
        }

        public static Status getStatusByCode(Long code){
            for(Status ss: Status.values()){
                if(ss.status.equals(code)){
                    return ss;
                }
            }
            return null;
        }
    }

    public String msg;
    public Long[] status;
    public Long closeType;
    public Long refundStatus;
    public boolean bsnFlag;
    public MetricsHolder metricsHolder;

    VideoOrderStatus(Long[] status, Long closeType, Long refundStatus, boolean bsnFlag, String msg, MetricsHolder metricsHolder){
        this.status=status;
        this.closeType=closeType;
        this.refundStatus=refundStatus;
        this.msg=msg;
        this.bsnFlag=bsnFlag;
        this.metricsHolder=metricsHolder;
    }

    public static VideoOrderStatus getOrderStatus(Long status, Long closeType, Long refundStatus ){
        for(VideoOrderStatus orderStatus : VideoOrderStatus.values()){
            if(orderStatus.bsnFlag){
                //业务处理
                //状态判断
                if(!checkStatus(orderStatus.status,status)){
                    continue;
                }
                //关闭类型
                if(!checkSinglStatus(orderStatus.closeType,closeType)){
                    continue;
                }

                //审核类型
                if(!checkSinglStatus(orderStatus.refundStatus,refundStatus)){
                    continue;
                }
                return orderStatus;
            }
        }
        return null;
    }

    private static boolean checkSinglStatus(Long _status,Long status){
        if(_status==null ){
            return status==null;
        }else{
            return _status.equals(status);
        }
    }


    private static boolean checkStatus(Long[] _status,Long status){
        if(_status==null){
            _status = new Long[]{};
        }
        List<Long> list= Arrays.asList(_status);
        if(status!=null){
            //不为空，判断是否存在
            return list.contains(status);
        }else{
            return CollectionUtils.isEmpty(list);
        }
    }


}
