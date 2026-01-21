package com.qdbank.mall.constant;


/***
 * 定义自增id缓存的key
 */
public interface Constant {
    public static final String USER_LOGIN_COUNT = "USER_LOGIN_COUNT:";
    /**返回结果加密*/
    public static final String ENCRYPT = "1";

    /**返回结果不加密*/
    public static final String NOT_ENCRYPT = "0";
    /**
     * 白名单文件记录条数
     */
    public static final Long FILE_SIZE = 100000L;
    /**
     * 优惠券导出文件名称
     */
    public static final String COUPON_FILE_NAME = "优惠券白名单使用明细导出表-";

    /**
     * 退款查询明细导出文件名称
     */
    public static final String ORDER_REFUND_FILE_NAME = "退款查询明细导出表-";
    /**
     * 退款查询明细导出工作簿名称
     */
    public static final String ORDER_REFUND_SHEET_NAME = "退款查询明细-导出表";

    /**
     * 订单查询明细导出文件名称
     */
    public static final String ORDER_FILE_NAME = "订单查询明细导出表-";
    /**
     * 订单查询明细导出工作簿名称
     */
    public static final String ORDER_SHEET_NAME = "订单查询明细-导出表";

    /**
     * 导出文件名后缀
     */
    public static final String COUPON_FILE_SUFFIX = ".xlsx";

    /**
     * 商城交易汇总工作簿名称
     */
    public static final String ORDER_TOTAL_SHEET_NAME = "商城交易汇总";

    public static final String ORDER_TOTAL_FILE_NAME = "青岛银行对账汇总报表报表";

    /**
     * 商城交易明细报表
     */
    public static final String ORDER_DETAIL_FILE_NAME = "青岛银行对账明细报表日期:";
    /**
     * 交易明细工作簿名称
     */
    public static final String ORDER_DETAIL_SHEET_NAME = "青岛银行对账明细";

    /**
     * 商城交易明细报表
     */
    public static final String INTEGRATION_TOTAL_FILE_NAME = "商城积分结算汇总表报表日期：";
    /**
     * 工作簿名称
     */
    public static final String INTEGRATION_TOTAL_SHEET_NAME = "商城积分结算汇总表";

    /**
     * 商城指定商品兑换券明细报表
     */
    public static final String PRODUCT_TOTAL_FILE_NAME = "营销费用月汇总表报表日期：";
    /**
     * 工作簿名称
     */
    public static final String PRODUCT_TOTAL_SHEET_NAME = "营销费用月汇总表";

    //模板状态为禁用
    public static final Long TEMPLATE_STATUS_STOP = 1L;

    //商品类型：0实物
    public static final Long PRODUCT_TYPE_OBJECT = 0L;
    //商品类型：1话费充值
    public static final Long PRODUCT_TYPE_RECHARGE = 1L;

    //订单状态：(根据商品类型区分）实物订单：0待支付
    public static final Long  OBJECT_STATUS_PAID = 0L;
    //订单状态：(根据商品类型区分）实物订单：1待发货
    public static final Long  OBJECT_STATUS_DELIVERED = 1L;
    //订单状态：(根据商品类型区分）实物订单：2已发货
    public static final Long  OBJECT_STATUS_DELIVEREDING = 2L;
    //订单状态：(根据商品类型区分）实物订单：3已完成
    public static final Long  OBJECT_STATUS_SUCCESS = 3L;
    //订单状态：(根据商品类型区分）实物订单：4已关闭
    public static final Long  OBJECT_STATUS_CLOSE = 4L;
    //订单状态：(根据商品类型区分）实物订单：0待支付
    public static final Long  RECHARGE_STATUS_PAID = 0L;
    //订单状态：(根据商品类型区分）实物订单：1充值中
    public static final Long  RECHARGE_STATUS_DELIVEREDING = 1L;
    //订单状态：(根据商品类型区分）实物订单：3已完成
    public static final Long  RECHARGE_STATUS_SUCCESS = 3L;
    //订单状态：(根据商品类型区分）实物订单：4已关闭
    public static final Long  RECHARGE_STATUS_CLOSE = 4L;

