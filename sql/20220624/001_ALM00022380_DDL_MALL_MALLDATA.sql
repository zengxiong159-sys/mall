CREATE TABLE mq_send
(
id number(20) NOT NULL primary key,
unique_id VARCHAR2 (64) ,
create_time DATE default null
) initrans 6;

create index idx_mq_send_coupon_id on mq_send(unique_id) initrans 16;
