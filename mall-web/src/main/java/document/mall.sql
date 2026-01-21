--创建自增序列
 create sequence mall_login_seq
 increment by 1
 start with 1
 minvalue 1
 maxvalue 999999999;

create sequence mall_merchant_seq
    increment by 1
    start with 60000
    minvalue 1
    maxvalue 999999999;
CREATE TABLE ums_admin
(
id number(20) NOT NULL primary key,
username VARCHAR2(64)NOT NULL,
password VARCHAR2(64) NOT NULL,
icon VARCHAR2(500) default NULL,
email VARCHAR2(100) default NULL,
nick_name VARCHAR2(200)default null,
note VARCHAR2(500) default NULL,
create_time DATE default null,
login_time DATE default null,
status  number(2) DEFAULT '1',
deptno number(20) default null,
mobile varchar2(16) default null,
created_by varchar(64) default null,
updated_by varchar(64) default null,
update_time DATE default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE ums_admin IS '后台用户表';
COMMENT ON COLUMN ums_admin.ID IS 'ID';
COMMENT ON COLUMN ums_admin.username IS '用户名';
COMMENT ON COLUMN ums_admin.password IS '密码';
COMMENT ON COLUMN ums_admin.icon IS '图标';
COMMENT ON COLUMN ums_admin.email IS '邮箱';
COMMENT ON COLUMN ums_admin.note IS '备注信息';
COMMENT ON COLUMN ums_admin.nick_name IS '姓名';
COMMENT ON COLUMN ums_admin.create_time IS '创建时间';
COMMENT ON COLUMN ums_admin.login_time IS '最后登录时间';
COMMENT ON COLUMN ums_admin.status IS '帐号启用状态：0->禁用；1->启用';
COMMENT ON COLUMN ums_admin.deptno IS '部门编号 ->部门表主键';
COMMENT ON column  ums_admin.mobile  is '手机号码';
COMMENT ON column  ums_admin.update_time   is '修改时间';
COMMENT ON column  ums_admin.created_by   is '创建人';
COMMENT ON column  ums_admin.updated_by   is '更新人';
-- index
create index idx_ums_admin_name on ums_admin(username) initrans 16;
create index idx_ums_admin_create_time on ums_admin(create_time) initrans 16;
create index idx_dept_no on ums_admin (deptno);
CREATE or replace PUBLIC SYNONYM ums_admin FOR ums_admin;
grant select,insert,update,delete on ums_admin to mallopr;
grant select on ums_admin to qdbankcx;

create table ums_department(
   id number(20) not null,
   name varchar2(256) not null,
   description varchar2(256) not null,
   create_time date default null,
   update_time date default null,
   created_by varchar2(64) default null,
   updated_by varchar2(64) default null
)initrans 6;
-- Add comments to the table
COMMENT ON TABLE ums_department IS '部门表';
COMMENT ON column  ums_department.id  is '部门编号';
COMMENT ON column  ums_department.name   is '部门名称';
COMMENT ON column  ums_department.create_time   is '创建时间';
COMMENT ON column  ums_department.update_time   is '修改时间';
COMMENT ON column  ums_department.created_by   is '创建人';
COMMENT ON column  ums_department.updated_by   is '更新人';

-- index
create index idx_ums_dept_name on ums_department(name) initrans 16;
create index idx_ums_dept_id on ums_department(ID) initrans 16;
CREATE or replace PUBLIC SYNONYM ums_department FOR ums_department;
grant select,insert,update,delete on ums_department to mallopr;
grant select on ums_department to qdbankcx;

--后台用户和角色关系表

CREATE TABLE ums_admin_role_relation
(
id number(20) NOT NULL,
admin_id VARCHAR2(20)DEFAULT NULL,
role_id VARCHAR2(20) DEFAULT NULL,
create_time date default null,
update_time date default null,
created_by varchar(64) default null,
updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE  ums_admin_role_relation IS '后台用户和角色关系表';
COMMENT ON COLUMN ums_admin_role_relation.ID IS 'ID';
COMMENT ON COLUMN ums_admin_role_relation.admin_id IS '用户id';
COMMENT ON COLUMN ums_admin_role_relation.role_id IS '角色id';
COMMENT ON column ums_admin_role_relation.create_time   is '创建时间';
COMMENT ON column ums_admin_role_relation.update_time   is '修改时间';
COMMENT ON column ums_admin_role_relation.created_by   is '创建人';
COMMENT ON column ums_admin_role_relation.updated_by   is '更新人';
-- index
create index idx_role_admin_id on ums_admin_role_relation(admin_id,role_id) initrans 16;
create index idx_role_id on ums_admin_role_relation(ID) initrans 16;
CREATE or replace PUBLIC SYNONYM ums_admin_role_relation FOR ums_admin_role_relation;
grant select,insert,update,delete on ums_admin_role_relation to mallopr;
grant select on ums_admin_role_relation to qdbankcx;

--后台菜单表
CREATE TABLE ums_menu
(
    id number(20) NOT NULL primary key,
    parent_id number(20)DEFAULT NULL,
    menu_level number(4)default null ,
    menu_sort number(4) default null,
    menu_name varchar2(256) default null,
    menu_icon varchar2(32) default null,
    route_name varchar2(256) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE ums_menu IS '后台菜单表';
COMMENT ON COLUMN ums_menu.ID IS '主键ID';
COMMENT ON COLUMN ums_menu.parent_id IS '父级菜单id';
COMMENT ON COLUMN ums_menu.create_time IS '创建时间';
COMMENT ON COLUMN ums_menu.menu_level is '菜单级数';
COMMENT ON COLUMN ums_menu.menu_sort is '菜单排序';
COMMENT ON COLUMN ums_menu.menu_name is '菜单名称';
COMMENT ON COLUMN ums_menu.menu_icon  IS '图标标识';
COMMENT ON COLUMN ums_menu.route_name  IS '前端路由名称';
COMMENT ON column ums_menu.create_time   is '创建时间';
COMMENT ON column ums_menu.update_time   is '修改时间';
COMMENT ON column ums_menu.created_by   is '创建人';
COMMENT ON column ums_menu.updated_by   is '更新人';
create index idx_menu_pid on ums_menu(parent_id);
create index idx_menu_create_time on ums_menu(create_time);
CREATE or replace PUBLIC SYNONYM ums_menu FOR ums_menu;
grant select,insert,update,delete on ums_menu to mallopr;
grant select on ums_menu to qdbankcx;

CREATE TABLE ums_resource
(
    id number(20) NOT NULL primary key,
    resource_url varchar2(200)default null ,
    resource_name varchar2(256) default null,
    resource_description varchar2(500) default null,
    category_id number(20) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null

) initrans 6;

-- Add comments to the table
COMMENT ON TABLE ums_resource IS '后台资源表';
COMMENT ON COLUMN ums_resource.ID IS 'ID';
COMMENT ON COLUMN ums_resource.resource_url IS '资源URL';
COMMENT ON COLUMN ums_resource.create_time IS '创建时间';
COMMENT ON COLUMN ums_resource.resource_name is '资源名称';
COMMENT ON COLUMN ums_resource.resource_description is '描述';
COMMENT ON COLUMN ums_resource.category_id is '资源分类表主键ID';
COMMENT ON column ums_resource.create_time   is '创建时间';
COMMENT ON column ums_resource.update_time   is '修改时间';
COMMENT ON column ums_resource.created_by   is '创建人';
COMMENT ON column ums_resource.updated_by   is '更新人';
-- index
create index idx_ums_resource_category_id on ums_resource(category_id) initrans 16;
create index idx_ums_resource_create_time on ums_resource (create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM ums_resource FOR ums_resource;
grant select,insert,update,delete on ums_resource to mallopr;
grant select on ums_resource to qdbankcx;
--资源分类表

CREATE TABLE ums_resource_category
(
    id number(20) NOT NULL primary key,
    name varchar2(100) default null,
    SORT number(4) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null

) initrans 6;

-- Add comments to the table
COMMENT ON TABLE ums_resource_category IS '资源分类表';
COMMENT ON COLUMN ums_resource_category.ID IS 'ID';
COMMENT ON COLUMN ums_resource_category.create_time IS '创建时间';
COMMENT ON COLUMN ums_resource_category.name is '分类名称';
COMMENT ON COLUMN ums_resource_category.SORT  is '排序';
COMMENT ON column ums_resource_category.create_time   is '创建时间';
COMMENT ON column ums_resource_category.update_time   is '修改时间';
COMMENT ON column ums_resource_category.created_by   is '创建人';
COMMENT ON column ums_resource_category.updated_by   is '更新人';
create index idx_resource_category_create on ums_resource_category (create_time);
CREATE or replace PUBLIC SYNONYM ums_resource_category FOR ums_resource_category;
grant select,insert,update,delete on ums_resource_category to mallopr;
grant select on ums_resource_category to qdbankcx;

--后台用户角色表


CREATE TABLE ums_role
(
    id number(20) NOT NULL primary key ,
    role_name varchar2(256) default null,
    role_description varchar2(500) default null,
    create_time date DEFAULT NULL,
    status number(1) default 1,
    role_sort number(4) default 0,
    update_time date default null,
    created_by varchar2(50) default null,
    updated_by varchar2(50) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE ums_role IS '后台用户角色表';
COMMENT ON COLUMN ums_role.ID IS 'ID';
COMMENT ON COLUMN ums_role.create_time IS '创建时间';
COMMENT ON COLUMN ums_role.role_name is '角色名称';
COMMENT ON COLUMN ums_role.role_sort is '角色排序';
COMMENT ON COLUMN ums_role.role_description is '描述';
COMMENT ON COLUMN ums_role.status is '启用状态：0->禁用；1->启用';
COMMENT ON COLUMN ums_role.update_time  is '修改时间';
COMMENT ON COLUMN ums_role.created_by  is '创建人';
COMMENT ON COLUMN ums_role.updated_by  is '修改人';

-- index
create index idx_ums_role_create_time on ums_role(create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM ums_role FOR ums_role;
grant select,insert,update,delete on ums_role to mallopr;
grant select on ums_role to qdbankcx;


--后台角色菜单关系表

CREATE TABLE ums_role_menu_relation
(
    id number(20) NOT NULL,
    role_id number(20) default null,
    menu_id number(20)default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE ums_role_menu_relation IS '后台角色菜单关系表';
COMMENT ON COLUMN ums_role_menu_relation.ID IS 'ID';
COMMENT ON COLUMN ums_role_menu_relation.role_id IS '角色ID';
COMMENT ON COLUMN ums_role_menu_relation.menu_id is '菜单ID';
COMMENT ON column ums_role_menu_relation.create_time   is '创建时间';
COMMENT ON column ums_role_menu_relation.update_time   is '修改时间';
COMMENT ON column ums_role_menu_relation.created_by   is '创建人';
COMMENT ON column ums_role_menu_relation.updated_by   is '更新人';
-- index
create index idx_role_menu_id on ums_role_menu_relation(role_id,menu_id) initrans 16;
CREATE or replace PUBLIC SYNONYM ums_role_menu_relation FOR ums_role_menu_relation;
grant select,insert,update,delete on ums_role_menu_relation to mallopr;
grant select on ums_role_menu_relation to qdbankcx;
--
CREATE TABLE ums_role_resource_relation
(
    id number(20) NOT NULL,
    role_id number(20) default null,
    resource_id number(20)default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE ums_role_resource_relation IS '后台角色资源关系表';
COMMENT ON COLUMN ums_role_resource_relation.ID IS 'ID';
COMMENT ON COLUMN ums_role_resource_relation.role_id IS '角色ID';
COMMENT ON COLUMN ums_role_resource_relation.resource_id is '资源ID';
COMMENT ON column ums_role_resource_relation.create_time   is '创建时间';
COMMENT ON column ums_role_resource_relation.update_time   is '修改时间';
COMMENT ON column ums_role_resource_relation.created_by   is '创建人';
COMMENT ON column ums_role_resource_relation.updated_by   is '更新人';
-- index
create index idx_role_resource_id on ums_role_resource_relation(role_id,resource_id) initrans 16;
CREATE or replace PUBLIC SYNONYM ums_role_resource_relation FOR ums_role_resource_relation;
grant select,insert,update,delete on ums_role_resource_relation to mallopr;
grant select on ums_role_resource_relation to qdbankcx;


CREATE TABLE cms_admin
(
id number(20) NOT NULL primary key,
username VARCHAR2(64)NOT NULL,
password VARCHAR2(64) NOT NULL,
icon VARCHAR2(500) default NULL,
email VARCHAR2(100) default NULL,
nick_name VARCHAR2(256)default null,
note VARCHAR2(500) default NULL,
create_time DATE default null,
login_time DATE default null,
status  number(1) DEFAULT '1',
deptno number(20) default null,
mobile varchar2(16) default null,
created_by varchar(64) default null,
updated_by varchar(64) default null,
update_time DATE default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE cms_admin IS '后台用户表';
COMMENT ON COLUMN cms_admin.ID IS 'ID';
COMMENT ON COLUMN cms_admin.username IS '用户名';
COMMENT ON COLUMN cms_admin.password IS '密码';
COMMENT ON COLUMN cms_admin.icon IS '图标';
COMMENT ON COLUMN cms_admin.email IS '邮箱';
COMMENT ON COLUMN cms_admin.note IS '备注信息';
COMMENT ON COLUMN cms_admin.nick_name IS '姓名';
COMMENT ON COLUMN cms_admin.create_time IS '创建时间';
COMMENT ON COLUMN cms_admin.login_time IS '最后登录时间';
COMMENT ON COLUMN cms_admin.status IS '帐号启用状态：0->禁用；1->启用';
COMMENT ON COLUMN cms_admin.deptno IS '部门编号 ->部门表主键';
COMMENT ON column  cms_admin.mobile  is '手机号码';
COMMENT ON column  cms_admin.update_time   is '修改时间';
COMMENT ON column  cms_admin.created_by   is '创建人';
COMMENT ON column  cms_admin.updated_by   is '更新人';
-- index
create index idx_cms_admin_name on cms_admin(username) initrans 16;
create index idx_cms_admin_create_time on cms_admin(create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM cms_admin FOR cms_admin;
grant select,insert,update,delete on cms_admin to mallopr;
grant select on cms_admin to qdbankcx;


create table cms_department(
   id number(20) not null,
   name varchar(256) not null,
   description varchar2(256) not null,
   create_time date default null,
   update_time date default null,
   created_by varchar(64) default null,
   updated_by varchar(64) default null
)initrans 6;
-- Add comments to the table
COMMENT ON TABLE cms_department IS '部门表';
COMMENT ON column  cms_department.id  is '部门编号';
COMMENT ON column  cms_department.name   is '部门名称';
COMMENT ON column  cms_department.create_time   is '创建时间';
COMMENT ON column  cms_department.update_time   is '修改时间';
COMMENT ON column  cms_department.created_by   is '创建人';
COMMENT ON column  cms_department.updated_by   is '更新人';

-- index
create index idx_cms_dept_name on cms_department(name) initrans 16;
create index idx_cms_dept_id on cms_department(ID) initrans 16;
CREATE or replace PUBLIC SYNONYM cms_department FOR cms_department;
grant select,insert,update,delete on cms_department to mallopr;
grant select on cms_department to qdbankcx;

--后台用户和角色关系表

CREATE TABLE cms_admin_role_relation
(
id number(20) NOT NULL,
admin_id VARCHAR2(20)DEFAULT NULL,
role_id VARCHAR2(20) DEFAULT NULL,
create_time date default null,
update_time date default null,
created_by varchar(64) default null,
updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE  cms_admin_role_relation IS '后台用户和角色关系表';
COMMENT ON COLUMN cms_admin_role_relation.ID IS 'ID';
COMMENT ON COLUMN cms_admin_role_relation.admin_id IS '用户id';
COMMENT ON COLUMN cms_admin_role_relation.role_id IS '角色id';
COMMENT ON column cms_admin_role_relation.create_time   is '创建时间';
COMMENT ON column cms_admin_role_relation.update_time   is '修改时间';
COMMENT ON column cms_admin_role_relation.created_by   is '创建人';
COMMENT ON column cms_admin_role_relation.updated_by   is '更新人';
-- index
create index idx_role_cms_admin_id on cms_admin_role_relation(admin_id,role_id) initrans 16;
create index idx_role_cms_id on cms_admin_role_relation(ID) initrans 16;
CREATE or replace PUBLIC SYNONYM cms_admin_role_relation FOR cms_admin_role_relation;
grant select,insert,update,delete on cms_admin_role_relation to mallopr;
grant select on cms_admin_role_relation to qdbankcx;

--后台菜单表
CREATE TABLE cms_menu
(
    id number(20) NOT NULL primary key,
    parent_id number(20)DEFAULT NULL,
    menu_level number(4)default null ,
    menu_sort number(4) default null,
    menu_name varchar2(256) default null,
    menu_icon varchar2(32) default null,
    route_name varchar2(32) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE cms_menu IS '后台菜单表';
COMMENT ON COLUMN cms_menu.ID IS '主键ID';
COMMENT ON COLUMN cms_menu.parent_id IS '父级菜单id';
COMMENT ON COLUMN cms_menu.create_time IS '创建时间';
COMMENT ON COLUMN cms_menu.menu_level is '菜单级数';
COMMENT ON COLUMN cms_menu.menu_sort is '菜单排序';
COMMENT ON COLUMN cms_menu.menu_name is '菜单名称';
COMMENT ON COLUMN cms_menu.menu_icon  IS '图标标识';
COMMENT ON COLUMN cms_menu.route_name  IS '前端路由名称';
COMMENT ON column cms_menu.create_time   is '创建时间';
COMMENT ON column cms_menu.update_time   is '修改时间';
COMMENT ON column cms_menu.created_by   is '创建人';
COMMENT ON column cms_menu.updated_by   is '更新人';
create index idx_cms_menu_pid on cms_menu(parent_id);
create index idx_cms_menu_create_time on cms_menu(create_time);
CREATE or replace PUBLIC SYNONYM cms_menu FOR cms_menu;
grant select,insert,update,delete on cms_menu to mallopr;
grant select on cms_menu to qdbankcx;

CREATE TABLE cms_resource
(
    id number(20) NOT NULL primary key,
    resource_url varchar2(200)default null ,
    resource_name varchar2(256) default null,
    resource_description varchar2(500) default null,
    category_id number(20) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null

) initrans 6;

-- Add comments to the table
COMMENT ON TABLE cms_resource IS '后台资源表';
COMMENT ON COLUMN cms_resource.ID IS 'ID';
COMMENT ON COLUMN cms_resource.resource_url IS '资源URL';
COMMENT ON COLUMN cms_resource.create_time IS '创建时间';
COMMENT ON COLUMN cms_resource.resource_name is '资源名称';
COMMENT ON COLUMN cms_resource.resource_description is '描述';
COMMENT ON COLUMN cms_resource.category_id is '资源分类表主键ID';
COMMENT ON column cms_resource.create_time   is '创建时间';
COMMENT ON column cms_resource.update_time   is '修改时间';
COMMENT ON column cms_resource.created_by   is '创建人';
COMMENT ON column cms_resource.updated_by   is '更新人';
-- index
create index idx_cms_resource_category_id on cms_resource(category_id) initrans 16;
create index idx_cms_resource_create_time on cms_resource (create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM cms_resource FOR cms_resource;
grant select,insert,update,delete on cms_resource to mallopr;
grant select on cms_resource to qdbankcx;
--资源分类表

CREATE TABLE cms_resource_category
(
    id number(20) NOT NULL primary key,
    name varchar2(256) default null,
    SORT number(4) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null

) initrans 6;

-- Add comments to the table
COMMENT ON TABLE cms_resource_category IS '资源分类表';
COMMENT ON COLUMN cms_resource_category.ID IS 'ID';
COMMENT ON COLUMN cms_resource_category.create_time IS '创建时间';
COMMENT ON COLUMN cms_resource_category.name is '分类名称';
COMMENT ON COLUMN cms_resource_category.SORT  is '排序';
COMMENT ON column cms_resource_category.create_time   is '创建时间';
COMMENT ON column cms_resource_category.update_time   is '修改时间';
COMMENT ON column cms_resource_category.created_by   is '创建人';
COMMENT ON column cms_resource_category.updated_by   is '更新人';
create index idx_cms_resource_create on cms_resource_category (create_time);
CREATE or replace PUBLIC SYNONYM cms_resource_category FOR cms_resource_category;
grant select,insert,update,delete on cms_resource_category to mallopr;
grant select on cms_resource_category to qdbankcx;

--后台用户角色表


CREATE TABLE cms_role
(
    id number(20) NOT NULL primary key ,
    role_name varchar2(256) default null,
    role_description varchar2(500) default null,
    create_time date DEFAULT NULL,
    status number(1) default 1,
    role_sort number(4) default 0,
    update_time date default null,
    created_by varchar2(50) default null,
    updated_by varchar2(50) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE cms_role IS '后台用户角色表';
COMMENT ON COLUMN cms_role.ID IS 'ID';
COMMENT ON COLUMN cms_role.create_time IS '创建时间';
COMMENT ON COLUMN cms_role.role_name is '角色名称';
COMMENT ON COLUMN cms_role.role_sort is '角色排序';
COMMENT ON COLUMN cms_role.role_description is '描述';
COMMENT ON COLUMN cms_role.status is '启用状态：0->禁用；1->启用';
COMMENT ON COLUMN cms_role.update_time  is '修改时间';
COMMENT ON COLUMN cms_role.created_by  is '创建人';
COMMENT ON COLUMN cms_role.updated_by  is '修改人';

-- index
create index idx_cms_role_create_time on cms_role(create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM cms_role FOR cms_role;
grant select,insert,update,delete on cms_role to mallopr;
grant select on cms_role to qdbankcx;


--后台角色菜单关系表

CREATE TABLE cms_role_menu_relation
(
    id number(20) NOT NULL,
    role_id number(20) default null,
    menu_id number(20)default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE cms_role_menu_relation IS '后台角色菜单关系表';
COMMENT ON COLUMN cms_role_menu_relation.ID IS 'ID';
COMMENT ON COLUMN cms_role_menu_relation.role_id IS '角色ID';
COMMENT ON COLUMN cms_role_menu_relation.menu_id is '菜单ID';
COMMENT ON column cms_role_menu_relation.create_time   is '创建时间';
COMMENT ON column cms_role_menu_relation.update_time   is '修改时间';
COMMENT ON column cms_role_menu_relation.created_by   is '创建人';
COMMENT ON column cms_role_menu_relation.updated_by   is '更新人';
-- index
create index idx_cms_role_menu_id on cms_role_menu_relation(role_id,menu_id) initrans 16;
CREATE or replace PUBLIC SYNONYM cms_role_menu_relation FOR cms_role_menu_relation;
grant select,insert,update,delete on cms_role_menu_relation to mallopr;
grant select on cms_role_menu_relation to qdbankcx;
--
CREATE TABLE cms_role_resource_relation
(
    id number(20) NOT NULL,
    role_id number(20) default null,
    resource_id number(20)default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE cms_role_resource_relation IS '后台角色资源关系表';
COMMENT ON COLUMN cms_role_resource_relation.ID IS 'ID';
COMMENT ON COLUMN cms_role_resource_relation.role_id IS '角色ID';
COMMENT ON COLUMN cms_role_resource_relation.resource_id is '资源ID';
COMMENT ON column cms_role_resource_relation.create_time   is '创建时间';
COMMENT ON column cms_role_resource_relation.update_time   is '修改时间';
COMMENT ON column cms_role_resource_relation.created_by   is '创建人';
COMMENT ON column cms_role_resource_relation.updated_by   is '更新人';
-- index
create index idx_cms_role_resource_id on cms_role_resource_relation(role_id,resource_id) initrans 16;
CREATE or replace PUBLIC SYNONYM cms_role_resource_relation FOR cms_role_resource_relation;
grant select,insert,update,delete on cms_role_resource_relation to mallopr;
grant select on cms_role_resource_relation to qdbankcx;

create table cms_admin_login_log
(
  id          NUMBER(20) not null,
  admin_id    number(20)default null,
  create_time date default null,
  ip    varchar2(64) default null,
  address varchar2(100)default null,
  user_agent varchar2(100) default null
)initrans 6;
-- Add comments to the table
comment on table cms_admin_login_log
  is '后台用户登录日志表';
create index  idx_cms_admin_id on cms_admin_login_log(admin_id);
create index idx_cms_log_create_time on cms_admin_login_log(create_time);
CREATE or replace PUBLIC SYNONYM cms_admin_login_log FOR cms_admin_login_log;
grant select,insert,update,delete on cms_admin_login_log to mallopr;
grant select on cms_admin_login_log to qdbankcx;

create table ums_admin_login_log
(
  id          NUMBER(20) not null,
  admin_id    number(20)default null,
  create_time date default null,
  ip    varchar2(64) default null,
  address varchar2(100)default null,
  user_agent varchar2(100) default null
)initrans 6;
-- Add comments to the table
comment on table ums_admin_login_log
  is '后台用户登录日志表';
create index  idx_admin_id on ums_admin_login_log(admin_id);
create index idx_ums_log_create_time on ums_admin_login_log(create_time);
CREATE or replace PUBLIC SYNONYM ums_admin_login_log FOR ums_admin_login_log;
grant select,insert,update,delete on ums_admin_login_log to mallopr;
grant select on ums_admin_login_log to qdbankcx;







--定时任务




CREATE TABLE oms_order
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
    REQ_URL                     VARCHAR2(2000)   ,
    ACCESS_SIGN_ID              VARCHAR2(100),
    EXCHANGE_COUPON_ID          NUMBER(20),
    RECHARGE_REMARK VARCHAR2(2000),
	RECHARGE_SEQ VARCHAR2(30)
) initrans 6;

COMMENT ON TABLE oms_order IS '订单表';
COMMENT ON COLUMN oms_order.ORDER_ID                  IS '订单主键 ';
COMMENT ON COLUMN oms_order.CUST_NO                   IS '通联客户号';
COMMENT ON COLUMN oms_order.CUST_NAME                 IS '通联核心用户姓名';
COMMENT ON COLUMN oms_order.CUST_MOBILE               IS '"通联客户号对应的银行预留手机号"';
COMMENT ON COLUMN oms_order.MERCHANT_NO               IS '商户编号';
COMMENT ON COLUMN oms_order.MERCHANT_NAME             IS '商户名称';
COMMENT ON COLUMN oms_order.ORDER_SN                  IS '订单编号';
COMMENT ON COLUMN oms_order.PRODUCT_ID                IS '商品编号';
COMMENT ON COLUMN oms_order.PRODUCT_NAME              IS '商品名称';
COMMENT ON COLUMN oms_order.PRODUCT_SKU_ID            IS '规格编号';
COMMENT ON COLUMN oms_order.PRODUCT_COUNT             IS '商品数量';
COMMENT ON COLUMN oms_order.PRODUCT_PRICE             IS '商品折算价 ';
COMMENT ON COLUMN oms_order.PRODUCT_CASH              IS '商品售价中现金金额';
COMMENT ON COLUMN oms_order.PRODUCT_INTEGRATION       IS '商品售价中积分量';
COMMENT ON COLUMN oms_order.CATEGORY_ID_1             IS '商品一级分类编号';
COMMENT ON COLUMN oms_order.CATEGORY_ID_2             IS '商品二级分类编号';
COMMENT ON COLUMN oms_order.CATEGORY_ID_3             IS '商品三级分类编号';
COMMENT ON COLUMN oms_order.CATEGORY_ID_4             IS '商品四级分类编号';
COMMENT ON COLUMN oms_order.PAY_AMOUNT                IS '订单实付款(折算价)';
COMMENT ON COLUMN oms_order.ORDER_CASH                IS '订单现金:包含商品售价中现金金额-优惠金额，不包含运费金额';
COMMENT ON COLUMN oms_order.ORDER_INTEGRATION         IS '订单积分';
COMMENT ON COLUMN oms_order.FREIGHT_AMOUNT            IS '运费金额';
COMMENT ON COLUMN oms_order.USER_COUPON_ID            IS '用户优惠券编号';
COMMENT ON COLUMN oms_order.COUPON_TYPE               IS '优惠券类型：0 积分兑换券 1 指定商品免费兑换券';
COMMENT ON COLUMN oms_order.COUPON_AMOUNT             IS '优惠券面值金额';
COMMENT ON COLUMN oms_order.PAY_TYPE                  IS '支付方式： 0 纯积分  1 纯现金 2 积分+现金';
comment on column oms_order.status is '订单状态：(根据商品类型区分）实物订单：0 待支付   1 待发货 2 已发货 3已完成  4 已关闭  话费订单：0 待支付 6 充值中  3 已完成 4 已关闭 积分券兑换订单：0 待支付 7 待使用 8 已使用 4 已关闭 5 已过期';
COMMENT ON COLUMN oms_order.CLOSE_TYPE                IS '订单关闭类型：-1 初始化 0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭 4充值失败关闭';
COMMENT ON COLUMN oms_order.REFUND_STATUS             IS '退款状态-1 初始化 0 待审核 1 审核通过 2 退款成功 3审核不通过 4 退款申请撤销  ';
COMMENT ON COLUMN oms_order.PRODUCT_TYPE              IS '商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券';
COMMENT ON COLUMN oms_order.DELIVERY_COMPANY_NAME     IS '物流公司名称';
COMMENT ON COLUMN oms_order.DELIVERY_SN               IS '物流单号';
COMMENT ON COLUMN oms_order.RECEIVER_NAME             IS '收货人姓名';
COMMENT ON COLUMN oms_order.RECEIVER_PHONE            IS '收货人电话';
COMMENT ON COLUMN oms_order.RECEIVER_PROVINCE         IS '省份直辖市';
COMMENT ON COLUMN oms_order.RECEIVER_CITY             IS '城市';
COMMENT ON COLUMN oms_order.RECEIVER_REGION           IS '区县';
COMMENT ON COLUMN oms_order.RECEIVER_DETAIL_ADDRESS   IS '详细地址 ';
COMMENT ON COLUMN oms_order.NOTE                      IS '订单备注 ';
COMMENT ON COLUMN oms_order.CONFIRM_STATUS            IS '确认收货状态：0 未确认1 已确认';
COMMENT ON COLUMN oms_order.INTEGRATION_PAY_FLAG      IS '积分结算标识：0 不结算 1 需要结算';
COMMENT ON COLUMN oms_order.PAYMENT_TIME              IS '支付完成时间';
COMMENT ON COLUMN oms_order.DELIVERY_TIME             IS '发货时间  ';
COMMENT ON COLUMN oms_order.RECEIVE_TIME              IS '确认收货时间';
COMMENT ON COLUMN oms_order.RECHARGE_MOBILE           IS '充值账号';
COMMENT ON COLUMN oms_order.MOBILE_ADDRESS            IS '号码归属地 ';
COMMENT ON COLUMN oms_order.NOTICE_STATUS             IS '通知状态 0 未通知 1 已通知';
COMMENT ON COLUMN oms_order.CREATE_TIME               IS '订单创建时间      ';
COMMENT ON COLUMN oms_order.UPDATE_TIME               IS '更新时间        ';
COMMENT ON COLUMN oms_order.REQ_URL                   IS '行内准入接口返回url ';
COMMENT ON COLUMN oms_order.ACCESS_SIGN_ID            IS '行内准入接口返回准入标识';
COMMENT ON COLUMN oms_order.EXCHANGE_COUPON_ID        IS '积分兑换优惠券编号';
COMMENT ON COLUMN OMS_ORDER.RECHARGE_REMARK is '充值原因';
COMMENT ON COLUMN OMS_ORDER.RECHARGE_SEQ is '充值流水';
-- index
create index oms_order_order_no on oms_order(order_sn) initrans 16;
create index oms_order_create_time on oms_order(create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM oms_order FOR oms_order;
grant select,insert,update,delete on oms_order to mallopr;
grant select on oms_order to qdbankcx;

CREATE TABLE oms_order_report
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
    RECHARGE_MOBILE             VARCHAR2(256)    ,
    MOBILE_ADDRESS              VARCHAR2(64)     ,
    NOTICE_STATUS               NUMBER(1)        ,
    CREATE_TIME                 DATE             ,
    UPDATE_TIME                 DATE             ,
    REQ_URL                     VARCHAR2(2000)   ,
    ACCESS_SIGN_ID              VARCHAR2(100)
) initrans 6;

COMMENT ON TABLE oms_order_report IS '订单表报表信息表';
COMMENT ON COLUMN oms_order_report.ORDER_ID                  IS '订单主键 ';
COMMENT ON COLUMN oms_order_report.CUST_NO                   IS '通联客户号';
COMMENT ON COLUMN oms_order_report.CUST_NAME                 IS '通联核心用户姓名';
COMMENT ON COLUMN oms_order_report.CUST_MOBILE               IS '"通联客户号对应的银行预留手机号"';
COMMENT ON COLUMN oms_order_report.MERCHANT_NO               IS '商户编号';
COMMENT ON COLUMN oms_order_report.MERCHANT_NAME             IS '商户名称';
COMMENT ON COLUMN oms_order_report.ORDER_SN                  IS '订单编号';
COMMENT ON COLUMN oms_order_report.PRODUCT_ID                IS '商品编号';
COMMENT ON COLUMN oms_order_report.PRODUCT_NAME              IS '商品名称';
COMMENT ON COLUMN oms_order_report.PRODUCT_SKU_ID            IS '规格编号';
COMMENT ON COLUMN oms_order_report.PRODUCT_COUNT             IS '商品数量';
COMMENT ON COLUMN oms_order_report.PRODUCT_PRICE             IS '商品折算价 ';
COMMENT ON COLUMN oms_order_report.PRODUCT_CASH              IS '商品售价中现金金额';
COMMENT ON COLUMN oms_order_report.PRODUCT_INTEGRATION       IS '商品售价中积分量';
COMMENT ON COLUMN oms_order_report.CATEGORY_ID_1             IS '商品一级分类编号';
COMMENT ON COLUMN oms_order_report.CATEGORY_ID_2             IS '商品二级分类编号';
COMMENT ON COLUMN oms_order_report.CATEGORY_ID_3             IS '商品三级分类编号';
COMMENT ON COLUMN oms_order_report.CATEGORY_ID_4             IS '商品四级分类编号';
COMMENT ON COLUMN oms_order_report.PAY_AMOUNT                IS '订单实付款(折算价)';
COMMENT ON COLUMN oms_order_report.ORDER_CASH                IS '订单现金:包含商品售价中现金金额+运费金额-优惠金额';
COMMENT ON COLUMN oms_order_report.ORDER_INTEGRATION         IS '订单积分';
COMMENT ON COLUMN oms_order_report.FREIGHT_AMOUNT            IS '运费金额';
COMMENT ON COLUMN oms_order_report.USER_COUPON_ID            IS '用户优惠券编号';
COMMENT ON COLUMN oms_order_report.COUPON_TYPE               IS '优惠券类型：0 积分兑换券 1 指定商品免费兑换券';
COMMENT ON COLUMN oms_order_report.COUPON_AMOUNT             IS '优惠券面值金额';
COMMENT ON COLUMN oms_order_report.PAY_TYPE                  IS '支付方式： 0 纯积分  1 纯现金 2 积分+现金';
COMMENT ON COLUMN oms_order_report.STATUS                    IS '订单状态：(根据商品类型区分）实物订单：0 待支付1 待发货2 已发货3 4 已关闭  话费订单：0 待支付1 充值中3 已完成4 已关闭 积分券兑换订单：0 待支付2 待使用3 已使用4 已关闭5 已过期';
COMMENT ON COLUMN oms_order_report.CLOSE_TYPE                IS '订单关闭类型：0 用户取消关闭 1 超时自动关闭 2 支付失败关闭 3 退款成功关闭';
COMMENT ON COLUMN oms_order_report.REFUND_STATUS             IS '退款状态-1 初始化 0 待审核 1 审核通过 2 退款成功 3审核不通过 4 退款申请撤销  ';
COMMENT ON COLUMN oms_order_report.PRODUCT_TYPE              IS '商品类型：0 实物1 话费充值2 油卡充值3 视频会员充值4 积分兑换券';
COMMENT ON COLUMN oms_order_report.DELIVERY_COMPANY_NAME     IS '物流公司名称';
COMMENT ON COLUMN oms_order_report.DELIVERY_SN               IS '物流单号';
COMMENT ON COLUMN oms_order_report.RECEIVER_NAME             IS '收货人姓名';
COMMENT ON COLUMN oms_order_report.RECEIVER_PHONE            IS '收货人电话';
COMMENT ON COLUMN oms_order_report.RECEIVER_PROVINCE         IS '省份直辖市';
COMMENT ON COLUMN oms_order_report.RECEIVER_CITY             IS '城市';
COMMENT ON COLUMN oms_order_report.RECEIVER_REGION           IS '区县';
COMMENT ON COLUMN oms_order_report.RECEIVER_DETAIL_ADDRESS   IS '详细地址 ';
COMMENT ON COLUMN oms_order_report.NOTE                      IS '订单备注 ';
COMMENT ON COLUMN oms_order_report.CONFIRM_STATUS            IS '确认收货状态：0 未确认1 已确认';
COMMENT ON COLUMN oms_order_report.INTEGRATION_PAY_FLAG      IS '积分结算标识：0 不结算 1 需要结算';
COMMENT ON COLUMN oms_order_report.PAYMENT_TIME              IS '支付完成时间';
COMMENT ON COLUMN oms_order_report.DELIVERY_TIME             IS '发货时间  ';
COMMENT ON COLUMN oms_order_report.RECEIVE_TIME              IS '确认收货时间';
COMMENT ON COLUMN oms_order_report.RECHARGE_MOBILE           IS '充值账号';
COMMENT ON COLUMN oms_order_report.MOBILE_ADDRESS            IS '号码归属地 ';
COMMENT ON COLUMN oms_order_report.NOTICE_STATUS             IS '通知状态 0 未通知 1 已通知';
COMMENT ON COLUMN oms_order_report.CREATE_TIME               IS '订单创建时间      ';
COMMENT ON COLUMN oms_order_report.UPDATE_TIME               IS '更新时间        ';
COMMENT ON COLUMN oms_order_report.REQ_URL                   IS '行内准入接口返回url ';
COMMENT ON COLUMN oms_order_report.ACCESS_SIGN_ID            IS '行内准入接口返回准入标识';
-- index
create index oms_order_report_no on oms_order_report(order_sn) initrans 16;
create index oms_order_report_time on oms_order_report(create_time) initrans 16;
CREATE or replace PUBLIC SYNONYM oms_order_report FOR oms_order_report;
grant select,insert,update,delete on oms_order_report to mallopr;
grant select on oms_order_report to qdbankcx;

CREATE TABLE pms_product_category(
 id number(20) NOT NULL ,
 parent_id number(20) DEFAULT NULL ,
 category_name varchar2(256) DEFAULT NULL,
 category_level number(1) DEFAULT NULL,
 category_sort number(11) DEFAULT 0,
 category_type number(1) DEFAULT NULL,
 create_time date default null,
 update_time date default null,
 created_by varchar(64) default null,
 updated_by varchar(64) default null

) initrans 6;
-- Add comments to the table
comment on table pms_product_category
    is '商品分类';
COMMENT ON COLUMN pms_product_category.id IS '主键ID';
COMMENT ON COLUMN pms_product_category.parent_id IS '分类的编号：0表示一级分类';
COMMENT ON COLUMN pms_product_category.category_name IS '分类名称';
COMMENT ON COLUMN pms_product_category.category_level IS '分类级别：0->一级；1->二级；2->三级 3->四级';
COMMENT ON COLUMN pms_product_category.category_sort IS '排序';
COMMENT ON COLUMN pms_product_category.category_type IS '类别 0 ->实物商品 ； 1->虚拟商品';
COMMENT ON column pms_product_category.create_time   is '创建时间';
COMMENT ON column pms_product_category.update_time   is '修改时间';
COMMENT ON column pms_product_category.created_by   is '创建人';
COMMENT ON column pms_product_category.updated_by   is '更新人';
create index pms_product_category_id on pms_product_category(id) initrans 16;
create index pms_product_category_pid on pms_product_category(parent_id) initrans 16;
CREATE or replace PUBLIC SYNONYM pms_product_category FOR pms_product_category;
grant select,insert,update,delete on pms_product_category to mallopr;
grant select on pms_product_category to qdbankcx;



CREATE TABLE pms_freight_template (
  freight_template_id number(20) NOT NULL primary key,
  merchant_no number(20) default null,
  template_name varchar2(256) DEFAULT NULL,
  free_flag number(1) default null,
  charge_rule number(1)  default null,
  transfer_type number(1) default null,
  default_product_count varchar2(50) default null,
  default_charge NUMBER(8,2) default null,
  default_add_product_count varchar2(50) default null,
  default_add_product_charge NUMBER(8,2) default null,
  status number(2) default null,
  create_time date default null,
  update_time date default null,
  created_by varchar(64) default null,
  updated_by varchar(64) default null
) initrans 6;

COMMENT ON TABLE pms_freight_template IS '运费模板配置表';
COMMENT ON COLUMN pms_freight_template.freight_template_id IS '运费模板编号';
COMMENT ON COLUMN pms_freight_template.merchant_no IS '商户编号-》商户表主键';
COMMENT ON COLUMN pms_freight_template.template_name  is '模板名称';
COMMENT ON COLUMN pms_freight_template.free_flag is '是否包邮 0 包邮 1 不包邮';
COMMENT ON COLUMN pms_freight_template.charge_rule is '计费规则 0 按件计费 1 按重量';
COMMENT ON COLUMN pms_freight_template.transfer_type is '运送方式 0 快递 1 其他';
COMMENT ON COLUMN pms_freight_template.default_product_count is '默认件数';
COMMENT ON COLUMN pms_freight_template.default_charge is '默认金额';
COMMENT ON COLUMN pms_freight_template.default_add_product_count is '增加件数';
COMMENT ON COLUMN pms_freight_template.default_add_product_charge is '增加金额';
COMMENT ON COLUMN pms_freight_template.status is '状态 0 启用 1 禁用';
COMMENT ON column pms_freight_template.create_time   is '创建时间';
COMMENT ON column pms_freight_template.update_time   is '修改时间';
COMMENT ON column pms_freight_template.created_by   is '创建人';
COMMENT ON column pms_freight_template.updated_by   is '更新人';
create unique index idx_freight_name_merchant_no on pms_freight_template(merchant_no,template_name);
create index idx_freight_create_time on pms_freight_template(create_time);
CREATE or replace PUBLIC SYNONYM pms_freight_template FOR pms_freight_template;
grant select,insert,update,delete on pms_freight_template to mallopr;
grant select on pms_freight_template to qdbankcx;

CREATE TABLE pms_area_freight_template (
    id number(20) NOT NULL primary key,
    freight_template_id number(20) not null,
    province_ids varchar2(64) not null,
    transfer_flag number(1) default null,
    count_unit varchar2(20)  default null,
    charge_unit varchar2(20) default null,
    add_count varchar2(20) default null,
    add_charge varchar2(20) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar(64) default null,
    updated_by varchar(64) default null
) initrans 6;

COMMENT ON TABLE pms_area_freight_template IS '指定地区设置模板表';
COMMENT ON COLUMN pms_area_freight_template.id IS '主键';
COMMENT ON COLUMN pms_area_freight_template.freight_template_id  is '运费模板编号';
COMMENT ON COLUMN pms_area_freight_template.province_ids is '省份编号,逗号分隔';
COMMENT ON COLUMN pms_area_freight_template.transfer_flag is '是否支持配送 0 支持 1 不支持';
COMMENT ON COLUMN pms_area_freight_template.count_unit is '件/单';
COMMENT ON COLUMN pms_area_freight_template.charge_unit is '件/元';
COMMENT ON COLUMN pms_area_freight_template.add_count is '续件';
COMMENT ON COLUMN pms_area_freight_template.add_charge is '续费';
COMMENT ON COLUMN pms_area_freight_template.create_time is '创建时间';
COMMENT ON COLUMN pms_area_freight_template.update_time is '修改时间';
COMMENT ON column pms_area_freight_template.created_by   is '创建人';
COMMENT ON column pms_area_freight_template.updated_by   is '更新人';
create unique index pms_area_freight_template on pms_area_freight_template(freight_template_id,province_ids);
CREATE or replace PUBLIC SYNONYM pms_area_freight_template FOR pms_area_freight_template;
grant select,insert,update,delete on pms_area_freight_template to mallopr;
grant select on pms_area_freight_template to qdbankcx;


CREATE TABLE pms_specification_attribute (
     id number(20) NOT NULL primary key,
     merchant_no number(20) default null,
     parent_id number(20) default null,
     attribute_name varchar2(256) default null,
     attribute_value varchar2(256) default null,
     status number(2) default 1,
     create_time date default null,
     update_time date default null,
     created_by varchar(64) default null,
     updated_by varchar(64) default null
) initrans 6;

COMMENT ON TABLE pms_specification_attribute IS '规格属性';
COMMENT ON COLUMN pms_specification_attribute.id IS '主键';
COMMENT ON COLUMN pms_specification_attribute.merchant_no IS '商户编号';
COMMENT ON COLUMN pms_specification_attribute.parent_id IS '父id';
COMMENT ON COLUMN pms_specification_attribute.attribute_name  is '属性名称';
COMMENT ON COLUMN pms_specification_attribute.attribute_value  is '属性值';
COMMENT ON COLUMN pms_specification_attribute.status is '状态 0 下架状态 1 上架状态';
COMMENT ON COLUMN pms_specification_attribute.create_time is '创建时间';
COMMENT ON COLUMN pms_specification_attribute.update_time is '修改时间';
COMMENT ON column pms_specification_attribute.created_by   is '创建人';
COMMENT ON column pms_specification_attribute.updated_by   is '更新人';
create index pms_attribute_pid on pms_specification_attribute(parent_id);
create unique index merchant_no_attri_name_pid on pms_specification_attribute(merchant_no,attribute_name,parent_id);
CREATE or replace PUBLIC SYNONYM pms_specification_attribute FOR pms_specification_attribute;
grant select,insert,update,delete on pms_specification_attribute to mallopr;
grant select on pms_specification_attribute to qdbankcx;

CREATE TABLE pms_sku_stock (
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
   PRODUCT_LOCK_STOCK      NUMBER(10)    default 0    ,
   PRODUCT_SP_DATA         VARCHAR2(2000)     ,
   CREATE_TIME             DATE               ,
   UPDATE_TIME             DATE               ,
   CREATED_BY              VARCHAR2(64)       ,
   UPDATED_BY              VARCHAR2(64)       ,
   STATUS                  NUMBER(2)          ,
   GROUP_ID                varchar2(1000)
)initrans 6;
COMMENT ON TABLE pms_sku_stock IS '商品规格信息';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_SKU_ID        IS '规格编号';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_ID            IS '商品编号';
COMMENT ON COLUMN pms_sku_stock.MARKET_PRICE          IS '市场价                  ';
COMMENT ON COLUMN pms_sku_stock.ADVICE_PRICE          IS '建议售价';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_PRICE         IS '商品售价';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_CASH          IS '商品售价中现金金额';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_INTEGRATION   IS '商品售价中积分值';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_NAME          IS '商品名称';
COMMENT ON COLUMN pms_sku_stock.PROMOTION_START_TIME  IS '限购开始时间';
COMMENT ON COLUMN pms_sku_stock.PROMOTION_END_TIME    IS '限购结束时间';
COMMENT ON COLUMN pms_sku_stock.PROMOTION_PER_LIMIT   IS '每人限购数量';
COMMENT ON COLUMN pms_sku_stock.INTEGRATION_PAY_FLAG  IS '积分结算标识：0 不结算 1 需要结算';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_STOCK         IS '商品库存';
COMMENT ON COLUMN pms_sku_stock.SKU_PIC_URL           IS '规格图片URL';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_LOCK_STOCK    IS '商品锁定库存(已下单商品数量)';
COMMENT ON COLUMN pms_sku_stock.PRODUCT_SP_DATA       IS '商品属性:JSON格式';
COMMENT ON COLUMN pms_sku_stock.CREATE_TIME           IS '创建时间';
COMMENT ON COLUMN pms_sku_stock.UPDATE_TIME           IS '更新时间';
COMMENT ON COLUMN pms_sku_stock.CREATED_BY            IS '创建人';
COMMENT ON COLUMN pms_sku_stock.UPDATED_BY            IS '更新人';
COMMENT ON COLUMN pms_sku_stock.STATUS                IS '状态标识：0上架 1下架';
COMMENT ON COLUMN pms_sku_stock.GROUP_ID              IS '文件组名';
create index idx_sku_product_id on pms_sku_stock(product_id);
CREATE or replace PUBLIC SYNONYM pms_sku_stock FOR pms_sku_stock;
grant select,insert,update,delete on pms_sku_stock to mallopr;
grant select on pms_sku_stock to qdbankcx;


create table pms_product(
    PRODUCT_ID              NUMBER(20)      not null primary key,
    MERCHANT_NO             NUMBER(20)                         ,
    MERCHANT_NAME           VARCHAR2(256)                       ,
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
    CREATED_BY              VARCHAR2(64)                       ,
    UPDATED_BY              VARCHAR2(64)
)initrans 6;
COMMENT ON TABLE pms_product IS '商品表';
COMMENT ON COLUMN pms_product.PRODUCT_ID           IS '商品编号';
COMMENT ON COLUMN pms_product.MERCHANT_NO          IS '商户编号';
COMMENT ON COLUMN pms_product.MERCHANT_NAME        IS '商户姓名';
COMMENT ON COLUMN pms_product.MAIL_PIC_URL         IS '商品主图URL';
COMMENT ON COLUMN pms_product.CATEGORY_ID_1        IS '一级分类编号';
COMMENT ON COLUMN pms_product.CATEGORY_ID_2        IS '二级分类编号';
COMMENT ON COLUMN pms_product.CATEGORY_ID_3        IS '三级分类编号';
COMMENT ON COLUMN pms_product.CATEGORY_ID_4        IS '四级分类编号';
COMMENT ON COLUMN pms_product.PUBLISH_STATUS       IS '上架状态 0 待入库 1 已入库 2 已上架';
COMMENT ON COLUMN pms_product.PRODUCT_NAME         IS '商品名称';
COMMENT ON COLUMN pms_product.PRODUCT_DESCRIPTION  IS '商品描述';
COMMENT ON COLUMN pms_product.FREIGHT_TEMPLATE_ID  IS '运费模板编号';
COMMENT ON COLUMN pms_product.PRODUCT_DETAIL       IS '商品详情';
COMMENT ON COLUMN pms_product.CREATE_TIME          IS '创建时间';
COMMENT ON COLUMN pms_product.UPDATE_TIME          IS '更新时间';
COMMENT ON COLUMN pms_product.CREATED_BY           IS '创建人';
COMMENT ON COLUMN pms_product.UPDATED_BY           IS '更新人';

create index idx_create_time on pms_product(create_time);
create index idx_update_time on pms_product(update_time);
create index idx_template_id on pms_product(FREIGHT_TEMPLATE_ID);
CREATE or replace PUBLIC SYNONYM pms_product FOR pms_product;
grant select,insert,update,delete on pms_product to mallopr;
grant select on pms_product to qdbankcx;



create table pms_virtual_product(
    PRODUCT_ID              NUMBER(20)      not null primary key,
    MERCHANT_NO             NUMBER(20)                         ,
    MERCHANT_NAME           VARCHAR2(64)                       ,
    PRODUCT_NAME            VARCHAR2(300)                       ,
    PRODUCT_DESCRIPTION     VARCHAR2(2000)                     ,
    PRODUCT_TYPE            NUMBER(1)                          ,
    CREATE_TIME             DATE                               ,
    UPDATE_TIME             DATE                               ,
    CREATED_BY              VARCHAR2(64)                       ,
    UPDATED_BY              VARCHAR2(64)
)initrans 6;
COMMENT ON TABLE pms_virtual_product IS '虚拟商品表';
COMMENT ON COLUMN pms_virtual_product.PRODUCT_ID           IS '商品编号';
COMMENT ON COLUMN pms_virtual_product.MERCHANT_NO          IS '商户编号';
COMMENT ON COLUMN pms_virtual_product.PRODUCT_NAME         IS '商品名称';
COMMENT ON COLUMN pms_virtual_product.PRODUCT_DESCRIPTION  IS '商品描述';
COMMENT ON COLUMN pms_virtual_product.PRODUCT_TYPE         IS '商品类型：1 话费充值2 油卡充值3 视频会员充值4 积分兑换券';
COMMENT ON COLUMN pms_virtual_product.CREATE_TIME          IS '创建时间';
COMMENT ON COLUMN pms_virtual_product.UPDATE_TIME          IS '更新时间';
COMMENT ON COLUMN pms_virtual_product.CREATED_BY           IS '创建人';
COMMENT ON COLUMN pms_virtual_product.UPDATED_BY           IS '更新人';
create unique index unq_product_id_type on pms_virtual_product(product_id,product_type);
CREATE or replace PUBLIC SYNONYM pms_virtual_product FOR pms_virtual_product;
grant select,insert,update,delete on pms_virtual_product to mallopr;
grant select on pms_product to qdbankcx;



create table PMS_PRODUCT_PIC_URL
(
    id          NUMBER(20)  not null primary key,
    product_id  NUMBER(20),
    main_flag   NUMBER(1),
    pic_url     VARCHAR2(1000),
    file_name   varchar2(1000),
    file_source varchar2(16),
    create_time DATE,
    update_time DATE,
    created_by varchar(64) default null,
    updated_by varchar(64) default null,
    GROUP_ID   varchar2(1000)
)initrans 6;
-- Add comments to the table
comment on table PMS_PRODUCT_PIC_URL
    is '商品图片表';
-- Add comments to the columns
comment on column PMS_PRODUCT_PIC_URL.id
    is '主键';
comment on column PMS_PRODUCT_PIC_URL.product_id
    is '商品编号';
comment on column PMS_PRODUCT_PIC_URL.main_flag
    is '主图标识：0 主图 1 非主图';
comment on column PMS_PRODUCT_PIC_URL.pic_url
    is '图片url地址';
comment on column PMS_PRODUCT_PIC_URL.file_name
    is '文件名称';
comment on column PMS_PRODUCT_PIC_URL.file_source
    is '文件来源 0 规格图片 1 商品图册 2 商品详情图片';
comment on column PMS_PRODUCT_PIC_URL.create_time
    is '创建时间';
comment on column PMS_PRODUCT_PIC_URL.update_time
    is '修改时间';
COMMENT ON column PMS_PRODUCT_PIC_URL.created_by   is '创建人';
COMMENT ON column PMS_PRODUCT_PIC_URL.updated_by   is '更新人';
COMMENT ON COLUMN PMS_PRODUCT_PIC_URL.GROUP_ID     IS '文件组名';
-- Create/Recreate indexes
create index IDX_PRODUCT_ID on PMS_PRODUCT_PIC_URL (PRODUCT_ID);
CREATE or replace PUBLIC SYNONYM PMS_PRODUCT_PIC_URL FOR PMS_PRODUCT_PIC_URL;
grant select,insert,update,delete on PMS_PRODUCT_PIC_URL to mallopr;
grant select on PMS_PRODUCT_PIC_URL to qdbankcx;

CREATE TABLE pms_refund_setting (
    id number(20) NOT NULL primary key,
    refund_reason varchar2(256) not null,
    status number(2) default null,
    create_time date default null,
    update_time date default null,
    created_by varchar2(64) default null,
    updated_by varchar2(64) default null
) initrans 6;

COMMENT ON TABLE pms_refund_setting IS '商品退款设置';
COMMENT ON COLUMN pms_refund_setting.id IS '主键';
COMMENT ON COLUMN pms_refund_setting.refund_reason IS '退款原因';
COMMENT ON COLUMN pms_refund_setting.status IS '是否可用 0 不可用 1 可用';
COMMENT ON COLUMN pms_refund_setting.create_time IS '创建时间';
COMMENT ON COLUMN pms_refund_setting.update_time IS '修改时间';
COMMENT ON COLUMN pms_refund_setting.created_by IS '创建人';
COMMENT ON COLUMN pms_refund_setting.updated_by IS '修改人';
create unique index unq_reason on pms_refund_setting(refund_reason);
CREATE or replace PUBLIC SYNONYM pms_refund_setting FOR pms_refund_setting;
grant select,insert,update,delete on pms_refund_setting to mallopr;
grant select on pms_refund_setting to qdbankcx;

create table sms_coupon(
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
CREATED_BY                  VARCHAR2(50)      ,
UPDATED_BY                  VARCHAR2(50)
)initrans 6;

COMMENT ON TABLE sms_coupon IS ' 优惠券信息表';
COMMENT ON COLUMN sms_coupon.COUPON_ID                  IS '"
券商品编号

"                ';
COMMENT ON COLUMN sms_coupon.BATCH_NO                   IS '批次号                       ';
COMMENT ON COLUMN sms_coupon.COUPON_NAME                IS '优惠券名称                     ';
COMMENT ON COLUMN sms_coupon.COUPON_TYPE                IS '优惠券类型：0 积分兑换券 1 指定商品免费兑换券';
COMMENT ON COLUMN sms_coupon.ALL_TOTAL                  IS '本批次白名单数量                  ';
COMMENT ON COLUMN sms_coupon.PRODUCT_INTEGRATION        IS '商品售价中积分量                  ';
COMMENT ON COLUMN sms_coupon.COUPON_AMOUNT              IS '优惠券面值';
COMMENT ON COLUMN sms_coupon.COUPON_DESCRIPTION         IS '优惠券描述';
COMMENT ON COLUMN sms_coupon.PRODUCT_STATUS             IS '券商品状态：0 待上架 1 已上架                    ';
COMMENT ON COLUMN sms_coupon.BATCH_STATUS               IS '批次状态：0 待发放1 已发放2 已过期 3 已作废';
COMMENT ON COLUMN sms_coupon.PRODUCT_TYPE               IS '"指定商品类型0 实物 1 话费充值 2 油卡充值 3 视频会员充值';
COMMENT ON COLUMN sms_coupon.PRODUCT_ID                 IS '指定商品编号                    ';
COMMENT ON COLUMN sms_coupon.PRODUCT_NAME               IS '指定商品名称                    ';
COMMENT ON COLUMN sms_coupon.PRODUCT_SKU_ID             IS '指定商品规格编号                  ';
COMMENT ON COLUMN sms_coupon.FILE_NAME                  IS '上传白名单文件名称                 ';
COMMENT ON COLUMN sms_coupon.FILE_URL                   IS '上传白名单文件URL                ';
COMMENT ON COLUMN sms_coupon.EXPIRE_DAYS                IS '优惠券过期天数                   ';
COMMENT ON COLUMN sms_coupon.EXPIRE_DATE                IS '优惠券过期时间                   ';
COMMENT ON COLUMN sms_coupon.START_TIME                 IS '优惠券有效期开始时间                ';
COMMENT ON COLUMN sms_coupon.END_TIME                   IS '优惠券有效期结束时间                ';
COMMENT ON COLUMN sms_coupon.SEND_TIME                  IS '优惠券发放时间                   ';
COMMENT ON COLUMN sms_coupon.group_id                   IS '文件组名';
COMMENT ON COLUMN sms_coupon.CREATE_TIME                IS '创建时间                      ';
COMMENT ON COLUMN sms_coupon.UPDATE_TIME                IS '更新时间                      ';
COMMENT ON COLUMN sms_coupon.CREATED_BY                    IS '创建人';
COMMENT ON COLUMN sms_coupon.UPDATED_BY                  IS '更新人';
COMMENT ON COLUMN sms_coupon.PRODUCT_STATUS             IS '券商品状态：0 待上架 1 已上架';
COMMENT ON COLUMN sms_coupon.BATCH_STATUS               IS '批次状态:0 待发放1 已发放2 已过期 3 已作废';

create  index unq_batch_no on sms_coupon(batch_no);
CREATE or replace PUBLIC SYNONYM sms_coupon FOR sms_coupon;
grant select,insert,update,delete on sms_coupon to mallopr;
grant select on sms_coupon to qdbankcx;


create table sms_user_coupon(
    USER_COUPON_ID      NUMBER(20)    not null primary key  ,
    COUPON_TYPE         NUMBER(1)       ,
    ORDER_SN            VARCHAR2(64)    ,
    CUST_NO             VARCHAR2(32)    ,
    COUPON_ID           NUMBER(20)      ,
    BATCH_NO            VARCHAR2(32)    ,
    ORDER_INTEGRATION   NUMBER(11)      ,
    COUPON_NAME         VARCHAR2(256)    ,
    STATUS              NUMBER(2)       ,
    CREATE_TIME         DATE            ,
    UPDATE_TIME         DATE            ,
    EXPIRE_DATE         DATE
)initrans 6;

COMMENT ON TABLE     sms_user_coupon   is '用户持券表';
COMMENT ON COLUMN    sms_user_coupon.USER_COUPON_ID     IS '用户优惠券编号';
COMMENT ON COLUMN    sms_user_coupon.COUPON_TYPE        IS '优惠券类型：0 积分兑换券1 指定商品免费兑换券';
COMMENT ON COLUMN    sms_user_coupon.ORDER_SN           IS '订单编号';
COMMENT ON COLUMN    sms_user_coupon.CUST_NO            IS '通联核心客户号';
COMMENT ON COLUMN    sms_user_coupon.COUPON_ID          IS '券商品编号';
COMMENT ON COLUMN    sms_user_coupon.BATCH_NO           IS '批次号';
COMMENT ON COLUMN    sms_user_coupon.ORDER_INTEGRATION  IS '积分券售价(积分值)';
COMMENT ON COLUMN    sms_user_coupon.STATUS             IS '"用户使用券状态 :0 待使用1 已使用2 已过期3 已作废"';
COMMENT ON COLUMN    sms_user_coupon.CREATE_TIME        IS '创建时间';
COMMENT ON COLUMN    sms_user_coupon.UPDATE_TIME        IS '更新时间';
COMMENT ON COLUMN    sms_user_coupon.EXPIRE_DATE        IS '优惠券过期时间';
CREATE index idx_cust_no on sms_user_coupon(CUST_NO);
CREATE index idx_COUPON_ID on sms_user_coupon(COUPON_ID);
CREATE or replace PUBLIC SYNONYM sms_user_coupon FOR sms_user_coupon;
grant select,insert,update,delete on sms_user_coupon to mallopr;
grant select on sms_user_coupon to qdbankcx;

CREATE TABLE cms_advertisement (
   id number(20) NOT NULL primary key,
   module_id number(1) default null,
   advertise_name varchar2(256) default null,
   advertise_description varchar2(200) default null,
   advertisment_level number(2) default null,
   start_time date default null,
   end_time date default null,
   jump_url varchar2(1000) default null,
   pic_url varchar2(2000)default null,
   status number(2) default 0,
   create_time date default null,
   update_time date default null,
   created_by varchar2(64) default null,
   updated_by varchar2(64) default null,
   group_id varchar2(1000)
) initrans 6;

COMMENT ON TABLE cms_advertisement IS '广告配置表';
COMMENT ON COLUMN cms_advertisement.id IS '主键';
COMMENT ON COLUMN cms_advertisement.status IS '广告状态 0 停用 1 启用';
COMMENT ON COLUMN cms_advertisement.module_id IS '广告模块编号';
COMMENT ON COLUMN cms_advertisement.advertise_name IS '广告名称';
COMMENT ON COLUMN cms_advertisement.advertise_description IS '广告描述';
COMMENT ON COLUMN cms_advertisement.advertisment_level IS '优先级';
COMMENT ON COLUMN cms_advertisement.start_time IS '广告开始时间';
COMMENT ON COLUMN cms_advertisement.end_time IS '广告结束时间';
COMMENT ON COLUMN cms_advertisement.jump_url IS '广告跳转链接';
COMMENT ON COLUMN cms_advertisement.pic_url IS '图片url';
COMMENT ON COLUMN cms_advertisement.created_by IS '创建人';
COMMENT ON COLUMN cms_advertisement.updated_by IS '修改人';
COMMENT ON COLUMN cms_advertisement.create_time IS '创建时间';
COMMENT ON COLUMN cms_advertisement.update_time IS '修改时间';
COMMENT ON COLUMN cms_advertisement.group_id IS '文件组名';

create index idx_advertise_create_time on cms_advertisement(create_time);
create index idx_advertise_end_time on cms_advertisement(end_time);
CREATE or replace PUBLIC SYNONYM cms_advertisement FOR cms_advertisement;
grant select,insert,update,delete on cms_advertisement to mallopr;
grant select on cms_advertisement to qdbankcx;

CREATE TABLE cms_prefecture (
id number(20) NOT NULL primary key,
prefecture_name varchar2(256) default null,
prefecture_decription varchar2(1000) default null,
prefecture_level number(2) default null,
start_time date default null,
end_time date default null,
status number(2) default null,
create_time date default null,
update_time date default null,
created_by varchar2(64) default null,
updated_by varchar2(64) default null
) initrans 6;

COMMENT ON TABLE cms_prefecture IS '专区表';
COMMENT ON COLUMN cms_prefecture.id  IS '专区编号';
COMMENT ON COLUMN cms_prefecture.prefecture_name IS '专区名称';
COMMENT ON COLUMN cms_prefecture.prefecture_decription IS '专区描述';
COMMENT ON COLUMN cms_prefecture.prefecture_level IS '优先级 数字越小级别越高';
COMMENT ON COLUMN cms_prefecture.start_time IS '专区启用开始时间';
COMMENT ON COLUMN cms_prefecture.end_time IS '专区启用结束时间';
COMMENT ON COLUMN cms_prefecture.created_by IS '创建人';
COMMENT ON COLUMN cms_prefecture.updated_by IS '修改人';
COMMENT ON COLUMN cms_prefecture.create_time IS '创建时间';
COMMENT ON COLUMN cms_prefecture.update_time IS '修改时间';
create index idx_prefecture_time on cms_prefecture(create_time);
create index idx_prefecture_end_time on cms_prefecture(end_time);
CREATE or replace PUBLIC SYNONYM cms_prefecture FOR cms_prefecture;
grant select,insert,update,delete on cms_prefecture to mallopr;
grant select on cms_prefecture to qdbankcx;

CREATE TABLE cms_prefecture_stock_relation (
   id number(20) NOT NULL primary key,
   prefecture_id number(20) default null,
   product_id number(20) default null,
   create_time date default null,
   update_time date default null,
   created_by varchar(64) default null,
   updated_by varchar(64) default null
) initrans 6;

COMMENT ON TABLE cms_prefecture_stock_relation IS '专区商品关联关系表';
COMMENT ON COLUMN cms_prefecture_stock_relation.id IS '主键';
COMMENT ON COLUMN cms_prefecture_stock_relation.prefecture_id IS '专区编号';
COMMENT ON COLUMN cms_prefecture_stock_relation.product_id IS '商品编号';
COMMENT ON column cms_prefecture_stock_relation.create_time   is '创建时间';
COMMENT ON column cms_prefecture_stock_relation.update_time   is '修改时间';
COMMENT ON column cms_prefecture_stock_relation.created_by   is '创建人';
COMMENT ON column cms_prefecture_stock_relation.updated_by   is '更新人';

create unique index unq_prefecture_stock_id on cms_prefecture_stock_relation(prefecture_id,product_id);
CREATE or replace PUBLIC SYNONYM cms_prefecture_stock_relation FOR cms_prefecture_stock_relation;
grant select,insert,update,delete on cms_prefecture_stock_relation to mallopr;
grant select on cms_prefecture_stock_relation to qdbankcx;



CREATE TABLE cms_activity (
  id number(20) NOT NULL primary key,
  activity_name varchar2(256) default null,
  activity_level number(2) default null,
  start_time date default null,
  end_time date default null,
  jump_url varchar2(1000) default null,
  pic_url varchar2(2000)default null,
  status number(2) default 0,
  group_id varchar2(1000),
  create_time date default null,
  update_time date default null,
  created_by varchar2(50) default null,
  updated_by varchar2(50) default null
) initrans 6;

COMMENT ON TABLE cms_activity IS '营销活动表';
COMMENT ON COLUMN cms_activity.id IS '主键';
COMMENT ON COLUMN cms_activity.activity_name IS '活动名称';
COMMENT ON COLUMN cms_activity.activity_level IS '优先级';
COMMENT ON COLUMN cms_activity.start_time IS '活动开始时间';
COMMENT ON COLUMN cms_activity.end_time IS '活动开始时间';
COMMENT ON COLUMN cms_activity.jump_url IS '活动调换url';
COMMENT ON COLUMN cms_activity.pic_url IS '图片url';
COMMENT ON COLUMN cms_activity.status IS '活动状态 0 停用 1 启用';
COMMENT ON COLUMN cms_activity.group_id IS '文件组名';
COMMENT ON COLUMN cms_activity.create_time IS '创建时间';
COMMENT ON COLUMN cms_activity.update_time IS '修改时间';
COMMENT ON COLUMN cms_activity.created_by IS '创建人';
COMMENT ON COLUMN cms_activity.updated_by IS '修改人';

create index idx_activity_time on cms_activity(create_time);
create index idx_end_time on cms_activity(end_time);
create index idx_activity_url on cms_activity(pic_url);
CREATE or replace PUBLIC SYNONYM cms_activity FOR cms_activity;
grant select,insert,update,delete on cms_activity to mallopr;
grant select on cms_activity to qdbankcx;

CREATE TABLE ums_user_receive_address (
    ID                          NUMBER(20)  not null primary key ,
    CUST_NO                     NUMBER(32)   ,
    CUST_NAME                   VARCHAR2(64)  ,
    RECEIVER_MOBILE             VARCHAR2(32)  ,
    RECEIVER_NAME               VARCHAR2(200) ,
    RECEIVER_PROVINCE           VARCHAR2(32)  ,
    RECEIVER_CITY               VARCHAR2(32)  ,
    RECEIVER_REGION             VARCHAR2(32)  ,
    RECEIVER_DETAIL_ADDRESS     VARCHAR2(1000) ,
    DEFAULT_FLAG                number(1)     ,
    CREATE_TIME                 DATE          ,
    UPDATE_TIME                 DATE
) initrans 6;

COMMENT ON TABLE ums_user_receive_address IS '用户收货地址表';
COMMENT ON COLUMN ums_user_receive_address.ID                       IS '主键';
COMMENT ON COLUMN ums_user_receive_address.CUST_NO                  IS '通联客户号';
COMMENT ON COLUMN ums_user_receive_address.CUST_NAME                IS '通联核心用户姓名';
COMMENT ON COLUMN ums_user_receive_address.RECEIVER_MOBILE          IS '收件人手机号';
COMMENT ON COLUMN ums_user_receive_address.RECEIVER_NAME            IS '收货人姓名';
COMMENT ON COLUMN ums_user_receive_address.RECEIVER_PROVINCE        IS '省份直辖市';
COMMENT ON COLUMN ums_user_receive_address.RECEIVER_CITY            IS '城市';
COMMENT ON COLUMN ums_user_receive_address.RECEIVER_REGION          IS '区县';
COMMENT ON COLUMN ums_user_receive_address.RECEIVER_DETAIL_ADDRESS  IS '详细地址';
COMMENT ON COLUMN ums_user_receive_address.DEFAULT_FLAG             IS '默认地址标识：0 非默认地址 1 默认地址';
COMMENT ON COLUMN ums_user_receive_address.CREATE_TIME              IS '创建时间';
COMMENT ON COLUMN ums_user_receive_address.UPDATE_TIME              IS '更新时间';
create  index idx_address_cust_no on ums_user_receive_address(cust_no);
create index idx_address_time on ums_user_receive_address(create_time);
CREATE or replace PUBLIC SYNONYM ums_user_receive_address FOR ums_user_receive_address;
grant select,insert,update,delete on ums_user_receive_address to mallopr;
grant select on ums_user_receive_address to qdbankcx;



CREATE TABLE oms_order_refund
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
    REASON                  VARCHAR2(1000)          ,
    REFUND_TYPE             NUMBER(1)              ,
    PROOF_PICS              VARCHAR2(2000)         ,
    ADMIN_NAME              VARCHAR2(64)           ,
    EMAIL                   VARCHAR2(64)           ,
    REFUND_NOTE             VARCHAR2(100)          ,
    MERCHANT_NO             NUMBER(20)             ,
    MERCHANT_NAME           VARCHAR2(64)           ,
    CREATE_TIME             DATE                   ,
    UPDATE_TIME             DATE                   ,
    COUPON_AMOUNT               NUMBER(8,2)

) initrans 6;

COMMENT ON TABLE OMS_ORDER_REFUND IS '退货申请表';
COMMENT ON COLUMN OMS_ORDER_REFUND.REFUND_SERIAL      IS '退款流水号                                          ';
COMMENT ON COLUMN OMS_ORDER_REFUND.CUST_NO            IS '通联客户号                                          ';
COMMENT ON COLUMN OMS_ORDER_REFUND.CUST_NAME          IS '通联核心用户姓名                                       ';
COMMENT ON COLUMN OMS_ORDER_REFUND.CUST_MOBILE        IS '"通联客户号对应的银行预
留手机号"                             ';
COMMENT ON COLUMN OMS_ORDER_REFUND.ORDER_SN           IS '订单编号                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.PRODUCT_ID         IS '商品编号                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.PRODUCT_NAME       IS '商品名称                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.PRODUCT_COUNT      IS '商品数量                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.PRODUCT_SKU_ID     IS '规格编号                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.CATEGORY_ID_1      IS '商品一级分类编号                                       ';
COMMENT ON COLUMN OMS_ORDER_REFUND.CATEGORY_ID_2      IS '商品二级分类编号                                       ';
COMMENT ON COLUMN OMS_ORDER_REFUND.CATEGORY_ID_3      IS '商品三级分类编号                                       ';
COMMENT ON COLUMN OMS_ORDER_REFUND.CATEGORY_ID_4      IS '商品四级分类编号                                       ';
COMMENT ON COLUMN OMS_ORDER_REFUND.ORDER_CASH         IS '"订单现金:包含商品售价中现金金额-优惠券金额，不包含运费"                ';
COMMENT ON COLUMN OMS_ORDER_REFUND.ORDER_INTEGRATION  IS '订单积分                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.REFUND_AMOUNT      IS '退款总金额折算价';
COMMENT ON COLUMN OMS_ORDER_REFUND.REFUND_CASH        IS '"退款现金，不包含运费';
COMMENT ON COLUMN OMS_ORDER_REFUND.FREIGHT_AMOUNT     IS '运费金额                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.REFUND_INTEGRATION IS '退款积分：订单积分                                      ';
COMMENT ON COLUMN OMS_ORDER_REFUND.REFUND_STATUS      IS '"退款状态
0 待审核
1 审核通过
2 退款成功
3审核不通过"            ';
COMMENT ON COLUMN OMS_ORDER_REFUND.PRODUCT_TYPE       IS '"商品类型：
0 实物
1 话费充值
2 油卡充值
3 视频会员充值
4 积分兑换券
"   ';
COMMENT ON COLUMN OMS_ORDER_REFUND.HANDLE_START_TIME  IS '退款处理时间                                         ';
COMMENT ON COLUMN OMS_ORDER_REFUND.HANDLE_FINISH_TIME IS '退款完成时间                                         ';
COMMENT ON COLUMN OMS_ORDER_REFUND.HANDLE_RESULT      IS '"处理结果：
0 同意退款
1 退款驳回"                          ';
COMMENT ON COLUMN OMS_ORDER_REFUND.REASON             IS '退款原因                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.REFUND_TYPE        IS '"退款类型
0 仅退款(无需退货)
1 退货退款"                    ';
COMMENT ON COLUMN OMS_ORDER_REFUND.PROOF_PICS         IS '上传凭证图片url                                      ';
COMMENT ON COLUMN OMS_ORDER_REFUND.ADMIN_NAME         IS '管理员姓名(处理人姓名)                                   ';
COMMENT ON COLUMN OMS_ORDER_REFUND.EMAIL              IS '管理员邮箱(处理人邮箱)                                   ';
COMMENT ON COLUMN OMS_ORDER_REFUND.REFUND_NOTE        IS '退款说明                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.MERCHANT_NO        IS '商户编号';
COMMENT ON COLUMN OMS_ORDER_REFUND.MERCHANT_NAME      IS '商户名称';
COMMENT ON COLUMN OMS_ORDER_REFUND.CREATE_TIME        IS '创建时间                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.UPDATE_TIME        IS '更新时间                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND.COUPON_AMOUNT     IS '优惠券面值';
-- index
create index unq_order_refund_id_sn on oms_order_refund(order_sn);
create index idx_order_refund_create_time on oms_order_refund  (create_time);
CREATE or replace PUBLIC SYNONYM OMS_ORDER_REFUND FOR OMS_ORDER_REFUND;
grant select,insert,update,delete on OMS_ORDER_REFUND to mallopr;
grant select on OMS_ORDER_REFUND to qdbankcx;




CREATE TABLE oms_order_refund_report
(
    REFUND_SERIAL           NUMBER(20)   not null primary key          ,
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
    REASON                  VARCHAR2(1000)          ,
    REFUND_TYPE             NUMBER(1)              ,
    PROOF_PICS              VARCHAR2(2000)         ,
    ADMIN_NAME              VARCHAR2(32)           ,
    EMAIL                   VARCHAR2(64)           ,
    REFUND_NOTE             VARCHAR2(100)          ,
    MERCHANT_NO             NUMBER(20),
    MERCHANT_NAME           VARCHAR2(64),
    CREATE_TIME             DATE                   ,
    UPDATE_TIME             DATE                   ,
    COUPON_AMOUNT               NUMBER(8,2)

) initrans 6;

COMMENT ON TABLE oms_order_refund_report IS '退货申请报表';
COMMENT ON COLUMN oms_order_refund_report.REFUND_SERIAL      IS '退款流水号                                          ';
COMMENT ON COLUMN oms_order_refund_report.CUST_NO            IS '通联客户号                                          ';
COMMENT ON COLUMN oms_order_refund_report.CUST_NAME          IS '通联核心用户姓名                                       ';
COMMENT ON COLUMN oms_order_refund_report.CUST_MOBILE        IS '"通联客户号对应的银行预
留手机号"                             ';
COMMENT ON COLUMN oms_order_refund_report.ORDER_SN           IS '订单编号                                           ';
COMMENT ON COLUMN oms_order_refund_report.PRODUCT_ID         IS '商品编号                                           ';
COMMENT ON COLUMN oms_order_refund_report.PRODUCT_NAME       IS '商品名称                                           ';
COMMENT ON COLUMN oms_order_refund_report.PRODUCT_COUNT      IS '商品数量                                           ';
COMMENT ON COLUMN oms_order_refund_report.PRODUCT_SKU_ID     IS '规格编号                                           ';
COMMENT ON COLUMN oms_order_refund_report.CATEGORY_ID_1      IS '商品一级分类编号                                       ';
COMMENT ON COLUMN oms_order_refund_report.CATEGORY_ID_2      IS '商品二级分类编号                                       ';
COMMENT ON COLUMN oms_order_refund_report.CATEGORY_ID_3      IS '商品三级分类编号                                       ';
COMMENT ON COLUMN oms_order_refund_report.CATEGORY_ID_4      IS '商品四级分类编号                                       ';
COMMENT ON COLUMN oms_order_refund_report.ORDER_CASH         IS '"订单现金:
包含商品售价中现金金额
+运费金额-优惠券金额"                ';
COMMENT ON COLUMN oms_order_refund_report.ORDER_INTEGRATION  IS '订单积分                                           ';
COMMENT ON COLUMN oms_order_refund_report.REFUND_AMOUNT      IS '退款总金额折算价                                       ';
COMMENT ON COLUMN oms_order_refund_report.REFUND_CASH        IS '"退款现金：
1 退款类型为仅退款：
 订单现金
2 退款类型为退货退款：
   订单现金-运费金额"';
COMMENT ON COLUMN oms_order_refund_report.FREIGHT_AMOUNT     IS '运费金额                                           ';
COMMENT ON COLUMN oms_order_refund_report.REFUND_INTEGRATION IS '退款积分：订单积分                                      ';
COMMENT ON COLUMN oms_order_refund_report.REFUND_STATUS      IS '"退款状态
0 待审核
1 审核通过
2 退款成功
3审核不通过"            ';
COMMENT ON COLUMN oms_order_refund_report.PRODUCT_TYPE       IS '"商品类型：
0 实物
1 话费充值
2 油卡充值
3 视频会员充值
4 积分兑换券
"   ';
COMMENT ON COLUMN oms_order_refund_report.HANDLE_START_TIME  IS '退款处理时间                                         ';
COMMENT ON COLUMN oms_order_refund_report.HANDLE_FINISH_TIME IS '退款完成时间                                         ';
COMMENT ON COLUMN oms_order_refund_report.HANDLE_RESULT      IS '"处理结果：
0 同意退款
1 退款驳回"                          ';
COMMENT ON COLUMN oms_order_refund_report.REASON             IS '退款原因                                           ';
COMMENT ON COLUMN oms_order_refund_report.REFUND_TYPE        IS '"退款类型
0 仅退款(无需退货)
1 退货退款"                    ';
COMMENT ON COLUMN oms_order_refund_report.PROOF_PICS         IS '上传凭证图片url                                      ';
COMMENT ON COLUMN oms_order_refund_report.ADMIN_NAME         IS '管理员姓名(处理人姓名)                                   ';
COMMENT ON COLUMN oms_order_refund_report.EMAIL              IS '管理员邮箱(处理人邮箱)                                   ';
COMMENT ON COLUMN oms_order_refund_report.REFUND_NOTE        IS '退款说明                                           ';
COMMENT ON COLUMN OMS_ORDER_REFUND_REPORT.MERCHANT_NO        IS '商户编号';
COMMENT ON COLUMN OMS_ORDER_REFUND_REPORT.MERCHANT_NAME      IS '商户名称';
COMMENT ON COLUMN oms_order_refund_report.CREATE_TIME        IS '创建时间                                           ';
COMMENT ON COLUMN oms_order_refund_report.UPDATE_TIME        IS '更新时间                                           ';
COMMENT ON COLUMN oms_order_refund_report.COUPON_AMOUNT     IS '优惠券面值';
-- index
create unique index unq_order_refund_report_id_sn on oms_order_refund_report(order_sn);
create index idx_order_refund_report_time on oms_order_refund_report  (create_time);

CREATE or replace PUBLIC SYNONYM oms_order_refund_report FOR oms_order_refund_report;
grant select,insert,update,delete on oms_order_refund_report to mallopr;
grant select on oms_order_refund_report to qdbankcx;


CREATE TABLE oms_recharge_status_detail(
ID            NUMBER(20)   NOT NULL PRIMARY KEY,
ORDER_SN      VARCHAR2(64)                     ,
DETAIL_LEVEL  NUMBER(2)                        ,
STATUS        NUMBER(2)                        ,
CREATE_TIME   DATE                             ,
UPDATE_TIME   DATE
)initrans 6;

comment on table oms_recharge_status_detail
    is '充值状态明细表';
COMMENT ON COLUMN oms_recharge_status_detail.ID             IS '主键 ';
COMMENT ON COLUMN oms_recharge_status_detail.ORDER_SN       IS '订单编号';
COMMENT ON COLUMN oms_recharge_status_detail.DETAIL_LEVEL   IS '排序优先级';
COMMENT ON COLUMN oms_recharge_status_detail.STATUS         IS '手机充值：0：已下单,1：待支付,2：支付处理中,3：已支付,4：充值中,5：充值成功,6：取消关闭,7：已完成；积分兑换: 0:已下单,1:待支付,2:支付处理中,3:已支付,4:待使用,5:已使用,6:取消关闭,7:已过期;';
COMMENT ON COLUMN oms_recharge_status_detail.CREATE_TIME    IS '创建时间';
COMMENT ON COLUMN oms_recharge_status_detail.UPDATE_TIME    IS '更新时间';
CREATE or replace PUBLIC SYNONYM oms_recharge_status_detail FOR oms_recharge_status_detail;
grant select,insert,update,delete on oms_recharge_status_detail to mallopr;
grant select on oms_recharge_status_detail to qdbankcx;





create table ums_merchant
(
    merchant_no         NUMBER(20) not null primary key,
    merchant_name    varchar2(64)default null,
    status number(2) default null,
    account_name varchar2(256) default null,
    bank_name varchar2(256)default null,
    bank_no varchar2(32) default null,
    admin_name varchar2(64)default null,
    id_no varchar2(20) default null,
    email varchar2(64) default null,
    mobile varchar2(16) default null,
    support_phone varchar2(15) default null,
    agreement_no varchar2(32)default null,
    start_time date default null,
    end_time date default null,
    remark varchar2(200)default null,
    created_by varchar2(64)default null,
    updated_by varchar2(64)default null,
    create_time date default null,
    update_time date default null

)initrans 6;
-- Add comments to the table
comment on table ums_merchant
    is '商户管理表';
COMMENT ON COLUMN ums_merchant.merchant_no IS '商户编号';
COMMENT ON COLUMN ums_merchant.merchant_name IS '商户名称';
COMMENT ON COLUMN ums_merchant.status IS '商户状态 0 未启用 1已启用';
COMMENT ON COLUMN ums_merchant.account_name IS '账户名称：青岛银行对公账户名称';
COMMENT ON COLUMN ums_merchant.bank_name IS '商户开户银行';
COMMENT ON COLUMN ums_merchant.bank_no IS '开户银行卡号';
COMMENT ON COLUMN ums_merchant.admin_name IS '管理员姓名';
COMMENT ON COLUMN ums_merchant.id_no IS '管理员身份证号';
COMMENT ON COLUMN ums_merchant.email IS '管理员邮箱';
COMMENT ON COLUMN ums_merchant.mobile IS '管理员手机号';
COMMENT ON COLUMN ums_merchant.support_phone IS '客服电话';
COMMENT ON COLUMN ums_merchant.agreement_no IS '合同编号';
COMMENT ON COLUMN ums_merchant.start_time IS '合同有效期开始时间';
COMMENT ON COLUMN ums_merchant.end_time IS '合同有效期结束时间';
COMMENT ON COLUMN ums_merchant.remark IS '备注';
COMMENT ON COLUMN ums_merchant.create_time IS '创建时间';
COMMENT ON COLUMN ums_merchant.update_time IS '修改时间';
COMMENT ON COLUMN ums_merchant.created_by IS '创建人';
COMMENT ON COLUMN ums_merchant.updated_by IS '修改人';
create unique index unq_id_no_agreement_no on ums_merchant(id_no,agreement_no);
create index idx_merchant_create_time on ums_merchant(create_time);
create unique index email on ums_merchant(email);
CREATE or replace PUBLIC SYNONYM ums_merchant FOR ums_merchant;
grant select,insert,update,delete on ums_merchant to mallopr;
grant select on ums_merchant to qdbankcx;



create table ums_address(
    ID number(20) not null primary key,
    ADDRESS_NAME VARCHAR2(256) default null,
    PARENT_ID NUMBER(20) default null,
    VIEW_ORDER number(5) default 0
) initrans 6;

COMMENT ON TABLE ums_address IS '统一地区表';
COMMENT ON COLUMN ums_address.ID IS '主键';
COMMENT ON COLUMN ums_address.ADDRESS_NAME IS '区域名称';
COMMENT ON COLUMN ums_address.PARENT_ID IS '父级ID';
COMMENT ON COLUMN ums_address.VIEW_ORDER IS '排序';
create index  idx_ums_address_id on ums_address (id,PARENT_ID);
CREATE or replace PUBLIC SYNONYM ums_address FOR ums_address;
grant select,insert,update,delete on ums_address to mallopr;
grant select on ums_address to qdbankcx;

CREATE TABLE system_params_confg (
  id number(20) NOT NULL primary key,
  param_name varchar2(256) default null,
  param_value varchar2(256) default null,
  param_type number(2) default null,
  param_description varchar2(256) default null,
  create_time date default null,
  update_time date default null,
  created_by varchar(64) default null,
  updated_by varchar(64) default null
) initrans 6;

COMMENT ON TABLE system_params_confg IS '系统参数配置表';
COMMENT ON COLUMN system_params_confg.id IS '主键';
COMMENT ON COLUMN system_params_confg.param_name IS '参数名称';
COMMENT ON COLUMN system_params_confg.param_value IS '参数值';
COMMENT ON COLUMN system_params_confg.param_description  is '参数描述';
COMMENT ON COLUMN system_params_confg.param_type  is '参数类型：0 积分值比例配置 1 自动确认收货失效配置 2 退款申请有效期配置 3 物流公司配置';
COMMENT ON COLUMN system_params_confg.create_time is '创建时间';
COMMENT ON COLUMN system_params_confg.update_time is '修改时间';
COMMENT ON column system_params_confg.created_by   is '创建人';
COMMENT ON column system_params_confg.updated_by   is '更新人';
create unique index system_params_confg on system_params_confg(param_name);
create index idx_system_params_type on system_params_confg(param_type);
CREATE or replace PUBLIC SYNONYM system_params_confg FOR system_params_confg;
grant select,insert,update,delete on system_params_confg to mallopr;
grant select on system_params_confg to qdbankcx;


create table trade_total_report(
    ID  number(20) not null primary key,
    MERCHANT_NO                 VARCHAR2(20)       ,
    MERCHANT_NAME               VARCHAR2(64)     ,
    ORDER_TYPE                  VARCHAR2(32),
    ORDER_COUNT                 VARCHAR2(16),
    PRODUCT_PRICE               VARCHAR2(16),
    INTEGRATION_COUNT           VARCHAR2(16),
    COUPON_COUNT                VARCHAR2(16),
    PRODUCT_COUPON_COUNT        VARCHAR2(16),
    ORDER_CASH                  VARCHAR2(16),
    FREIGHT_AMOUNT              VARCHAR2(16),
    CASH_FREIGHT_AMOUNT         VARCHAR2(16),
    PAYMENT_TIME                    VARCHAR2(32),
    CREATE_TIME                 DATE)initrans 6;
COMMENT ON TABLE trade_total_report IS '商城交易汇总表';
COMMENT ON COLUMN trade_total_report.ID  IS '主键';
COMMENT ON COLUMN trade_total_report.MERCHANT_NO  IS '商户号';
COMMENT ON COLUMN trade_total_report.MERCHANT_NAME  IS '商户名称';
COMMENT ON COLUMN trade_total_report.ORDER_TYPE  IS '订单类型';
COMMENT ON COLUMN trade_total_report.ORDER_COUNT  IS '订单笔数汇总';
COMMENT ON COLUMN trade_total_report.PRODUCT_PRICE  IS '商品实际售价折算价';
COMMENT ON COLUMN trade_total_report.INTEGRATION_COUNT  IS '积分汇总';
COMMENT ON COLUMN trade_total_report.COUPON_COUNT  IS '积分兑换券汇总';
COMMENT ON COLUMN trade_total_report.PRODUCT_COUPON_COUNT  IS '指定商品兑换券汇总';
COMMENT ON COLUMN trade_total_report.ORDER_CASH  IS '现金汇总';
COMMENT ON COLUMN trade_total_report.FREIGHT_AMOUNT  IS '运费汇总';
COMMENT ON COLUMN trade_total_report.CASH_FREIGHT_AMOUNT  IS '现金+运费';
COMMENT ON COLUMN trade_total_report.PAYMENT_TIME   IS '交易日期';
COMMENT ON COLUMN trade_total_report.CREATE_TIME   IS '创建时间';
create unique index unq_create_time_merchant_no on  trade_total_report(CREATE_TIME,MERCHANT_NO,ORDER_TYPE);
create index idx_total_create_time on trade_total_report(CREATE_TIME);
create index idx_total_merchant_no on trade_total_report(MERCHANT_NO);

CREATE or replace PUBLIC SYNONYM trade_total_report FOR trade_total_report;
grant select,insert,update,delete on trade_total_report to mallopr;
grant select on trade_total_report to qdbankcx;


create table trade_detail_report(
    ID  number(20) not null primary key,
    ORDER_ID                    VARCHAR2(20),
    MERCHANT_NO                 VARCHAR2(20),
    MERCHANT_NAME               VARCHAR2(64),
    ORDER_SN                    VARCHAR2(64),
    PRODUCT_ID                  VARCHAR2(20),
    PRODUCT_SKU_ID              VARCHAR2(20),
    PRODUCT_PRICE               VARCHAR2(20),
    ORDER_CASH                  VARCHAR2(16),
    ORDER_INTEGRATION           VARCHAR2(16),
    FREIGHT_AMOUNT              VARCHAR2(16),
    COUPON_TYPE                 VARCHAR2(16),
    INTEGRATION_COUPON_AMOUNT   VARCHAR2(16),
    PRODUCT_COUPON_AMOUNT       VARCHAR2(16),
    INTEGRATION_PAY_FLAG        VARCHAR2(8),
    PAYMENT_TIME                VARCHAR2(32),
    BATCH_NO                    VARCHAR2(64),
    ORDER_TYPE                  VARCHAR2(32),
    CASH_FREIGHT_AMOUNT         VARCHAR2(16),
    CREATE_TIME                 DATE)initrans 6;
COMMENT ON TABLE trade_detail_report IS '商城交易明细报表';
COMMENT ON COLUMN trade_detail_report.ID  IS '主键';
COMMENT ON COLUMN trade_detail_report.ORDER_ID  IS '订单流水号';
COMMENT ON COLUMN trade_detail_report.MERCHANT_NO  IS '商户编号';
COMMENT ON COLUMN trade_detail_report.MERCHANT_NAME  IS '商户名称';
COMMENT ON COLUMN trade_detail_report.ORDER_SN  IS '订单编号';
COMMENT ON COLUMN trade_detail_report.PRODUCT_ID  IS '商品编号';
COMMENT ON COLUMN trade_detail_report.PRODUCT_SKU_ID  IS '规格编号';
COMMENT ON COLUMN trade_detail_report.PRODUCT_PRICE  IS '商品实际售价折算价';
COMMENT ON COLUMN trade_detail_report.ORDER_CASH  IS '支付现金：实际支付现金(商品售价-券面值金额)部分不包含运费';
COMMENT ON COLUMN trade_detail_report.ORDER_INTEGRATION  IS '订单积分';
COMMENT ON COLUMN trade_detail_report.FREIGHT_AMOUNT  IS '运费金额';
COMMENT ON COLUMN trade_detail_report.COUPON_TYPE  IS '优惠券类型：0积分兑换券1指定商品免费兑换券';
COMMENT ON COLUMN trade_detail_report.INTEGRATION_COUPON_AMOUNT  IS '积分兑换券面值';
COMMENT ON COLUMN trade_detail_report.PRODUCT_COUPON_AMOUNT  IS '指定商品免费兑换券面值';
COMMENT ON COLUMN trade_detail_report.INTEGRATION_PAY_FLAG  IS '积分结算标识：否 不结算 是 需要结算';
COMMENT ON COLUMN trade_detail_report.PAYMENT_TIME  IS '支付完成时间';
COMMENT ON COLUMN trade_detail_report.BATCH_NO  IS '指定商品免费兑换券批次号';
COMMENT ON COLUMN trade_detail_report.ORDER_TYPE  IS '订单类型';
COMMENT ON COLUMN trade_detail_report.CASH_FREIGHT_AMOUNT  IS '现金+运费';
COMMENT ON COLUMN trade_detail_report.CREATE_TIME   IS '创建时间';
create index idx_detail_payment_time on trade_detail_report(PAYMENT_TIME);
create index idx_detail_merchant_no on trade_detail_report(MERCHANT_NO);
CREATE or replace PUBLIC SYNONYM trade_detail_report FOR trade_detail_report;
grant select,insert,update,delete on trade_detail_report to mallopr;
grant select on trade_detail_report to qdbankcx;

create table trade_market_fee_report(
ID  number(20) not null primary key,
MERCHANT_NO                 VARCHAR2(20),
MERCHANT_NAME               VARCHAR2(64),
PRODUCT_COUPON               VARCHAR2(20),
COUPON_NAME                 VARCHAR2(64),
PAYMENT_TIME                VARCHAR2(32),
BATCH_NO                    VARCHAR2(64),
CREATE_TIME                 DATE)initrans 6;
COMMENT ON TABLE trade_market_fee_report IS '营销费用月度汇总报表';
COMMENT ON COLUMN trade_market_fee_report.ID  IS '主键';
COMMENT ON COLUMN trade_market_fee_report.MERCHANT_NO  IS '商户编号';
COMMENT ON COLUMN trade_market_fee_report.MERCHANT_NAME  IS '商户名称';
COMMENT ON COLUMN trade_market_fee_report.PRODUCT_COUPON  IS '指定商品免费兑换券金额';
COMMENT ON COLUMN trade_market_fee_report.COUPON_NAME  IS '商品券名称';
COMMENT ON COLUMN trade_market_fee_report.PAYMENT_TIME  IS '日期';
COMMENT ON COLUMN trade_market_fee_report.BATCH_NO  IS '指定商品免费兑换券批次号';
COMMENT ON COLUMN trade_market_fee_report.CREATE_TIME   IS '创建时间';
create index idx_market_payment_time on trade_market_fee_report(PAYMENT_TIME);
create index idx_market_merchant_no on trade_market_fee_report(MERCHANT_NO);
CREATE or replace PUBLIC SYNONYM trade_market_fee_report FOR trade_market_fee_report;
grant select,insert,update,delete on trade_market_fee_report to mallopr;
grant select on trade_market_fee_report to qdbankcx;

create table trade_integration_report(
 ID  number(20) not null primary key,
 MERCHANT_NO                 VARCHAR2(20),
 MERCHANT_NAME               VARCHAR2(64),
 NEED_PAY_INTEGRATION        VARCHAR2(32),
 NOT_NEED_PAY_INTEGRATION    VARCHAR2(32),
 INTEGRATION_COUPON          VARCHAR2(32),
 NEED_PAY_TOTAL_INTEGRATION  VARCHAR2(32),
 TOTAL_INTEGRATION           VARCHAR2(32),
 PAYMENT_TIME                VARCHAR2(32),
 CREATE_TIME                 DATE)initrans 6;
COMMENT ON TABLE trade_integration_report IS '商城积分交易汇总';
COMMENT ON COLUMN trade_integration_report.ID  IS '主键';
COMMENT ON COLUMN trade_integration_report.MERCHANT_NO  IS '商户编号';
COMMENT ON COLUMN trade_integration_report.MERCHANT_NAME  IS '商户名称';
COMMENT ON COLUMN trade_integration_report.NEED_PAY_INTEGRATION  IS '需结算积分（元）';
COMMENT ON COLUMN trade_integration_report.NOT_NEED_PAY_INTEGRATION  IS '不需结算积分(元)';
COMMENT ON COLUMN trade_integration_report.PAYMENT_TIME  IS '日期';
COMMENT ON COLUMN trade_integration_report.INTEGRATION_COUPON  IS '积分兑换券(元)';
COMMENT ON COLUMN trade_integration_report.NEED_PAY_TOTAL_INTEGRATION  IS '需结算汇总';
COMMENT ON COLUMN trade_integration_report.TOTAL_INTEGRATION  IS '汇总';
COMMENT ON COLUMN trade_integration_report.CREATE_TIME   IS '创建时间';
create index idx_integration_payment_time on trade_integration_report(PAYMENT_TIME);
create index idx_integration_merchant_no on trade_integration_report(MERCHANT_NO);
CREATE or replace PUBLIC SYNONYM trade_integration_report FOR trade_integration_report;
grant select,insert,update,delete on trade_integration_report to mallopr;
grant select on trade_integration_report to qdbankcx;

create table trade_file_data(
    ID  number(20) not null primary key,
    ORG                         VARCHAR2(12),
    MER_CODE                    VARCHAR2(15),
    MER_NAME                    VARCHAR2(100),
    ORDER_SN                    VARCHAR2(64),
    CUST_NO                     VARCHAR2(32),
    TXN_DATE                    VARCHAR2(16),
    TXN_TIME                    VARCHAR2(16),
    ORDER_STATUS                VARCHAR2(2),
    ORDER_AMT                   NUMBER(15,2),
    EXPEND_POINT                VARCHAR2(32),
    EXPEND_AMT                  NUMBER(15,2),
    ITEM_ID                     VARCHAR2(20),
    ADVICE_PRICE                NUMBER(15,2),
    RESERVED                    VARCHAR2(200),
    PAYMENT_METHOD              VARCHAR2(2),
    POINT_SET_MARK              VARCHAR2(1),
    TX_DT                       VARCHAR2(16),
    CREATE_TIME                 DATE)initrans 6;
COMMENT ON TABLE trade_file_data IS '通联对账文件交易数据表';
COMMENT ON COLUMN trade_file_data.ID  IS '主键';
COMMENT ON COLUMN trade_file_data.ORG IS '机构号';
COMMENT ON COLUMN trade_file_data.MER_CODE  IS '商户编号';
COMMENT ON COLUMN trade_file_data.MER_NAME  IS '商户名称';
COMMENT ON COLUMN trade_file_data.ORDER_SN  IS '订单号';
COMMENT ON COLUMN trade_file_data.CUST_NO  IS '客户号';
COMMENT ON COLUMN trade_file_data.TXN_DATE  IS '订单日期';
COMMENT ON COLUMN trade_file_data.TXN_TIME  IS '订单时间';
COMMENT ON COLUMN trade_file_data.ORDER_STATUS  IS '订单状态 R 撤销 S正常';
COMMENT ON COLUMN trade_file_data.ORDER_AMT  IS '订单总金额：现金+积分';
COMMENT ON COLUMN trade_file_data.EXPEND_POINT IS '订单消耗积分';
COMMENT ON COLUMN trade_file_data.EXPEND_AMT IS '订单消耗金额';
COMMENT ON COLUMN trade_file_data.ITEM_ID IS '商品编号';
COMMENT ON COLUMN trade_file_data.ADVICE_PRICE IS '建议售价';
COMMENT ON COLUMN trade_file_data.RESERVED IS '预留域';
COMMENT ON COLUMN trade_file_data.PAYMENT_METHOD IS '支付方式：BP|纯积分 BM|纯现金 PM|积分+现金';
COMMENT ON COLUMN trade_file_data.POINT_SET_MARK IS '积分结算标志： Y|结算 N|不结算';
COMMENT ON COLUMN trade_file_data.CREATE_TIME   IS '创建时间';
COMMENT ON COLUMN trade_file_data.TX_DT   IS '日切时间';
create index idx_file_txn_date on trade_file_data(TXN_DATE);
create index idx_file_create_time on trade_file_data(CREATE_TIME);
create index idx_file_TX_DT on trade_file_data(TX_DT);

CREATE or replace PUBLIC SYNONYM trade_file_data FOR trade_file_data;
grant select,insert,update,delete on trade_file_data to mallopr;
grant select on trade_file_data to qdbankcx;



create table PMS_MOBILE_SKU
(
	MOBILE_SKU_ID NUMBER(20) not null
		primary key,
	PRODUCT_PRICE NUMBER(8,2) default 0,
	PRODUCT_NAME VARCHAR2(300),
	SKU_PIC_URL VARCHAR2(2000),
	PRODUCT_ID NUMBER(20) default 100031,
	SUPPLY_PRODUCT_ID VARCHAR2(50),
	SUPPLY_TYPE VARCHAR2(50) not null,
	SUPPLY_PRODUCT_SIZE VARCHAR2(50) not null,
	SUPPLY_SUPPLIER_TYPE VARCHAR2(50) not null,
	SUPPLY_PRICE NUMBER(8,2) default 0 not null,
	STATUS NUMBER(1) default 1 not null,
	CREATE_TIME DATE,
	UPDATE_TIME DATE,
	CREATED_BY VARCHAR2(64),
	UPDATED_BY VARCHAR2(64)
)initrans 6;
comment on table PMS_MOBILE_SKU is '话费规格信息';
comment on column PMS_MOBILE_SKU.PRODUCT_PRICE is '商品售价';
comment on column PMS_MOBILE_SKU.PRODUCT_NAME is '商品名称';
comment on column PMS_MOBILE_SKU.SKU_PIC_URL is '规格图片URL';
comment on column PMS_MOBILE_SKU.PRODUCT_ID is '商城产品id';
comment on column PMS_MOBILE_SKU.SUPPLY_PRODUCT_ID is '网信产品id';
comment on column PMS_MOBILE_SKU.SUPPLY_TYPE is '产品类型 1：话费 2：流量';
comment on column PMS_MOBILE_SKU.SUPPLY_PRODUCT_SIZE is '产品大小，type=1 时单位为元，type=2 时单位 为 M';
comment on column PMS_MOBILE_SKU.SUPPLY_SUPPLIER_TYPE is '运营商类型';
comment on column PMS_MOBILE_SKU.STATUS is '状态标识：1上架 0下架';
comment on column PMS_MOBILE_SKU.CREATE_TIME is '创建时间';
comment on column PMS_MOBILE_SKU.UPDATE_TIME is '更新时间';
comment on column PMS_MOBILE_SKU.CREATED_BY is '创建人';
comment on column PMS_MOBILE_SKU.UPDATED_BY is '更新人';
CREATE or replace PUBLIC SYNONYM PMS_MOBILE_SKU FOR PMS_MOBILE_SKU;
grant select,insert,update,delete on PMS_MOBILE_SKU to mallopr;
grant select on PMS_MOBILE_SKU to qdbankcx;

create table PMS_MOBILE_SKU_PROVINCE
(
	MOBILE_SKU_PROVINCE_ID NUMBER(20) not null
		primary key,
	MOBILE_SKU_ID NUMBER(20) not null,
	PRODUCT_PRICE NUMBER(8,2) default 0,
	PRODUCT_NAME VARCHAR2(300),
	SUPPLY_PRICE NUMBER(8,2) default 0 not null,
	STATUS NUMBER(1) default 0 not null,
	CREATE_TIME DATE,
	UPDATE_TIME DATE,
	CREATED_BY VARCHAR2(64),
	UPDATED_BY VARCHAR2(64),
	MOBILE_ADDRESS VARCHAR2(256) not null
)initrans 6;
comment on table PMS_MOBILE_SKU_PROVINCE is '话费省份规格信息';
comment on column PMS_MOBILE_SKU_PROVINCE.MOBILE_SKU_ID is '规格id';
comment on column PMS_MOBILE_SKU_PROVINCE.PRODUCT_PRICE is '商品售价';
comment on column PMS_MOBILE_SKU_PROVINCE.PRODUCT_NAME is '商品名称';
comment on column PMS_MOBILE_SKU_PROVINCE.STATUS is '状态标识：1上架 0下架';
comment on column PMS_MOBILE_SKU_PROVINCE.CREATE_TIME is '创建时间';
comment on column PMS_MOBILE_SKU_PROVINCE.UPDATE_TIME is '更新时间';
comment on column PMS_MOBILE_SKU_PROVINCE.CREATED_BY is '创建人';
comment on column PMS_MOBILE_SKU_PROVINCE.UPDATED_BY is '更新人';
comment on column PMS_MOBILE_SKU_PROVINCE.MOBILE_ADDRESS is '手机归属地';
CREATE or replace PUBLIC SYNONYM PMS_MOBILE_SKU_PROVINCE FOR PMS_MOBILE_SKU_PROVINCE;
grant select,insert,update,delete on PMS_MOBILE_SKU_PROVINCE to mallopr;
grant select on PMS_MOBILE_SKU_PROVINCE to qdbankcx;

create table OMS_RECHARGE_MOBILE
(
	RECHARGE_MOBILE_ID NUMBER(20) not null
		primary key,
	CUST_NO NUMBER(32),
	MOBILE NUMBER(11),
	SKU_ID NUMBER(20),
	CREATE_TIME DATE,
	UPDATE_TIME DATE,
	MOBILE_ADDRESS VARCHAR2(256),
	SUPPLIER_TYPE VARCHAR2(20)
)initrans 6;
comment on table OMS_RECHARGE_MOBILE is '用户手机充值表';
comment on column OMS_RECHARGE_MOBILE.RECHARGE_MOBILE_ID is '用户手机充值主键 ';
comment on column OMS_RECHARGE_MOBILE.CUST_NO is '通联客户号';
comment on column OMS_RECHARGE_MOBILE.MOBILE is '客户充值手机号码';
comment on column OMS_RECHARGE_MOBILE.SKU_ID is '充值规格';
comment on column OMS_RECHARGE_MOBILE.CREATE_TIME is '创建时间      ';
comment on column OMS_RECHARGE_MOBILE.UPDATE_TIME is '更新时间        ';
comment on column OMS_RECHARGE_MOBILE.MOBILE_ADDRESS is '归属地址';
comment on column OMS_RECHARGE_MOBILE.SUPPLIER_TYPE is '运营商';
create unique index OMS_RECHARGE_MOBILE_CUST_NO
	on OMS_RECHARGE_MOBILE (CUST_NO);
CREATE or replace PUBLIC SYNONYM OMS_RECHARGE_MOBILE FOR OMS_RECHARGE_MOBILE;
grant select,insert,update,delete on OMS_RECHARGE_MOBILE to mallopr;
grant select on OMS_RECHARGE_MOBILE to qdbankcx;



create table OMS_RECHARGE_MOBILE_FLOW
(
    RECHARGE_MOBILE_FOLW_ID NUMBER(20) not null
        primary key,
    CUST_NO NUMBER(32),
    ORDER_SN VARCHAR2(64),
    MOBILE NUMBER(11),
    SKU_ID NUMBER(20),
    WX_ORDER_ID VARCHAR2(32),
    RECHARGE_REMARK varchar2(2000),
    STATUS VARCHAR2(2) default '0',
    CREATE_TIME DATE,
    UPDATE_TIME DATE
);

comment on table OMS_RECHARGE_MOBILE_FLOW is '用户手机充值流水表';
comment on column OMS_RECHARGE_MOBILE_FLOW.RECHARGE_MOBILE_FOLW_ID is '用户手机充值流水主键 ';
comment on column OMS_RECHARGE_MOBILE_FLOW.CUST_NO is '通联客户号';
comment on column OMS_RECHARGE_MOBILE_FLOW.ORDER_SN is '订单号';
comment on column OMS_RECHARGE_MOBILE_FLOW.MOBILE is '客户充值手机号码';
comment on column OMS_RECHARGE_MOBILE_FLOW.SKU_ID is '充值规格';
comment on column OMS_RECHARGE_MOBILE_FLOW.WX_ORDER_ID is '网信订单号';
comment on column OMS_RECHARGE_MOBILE_FLOW.RECHARGE_REMARK is '话费充值原因';
comment on column OMS_RECHARGE_MOBILE_FLOW.STATUS is '状态';
comment on column OMS_RECHARGE_MOBILE_FLOW.CREATE_TIME is '创建时间      ';
comment on column OMS_RECHARGE_MOBILE_FLOW.UPDATE_TIME is '更新时间        ';
create index OMS_MOBILE_FLOW_ORDER_INDEX
    on OMS_RECHARGE_MOBILE_FLOW (ORDER_SN desc);
CREATE or replace PUBLIC SYNONYM OMS_RECHARGE_MOBILE_FLOW FOR OMS_RECHARGE_MOBILE_FLOW;
grant select,insert,update,delete on OMS_RECHARGE_MOBILE_FLOW to mallopr;
grant select on OMS_RECHARGE_MOBILE_FLOW to qdbankcx;



create table OMS_PAYMENT_FLOW
(
	PAYMENT_FOLW_ID NUMBER(20) not null
		primary key,
	CUST_NO NUMBER(32),
	ORDER_SN VARCHAR2(64),
    ACCT_TYPE VARCHAR2(10),
    ORI_TRANS_DT VARCHAR2(14),
    ORI_TRANSSER VARCHAR2(14),
    QUERY_TYPE VARCHAR2(2),
    TRANS_AMT VARCHAR2(10),
    TRANSE VARCHAR2(10),
    ACCESS_SIGN_ID VARCHAR2(100),
	STATUS VARCHAR2(2) default '0',
	CREATE_TIME DATE,
	UPDATE_TIME DATE
);
comment on table OMS_PAYMENT_FLOW is '支付流水表';
comment on column OMS_PAYMENT_FLOW.CUST_NO is '通联客户号';
comment on column OMS_PAYMENT_FLOW.ORDER_SN is '订单号';
comment on column OMS_PAYMENT_FLOW.ACCT_TYPE is '账户类型';
comment on column OMS_PAYMENT_FLOW.ORI_TRANS_DT is '交易时间';
comment on column OMS_PAYMENT_FLOW.ORI_TRANSSER is '交易流水号';
comment on column OMS_PAYMENT_FLOW.QUERY_TYPE is '支付类型';
comment on column OMS_PAYMENT_FLOW.TRANS_AMT is '金额';
comment on column OMS_PAYMENT_FLOW.TRANSE is '积分';
comment on column OMS_PAYMENT_FLOW.ACCESS_SIGN_ID is '行内准入接口返回准入标识';
comment on column OMS_PAYMENT_FLOW.STATUS is '状态';
comment on column OMS_PAYMENT_FLOW.CREATE_TIME is '创建时间      ';
comment on column OMS_PAYMENT_FLOW.UPDATE_TIME is '更新时间        ';
create index OMS_PAYMENT_FLOW_INDEX
	on OMS_PAYMENT_FLOW (ORDER_SN desc);
CREATE or replace PUBLIC SYNONYM OMS_PAYMENT_FLOW FOR OMS_PAYMENT_FLOW;
grant select,insert,update,delete on OMS_PAYMENT_FLOW to mallopr;
grant select on OMS_PAYMENT_FLOW to qdbankcx;

--商品规格和规格属性关系表
CREATE TABLE PMS_PRODUCT_SKU_ATTR_RELATION
(
id number(20) NOT NULL,
product_id NUMBER(20) DEFAULT NULL,
sku_stock_id NUMBER(20) DEFAULT NULL,
attribute_id NUMBER(20) DEFAULT NULL,
create_time date default null,
update_time date default null,
created_by varchar(64) default null,
updated_by varchar(64) default null
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE  PMS_PRODUCT_SKU_ATTR_RELATION IS '商品规格和规格属性关系表';
COMMENT ON COLUMN PMS_PRODUCT_SKU_ATTR_RELATION.ID IS 'ID';
COMMENT ON COLUMN PMS_PRODUCT_SKU_ATTR_RELATION.product_id IS '商品id';
COMMENT ON COLUMN PMS_PRODUCT_SKU_ATTR_RELATION.sku_stock_id IS '规格信息id';
COMMENT ON COLUMN PMS_PRODUCT_SKU_ATTR_RELATION.attribute_id IS '规格属性id';
COMMENT ON column PMS_PRODUCT_SKU_ATTR_RELATION.create_time   is '创建时间';
COMMENT ON column PMS_PRODUCT_SKU_ATTR_RELATION.update_time   is '修改时间';
COMMENT ON column PMS_PRODUCT_SKU_ATTR_RELATION.created_by   is '创建人';
COMMENT ON column PMS_PRODUCT_SKU_ATTR_RELATION.updated_by   is '更新人';
-- index
create unique index idx_prod_id_sku_id_attr_id on PMS_PRODUCT_SKU_ATTR_RELATION(product_id,sku_stock_id,attribute_id);
create index idx_psar_attribute_id on PMS_PRODUCT_SKU_ATTR_RELATION(attribute_id) initrans 16;
create index idx_psar_id on PMS_PRODUCT_SKU_ATTR_RELATION(ID) initrans 16;
CREATE or replace PUBLIC SYNONYM PMS_PRODUCT_SKU_ATTR_RELATION FOR PMS_PRODUCT_SKU_ATTR_RELATION;
grant select,insert,update,delete on PMS_PRODUCT_SKU_ATTR_RELATION to mallopr;
grant select on PMS_PRODUCT_SKU_ATTR_RELATION to qdbankcx;






INSERT INTO ums_menu VALUES (1,0,0,0,    '订单管理',      'icon_dingdanguanli',  'ord',           sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (2,1,1,0,    '订单查询',      'daohangweixuanzhong', 'order',         sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (3,1,1,0,    '退款查询',      'daohangweixuanzhong', 'refund',        sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (4,0,0,0,    '运营管理',      'training',            'ope',           sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (5,4,1,0,    '广告配置',      'daohangweixuanzhong', 'advert',        sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (6,4,1,0,    '专区配置',      'daohangweixuanzhong', 'arrondi',       sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (7,4,1,0,    '活动配置',      'daohangweixuanzhong', 'activity',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (8,0,0,0,    '营销管理',      'icon_yingxiaoguanli', 'mar',           sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (9,8,1,0,    '优惠卷管理',    'daohangweixuanzhong', 'coupon',        sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (10,8,1,0,   '积分兑换券管理','daohangweixuanzhong', 'integrate',     sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (11,0,0,0,   '商户管理' ,     'icon_shanghuguanli',  'mer',           sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (12,11,1,0,  '商户查询',      'daohangweixuanzhong', 'merchant',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (13,0,0,0,   '商品管理',      'icon_shangpinguanli', 'com',           sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (14,13,1,0,  '商品库',        'daohangweixuanzhong', 'commodity',     sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (15,13,1,0,  '类目管理',      'daohangweixuanzhong', 'category',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (16,13,1,0,  '退款设置',      'daohangweixuanzhong', 'refunds',       sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (17,0 ,0,0,  '财务管理' ,     'icon_caiwuguanli',    'fin',           sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (18,17,1,0,  '交易汇总表',    'daohangweixuanzhong', 'summary',       sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (19,17,1,0,  '交易明细表' ,   'daohangweixuanzhong', 'detailed',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (20,17,1,0,  '积分月汇总表' , 'daohangweixuanzhong', 'points',        sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (21,17,1,0,  '营销费月汇总表','daohangweixuanzhong', 'marketings',    sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (22,0 ,0,0,  '系统管理',      'xingzhuang',          'ums',           sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (23,22,1,0,  '用户管理',      'daohangweixuanzhong', 'admin',         sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (24,22,1,0,  '部门管理',      'daohangweixuanzhong', 'department',    sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (25,22,1,0,  '角色管理',      'daohangweixuanzhong', 'role',          sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (26,28,1,0,  '菜单管理',      'daohangweixuanzhong', 'menu',          sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (27,28,1,0,  '资源管理',      'daohangweixuanzhong', 'resource',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (28,0,0,0,  '配置管理',      'icon_shouye', 'par',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (29,28,1,0,  '参数配置管理',      'daohangweixuanzhong', 'parameter',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (30,28,1,0,  '定时设置管理',      'daohangweixuanzhong', 'timing',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (31,28,1,0,  '日志列表管理',      'daohangweixuanzhong', 'journal',      sysdate, sysdate, '系统管理员',  '系统管理员');
INSERT INTO ums_menu VALUES (32,28,1,0,  '上传历史数据管理',      'daohangweixuanzhong', 'historical',      sysdate, sysdate, '系统管理员',  '系统管理员');

insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10000', null, '10元话费充值券', '0', null, '1000', '10', '1.本抵用券可进行移动、联通以及电信手机号码的充值。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对手机号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '1', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10001', null, '10元油卡充值券', '0', null, '1000', '10', '1.本电子券仅供中石油/中石化客户使用，请确认已开通相关的加油充值卡再进行兑换。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对卡号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '2', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10002', null, '15元话费充值券', '0', null, '1500', '15', '1.本抵用券可进行移动、联通以及电信手机号码的充值。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对手机号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '1', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10003', null, '30元油卡充值券', '0', null, '3000', '30', '1.本电子券仅供中石油/中石化客户使用，请确认已开通相关的加油充值卡再进行兑换。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对卡号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '2', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10004', null, '3元话费充值券', '0', null, '300', '3', '1.本抵用券可进行移动、联通以及电信手机号码的充值。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对手机号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '1', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10005', null, '3元视频会员充值券', '0', null, '300', '3', '1.充值前，需先注册成为爱奇艺/优酷/腾讯等的会员，再输入相应账号进行激活/充值。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对帐号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '3', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10006', null, '50元油卡充值券', '0', null, '5000', '50', '1.本电子券仅供中石油/中石化客户使用，请确认已开通相关的加油充值卡再进行兑换。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对卡号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '2', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10007', null, '5元话费充值券', '0', null, '500', '5', '1.本抵用券可进行移动、联通以及电信手机号码的充值。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对手机号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '1', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
insert into sms_coupon (COUPON_ID, BATCH_NO, COUPON_NAME, COUPON_TYPE, ALL_TOTAL, PRODUCT_INTEGRATION, COUPON_AMOUNT, COUPON_DESCRIPTION, PRODUCT_STATUS, BATCH_STATUS, PRODUCT_TYPE, PRODUCT_ID, PRODUCT_NAME, PRODUCT_SKU_ID, FILE_NAME, FILE_URL, EXPIRE_DAYS, EXPIRE_DATE, START_TIME, END_TIME, SEND_TIME, GROUP_ID, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('10008', null, '5元视频会员充值券', '0', null, '500', '5', '1.	充值前，需先注册成为爱奇艺/优酷/腾讯等的会员，再输入相应账号进行激活/充值。
2.请在兑换后30天内进行使用，逾期作废，且积分不予退还。
3.该抵用券售出后不退不换。
4.本券不找零，不兑现，不可与其他优惠叠加使用，每次充值仅可使用一张优惠券，不可叠加使用。
5.请用户充值时仔细核对帐号，如填写错误，导致充值失败，平台概不负责。
6.客服电话：400-669-6588', '2', null, '3', '100031', null, null, null, null, '30', to_date('01-05-2021 09:53:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-05-2021 11:51:18', 'dd-mm-yyyy hh24:mi:ss'), to_date('21-05-2021 11:51:43', 'dd-mm-yyyy hh24:mi:ss'), '酷屏', '酷屏');
