package com.qdbank.mall.constant.payment.orderstatus;

import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsHolder;
import com.qdbank.mall.constant.payment.metrics.OrderRefundMetricsEnum;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public enum RealOrderStatus implements OrderStatusAware {

        INIT(null, CloseTypeEnum.INIT.closeType, new Long[]{RefundStatausEnum.INIT.refundStatus},true,"初始化",null),
        PREPARE_PAY(new Long[]{0L},CloseTypeEnum.INIT.closeType,new Long[]{RefundStatausEnum.INIT.refundStatus},true,"待支付",new MetricsHolder(MetricsEnum.CREATE_ORDER,null)),
        PAY_WAIT(null,null,null,false,"支付中-过渡",null),

        PAY_NOTIC_CHOICE(null,null,null,false,"支付通知选择-过度",null),

        PAY_SUCCESS(null,null,null,false,"支付成功-过度",new MetricsHolder(MetricsEnum.PAY_SUCESS,null)),
        PAY_FAIL(new Long[]{4L},2L,new Long[]{RefundStatausEnum.INIT.refundStatus},true,"支付失败",new MetricsHolder(MetricsEnum.PAY_ERROR,null)),

        REFUND(null,null,null,false,"支付成功后，已取消退款",new MetricsHolder(MetricsEnum.REFUND,OrderRefundMetricsEnum.ERROR)),

        STAY_DELIVER_GOODS(new Long[]{1L},CloseTypeEnum.INIT.closeType,new Long[]{RefundStatausEnum.INIT.refundStatus,3L,4L},true,"待发货",null),
        DELIVER_GOODS(new Long[]{2L},CloseTypeEnum.INIT.closeType,new Long[]{RefundStatausEnum.INIT.refundStatus,3L,4L},true,"已发货",null),
        HAS_FINISH(new Long[]{3L},CloseTypeEnum.INIT.closeType,new Long[]{RefundStatausEnum.INIT.refundStatus,3L,4L},true,"已完成",null),

       REVIEWED(new Long[]{1L,3L},CloseTypeEnum.INIT.closeType,new Long[]{0L},true,"取消-待审核",null),

        REVIEWED_CHOICE(null,null,null,false,"待审核-分支",null),
        REVOCATION(null,null,null,false,"退款申请撤销",null),

        REVIEWED_PASS(new Long[]{1L,3L},3L,new Long[]{1L},true,"审核通过",new MetricsHolder(MetricsEnum.REFUND,OrderRefundMetricsEnum.USER_REFUND)),
        REVIEWED_FAIL(new Long[]{1L,3L},CloseTypeEnum.INIT.closeType,new Long[]{3L},true,"审核不通过",null),
     //   REFUND_SUCCESS(new Long[]{1L,3L},null,2L,false,"退款通过"),
        CANCEL(new Long[]{4L},0L,new Long[]{RefundStatausEnum.INIT.refundStatus},true,"已取消",new MetricsHolder(MetricsEnum.USER_OUT_CLOSE,null)),
        TIMEOUT_CANCEL(new Long[]{4L},1L,new Long[]{RefundStatausEnum.INIT.refundStatus},true,"超时已取消",new MetricsHolder(MetricsEnum.TIME_OUT_CLOSE,null)),
        ;

        public static enum Status{
            PREPARE_PAY(0L,"待支付"),
            STAY_DELIVER_GOODS(1L,"待发货"),
            DELIVER_GOODS(2L,"已发货"),
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
       public Long[] refundStatus;
       public boolean bsnFlag;
       public MetricsHolder metricsHolder;

    RealOrderStatus(Long[] status, Long closeType, Long[] refundStatus, boolean bsnFlag, String msg,MetricsHolder metricsHolder){
            this.status=status;
            this.closeType=closeType;
            this.refundStatus=refundStatus;
            this.msg=msg;
            this.bsnFlag=bsnFlag;
            this.metricsHolder= metricsHolder;
    }

      public static RealOrderStatus getOrderStatus(Long status, Long closeType, Long refundStatus ){
            for(RealOrderStatus orderStatus : RealOrderStatus.values()){
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
                        if(!checkStatus(orderStatus.refundStatus,refundStatus)){
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


    public static void main(String[] args) {
        Long[] ss = null;
        System.out.println(Arrays.asList(ss));
    }

}
