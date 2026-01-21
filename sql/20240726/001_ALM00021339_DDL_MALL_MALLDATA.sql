
create table USER_WHITE_NAME_CONFIG(
                            ID              NUMBER(20)      not null primary key,
                            USER_NAME             VARCHAR2(32)                         ,
                            GENDER           VARCHAR2(2)                     ,
                            STATUS           VARCHAR2(1) default 0,
                            MOBILE               VARCHAR2(16)                     ,
                            ID_NO                VARCHAR2(32)                         ,
                            CREATE_TIME             DATE                               ,
                            UPDATE_TIME             DATE                               ,
                            REMARK                  VARCHAR2(256)                      ,
                            CREATED_BY              VARCHAR2(64)                       ,
                            UPDATED_BY              VARCHAR2(64)
)initrans 6;
create index idx_user_config_time on USER_WHITE_NAME_CONFIG(CREATE_TIME);
create unique index  idx_user_id_no on USER_WHITE_NAME_CONFIG(ID_NO);

alter  table wechat_bind_info add  user_type varchar2(2) default 1;