--创建自增序列 使用OPR用户执行
 create sequence mall_user_info_seq
 increment by 1
 start with 1
 minvalue 1
 maxvalue 999999999;

--使用malldata用户执行
create table ums_user_info
(
  id          NUMBER(20) not null,
  mobile    varchar2(20) default null,
  create_time date default null,
  update_time date default null,
  user_type varchar2(1) default  null
)initrans 6;
-- Add comments to the table
comment on table ums_user_info
  is '用户信息表';

COMMENT ON column  ums_user_info.id  is '用户编号';
COMMENT ON column  ums_user_info.mobile   is '手机号';
COMMENT ON column  ums_user_info.create_time   is '创建时间';
COMMENT ON column  ums_user_info.update_time   is '修改时间';
COMMENT ON column  ums_user_info.user_type   is '用户类型： 0 行员 1 白名单 2 非行员';

create index  idx_mobile on ums_user_info(mobile);
CREATE or replace PUBLIC SYNONYM ums_user_info FOR ums_user_info;
grant select,insert,update,delete on ums_user_info to amallopr;
grant select on ums_user_info to qdbank_cx;