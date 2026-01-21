ALTER TABLE SMS_COUPON RENAME COLUMN REL_COUPON_ID to EXCHANGE_COUPON_ID;
COMMENT ON COLUMN SMS_COUPON.EXCHANGE_COUPON_ID IS '积分兑换优惠券编号';

CREATE TABLE oms_download
(
id number(20) NOT NULL primary key,
merchant_no   NUMBER(20) default NULL,
file_name VARCHAR2(256) NOT NULL,
file_url VARCHAR2(4000) default NULL,
file_type number(2) default null,
create_time DATE default null,
update_time DATE default null,
status  number(2) DEFAULT '0',
file_group  VARCHAR2(16) DEFAULT  NULL,
file_path VARCHAR2(2000) DEFAULT  NULL,
created_by varchar(64) default null
) initrans 6;

create index idx_download_file_name on oms_download(file_name) initrans 16;
create index idx_download_create_time on oms_download(create_time) initrans 16;
create index idx_download_created_by on oms_download (created_by);