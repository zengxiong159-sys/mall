package com.qdbank.mall.api;

import lombok.Getter;

@Getter
public enum TaskServiceEnum {
    ACTIVITY("1","activityTaskImpl","活动状态自动更新定时任务"),
    ADVERTISEMENT ("2","advertisementTaskImpl","广告状态自动更新定时任务"),
    INTEGRATIONCOUPON("3","integrationCouponTaskImpl","积分兑换券券过期定时任务"),
    MOBILERECHARGESTATUS("4","mobileRechargeStatusTaskImpl","话费订单自动更新定时任务"),
    NOTICEORDER("5","noticeOrderTaskImpl","异步通知回调补偿任务"),
    ODSORDER("6","odsOrderTaskImpl","充值订单下发通联大数据平台定时任务"),
    ODSPRODUCT("7","odsProductTaskImpl","商品信息落通联大数据平台定时任务"),
    ODSREFUNDORDER("8","odsRefundOrderTaskImpl","退款交易落通联大数据平台定时任务"),
    ODSSKUSTOCK("9","odsSkuStockTaskImpl","规格数据下发通联大数据平台"),
    ODSSMSCOUPON("10","odsSmsCouponTaskImpl","券商品信息定时任务"),
    ODSUSERCOUPON("11","odsUserCouponTaskImpl","指定用户持券信息数据定时任务"),
    ORDERSTATUS("12","orderStatusTaskImpl","订单收货状态定时任务"),
    ORDERTRADE("13","orderTradeTaskImpl","通联对账文件数据定时任务"),
    PREFECTURE("14","prefectureTaskImpl","专区活动状态自动更新定时任务"),
    PRODUCTCOUPON("15","productCouponTaskImpl","指定商品免费兑换券过期定时任务"),
    TRADEDETAIL("16","tradeDetailTaskImpl","交易明细定时任务"),
    TRADEMARKET("17","tradeMarketTaskImpl","营销费用汇总定时任务"),
    TRADETOTALCOUPON("18","tradeTotalCouponTaskImpl","优惠券汇总定时任务"),
    TRADETOTAL("19","tradeTotalTaskImpl","交易汇总定时任务"),
    BATCHCOUPON("20","integrationBatchCouponTaskImpl","行发批次券过期定时任务"),

    DESIGNATEDPREFECTURE("21","designatedPrefectureCouponTaskImpl","指定专区现金优惠券过期定时任务"),
    BATCHMAKEFILE("22","batchMakeFileTaskImpl","商城订单对账文件生成任务"),
    COUPONEXPIRE("23","couponExpireNoticeTaskImpl","优惠券到期提醒消息订阅"),
    COUPONSENDMQEXCEPTION("24","couponSendMqExceptionTaskImpl","优惠券发送失败写入MQ"),
    COUPONSENDMQ("25","couponSendMqTaskImpl","存量用户写入MQ"),
    DELETESCHEDUL("26","deleteSchedulLogTaskImpl","批量任务日志数据清理"),
    DESIGNATEDPREFECTUREBEIDOU("27","designatedPrefectureBeidouCouponTaskImpl","指定专区现金优惠券过期任务"),
    DETAILFILEDOWNLOAD("28","detailFileDownLoadTaskImpl","明细报表平台"),
    DOWNLOAD("29","downloadTaskImpl","删除fastdfs无关联商品图片"),
    FAILSENDFILE("30","failSendFileTaskImpl","数仓下载文件失败"),
    FIRSTBATCHSENDFILE("31","firstBatchSendFileTaskImpl","数仓第一批次文件"),
    HANDLEFILE("32","handleFileTaskImpl","手动执行数仓文件"),
    HISTORYFILE("33","historyFileTaskImpl","数仓历史文件"),
    HISTORYORDERINSERT("34","historyOrderInsertTaskImpl","超过一年的关闭订单"),
    HISTORYUPDATEORDER("35","historyUpdateOrderTaskImpl","订单掩码处理"),
    INVALIDATIONCOUPON("36","invalidationCouponTaskImpl","优惠券自动失效"),
    SECONDBATCHSENDFILE("37","secondBatchSendFileTaskImpl","数仓第二批次批量任务");
    private String taskType;
    private String serviceName;
    private String desc;
    private TaskServiceEnum(String taskType,String serviceName,String desc){
        this.taskType = taskType;
        this.serviceName = serviceName;
        this.desc = desc;
    }
    public static TaskServiceEnum getServiceName(String taskType){
        for(TaskServiceEnum serviceEnum : TaskServiceEnum.values()){
            if(serviceEnum.taskType.equals(taskType) ) return serviceEnum;
        }
        return null;
    }
}
