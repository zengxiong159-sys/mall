alter table OMS_ORDER rename column RECHARGE_MOBILE to RECHARGE_ACCT_NO;

alter table OMS_RECHARGE_MOBILE_FLOW add RECHARGE_ACCT_NO varchar(256);

comment on column OMS_RECHARGE_MOBILE_FLOW.RECHARGE_ACCT_NO is '充值号码';
comment on column PMS_SKU_STOCK.SKU_STOCK_PARAM1 is '虚拟商品标识：1：是，0不是';