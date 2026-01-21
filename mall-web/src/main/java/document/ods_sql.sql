CREATE TABLE ods_oms_order
(
    ORDER_ID                    NUMBER(20)   not null primary key ,
    CUST_NO                     NUMBER(32)       ,
    CUST_NAME                   VARCHAR2(64)     ,
    CUST_MOBILE                 NUMBER(11)       ,
    MERCHANT_NO                 NUMBER(20)       ,
    MERCHANT_NAME               VARCHAR2(256)     ,
    ORDER_SN                    VARCHAR2(64)     ,
    PRODUCT_ID                  NUMBER(20)       ,
    PRODUCT_NAME                VARCHAR2(300)     ,
    PRODUCT_SKU_ID              NUMBER(20)       ,
    PRODUCT_COUNT               NUMBER(10)       ,
    PRODUCT_PRICE               NUMBER(8,2)      ,
    PRODUCT_CASH                NUMBER(8,2)      ,
    PRODUCT_INTEGRATION         NUMBER(11)       ,
    CATEGORY_ID_1               NUMBER(20)       ,
    CATEGORY_ID_2               NUMBER(20)       ,
    CATEGORY_ID_3               NUMBER(20)       ,
    CATEGORY_ID_4               NUMBER(20)       ,
    PAY_AMOUNT                  NUMBER(8,2)      ,
    ORDER_CASH                  NUMBER(8,2)      ,
    ORDER_INTEGRATION           NUMBER(11)       ,
    FREIGHT_AMOUNT              NUMBER(8,2)      ,
    USER_COUPON_ID              NUMBER(20)       ,
    COUPON_TYPE                 NUMBER(1)        ,
    COUPON_AMOUNT               NUMBER(8,2)      ,
    PAY_TYPE                    NUMBER(1)        ,
    STATUS                      NUMBER(2)        ,
    CLOSE_TYPE                  NUMBER(1)        ,
    REFUND_STATUS               NUMBER(1)        ,
    PRODUCT_TYPE                NUMBER(1)        ,
    DELIVERY_COMPANY_NAME       VARCHAR2(256)     ,
    DELIVERY_SN                 VARCHAR2(64)     ,
    RECEIVER_NAME               VARCHAR2(256)    ,
    RECEIVER_PHONE              VARCHAR2(32)     ,
    RECEIVER_PROVINCE           VARCHAR2(32)     ,
    RECEIVER_CITY               VARCHAR2(32)     ,
    RECEIVER_REGION             VARCHAR2(32)     ,
    RECEIVER_DETAIL_ADDRESS     VARCHAR2(200)    ,
    NOTE                        VARCHAR2(500)    ,
    CONFIRM_STATUS              NUMBER(1)        ,
    INTEGRATION_PAY_FLAG        NUMBER(1)        ,
    PAYMENT_TIME                DATE             ,
    DELIVERY_TIME               DATE             ,
    RECEIVE_TIME                DATE             ,
    RECHARGE_MOBILE             VARCHAR2(256)       ,
    MOBILE_ADDRESS              VARCHAR2(1000)     ,
    NOTICE_STATUS               NUMBER(1)        ,
    CREATE_TIME                 DATE             ,
    UPDATE_TIME                 DATE             ,
    TX_DT                       VARCHAR2(16)     ,
    REQ_URL                     VARCHAR2(2000)   ,
    ACCESS_SIGN_ID              VARCHAR2(100),
    EXCHANGE_COUPON_ID          NUMBER(20)
) initrans 6;