    //上架状态 0待入库
    public static final Long PUBLISH_STATUS_STORAGEING = 0L;
    //上架状态 2已上架
    public static final Long PUBLISH_STATUS_SHELVES = 2L;
    //状态标识：0上架
    public static final Long STATUS_STORAGEING = 0L;
    //状态标识：1下架
    public static final Long STATUS_STOSHELF = 1L;
    //是否更改：1新增
    public static final Long INSERT = 1L;

    //退款状态0待审核1审核通过2退款成功3审核不通过
    public static final Long REFUND_STATUS_WAIT_REVIEWED = 0L;
    public static final Long REFUND_STATUS_REVIEWED_PASS = 1L;
    public static final Long REFUND_STATUS_SUCCESS = 2L;
    public static final Long REFUND_STATUS_NO_PASS = 3L;
    //退款类型0仅退款(无需退货)1退货退款
    public static final Long REFUND_TYPE = 0L;
    public static final Long REFUND_TYPE_GOOD = 1L;
    public static final Long  REFUND = 1L;
    /**
     * 商城支付
     */
    String THIRD_PAY_CHANNEL="mall";
    /**
     * 第三方正确码
     */
    String THIRD_SUCCESS_CODE ="0";


    /**
     * 行内已通知
     */
    String HAS_NOTIC_STATUS = "1";

    /**
     * 行内未通知
     */
    String NOT_NOTIC_STATUS = "0";


    /**
     * 订单编号前缀标识
     */
    String ORDER_PRE = "XZ";

    /**
     * 获取支付页面
     */
    String THIRD_GET_TYPE_PAY="00";

    /**
     * 退款
     */
    String THIRD_GET_TYPE_REFUND="01";

    /**
     * 获取订单状态
     */
    String THIRD_GET_TYPE_QUERY="02";


    /**
     * 积分兑换券
     */
    Long COUPON_TYPE_EXCHANGE=0L;

    /**
     * 指定兑换券
     */
    Long COUPON_TYPE_APPOINT=1L;

    /**
     * 订单字段
     */
    String ORDER_STR="order";

    /**
     * 实物订单状态机
     */
    String REAL_ORDER_MACHINE="realOrderMachine";

    /**
     * 积分订单状态机
     */
    String INTEGRAL_ORDER_MACHINE="integralOrderMachine";


    /**
     * 积分订单状态机
     */
    String MOBILE_RECHARGEPAY_ORDER_MACHINE="mobileReChargePayOrderMachine";

    /**
     * 状态机内部异常
     */
    String STATE_MACHINE_ERROR = "stateMachineError";

    /**
     * 超时处理
     */
    String ROCKET_TIME_OUT="ROCKET_TIME_OUT";

    /**
     * 充值失败退款处理
     */
    String ROCKET_MOBILE_RECHARGE_FAIL="ROCKET_MOBILE_RECHARGE_FAIL";

    /**
     * 查询操作
     */
    String ROCKET_MOBILE_QRY="ROCKET_MOBILE_QRY";



    /**
     *手机上架
     */
    Long MOBILE_SKU_UP=1L;

    /**
     *手机下架
     */
    Long MOBILE_SKU_DOWN=0L;


    /**
     * 订单业务参数枚举
     */
    Long ORDER_PARAMS_TYPE= 5L;

    /**
     * 超时
     */
    String CLOSE_TIME_OUT="closeTimeOut";

    /**
     * 退款超时
     */
    String REFUND_TIME_OUT="refundTimeOut";
    /**
     * 收货确认时间
     */
    String DELIVERY_TIME_OUT="deliveryTimeOut";

    /**
     * 客服电话
     */
    Long SUPPER_MOBILE_TYPE= 4L;
    /**
     * 积分客服电话
     */
    String SUPPER_MOBILE= "integral";

    String PAYMENT_ORDER_TYPE = "1";
    String REFUND_ORDER_TYPE = "2";
    String NEED_BALANCE = "3";
    /**
     * 积分结算标识 0 不结算
     */
    Long INTEGRATION_FLAG_NOT = 0L;
    /**
     * 积分结算标识：1需要结算
     */
    Long INTEGRATION_FLAG = 1L;

    /**
     * 同一属性下允许的参数最大数量
     */
    Integer MAXIMUM_SPECIFICATION_ATTRIBUTE = 20;

    String OPERATE_TYPE_INSERT = "insert";

    String OPERATE_TYPE_DELETE = "delete";
}
