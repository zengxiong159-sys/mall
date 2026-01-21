create table OMS_REFUND_ERROR_LOG(
                                     REFUND_ERROR_LOG_ID NUMBER(20) not null
                                         primary key,
                                     CUST_NO NUMBER(32),
                                     REFUND_SERIAL NUMBER(20),
                                     ORDER_SN VARCHAR2(64),
                                     STATUS VARCHAR2(2) default '0',
                                     CREATE_TIME DATE,
                                     UPDATE_TIME DATE
);
comment on table OMS_REFUND_ERROR_LOG is '退款异常记录表';
comment on column OMS_REFUND_ERROR_LOG.REFUND_ERROR_LOG_ID is '主键';
comment on column OMS_REFUND_ERROR_LOG.REFUND_SERIAL is '退款流水号';
comment on column OMS_REFUND_ERROR_LOG.CUST_NO is '通联客户号';
comment on column OMS_REFUND_ERROR_LOG.ORDER_SN is '订单号';
comment on column OMS_REFUND_ERROR_LOG.STATUS is '状态';
comment on column OMS_REFUND_ERROR_LOG.CREATE_TIME is '创建时间      ';
comment on column OMS_REFUND_ERROR_LOG.UPDATE_TIME is '更新时间        ';
create index OMS_REFUND_ERROR_LOG_INDEX
    on OMS_REFUND_ERROR_LOG (ORDER_SN desc);
CREATE or replace PUBLIC SYNONYM OMS_REFUND_ERROR_LOG FOR OMS_REFUND_ERROR_LOG;
grant select,insert,update,delete on OMS_REFUND_ERROR_LOG to mallopr;
grant select on OMS_REFUND_ERROR_LOG to qdbankcx;