COMMENT ON TABLE ods_oms_order IS '订单表';
COMMENT ON COLUMN ods_oms_order.ORDER_ID                  IS '订单主键 ';
COMMENT ON COLUMN ods_oms_order.CUST_NO                   IS '通联客户号';
COMMENT ON COLUMN ods_oms_order.CUST_NAME                 IS '通联核心用户姓名';
COMMENT ON COLUMN ods_oms_order.CUST_MOBILE               IS '"通联客户号对应的银行预留手机号"';
COMMENT ON COLUMN ods_oms_order.MERCHANT_NO               IS '商户编号';
COMMENT ON COLUMN ods_oms_order.MERCHANT_NAME             IS '商户名称';
COMMENT ON COLUMN ods_oms_order.ORDER_SN                  IS '订单编号';
COMMENT ON COLUMN ods_oms_order.PRODUCT_ID                IS '商品编号';
COMMENT ON COLUMN ods_oms_order.PRODUCT_NAME              IS '商品名称';
COMMENT ON COLUMN ods_oms_order.PRODUCT_SKU_ID            IS '规格编号';
COMMENT ON COLUMN ods_oms_order.PRODUCT_COUNT             IS '商品数量';
COMMENT ON COLUMN ods_oms_order.PRODUCT_PRICE             IS '商品折算价 ';
COMMENT ON COLUMN ods_oms_order.PRODUCT_CASH              IS '商品售价中现金金额';
COMMENT ON COLUMN ods_oms_order.PRODUCT_INTEGRATION       IS '商品售价中积分量';
COMMENT ON COLUMN ods_oms_order.CATEGORY_ID_1             IS '商品一级分类编号';
COMMENT ON COLUMN ods_oms_order.CATEGORY_ID_2             IS '商品二级分类编号';
COMMENT ON COLUMN ods_oms_order.CATEGORY_ID_3             IS '商品三级分类编号';
COMMENT ON COLUMN ods_oms_order.CATEGORY_ID_4             IS '商品四级分类编号';
COMMENT ON COLUMN ods_oms_order.PAY_AMOUNT                IS '订单实付款(折算价)';
COMMENT ON COLUMN ods_oms_order.ORDER_CASH                IS '订单现金:包含商品售价中现金金额-优惠金额，不包含运费金额';
COMMENT ON COLUMN ods_oms_order.ORDER_INTEGRATION         IS '订单积分';
COMMENT ON COLUMN ods_oms_order.FREIGHT_AMOUNT            IS '运费金额';
COMMENT ON COLUMN ods_oms_order.USER_COUPON_ID            IS '用户优惠券编号';
COMMENT ON COLUMN ods_oms_order.COUPON_TYPE               IS '优惠券类型：0 积分兑换券 1 指定商品免费兑换券';
COMMENT ON COLUMN ods_oms_order.COUPON_AMOUNT             IS '优惠券面值金额';
COMMENT ON COLUMN ods_oms_order.PAY_TYPE                  IS '支付方式： 0 纯积分  1 纯现金 2 积分+现金';
COMMENT ON COLUMN ods_oms_order.STATUS                    IS '订单状态：(根据商品类型区分）实物订单：0 待支付1 待发货2 已发货3 4 已关闭  话费订单：0 待支付1 充值中3 已完成4 已关闭 积分券兑换订单：0 待支付2 待使用3 已使用4 已关闭5 已过期';
COMMENT ON COLUMN ods_oms_order.CLOSE_TYPE                IS '订单关闭类型：0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭';
COMMENT ON COLUMN ods_oms_order.REFUND_STATUS             IS '退款状态 0 待审核 1 审核通过 2 退款成功 3审核不通过';
COMMENT ON COLUMN ods_oms_order.PRODUCT_TYPE              IS '商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券';
COMMENT ON COLUMN ods_oms_order.DELIVERY_COMPANY_NAME     IS '物流公司名称';
COMMENT ON COLUMN ods_oms_order.DELIVERY_SN               IS '物流单号';
COMMENT ON COLUMN ods_oms_order.RECEIVER_NAME             IS '收货人姓名';
COMMENT ON COLUMN ods_oms_order.RECEIVER_PHONE            IS '收货人电话';
COMMENT ON COLUMN ods_oms_order.RECEIVER_PROVINCE         IS '省份直辖市';
COMMENT ON COLUMN ods_oms_order.RECEIVER_CITY             IS '城市';
COMMENT ON COLUMN ods_oms_order.RECEIVER_REGION           IS '区县';
COMMENT ON COLUMN ods_oms_order.RECEIVER_DETAIL_ADDRESS   IS '详细地址 ';
COMMENT ON COLUMN ods_oms_order.NOTE                      IS '订单备注 ';
COMMENT ON COLUMN ods_oms_order.CONFIRM_STATUS            IS '确认收货状态：0 未确认1 已确认';
COMMENT ON COLUMN ods_oms_order.INTEGRATION_PAY_FLAG      IS '积分结算标识：0 不结算 1 需要结算';
COMMENT ON COLUMN ods_oms_order.PAYMENT_TIME              IS '支付完成时间';
COMMENT ON COLUMN ods_oms_order.DELIVERY_TIME             IS '发货时间  ';
COMMENT ON COLUMN ods_oms_order.RECEIVE_TIME              IS '确认收货时间';
COMMENT ON COLUMN ods_oms_order.RECHARGE_MOBILE           IS '充值手机号 ';
COMMENT ON COLUMN ods_oms_order.MOBILE_ADDRESS            IS '号码归属地 ';
COMMENT ON COLUMN ods_oms_order.NOTICE_STATUS             IS '通知状态 0 未通知 1 已通知';
COMMENT ON COLUMN ods_oms_order.CREATE_TIME               IS '订单创建时间      ';
COMMENT ON COLUMN ods_oms_order.UPDATE_TIME               IS '更新时间        ';
COMMENT ON COLUMN ods_oms_order.REQ_URL                   IS '行内准入接口返回url ';
COMMENT ON COLUMN ods_oms_order.ACCESS_SIGN_ID            IS '行内准入接口返回准入标识';
COMMENT ON COLUMN ods_oms_order.TX_DT                   IS '日切时间';
COMMENT ON COLUMN ods_oms_order.EXCHANGE_COUPON_ID        IS '积分兑换优惠券编号';
-- index
create index ods_oms_order_order_no on ods_oms_order(order_sn) initrans 16;
create index ods_oms_order_create_time on ods_oms_order(create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM ods_oms_order FOR ods_oms_order;
grant select,insert,update,delete on ods_oms_order to mallopr;
grant select on ods_oms_order to qdbankcx;



CREATE TABLE ods_oms_order_refund
(
    REFUND_SERIAL           NUMBER(20)  not null  primary key  ,
    CUST_NO                 VARCHAR2(32)           ,
    CUST_NAME               VARCHAR2(64)           ,
    CUST_MOBILE             NUMBER(11)             ,
    ORDER_SN                VARCHAR2(64)             ,
    PRODUCT_ID              NUMBER(20)             ,
    PRODUCT_NAME            VARCHAR2(300)           ,
    PRODUCT_COUNT           NUMBER(11)             ,
    PRODUCT_SKU_ID          NUMBER(20)             ,
    CATEGORY_ID_1           NUMBER(20)             ,
    CATEGORY_ID_2           NUMBER(20)             ,
    CATEGORY_ID_3           NUMBER(20)             ,
    CATEGORY_ID_4           NUMBER(20)             ,
    ORDER_CASH              NUMBER(8,2)            ,
    ORDER_INTEGRATION       NUMBER(11)             ,
    REFUND_AMOUNT           NUMBER(8,2)            ,
    REFUND_CASH             NUMBER(8,2)            ,
    FREIGHT_AMOUNT          NUMBER(8,2)            ,
    REFUND_INTEGRATION      NUMBER(11)             ,
    REFUND_STATUS           NUMBER(1)              ,
    PRODUCT_TYPE            NUMBER(1)              ,
    HANDLE_START_TIME       DATE                   ,
    HANDLE_FINISH_TIME      DATE                   ,
    HANDLE_RESULT           NUMBER(1)              ,
    REASON                  VARCHAR2(200)          ,
    REFUND_TYPE             NUMBER(1)              ,
    PROOF_PICS              VARCHAR2(2000)         ,
    ADMIN_NAME              VARCHAR2(32)           ,
    EMAIL                   VARCHAR2(64)           ,
    REFUND_NOTE             VARCHAR2(100)          ,
    MERCHANT_NO             NUMBER(20)             ,
    MERCHANT_NAME           VARCHAR2(64)           ,
    TX_DT                   VARCHAR2(16)           ,
    CREATE_TIME             DATE                   ,
    UPDATE_TIME             DATE                   ,
    COUPON_AMOUNT           NUMBER(8,2)
) initrans 6;

COMMENT ON TABLE ods_oms_order_refund IS '退货申请表';
COMMENT ON COLUMN ods_oms_order_refund.REFUND_SERIAL      IS '退款流水号                                          ';
COMMENT ON COLUMN ods_oms_order_refund.CUST_NO            IS '通联客户号                                          ';
COMMENT ON COLUMN ods_oms_order_refund.CUST_NAME          IS '通联核心用户姓名                                       ';
COMMENT ON COLUMN ods_oms_order_refund.CUST_MOBILE        IS '"通联客户号对应的银行预
留手机号"                             ';
COMMENT ON COLUMN ods_oms_order_refund.ORDER_SN           IS '订单编号                                           ';
COMMENT ON COLUMN ods_oms_order_refund.PRODUCT_ID         IS '商品编号                                           ';
COMMENT ON COLUMN ods_oms_order_refund.PRODUCT_NAME       IS '商品名称                                           ';
COMMENT ON COLUMN ods_oms_order_refund.PRODUCT_COUNT      IS '商品数量                                           ';
COMMENT ON COLUMN ods_oms_order_refund.PRODUCT_SKU_ID     IS '规格编号                                           ';
COMMENT ON COLUMN ods_oms_order_refund.CATEGORY_ID_1      IS '商品一级分类编号                                       ';
COMMENT ON COLUMN ods_oms_order_refund.CATEGORY_ID_2      IS '商品二级分类编号                                       ';
COMMENT ON COLUMN ods_oms_order_refund.CATEGORY_ID_3      IS '商品三级分类编号                                       ';
COMMENT ON COLUMN ods_oms_order_refund.CATEGORY_ID_4      IS '商品四级分类编号                                       ';
COMMENT ON COLUMN ods_oms_order_refund.ORDER_CASH         IS '"订单现金:包含商品售价中现金金额-优惠券金额，不包含运费"                ';
COMMENT ON COLUMN ods_oms_order_refund.ORDER_INTEGRATION  IS '订单积分                                           ';
COMMENT ON COLUMN ods_oms_order_refund.REFUND_AMOUNT      IS '退款总金额折算价';
COMMENT ON COLUMN ods_oms_order_refund.REFUND_CASH        IS '"退款现金，不包含运费';
COMMENT ON COLUMN ods_oms_order_refund.FREIGHT_AMOUNT     IS '运费金额                                           ';
COMMENT ON COLUMN ods_oms_order_refund.REFUND_INTEGRATION IS '退款积分：订单积分                                      ';
COMMENT ON COLUMN ods_oms_order_refund.REFUND_STATUS      IS '"退款状态
0 待审核
1 审核通过
2 退款成功
3审核不通过"            ';
COMMENT ON COLUMN ods_oms_order_refund.PRODUCT_TYPE       IS '"商品类型：
0 实物
1 话费充值
2 油卡充值
3 视频会员充值
4 积分兑换券
"   ';
COMMENT ON COLUMN ods_oms_order_refund.HANDLE_START_TIME  IS '退款处理时间                                         ';
COMMENT ON COLUMN ods_oms_order_refund.HANDLE_FINISH_TIME IS '退款完成时间                                         ';
COMMENT ON COLUMN ods_oms_order_refund.HANDLE_RESULT      IS '"处理结果：
0 同意退款
1 退款驳回"                          ';
COMMENT ON COLUMN ods_oms_order_refund.REASON             IS '退款原因                                           ';
COMMENT ON COLUMN ods_oms_order_refund.REFUND_TYPE        IS '"退款类型
0 仅退款(无需退货)
1 退货退款"                    ';
COMMENT ON COLUMN ods_oms_order_refund.PROOF_PICS         IS '上传凭证图片url                                      ';
COMMENT ON COLUMN ods_oms_order_refund.ADMIN_NAME         IS '管理员姓名(处理人姓名)                                   ';
COMMENT ON COLUMN ods_oms_order_refund.EMAIL              IS '管理员邮箱(处理人邮箱)                                   ';
COMMENT ON COLUMN ods_oms_order_refund.REFUND_NOTE        IS '退款说明                                           ';
COMMENT ON COLUMN ods_oms_order_refund.MERCHANT_NO        IS '商户编号';
COMMENT ON COLUMN ods_oms_order_refund.MERCHANT_NAME      IS '商户名称';
COMMENT ON COLUMN ods_oms_order_refund.CREATE_TIME        IS '创建时间                                           ';
COMMENT ON COLUMN ods_oms_order_refund.UPDATE_TIME        IS '更新时间                                           ';
COMMENT ON COLUMN ods_oms_order_refund.TX_DT                   IS '日切时间';
COMMENT ON COLUMN ods_oms_order_refund.COUPON_AMOUNT      IS '优惠券面值';
-- index
create index idx_ods_refund_create_time on ods_oms_order_refund  (create_time);
CREATE or replace PUBLIC SYNONYM ods_oms_order_refund FOR ods_oms_order_refund;
grant select,insert,update,delete on ods_oms_order_refund to mallopr;
grant select on ods_oms_order_refund to qdbankcx;

create table ods_sms_coupon(
COUPON_ID                   NUMBER(20)   not null primary key     ,
BATCH_NO                    VARCHAR2(32)      ,
COUPON_NAME                 VARCHAR2(256)      ,
COUPON_TYPE                 NUMBER(1)         ,
ALL_TOTAL                   NUMBER(10)        ,
PRODUCT_INTEGRATION         NUMBER(11)        ,
COUPON_AMOUNT               NUMBER(8,2)       ,
COUPON_DESCRIPTION          VARCHAR2(2000)    ,
PRODUCT_STATUS              NUMBER(1)         ,
BATCH_STATUS                NUMBER(1)         ,
PRODUCT_TYPE                NUMBER(1)         ,
PRODUCT_ID                  NUMBER(20)        ,
PRODUCT_NAME                VARCHAR2(300)      ,
PRODUCT_SKU_ID              NUMBER(20)        ,
FILE_NAME                   VARCHAR2(200)      ,
FILE_URL                    VARCHAR2(2000)    ,
EXPIRE_DAYS                 NUMBER(3)         ,
EXPIRE_DATE                 DATE              ,
START_TIME                  DATE              ,
END_TIME                    DATE              ,
SEND_TIME                   DATE              ,
group_id                    varchar2(1000),
CREATE_TIME                 DATE              ,
UPDATE_TIME                 DATE              ,
TX_DT                       VARCHAR2(16)           ,
CREATED_BY                  VARCHAR2(50)      ,
UPDATED_BY                  VARCHAR2(50)
)initrans 6;

COMMENT ON TABLE ods_sms_coupon IS ' 优惠券信息表';
COMMENT ON COLUMN ods_sms_coupon.COUPON_ID                  IS '"
券商品编号

"                ';
COMMENT ON COLUMN ods_sms_coupon.BATCH_NO                   IS '批次号                       ';
COMMENT ON COLUMN ods_sms_coupon.COUPON_NAME                IS '优惠券名称                     ';
COMMENT ON COLUMN ods_sms_coupon.COUPON_TYPE                IS '优惠券类型：0 积分兑换券 1 指定商品免费兑换券';
COMMENT ON COLUMN ods_sms_coupon.ALL_TOTAL                  IS '本批次白名单数量                  ';
COMMENT ON COLUMN ods_sms_coupon.PRODUCT_INTEGRATION        IS '商品售价中积分量                  ';
COMMENT ON COLUMN ods_sms_coupon.COUPON_AMOUNT              IS '优惠券面值';
COMMENT ON COLUMN ods_sms_coupon.COUPON_DESCRIPTION         IS '优惠券描述';
COMMENT ON COLUMN ods_sms_coupon.PRODUCT_STATUS             IS '券商品状态：0 待上架 1 已上架                    ';
COMMENT ON COLUMN ods_sms_coupon.BATCH_STATUS               IS '批次状态：0 待发放1 已发放2 已过期 3 已作废';
COMMENT ON COLUMN ods_sms_coupon.PRODUCT_TYPE               IS '"指定商品类型0 实物 1 话费充值 2 油卡充值 3 视频会员充值';
COMMENT ON COLUMN ods_sms_coupon.PRODUCT_ID                 IS '指定商品编号                    ';
COMMENT ON COLUMN ods_sms_coupon.PRODUCT_NAME               IS '指定商品名称                    ';
COMMENT ON COLUMN ods_sms_coupon.PRODUCT_SKU_ID             IS '指定商品规格编号                  ';
COMMENT ON COLUMN ods_sms_coupon.FILE_NAME                  IS '上传白名单文件名称                 ';
COMMENT ON COLUMN ods_sms_coupon.FILE_URL                   IS '上传白名单文件URL                ';
COMMENT ON COLUMN ods_sms_coupon.EXPIRE_DAYS                IS '优惠券过期天数                   ';
COMMENT ON COLUMN ods_sms_coupon.EXPIRE_DATE                IS '优惠券过期时间                   ';
COMMENT ON COLUMN ods_sms_coupon.START_TIME                 IS '优惠券有效期开始时间                ';
COMMENT ON COLUMN ods_sms_coupon.END_TIME                   IS '优惠券有效期结束时间                ';
COMMENT ON COLUMN ods_sms_coupon.SEND_TIME                  IS '优惠券发放时间                   ';
COMMENT ON COLUMN ods_sms_coupon.group_id                   IS '文件组名';
COMMENT ON COLUMN ods_sms_coupon.CREATE_TIME                IS '创建时间                      ';
COMMENT ON COLUMN ods_sms_coupon.UPDATE_TIME                IS '更新时间                      ';
COMMENT ON COLUMN ods_sms_coupon.CREATED_BY                    IS '创建人';
COMMENT ON COLUMN ods_sms_coupon.UPDATED_BY                  IS '更新人';
COMMENT ON COLUMN ods_sms_coupon.TX_DT                       IS '日切时间';
create  index unq_ods_batch_no on ods_sms_coupon(batch_no);
CREATE or replace PUBLIC SYNONYM ods_sms_coupon FOR ods_sms_coupon;
grant select,insert,update,delete on ods_sms_coupon to mallopr;
grant select on ods_sms_coupon to qdbankcx;

create table ods_sms_user_coupon(
    USER_COUPON_ID      NUMBER(20)    not null primary key  ,
    COUPON_TYPE         NUMBER(1)       ,
    ORDER_SN            VARCHAR2(64)    ,
    CUST_NO             VARCHAR2(32)    ,
    COUPON_ID           NUMBER(20)      ,
    BATCH_NO            VARCHAR2(32)    ,
    ORDER_INTEGRATION   NUMBER(11)      ,
    COUPON_NAME         VARCHAR2(256)    ,
    STATUS              NUMBER(2)       ,
    TX_DT               VARCHAR2(16)           ,
    CREATE_TIME         DATE            ,
    UPDATE_TIME         DATE,
    EXPIRE_DATE         DATE
)initrans 6;

COMMENT ON TABLE     ods_sms_user_coupon   is '用户持券表';
COMMENT ON COLUMN    ods_sms_user_coupon.USER_COUPON_ID     IS '用户优惠券编号';
COMMENT ON COLUMN    ods_sms_user_coupon.COUPON_TYPE        IS '优惠券类型：0 积分兑换券1 指定商品免费兑换券';
COMMENT ON COLUMN    ods_sms_user_coupon.ORDER_SN           IS '订单编号';
COMMENT ON COLUMN    ods_sms_user_coupon.CUST_NO            IS '通联核心客户号';
COMMENT ON COLUMN    ods_sms_user_coupon.COUPON_ID          IS '券商品编号';
COMMENT ON COLUMN    ods_sms_user_coupon.BATCH_NO           IS '批次号';
COMMENT ON COLUMN    ods_sms_user_coupon.ORDER_INTEGRATION  IS '积分券售价(积分值)';
COMMENT ON COLUMN    ods_sms_user_coupon.STATUS             IS '"用户使用券状态 :0 待使用1 已使用2 已过期3 已作废"';
COMMENT ON COLUMN    ods_sms_user_coupon.CREATE_TIME        IS '创建时间';
COMMENT ON COLUMN    ods_sms_user_coupon.UPDATE_TIME        IS '更新时间';
COMMENT ON COLUMN    ods_sms_user_coupon.TX_DT                       IS '日切时间';
COMMENT ON COLUMN    ods_sms_user_coupon.EXPIRE_DATE        IS '优惠券过期时间';
CREATE index idx_ods_cust_no on ods_sms_user_coupon(CUST_NO);
CREATE index idx_ods_COUPON_ID on ods_sms_user_coupon(COUPON_ID);
CREATE or replace PUBLIC SYNONYM ods_sms_user_coupon FOR ods_sms_user_coupon;
grant select,insert,update,delete on ods_sms_user_coupon to mallopr;
grant select on ods_sms_user_coupon to qdbankcx;

create table ods_pms_product(
    PRODUCT_ID              NUMBER(20)      not null primary key,
    MERCHANT_NO             NUMBER(20)                         ,
    MERCHANT_NAME           VARCHAR2(64)                       ,
    MAIL_PIC_URL              VARCHAR2(2000)                     ,
    CATEGORY_ID_1           NUMBER(20)                         ,
    CATEGORY_ID_2           NUMBER(20)                         ,
    CATEGORY_ID_3           NUMBER(20)                         ,
    CATEGORY_ID_4           NUMBER(20)                         ,
    PUBLISH_STATUS          NUMBER(1)                          ,
    PRODUCT_NAME            VARCHAR2(300)                       ,
    PRODUCT_DESCRIPTION     VARCHAR2(2000)                     ,
    FREIGHT_TEMPLATE_ID     NUMBER(20)                         ,
    PRODUCT_DETAIL          CLOB                               ,
    CREATE_TIME             DATE                               ,
    UPDATE_TIME             DATE                               ,
    TX_DT               VARCHAR2(16)           ,
    CREATED_BY              VARCHAR2(50)                       ,
    UPDATED_BY              VARCHAR2(50)
)initrans 6;
COMMENT ON TABLE ods_pms_product IS '商品表';
COMMENT ON COLUMN ods_pms_product.PRODUCT_ID           IS '商品编号';
COMMENT ON COLUMN ods_pms_product.MERCHANT_NO          IS '商户编号';
COMMENT ON COLUMN ods_pms_product.MERCHANT_NAME        IS '商户姓名';
COMMENT ON COLUMN ods_pms_product.MAIL_PIC_URL         IS '商品主图URL';
COMMENT ON COLUMN ods_pms_product.CATEGORY_ID_1        IS '一级分类编号';
COMMENT ON COLUMN ods_pms_product.CATEGORY_ID_2        IS '二级分类编号';
COMMENT ON COLUMN ods_pms_product.CATEGORY_ID_3        IS '三级分类编号';
COMMENT ON COLUMN ods_pms_product.CATEGORY_ID_4        IS '四级分类编号';
COMMENT ON COLUMN ods_pms_product.PUBLISH_STATUS       IS '上架状态 0 待入库 1 已入库 2 已上架';
COMMENT ON COLUMN ods_pms_product.PRODUCT_NAME         IS '商品名称';
COMMENT ON COLUMN ods_pms_product.PRODUCT_DESCRIPTION  IS '商品描述';
COMMENT ON COLUMN ods_pms_product.FREIGHT_TEMPLATE_ID  IS '运费模板编号';
COMMENT ON COLUMN ods_pms_product.PRODUCT_DETAIL       IS '商品详情';
COMMENT ON COLUMN ods_pms_product.CREATE_TIME          IS '创建时间';
COMMENT ON COLUMN ods_pms_product.UPDATE_TIME          IS '更新时间';
COMMENT ON COLUMN ods_pms_product.CREATED_BY           IS '创建人';
COMMENT ON COLUMN ods_pms_product.UPDATED_BY           IS '更新人';
COMMENT ON COLUMN    ods_pms_product.TX_DT                       IS '日切时间';

create index idx_ods_create_time on ods_pms_product(create_time);
create index idx_ods_update_time on ods_pms_product(update_time);
CREATE or replace PUBLIC SYNONYM ods_pms_product FOR ods_pms_product;
grant select,insert,update,delete on ods_pms_product to mallopr;
grant select on ods_pms_product to qdbankcx;

CREATE TABLE ods_pms_sku_stock (
   PRODUCT_SKU_ID          NUMBER(20)  not null primary key,
   PRODUCT_ID              NUMBER(20)         ,
   MARKET_PRICE            NUMBER(8,2)        ,
   ADVICE_PRICE            NUMBER(8,2)        ,
   PRODUCT_PRICE           NUMBER(8,2)        ,
   PRODUCT_CASH            NUMBER(8,2)        ,
   PRODUCT_INTEGRATION     NUMBER(11)         ,
   PRODUCT_NAME            VARCHAR2(300)       ,
   PROMOTION_START_TIME    DATE               ,
   PROMOTION_END_TIME      DATE               ,
   PROMOTION_PER_LIMIT     NUMBER(10)         ,
   INTEGRATION_PAY_FLAG    NUMBER(1)          ,
   PRODUCT_STOCK           NUMBER(10)         ,
   SKU_PIC_URL             VARCHAR2(2000)     ,
   PRODUCT_LOCK_STOCK      NUMBER(10)         ,
   PRODUCT_SP_DATA         VARCHAR2(2000)     ,
   CREATE_TIME             DATE               ,
   UPDATE_TIME             DATE               ,
   CREATED_BY              VARCHAR2(50)       ,
   UPDATED_BY              VARCHAR2(50)       ,
   STATUS                  NUMBER(1)          ,
   TX_DT                   VARCHAR2(16)           ,
   GROUP_ID                varchar2(1000)
)initrans 6;
COMMENT ON TABLE ods_pms_sku_stock IS '商品规格信息';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_SKU_ID        IS '规格编号';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_ID            IS '商品编号';
COMMENT ON COLUMN ods_pms_sku_stock.MARKET_PRICE          IS '市场价                  ';
COMMENT ON COLUMN ods_pms_sku_stock.ADVICE_PRICE          IS '建议售价';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_PRICE         IS '商品售价';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_CASH          IS '商品售价中现金金额';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_INTEGRATION   IS '商品售价中积分值';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_NAME          IS '商品名称';
COMMENT ON COLUMN ods_pms_sku_stock.PROMOTION_START_TIME  IS '限购开始时间';
COMMENT ON COLUMN ods_pms_sku_stock.PROMOTION_END_TIME    IS '限购结束时间';
COMMENT ON COLUMN ods_pms_sku_stock.PROMOTION_PER_LIMIT   IS '每人限购数量';
COMMENT ON COLUMN ods_pms_sku_stock.INTEGRATION_PAY_FLAG  IS '积分结算标识：0 不结算 1 需要结算';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_STOCK         IS '商品库存';
COMMENT ON COLUMN ods_pms_sku_stock.SKU_PIC_URL           IS '规格图片URL';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_LOCK_STOCK    IS '商品锁定库存(已下单商品数量)';
COMMENT ON COLUMN ods_pms_sku_stock.PRODUCT_SP_DATA       IS '商品属性:JSON格式';
COMMENT ON COLUMN ods_pms_sku_stock.CREATE_TIME           IS '创建时间';
COMMENT ON COLUMN ods_pms_sku_stock.UPDATE_TIME           IS '更新时间';
COMMENT ON COLUMN ods_pms_sku_stock.CREATED_BY            IS '创建人';
COMMENT ON COLUMN ods_pms_sku_stock.UPDATED_BY            IS '更新人';
COMMENT ON COLUMN ods_pms_sku_stock.STATUS                IS '状态标识：0上架 1下架';
COMMENT ON COLUMN ods_pms_sku_stock.GROUP_ID              IS '文件组名';
COMMENT ON COLUMN ods_pms_sku_stock.TX_DT                 IS '日切时间';
create index ods_idx_sku_product_id on ods_pms_sku_stock(product_id);
CREATE or replace PUBLIC SYNONYM ods_pms_sku_stock FOR ods_pms_sku_stock;
grant select,insert,update,delete on ods_pms_sku_stock to mallopr;
grant select on ods_pms_sku_stock to qdbankcx;





INSERT INTO ODS_OMS_ORDER (ORDER_ID, CUST_NO, CUST_NAME, CUST_MOBILE, MERCHANT_NO, MERCHANT_NAME, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, PRODUCT_COUNT, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PAY_AMOUNT, ORDER_CASH, ORDER_INTEGRATION, FREIGHT_AMOUNT, USER_COUPON_ID, COUPON_TYPE, COUPON_AMOUNT, PAY_TYPE, STATUS, CLOSE_TYPE, REFUND_STATUS, PRODUCT_TYPE, DELIVERY_COMPANY_NAME, DELIVERY_SN, RECEIVER_NAME, RECEIVER_PHONE, RECEIVER_PROVINCE, RECEIVER_CITY, RECEIVER_REGION, RECEIVER_DETAIL_ADDRESS, NOTE, CONFIRM_STATUS, INTEGRATION_PAY_FLAG, PAYMENT_TIME, DELIVERY_TIME, RECEIVE_TIME, RECHARGE_MOBILE, MOBILE_ADDRESS, NOTICE_STATUS, CREATE_TIME, UPDATE_TIME, TX_DT, REQ_URL, ACCESS_SIGN_ID, EXCHANGE_COUPON_ID) VALUES (150716004419248128, 120, null, 13541224283, 20001, '测测-uat', 'QD202107191343590011', 146747927969144832, '免费兑换商品-包邮', 146747928162082816, 1, 120.00, 120.00, 0, 99619799977558016, 99621376415105024, 99624977195728896, 99636479382388736, 0.01, 0.01, 0, 0.00, 150145343615418369, 1, 119.99, 1, 1, -1, -1, 0, null, null, '河北', '15112345678', '河北省', '石家庄市', '长安区', '包邮', null, null, 0, TO_DATE('2021-07-19 13:44:12', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, null, 1, TO_DATE('2021-07-19 13:44:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 13:44:12', 'YYYY-MM-DD HH24:MI:SS'), '2021-07-19', '', '331770', 150145343489589248);
INSERT INTO ODS_OMS_ORDER (ORDER_ID, CUST_NO, CUST_NAME, CUST_MOBILE, MERCHANT_NO, MERCHANT_NAME, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, PRODUCT_COUNT, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PAY_AMOUNT, ORDER_CASH, ORDER_INTEGRATION, FREIGHT_AMOUNT, USER_COUPON_ID, COUPON_TYPE, COUPON_AMOUNT, PAY_TYPE, STATUS, CLOSE_TYPE, REFUND_STATUS, PRODUCT_TYPE, DELIVERY_COMPANY_NAME, DELIVERY_SN, RECEIVER_NAME, RECEIVER_PHONE, RECEIVER_PROVINCE, RECEIVER_CITY, RECEIVER_REGION, RECEIVER_DETAIL_ADDRESS, NOTE, CONFIRM_STATUS, INTEGRATION_PAY_FLAG, PAYMENT_TIME, DELIVERY_TIME, RECEIVE_TIME, RECHARGE_MOBILE, MOBILE_ADDRESS, NOTICE_STATUS, CREATE_TIME, UPDATE_TIME, TX_DT, REQ_URL, ACCESS_SIGN_ID, EXCHANGE_COUPON_ID) VALUES (150776185350004736, 382, null, 15055550004, 20002, '方婕的测试商户', 'QD202107191743070016', 145706296897941504, 'FJ的测试商品FJ的测试商品FJ的测试商品FJ的测试商品FJ的测试商品FJ的测试商品FJ的', 145706297082490880, 1, 2.00, 1.99, 1, 99619762665029632, 99621153290715136, 99624486604767232, 99629252634345472, 7.00, 1.99, 1, 5.00, null, null, 0.00, 2, 1, -1, -1, 0, null, null, '蔡磊', '17317829192', '上海市', '上海市', '浦东新区', '软件园2号', null, null, 1, TO_DATE('2021-07-19 17:43:23', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, null, 1, TO_DATE('2021-07-19 17:43:08', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 17:43:23', 'YYYY-MM-DD HH24:MI:SS'), '2021-07-19', '', '335765', null);
INSERT INTO ODS_OMS_ORDER (ORDER_ID, CUST_NO, CUST_NAME, CUST_MOBILE, MERCHANT_NO, MERCHANT_NAME, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, PRODUCT_COUNT, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PAY_AMOUNT, ORDER_CASH, ORDER_INTEGRATION, FREIGHT_AMOUNT, USER_COUPON_ID, COUPON_TYPE, COUPON_AMOUNT, PAY_TYPE, STATUS, CLOSE_TYPE, REFUND_STATUS, PRODUCT_TYPE, DELIVERY_COMPANY_NAME, DELIVERY_SN, RECEIVER_NAME, RECEIVER_PHONE, RECEIVER_PROVINCE, RECEIVER_CITY, RECEIVER_REGION, RECEIVER_DETAIL_ADDRESS, NOTE, CONFIRM_STATUS, INTEGRATION_PAY_FLAG, PAYMENT_TIME, DELIVERY_TIME, RECEIVE_TIME, RECHARGE_MOBILE, MOBILE_ADDRESS, NOTICE_STATUS, CREATE_TIME, UPDATE_TIME, TX_DT, REQ_URL, ACCESS_SIGN_ID, EXCHANGE_COUPON_ID) VALUES (150775734495879168, 382, null, 15055550004, 20002, '方婕的测试商户', 'QD202107191741200015', 148989765346140160, 'FJ的测试商品9', 148989765375500288, 1, 5.55, 5.55, 0, 99619569144037376, 99620992846004224, 99624355302080512, 99629134153646080, 5.55, 5.55, 0, 0.00, null, null, 0.00, 1, 1, -1, -1, 0, null, null, '蔡磊', '17317829192', '上海市', '上海市', '浦东新区', '软件园2号', null, null, 0, TO_DATE('2021-07-19 17:41:40', 'YYYY-MM-DD HH24:MI:SS'), null, null, null, null, 1, TO_DATE('2021-07-19 17:41:20', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 17:41:40', 'YYYY-MM-DD HH24:MI:SS'), '2021-07-19', '', '335764', null);


INSERT INTO ODS_OMS_ORDER_REFUND (REFUND_SERIAL, CUST_NO, CUST_NAME, CUST_MOBILE, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_COUNT, PRODUCT_SKU_ID, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, ORDER_CASH, ORDER_INTEGRATION, REFUND_AMOUNT, REFUND_CASH, FREIGHT_AMOUNT, REFUND_INTEGRATION, REFUND_STATUS, PRODUCT_TYPE, HANDLE_START_TIME, HANDLE_FINISH_TIME, HANDLE_RESULT, REASON, REFUND_TYPE, PROOF_PICS, ADMIN_NAME, EMAIL, REFUND_NOTE, MERCHANT_NO, MERCHANT_NAME, TX_DT, CREATE_TIME, UPDATE_TIME, COUPON_AMOUNT) VALUES (150691110331621376, '120', null, 13541224283, 'QD202107172142150004', 146693958634831872, '现金+积分-自定义', 1, 146693958706135040, 99619799977558016, 99621277232398336, 99624805090852864, 99632293135646720, 29.90, 10, 42.00, 41.90, 12.00, 10, 2, 0, TO_DATE('2021-07-19 13:42:05', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 13:42:05', 'YYYY-MM-DD HH24:MI:SS'), 1, '不想要了-用户需承担运费', 0, null, null, null, null, 20001, '测测-uat', '2021-07-19', TO_DATE('2021-07-19 12:05:04', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 13:42:05', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_OMS_ORDER_REFUND (REFUND_SERIAL, CUST_NO, CUST_NAME, CUST_MOBILE, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_COUNT, PRODUCT_SKU_ID, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, ORDER_CASH, ORDER_INTEGRATION, REFUND_AMOUNT, REFUND_CASH, FREIGHT_AMOUNT, REFUND_INTEGRATION, REFUND_STATUS, PRODUCT_TYPE, HANDLE_START_TIME, HANDLE_FINISH_TIME, HANDLE_RESULT, REASON, REFUND_TYPE, PROOF_PICS, ADMIN_NAME, EMAIL, REFUND_NOTE, MERCHANT_NO, MERCHANT_NAME, TX_DT, CREATE_TIME, UPDATE_TIME, COUPON_AMOUNT) VALUES (150722383112577024, '218', null, 18075851867, 'QD202107191409040012', 100031, '浙江移动30元', 1, 1001, 99620494629797888, 99622615047602176, 99627375096102912, 99647011917266944, 26.98, 0, 26.98, 26.98, 0.00, 0, 2, 1, null, TO_DATE('2021-07-19 14:09:20', 'YYYY-MM-DD HH24:MI:SS'), null, null, 2, null, null, null, '充值失败，自动退款', 20020, '青岛网信信息科技有限公司UAT', '2021-07-19', TO_DATE('2021-07-19 14:09:20', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 14:09:20', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_OMS_ORDER_REFUND (REFUND_SERIAL, CUST_NO, CUST_NAME, CUST_MOBILE, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_COUNT, PRODUCT_SKU_ID, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, ORDER_CASH, ORDER_INTEGRATION, REFUND_AMOUNT, REFUND_CASH, FREIGHT_AMOUNT, REFUND_INTEGRATION, REFUND_STATUS, PRODUCT_TYPE, HANDLE_START_TIME, HANDLE_FINISH_TIME, HANDLE_RESULT, REASON, REFUND_TYPE, PROOF_PICS, ADMIN_NAME, EMAIL, REFUND_NOTE, MERCHANT_NO, MERCHANT_NAME, TX_DT, CREATE_TIME, UPDATE_TIME, COUPON_AMOUNT) VALUES (150760259644825600, '120', null, 13541224283, 'QD202107191639310014', 100031, '江苏移动100元', 1, 1003, 99620494629797888, 99622615047602176, 99627375096102912, 99647011917266944, 99.80, 0, 99.80, 99.80, 0.00, 0, 2, 1, null, TO_DATE('2021-07-19 16:39:50', 'YYYY-MM-DD HH24:MI:SS'), null, null, 2, null, null, null, '充值失败，自动退款', 20020, '青岛网信信息科技有限公司UAT', '2021-07-19', TO_DATE('2021-07-19 16:39:50', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 16:39:50', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_OMS_ORDER_REFUND (REFUND_SERIAL, CUST_NO, CUST_NAME, CUST_MOBILE, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_COUNT, PRODUCT_SKU_ID, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, ORDER_CASH, ORDER_INTEGRATION, REFUND_AMOUNT, REFUND_CASH, FREIGHT_AMOUNT, REFUND_INTEGRATION, REFUND_STATUS, PRODUCT_TYPE, HANDLE_START_TIME, HANDLE_FINISH_TIME, HANDLE_RESULT, REASON, REFUND_TYPE, PROOF_PICS, ADMIN_NAME, EMAIL, REFUND_NOTE, MERCHANT_NO, MERCHANT_NAME, TX_DT, CREATE_TIME, UPDATE_TIME, COUPON_AMOUNT) VALUES (150795296905895936, '382', null, 15055550004, 'QD202107191858320025', 100031, '上海电信30元', 1, 3001, 99620494629797888, 99622615047602176, 99627375096102912, 99647011917266944, 19.98, 0, 19.98, 19.98, 0.00, 0, 2, 1, null, TO_DATE('2021-07-19 18:59:04', 'YYYY-MM-DD HH24:MI:SS'), null, null, 2, null, null, null, '充值失败，自动退款', 20020, '青岛网信信息科技有限公司UAT', '2021-07-19', TO_DATE('2021-07-19 18:59:04', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 18:59:04', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_OMS_ORDER_REFUND (REFUND_SERIAL, CUST_NO, CUST_NAME, CUST_MOBILE, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_COUNT, PRODUCT_SKU_ID, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, ORDER_CASH, ORDER_INTEGRATION, REFUND_AMOUNT, REFUND_CASH, FREIGHT_AMOUNT, REFUND_INTEGRATION, REFUND_STATUS, PRODUCT_TYPE, HANDLE_START_TIME, HANDLE_FINISH_TIME, HANDLE_RESULT, REASON, REFUND_TYPE, PROOF_PICS, ADMIN_NAME, EMAIL, REFUND_NOTE, MERCHANT_NO, MERCHANT_NAME, TX_DT, CREATE_TIME, UPDATE_TIME, COUPON_AMOUNT) VALUES (150862894280482816, '120', null, 13541224283, 'QD202107192327230033', 100031, '江苏移动100元', 1, 1003, 99620494629797888, 99622615047602176, 99627375096102912, 99647011917266944, 99.60, 0, 99.60, 99.60, 0.00, 0, 2, 1, null, TO_DATE('2021-07-19 23:27:40', 'YYYY-MM-DD HH24:MI:SS'), null, null, 2, null, null, null, '充值失败，自动退款', 20020, '青岛网信信息科技有限公司UAT', '2021-07-19', TO_DATE('2021-07-19 23:27:40', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 23:27:40', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_OMS_ORDER_REFUND (REFUND_SERIAL, CUST_NO, CUST_NAME, CUST_MOBILE, ORDER_SN, PRODUCT_ID, PRODUCT_NAME, PRODUCT_COUNT, PRODUCT_SKU_ID, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, ORDER_CASH, ORDER_INTEGRATION, REFUND_AMOUNT, REFUND_CASH, FREIGHT_AMOUNT, REFUND_INTEGRATION, REFUND_STATUS, PRODUCT_TYPE, HANDLE_START_TIME, HANDLE_FINISH_TIME, HANDLE_RESULT, REASON, REFUND_TYPE, PROOF_PICS, ADMIN_NAME, EMAIL, REFUND_NOTE, MERCHANT_NO, MERCHANT_NAME, TX_DT, CREATE_TIME, UPDATE_TIME, COUPON_AMOUNT) VALUES (150077019103698944, '120', null, 13541224283, 'QD202107171922450001', 146693958634831872, '现金+积分-自定义', 2, 146693958668386304, 99619799977558016, 99621277232398336, 99624805090852864, 99632293135646720, 59.90, 10, 75.00, 74.90, 15.00, 10, 2, 0, TO_DATE('2021-07-19 17:31:48', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 17:31:48', 'YYYY-MM-DD HH24:MI:SS'), 1, '七天无理由-用户需承担运费', 0, null, null, null, null, 20001, '测测-uat', '2021-07-19', TO_DATE('2021-07-17 19:24:53', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-19 17:31:48', 'YYYY-MM-DD HH24:MI:SS'), null);

INSERT INTO ODS_PMS_PRODUCT (PRODUCT_ID, MERCHANT_NO, MERCHANT_NAME, MAIL_PIC_URL, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PUBLISH_STATUS, PRODUCT_NAME, PRODUCT_DESCRIPTION, FREIGHT_TEMPLATE_ID, PRODUCT_DETAIL, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (116315526116106240, 100724737512177664, '顺丰', 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB36meEfM6pAAAAAAXLO4U034.png', 99619799977558016, 111633927696977920, null, null, 0, '大阿38', '阿凡地方', 114164312163176448, '<CLOB>', TO_DATE('2021-04-15 15:28:46', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 15:28:46', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');
INSERT INTO ODS_PMS_PRODUCT (PRODUCT_ID, MERCHANT_NO, MERCHANT_NAME, MAIL_PIC_URL, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PUBLISH_STATUS, PRODUCT_NAME, PRODUCT_DESCRIPTION, FREIGHT_TEMPLATE_ID, PRODUCT_DETAIL, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (116316055756038144, 100724737512177664, '顺丰', 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB36uWEYnCUAAAAAAXLO4U701.png', 99619799977558016, 111633927696977920, null, null, 0, '大阿39', '阿凡地方', 114164312163176448, '<CLOB>', TO_DATE('2021-04-15 15:30:52', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 15:30:52', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');
INSERT INTO ODS_PMS_PRODUCT (PRODUCT_ID, MERCHANT_NO, MERCHANT_NAME, MAIL_PIC_URL, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PUBLISH_STATUS, PRODUCT_NAME, PRODUCT_DESCRIPTION, FREIGHT_TEMPLATE_ID, PRODUCT_DETAIL, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (116316564558667776, 100724737512177664, '顺丰', 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB3616EJY9iAAAAAAXLO4U162.png', 99619799977558016, 111633927696977920, null, null, 0, '大阿90', '阿凡地方', 114164312163176448, '<CLOB>', TO_DATE('2021-04-15 15:32:53', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 15:32:53', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');
INSERT INTO ODS_PMS_PRODUCT (PRODUCT_ID, MERCHANT_NO, MERCHANT_NAME, MAIL_PIC_URL, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PUBLISH_STATUS, PRODUCT_NAME, PRODUCT_DESCRIPTION, FREIGHT_TEMPLATE_ID, PRODUCT_DETAIL, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (116333683786604544, 100724737512177664, '顺丰', 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB3-1CEUa5oAAAAAJa4e8M603.png', 99619799977558016, 111633927696977920, null, null, 0, '阿达', '阿道夫', 114164312163176448, '<CLOB>', TO_DATE('2021-04-15 16:40:55', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 16:40:55', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');
INSERT INTO ODS_PMS_PRODUCT (PRODUCT_ID, MERCHANT_NO, MERCHANT_NAME, MAIL_PIC_URL, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PUBLISH_STATUS, PRODUCT_NAME, PRODUCT_DESCRIPTION, FREIGHT_TEMPLATE_ID, PRODUCT_DETAIL, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (116334062062493696, 100724737512177664, '顺丰', 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB3-6qEUDV4AAAAAJa4e8M948.png', 99619799977558016, 111633927696977920, null, null, 0, '阿达11', '阿道夫', 114164312163176448, '<CLOB>', TO_DATE('2021-04-15 16:42:25', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 16:42:25', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');
INSERT INTO ODS_PMS_PRODUCT (PRODUCT_ID, MERCHANT_NO, MERCHANT_NAME, MAIL_PIC_URL, CATEGORY_ID_1, CATEGORY_ID_2, CATEGORY_ID_3, CATEGORY_ID_4, PUBLISH_STATUS, PRODUCT_NAME, PRODUCT_DESCRIPTION, FREIGHT_TEMPLATE_ID, PRODUCT_DETAIL, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (116730990361272320, 100724737512177664, '顺丰', null, 99619840473563136, 99621532401270784, 99625367228252160, 99637621621391360, 1, '水桶1', '描述', 114163838840164352, '<CLOB>', TO_DATE('2021-04-16 18:59:36', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-05-07 15:28:40', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '饭饭总专用');

INSERT INTO ODS_PMS_SKU_STOCK (PRODUCT_SKU_ID, PRODUCT_ID, MARKET_PRICE, ADVICE_PRICE, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, PRODUCT_NAME, PROMOTION_START_TIME, PROMOTION_END_TIME, PROMOTION_PER_LIMIT, INTEGRATION_PAY_FLAG, PRODUCT_STOCK, SKU_PIC_URL, PRODUCT_LOCK_STOCK, PRODUCT_SP_DATA, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY, STATUS, TX_DT, GROUP_ID) VALUES (110895946241544192, 105731803112951808, 4.00, 3.00, 3.00, 3.00, 100, '女士--', TO_DATE('2021-04-13 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-28 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 1, 100, null, 1, '[{"颜色":"蛋"}]', TO_DATE('2021-03-31 16:33:17', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-13 15:16:41', 'YYYY-MM-DD HH24:MI:SS'), '系统管理员', '系统管理员', 0, '2021-05-13', null);
INSERT INTO ODS_PMS_SKU_STOCK (PRODUCT_SKU_ID, PRODUCT_ID, MARKET_PRICE, ADVICE_PRICE, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, PRODUCT_NAME, PROMOTION_START_TIME, PROMOTION_END_TIME, PROMOTION_PER_LIMIT, INTEGRATION_PAY_FLAG, PRODUCT_STOCK, SKU_PIC_URL, PRODUCT_LOCK_STOCK, PRODUCT_SP_DATA, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY, STATUS, TX_DT, GROUP_ID) VALUES (116300173222830080, 116300171532525568, 1.00, 1.00, null, null, null, null, null, null, null, null, 1, 'http://wx.qdccb.cn/M00/07/AF/CgFRWGB33BmERbuBAAAAAJa4e8M795.png', 0, '[{"颜色":"黑色"}]', TO_DATE('2021-04-15 14:27:45', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 14:27:45', 'YYYY-MM-DD HH24:MI:SS'), '系统管理员', '系统管理员', 0, '2021-05-13', 'group1');
INSERT INTO ODS_PMS_SKU_STOCK (PRODUCT_SKU_ID, PRODUCT_ID, MARKET_PRICE, ADVICE_PRICE, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, PRODUCT_NAME, PROMOTION_START_TIME, PROMOTION_END_TIME, PROMOTION_PER_LIMIT, INTEGRATION_PAY_FLAG, PRODUCT_STOCK, SKU_PIC_URL, PRODUCT_LOCK_STOCK, PRODUCT_SP_DATA, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY, STATUS, TX_DT, GROUP_ID) VALUES (116300175789744128, 116300171532525568, 1.00, 1.00, null, null, null, null, null, null, null, null, 1, 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB33BmEZBQeAAAAAAXLO4U407.png', 0, '[{"颜色":"红色"}]', TO_DATE('2021-04-15 14:27:45', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 14:27:45', 'YYYY-MM-DD HH24:MI:SS'), '系统管理员', '系统管理员', 0, '2021-05-13', 'group1');
INSERT INTO ODS_PMS_SKU_STOCK (PRODUCT_SKU_ID, PRODUCT_ID, MARKET_PRICE, ADVICE_PRICE, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, PRODUCT_NAME, PROMOTION_START_TIME, PROMOTION_END_TIME, PROMOTION_PER_LIMIT, INTEGRATION_PAY_FLAG, PRODUCT_STOCK, SKU_PIC_URL, PRODUCT_LOCK_STOCK, PRODUCT_SP_DATA, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY, STATUS, TX_DT, GROUP_ID) VALUES (116300323315998720, 116300321533419520, 1.00, 1.00, null, null, null, null, null, null, null, null, 1, 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB33D2EasfDAAAAAJa4e8M653.png', 0, '[{"颜色":"黑色"}]', TO_DATE('2021-04-15 14:28:21', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 14:28:21', 'YYYY-MM-DD HH24:MI:SS'), '系统管理员', '系统管理员', 0, '2021-05-13', 'group1');
INSERT INTO ODS_PMS_SKU_STOCK (PRODUCT_SKU_ID, PRODUCT_ID, MARKET_PRICE, ADVICE_PRICE, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, PRODUCT_NAME, PROMOTION_START_TIME, PROMOTION_END_TIME, PROMOTION_PER_LIMIT, INTEGRATION_PAY_FLAG, PRODUCT_STOCK, SKU_PIC_URL, PRODUCT_LOCK_STOCK, PRODUCT_SP_DATA, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY, STATUS, TX_DT, GROUP_ID) VALUES (116300325966798848, 116300321533419520, 1.00, 1.00, null, null, null, null, null, null, null, null, 1, 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB33D2EX_2YAAAAAAXLO4U486.png', 0, '[{"颜色":"红色"}]', TO_DATE('2021-04-15 14:28:21', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 14:28:21', 'YYYY-MM-DD HH24:MI:SS'), '系统管理员', '系统管理员', 0, '2021-05-13', 'group1');
INSERT INTO ODS_PMS_SKU_STOCK (PRODUCT_SKU_ID, PRODUCT_ID, MARKET_PRICE, ADVICE_PRICE, PRODUCT_PRICE, PRODUCT_CASH, PRODUCT_INTEGRATION, PRODUCT_NAME, PROMOTION_START_TIME, PROMOTION_END_TIME, PROMOTION_PER_LIMIT, INTEGRATION_PAY_FLAG, PRODUCT_STOCK, SKU_PIC_URL, PRODUCT_LOCK_STOCK, PRODUCT_SP_DATA, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY, STATUS, TX_DT, GROUP_ID) VALUES (116300816335462400, 116300813768548352, 1.00, 1.00, null, null, null, null, null, null, null, null, 1, 'http://wx.qdccb.cn/M00/07/B0/CgFRWGB33LKEHVw3AAAAAJa4e8M265.png', 0, '[{"颜色":"黑色"}]', TO_DATE('2021-04-15 14:30:18', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-15 14:30:18', 'YYYY-MM-DD HH24:MI:SS'), '系统管理员', '系统管理员', 0, '2021-05-13', 'group1');


INSERT INTO ODS_SMS_COUPON (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (125778097911066624, null, '五元优惠券', 0, null, null, 5.00, '1.asdasdasd2.爱上打扫打扫的3.爱大叔大叔的', 1, null, 1, null, null, null, null, null, 90, null, null, null, TO_DATE('2021-05-12 16:25:47', 'YYYY-MM-DD HH24:MI:SS'), null, TO_DATE('2021-05-11 18:09:39', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-05-12 16:25:47', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '测试用户0430', '测试用户0430');
INSERT INTO ODS_SMS_COUPON (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (113036458465595392, null, '测试修改0507', 0, null, null, 1.00, '1.23423犯得上犯得上发射点犯得上2.大师傅士大夫士大夫大师傅似的3.啊撒撒反对发射点发生啊大大', 0, null, 1, 22, 'null', 1, null, null, 34, null, null, null, TO_DATE('2021-04-13 16:44:26', 'YYYY-MM-DD HH24:MI:SS'), null, TO_DATE('2021-04-06 14:18:55', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-05-07 15:52:28', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '测试用户0430');
INSERT INTO ODS_SMS_COUPON (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (113713895935479808, null, '积分券001', 0, null, 1000, 10.00, '1.爱的当时的撒旦2.湿答答阿萨德的3.按当时的时代', 1, null, 1, 105731803112951808, null, 105794018226634752, null, null, 80, null, null, null, TO_DATE('2021-04-14 13:44:46', 'YYYY-MM-DD HH24:MI:SS'), null, TO_DATE('2021-04-08 11:10:49', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-14 13:44:46', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');
INSERT INTO ODS_SMS_COUPON (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (103619467652759552, '103619467652759553', '指定商品免费兑换券', 1, 100, 100, 1.00, '优惠券描述', null, 3, 0, 1, null, 1, '优惠券白名单导入表模板 - 副本.xlsx', 'http://wx.qdccb.cn/M00/07/AA/CgFRWGBJumOED0b2AAAAAMfu2Hg30.xlsx', null, null, null, TO_DATE('2021-07-07 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), null, 'group1', TO_DATE('2021-03-11 14:39:11', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-03-16 11:24:01', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');
INSERT INTO ODS_SMS_COUPON (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (113025803796779008, '113025803796779009', '优惠卷测试02', 1, 53, null, 79.99, '啊萨达萨达·123是的按实际供货的嘎手机号大使馆', null, 3, null, 1, 'null', 1, '优惠券白名单-0407.xlsx', 'http://wx.qdccb.cn/M00/07/AD/CgFRWGBsBSKEJ_KJAAAAADKELvc55.xlsx', null, null, TO_DATE('2021-04-07 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-05-25 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-05-13 17:34:18', 'YYYY-MM-DD HH24:MI:SS'), 'group1', TO_DATE('2021-04-06 13:36:36', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-05-13 17:34:18', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '测试用户0430');
INSERT INTO ODS_SMS_COUPON (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, TX_DT, CREATED_BY, UPDATED_BY) VALUES (112975625643528192, '112975625643528193', '啊是堵塞', 1, 57, null, 79.99, '阿萨大', null, 2, null, 1, null, 1, '优惠券白名单-0406.xlsx', 'http://wx.qdccb.cn/M00/07/AD/CgFRWGBrw-eEb7FMAAAAAIt7Idg34.xlsx', null, null, TO_DATE('2021-04-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-06 17:20:08', 'YYYY-MM-DD HH24:MI:SS'), 'group1', TO_DATE('2021-04-06 10:17:12', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-04-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), '2021-05-13', '系统管理员', '系统管理员');


INSERT INTO ODS_SMS_USER_COUPON (USER_COUPON_ID, COUPON_TYPE, ORDER_SN, CUST_NO, COUPON_ID, BATCH_NO, ORDER_INTEGRATION, COUPON_NAME, STATUS, TX_DT, CREATE_TIME, UPDATE_TIME, EXPIRE_DATE) VALUES (103619471452799023, 1, null, '48', 103619467652759552, '103619467652759553', null, null, 1, '2021-05-13', TO_DATE('2021-03-11 14:39:11', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-03-11 14:56:01', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_SMS_USER_COUPON (USER_COUPON_ID, COUPON_TYPE, ORDER_SN, CUST_NO, COUPON_ID, BATCH_NO, ORDER_INTEGRATION, COUPON_NAME, STATUS, TX_DT, CREATE_TIME, UPDATE_TIME, EXPIRE_DATE) VALUES (103619471452799024, 1, null, '49', 103619467652759552, '103619467652759553', null, null, 1, '2021-05-13', TO_DATE('2021-03-11 14:39:11', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-03-11 14:56:01', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_SMS_USER_COUPON (USER_COUPON_ID, COUPON_TYPE, ORDER_SN, CUST_NO, COUPON_ID, BATCH_NO, ORDER_INTEGRATION, COUPON_NAME, STATUS, TX_DT, CREATE_TIME, UPDATE_TIME, EXPIRE_DATE) VALUES (103619471452799025, 1, null, '50', 103619467652759552, '103619467652759553', null, null, 1, '2021-05-13', TO_DATE('2021-03-11 14:39:11', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-03-11 14:56:01', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_SMS_USER_COUPON (USER_COUPON_ID, COUPON_TYPE, ORDER_SN, CUST_NO, COUPON_ID, BATCH_NO, ORDER_INTEGRATION, COUPON_NAME, STATUS, TX_DT, CREATE_TIME, UPDATE_TIME, EXPIRE_DATE) VALUES (103619471452799026, 1, null, '51', 103619467652759552, '103619467652759553', null, null, 1, '2021-05-13', TO_DATE('2021-03-11 14:39:11', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-03-11 14:56:01', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_SMS_USER_COUPON (USER_COUPON_ID, COUPON_TYPE, ORDER_SN, CUST_NO, COUPON_ID, BATCH_NO, ORDER_INTEGRATION, COUPON_NAME, STATUS, TX_DT, CREATE_TIME, UPDATE_TIME, EXPIRE_DATE) VALUES (103619471452799027, 1, null, '52', 103619467652759552, '103619467652759553', null, null, 1, '2021-05-13', TO_DATE('2021-03-11 14:39:11', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-03-11 14:56:01', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO ODS_SMS_USER_COUPON (USER_COUPON_ID, COUPON_TYPE, ORDER_SN, CUST_NO, COUPON_ID, BATCH_NO, ORDER_INTEGRATION, COUPON_NAME, STATUS, TX_DT, CREATE_TIME, UPDATE_TIME, EXPIRE_DATE) VALUES (103619471452799028, 1, null, '53', 103619467652759552, '103619467652759553', null, null, 1, '2021-05-13', TO_DATE('2021-03-11 14:39:11', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-03-11 14:56:01', 'YYYY-MM-DD HH24:MI:SS'), null);











