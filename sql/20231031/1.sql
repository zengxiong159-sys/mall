

create table CONFIG_CENTER(
                            ID              NUMBER(20)      not null primary key,
                            CONFIGY_TYPE             NUMBER(1)                         ,
                            TITLE           VARCHAR2(256)                       ,
                            STATUS           VARCHAR2(1) default 0,
                            START_TIME              DATE                     ,
                            END_TIME                DATE                         ,
                            CONTENT_DETAIL          CLOB                               ,
                            CREATE_TIME             DATE                               ,
                            UPDATE_TIME             DATE                               ,
                            CREATED_BY              VARCHAR2(64)                       ,
                            UPDATED_BY              VARCHAR2(64)
)initrans 6;
COMMENT ON TABLE CONFIG_CENTER IS '配置中心信息表';
COMMENT ON COLUMN CONFIG_CENTER.CONFIGY_TYPE           IS '配置类型： 1 新闻中心 2 小程序隐私协议 3 商城隐私协议';
COMMENT ON COLUMN CONFIG_CENTER.TITLE          IS '标题';
COMMENT ON COLUMN CONFIG_CENTER.STATUS          IS '状态 0 停用 1 启用';
COMMENT ON COLUMN CONFIG_CENTER.START_TIME        IS '生效开始时间';
COMMENT ON COLUMN CONFIG_CENTER.END_TIME         IS '结束时间';
COMMENT ON COLUMN CONFIG_CENTER.CONTENT_DETAIL        IS '内容详情';
COMMENT ON COLUMN CONFIG_CENTER.CREATE_TIME          IS '创建时间';
COMMENT ON COLUMN CONFIG_CENTER.UPDATE_TIME          IS '更新时间';
COMMENT ON COLUMN CONFIG_CENTER.CREATED_BY           IS '创建人';
COMMENT ON COLUMN CONFIG_CENTER.UPDATED_BY           IS '更新人';

create index idx_config_time on CONFIG_CENTER(update_time);
create index idx_type on CONFIG_CENTER(CONFIGY_TYPE);
CREATE or replace PUBLIC SYNONYM CONFIG_CENTER FOR CONFIG_CENTER;
grant select,insert,update,delete on CONFIG_CENTER to amallopr;
grant select on CONFIG_CENTER to qdbankcx;




CREATE TABLE cms_position (
  id number(20) NOT NULL primary key,
  position_name varchar2(256) default null,
  position_level number(2) default null,
  jump_url varchar2(1000) default null,
  pic_url varchar2(2000)default null,
  status number(2) default 0,
  group_id varchar2(1000),
  create_time date default null,
  update_time date default null,
  created_by varchar2(50) default null,
  updated_by varchar2(50) default null
) initrans 6;

COMMENT ON TABLE cms_position IS '金刚位信息表';
COMMENT ON COLUMN cms_position.id IS '主键';
COMMENT ON COLUMN cms_position.position_name IS '金刚位名称';
COMMENT ON COLUMN cms_position.position_level IS '优先级';
COMMENT ON COLUMN cms_position.jump_url IS '跳转url';
COMMENT ON COLUMN cms_position.pic_url IS '图片url';
COMMENT ON COLUMN cms_position.status IS '金刚位状态 0 停用 1 启用';
COMMENT ON COLUMN cms_position.group_id IS '文件组名';
COMMENT ON COLUMN cms_position.create_time IS '创建时间';
COMMENT ON COLUMN cms_position.update_time IS '修改时间';
COMMENT ON COLUMN cms_position.created_by IS '创建人';
COMMENT ON COLUMN cms_position.updated_by IS '修改人';

create index idx_position_level on cms_position(position_level);
create index idx_position_time on cms_position(create_time);
CREATE or replace PUBLIC SYNONYM cms_position FOR cms_position;
grant select,insert,update,delete on cms_position to amallopr;
grant select on cms_position to qdbankcx;






CREATE TABLE bank_node_address (
   id number(20) NOT NULL primary key,
   city_name varchar2(256) default null,
   branch_no varchar2(256) default null,
   branch_name varchar2(256) default null,
   branch_address varchar2(2000)default null,
   create_time date default null,
   update_time date default null,
   created_by varchar2(50) default null,
   updated_by varchar2(50) default null
) initrans 6;

COMMENT ON TABLE bank_node_address IS '银行网点信息表';
COMMENT ON COLUMN bank_node_address.id IS '主键';
COMMENT ON COLUMN bank_node_address.city_name IS '城市名称';
COMMENT ON COLUMN bank_node_address.branch_no IS '机构编号';
COMMENT ON COLUMN bank_node_address.branch_name IS '机构名称';
COMMENT ON COLUMN bank_node_address.branch_address IS '机构地址';
COMMENT ON COLUMN bank_node_address.create_time IS '创建时间';
COMMENT ON COLUMN bank_node_address.update_time IS '修改时间';
COMMENT ON COLUMN bank_node_address.created_by IS '创建人';
COMMENT ON COLUMN bank_node_address.updated_by IS '修改人';

create  unique index udx_branch_no on bank_node_address(branch_no);
create index idx_branch_name on bank_node_address(branch_name);
CREATE or replace PUBLIC SYNONYM bank_node_address FOR bank_node_address;
grant select,insert,update,delete on bank_node_address to amallopr;
grant select on bank_node_address to qdbankcx;