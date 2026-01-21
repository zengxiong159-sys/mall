CREATE TABLE oms_send_return
(
id number(20) NOT NULL primary key,
ORDER_SN VARCHAR2(64) ,
DELIVERY_COMPANY_NAME VARCHAR2(256)     ,
DELIVERY_SN VARCHAR2(64)     ,
return_reason varchar2(64) default null,
create_time DATE default null,
update_time DATE default null,
return_status  number(2) DEFAULT '0'
) initrans 6;

create index idx_send_return_order_sn on oms_send_return(ORDER_SN) initrans 16;
create index idx_send_return_create_time on oms_send_return(create_time) initrans 16;
