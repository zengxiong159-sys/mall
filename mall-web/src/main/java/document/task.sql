--
-- A hint submitted by a user: Oracle DB MUST be created as "shared" and the 
-- job_queue_processes parameter  must be greater than 2
-- However, these settings are pretty much standard after any
-- Oracle install, so most users need not worry about this.
--
-- Many other users (including the primary author of Quartz) have had success
-- runing in dedicated mode, so only consider the above as a hint ;-)
--

CREATE TABLE qrtz_job_details
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    JOB_NAME  VARCHAR2(200) NOT NULL,
    JOB_GROUP VARCHAR2(200) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    JOB_CLASS_NAME   VARCHAR2(250) NOT NULL,
    IS_DURABLE VARCHAR2(1) NOT NULL,
    IS_NONCONCURRENT VARCHAR2(1) NOT NULL,
    IS_UPDATE_DATA VARCHAR2(1) NOT NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NOT NULL,
    JOB_DATA BLOB NULL,
    CONSTRAINT QRTZ_JOB_DETAILS_PK PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
);
CREATE or replace PUBLIC SYNONYM qrtz_job_details FOR qrtz_job_details;
grant select,insert,update,delete on qrtz_job_details to mallopr;
grant select on qrtz_job_details to qdbankcx;

CREATE TABLE qrtz_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    JOB_NAME  VARCHAR2(200) NOT NULL,
    JOB_GROUP VARCHAR2(200) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    NEXT_FIRE_TIME NUMBER(13) NULL,
    PREV_FIRE_TIME NUMBER(13) NULL,
    PRIORITY NUMBER(13) NULL,
    TRIGGER_STATE VARCHAR2(16) NOT NULL,
    TRIGGER_TYPE VARCHAR2(8) NOT NULL,
    START_TIME NUMBER(13) NOT NULL,
    END_TIME NUMBER(13) NULL,
    CALENDAR_NAME VARCHAR2(200) NULL,
    MISFIRE_INSTR NUMBER(2) NULL,
    JOB_DATA BLOB NULL,
    CONSTRAINT QRTZ_TRIGGERS_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_TRIGGER_TO_JOBS_FK FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
      REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP)
);
CREATE or replace PUBLIC SYNONYM qrtz_triggers FOR qrtz_triggers;
grant select,insert,update,delete on qrtz_triggers to mallopr;
grant select on qrtz_triggers to qdbankcx;

CREATE TABLE qrtz_simple_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    REPEAT_COUNT NUMBER(7) NOT NULL,
    REPEAT_INTERVAL NUMBER(12) NOT NULL,
    TIMES_TRIGGERED NUMBER(10) NOT NULL,
    CONSTRAINT QRTZ_SIMPLE_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_SIMPLE_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
	REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE or replace PUBLIC SYNONYM qrtz_simple_triggers FOR qrtz_simple_triggers;
grant select,insert,update,delete on qrtz_simple_triggers to mallopr;
grant select on qrtz_simple_triggers to qdbankcx;

CREATE TABLE qrtz_cron_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    CRON_EXPRESSION VARCHAR2(120) NOT NULL,
    TIME_ZONE_ID VARCHAR2(80),
    CONSTRAINT QRTZ_CRON_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_CRON_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
      REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE or replace PUBLIC SYNONYM qrtz_cron_triggers FOR qrtz_cron_triggers;
grant select,insert,update,delete on qrtz_cron_triggers to mallopr;
grant select on qrtz_cron_triggers to qdbankcx;

CREATE TABLE qrtz_simprop_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    STR_PROP_1 VARCHAR2(512) NULL,
    STR_PROP_2 VARCHAR2(512) NULL,
    STR_PROP_3 VARCHAR2(512) NULL,
    INT_PROP_1 NUMBER(10) NULL,
    INT_PROP_2 NUMBER(10) NULL,
    LONG_PROP_1 NUMBER(13) NULL,
    LONG_PROP_2 NUMBER(13) NULL,
    DEC_PROP_1 NUMERIC(13,4) NULL,
    DEC_PROP_2 NUMERIC(13,4) NULL,
    BOOL_PROP_1 VARCHAR2(1) NULL,
    BOOL_PROP_2 VARCHAR2(1) NULL,
    CONSTRAINT QRTZ_SIMPROP_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_SIMPROP_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
      REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE or replace PUBLIC SYNONYM qrtz_simprop_triggers FOR qrtz_simprop_triggers;
grant select,insert,update,delete on qrtz_simprop_triggers to mallopr;
grant select on qrtz_simprop_triggers to qdbankcx;

CREATE TABLE qrtz_blob_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    BLOB_DATA BLOB NULL,
    CONSTRAINT QRTZ_BLOB_TRIG_PK PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    CONSTRAINT QRTZ_BLOB_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE or replace PUBLIC SYNONYM qrtz_blob_triggers FOR qrtz_blob_triggers;
grant select,insert,update,delete on qrtz_blob_triggers to mallopr;
grant select on qrtz_blob_triggers to qdbankcx;

CREATE TABLE qrtz_calendars
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    CALENDAR_NAME  VARCHAR2(200) NOT NULL,
    CALENDAR BLOB NOT NULL,
    CONSTRAINT QRTZ_CALENDARS_PK PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)
);
CREATE or replace PUBLIC SYNONYM qrtz_calendars FOR qrtz_calendars;
grant select,insert,update,delete on qrtz_calendars to mallopr;
grant select on qrtz_calendars to qdbankcx;

CREATE TABLE qrtz_paused_trigger_grps
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    TRIGGER_GROUP  VARCHAR2(200) NOT NULL,
    CONSTRAINT QRTZ_PAUSED_TRIG_GRPS_PK PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)
);
CREATE or replace PUBLIC SYNONYM qrtz_paused_trigger_grps FOR qrtz_paused_trigger_grps;
grant select,insert,update,delete on qrtz_paused_trigger_grps to mallopr;
grant select on qrtz_paused_trigger_grps to qdbankcx;

CREATE TABLE qrtz_fired_triggers
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    ENTRY_ID VARCHAR2(95) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    FIRED_TIME NUMBER(13) NOT NULL,
    SCHED_TIME NUMBER(13) NOT NULL,
    PRIORITY NUMBER(13) NOT NULL,
    STATE VARCHAR2(16) NOT NULL,
    JOB_NAME VARCHAR2(200) NULL,
    JOB_GROUP VARCHAR2(200) NULL,
    IS_NONCONCURRENT VARCHAR2(1) NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NULL,
    CONSTRAINT QRTZ_FIRED_TRIGGER_PK PRIMARY KEY (SCHED_NAME,ENTRY_ID)
);
CREATE or replace PUBLIC SYNONYM qrtz_fired_triggers FOR qrtz_fired_triggers;
grant select,insert,update,delete on qrtz_fired_triggers to mallopr;
grant select on qrtz_fired_triggers to qdbankcx;

CREATE TABLE qrtz_scheduler_state
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    LAST_CHECKIN_TIME NUMBER(13) NOT NULL,
    CHECKIN_INTERVAL NUMBER(13) NOT NULL,
    CONSTRAINT QRTZ_SCHEDULER_STATE_PK PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)
);
CREATE or replace PUBLIC SYNONYM qrtz_scheduler_state FOR qrtz_scheduler_state;
grant select,insert,update,delete on qrtz_scheduler_state to mallopr;
grant select on qrtz_scheduler_state to qdbankcx;

CREATE TABLE qrtz_locks
  (
    SCHED_NAME VARCHAR2(120) NOT NULL,
    LOCK_NAME  VARCHAR2(40) NOT NULL,
    CONSTRAINT QRTZ_LOCKS_PK PRIMARY KEY (SCHED_NAME,LOCK_NAME)
);
CREATE or replace PUBLIC SYNONYM qrtz_locks FOR qrtz_locks;
grant select,insert,update,delete on qrtz_locks to mallopr;
grant select on qrtz_locks to qdbankcx;


create index idx_qrtz_j_req_recovery on qrtz_job_details(SCHED_NAME,REQUESTS_RECOVERY);
create index idx_qrtz_j_grp on qrtz_job_details(SCHED_NAME,JOB_GROUP);

create index idx_qrtz_t_j on qrtz_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
create index idx_qrtz_t_jg on qrtz_triggers(SCHED_NAME,JOB_GROUP);
create index idx_qrtz_t_c on qrtz_triggers(SCHED_NAME,CALENDAR_NAME);
create index idx_qrtz_t_g on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP);
create index idx_qrtz_t_state on qrtz_triggers(SCHED_NAME,TRIGGER_STATE);
create index idx_qrtz_t_n_state on qrtz_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
create index idx_qrtz_t_n_g_state on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
create index idx_qrtz_t_next_fire_time on qrtz_triggers(SCHED_NAME,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_st on qrtz_triggers(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_misfire on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
create index idx_qrtz_t_nft_st_misfire on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
create index idx_qrtz_t_nft_st_misfire_grp on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

create index idx_qrtz_ft_trig_inst_name on qrtz_fired_triggers(SCHED_NAME,INSTANCE_NAME);
create index idx_qrtz_ft_inst_job_req_rcvry on qrtz_fired_triggers(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
create index idx_qrtz_ft_j_g on qrtz_fired_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
create index idx_qrtz_ft_jg on qrtz_fired_triggers(SCHED_NAME,JOB_GROUP);
create index idx_qrtz_ft_t_g on qrtz_fired_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
create index idx_qrtz_ft_tg on qrtz_fired_triggers(SCHED_NAME,TRIGGER_GROUP);


CREATE TABLE schedule_job
(
    JOB_ID                  number(20)      NOT NULL,
    BEAN_NAME               varchar2(100)   not null,
    PARAMS                  varchar2(100)   default null,
    CRON_EXPRESSION                    varchar2(20)    not null,
    STATUS                  number(1)       default 0,
    REMARK                  varchar2(200)   default null,
    create_time     DATE,
  update_time     DATE,
  created_by      VARCHAR2(64),
  updated_by      VARCHAR2(64)
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE schedule_job IS '定时任务表';
COMMENT ON COLUMN schedule_job.JOB_ID IS 'ID';
COMMENT ON COLUMN schedule_job.BEAN_NAME IS 'bean名称';
COMMENT ON COLUMN schedule_job.PARAMS is '参数';
COMMENT ON COLUMN schedule_job.CRON_EXPRESSION is 'cron表达式';
COMMENT ON COLUMN schedule_job.STATUS is '任务状态 0:正常,1:暂停';
COMMENT ON COLUMN schedule_job.REMARK is '备注';
COMMENT ON COLUMN schedule_job.CREATE_TIME  is '创建时间';
comment on column SCHEDULE_JOB.update_time
  is '修改时间';
comment on column SCHEDULE_JOB.created_by
  is '创建人';
comment on column SCHEDULE_JOB.updated_by
  is '修改人';
CREATE or replace PUBLIC SYNONYM schedule_job FOR schedule_job;
grant select,insert,update,delete on schedule_job to mallopr;
grant select on schedule_job to qdbankcx;

--定时任务日志

CREATE TABLE schedule_job_log
(
    LOG_ID                  number(20)      NOT NULL,
    JOB_ID                  number(20)      NOT NULL,
    BEAN_NAME               varchar2(100)   not null,
    PARAMS                  varchar2(100)   default null,
    STATUS                  number(1)       default 0,
    ERROR                   varchar2(2000)   default null,
    TIMES                   number(20)      default null,
    IP                      varchar2(20)    default null,
    CREATE_TIME             date            DEFAULT NULL
) initrans 6;

-- Add comments to the table
COMMENT ON TABLE schedule_job_log IS '定时任务日志';
COMMENT ON COLUMN schedule_job_log.LOG_ID IS '日志id';
COMMENT ON COLUMN schedule_job_log.JOB_ID IS '任务id';
COMMENT ON COLUMN schedule_job_log.BEAN_NAME is 'bean名称';
COMMENT ON COLUMN schedule_job_log.PARAMS is '参数';
COMMENT ON COLUMN schedule_job_log.STATUS is '任务状态 0：成功 1：失败';
COMMENT ON COLUMN schedule_job_log.ERROR is '失败信息';
COMMENT ON COLUMN schedule_job_log.TIMES is '耗时(单位：毫秒)';
COMMENT ON COLUMN schedule_job_log.IP is 'ip';
COMMENT ON COLUMN schedule_job_log.CREATE_TIME  is '创建时间';

-- index
create index IDX_SCHEDULE_CREATE_TIME on schedule_job_log(CREATE_TIME) initrans 16;
create index INDEX_JOB_ID on schedule_job_log(JOB_ID, IP) initrans 16;
CREATE or replace PUBLIC SYNONYM schedule_job_log FOR schedule_job_log;
grant select,insert,update,delete on schedule_job_log to mallopr;
grant select on schedule_job_log to qdbankcx;


-- ϵͳ��־
CREATE TABLE sys_log (
  id NUMBER(20, 0) NOT NULL,
  username varchar2(50),
  operation varchar2(50),
  method varchar2(200),
  params clob,
  time NUMBER(20, 0) NOT NULL,
  ip varchar2(64),
  create_date timestamp,
  PRIMARY KEY (id)
);
CREATE or replace PUBLIC SYNONYM sys_log FOR sys_log;
grant select,insert,update,delete on sys_log to mallopr;
grant select on sys_log to qdbankcx;


insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119937244785233920', 'integrationCouponTaskImpl', null, '0 0 0 */1 * ?', '0', '积分兑换券券过期定时任务：每天凌点执行', to_date('25-04-2021 15:20:11', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-06-2021 14:36:33', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119939185296756736', 'productCouponTaskImpl', null, '0 0 0 */1 * ?', '0', '指定商品免费兑换券过期定时任务：每天零点执行', to_date('25-04-2021 15:27:54', 'dd-mm-yyyy hh24:mi:ss'), to_date('25-04-2021', 'dd-mm-yyyy'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('120002147525021696', 'odsUserCouponTaskImpl', null, '0 45 2 * * ?', '0', '指定用户持券信息数据定时任务：每天凌晨2点45执行一次', to_date('25-04-2021 19:38:05', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-06-2021 16:20:46', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('120001886731587584', 'odsSmsCouponTaskImpl', null, '0 30 2 * * ?', '0', '券商品信息定时任务：每天凌晨2点半执行一次', to_date('25-04-2021 19:37:03', 'dd-mm-yyyy hh24:mi:ss'), to_date('25-04-2021', 'dd-mm-yyyy'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('120002690314096640', 'odsProductTaskImpl', null, '0 0 3 * * ?', '0', '商品信息落通联大数据平台定时任务：每天凌晨3点执行', to_date('25-04-2021 19:40:14', 'dd-mm-yyyy hh24:mi:ss'), to_date('25-04-2021', 'dd-mm-yyyy'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119989426884661248', 'odsRefundOrderTaskImpl', null, '0 15 2 * * ?', '0', '退款交易落通联大数据平台定时任务：每天凌晨2点15跑一次', to_date('25-04-2021 18:47:32', 'dd-mm-yyyy hh24:mi:ss'), to_date('25-04-2021', 'dd-mm-yyyy'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('135417929140383744', 'activityTaskImpl', 'null', '0 0/1 * * * ?', '0', '活动状态自动更新定时任务，一分钟执行一次', to_date('07-06-2021 08:34:54', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-06-2021 08:46:06', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('135418380422328320', 'advertisementTaskImpl', 'null', '0 0/1 * * * ?', '0', '广告状态自动更新定时任务1分钟执行一次', to_date('07-06-2021 08:36:41', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-06-2021 08:45:59', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('135419313503973376', 'prefectureTaskImpl', 'null', '0 0/1 * * * ?', '0', '专区活动状态自动更新定时任务1分钟执行一次', to_date('07-06-2021 08:40:24', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-06-2021 08:45:50', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('135426912588771328', 'deleteSchedulLogTaskImpl', null, '0 0 5 * * ?', '0', '删除定时任务日志定时任务，每天凌晨5点执行', to_date('07-06-2021 09:10:36', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-06-2021 09:10:36', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('136668567656574976', 'orderStatusTaskImpl', null, '0 0/1 * * * ?', '0', '订单收货状态定时任务，每一分钟执行一次', to_date('10-06-2021 19:24:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-06-2021 19:24:29', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119966670835109888', 'tradeDetailTaskImpl', null, '0 0 1 * * ?', '0', '交易明细定时任务：每天凌晨一点执行一次', to_date('25-04-2021 17:17:07', 'dd-mm-yyyy hh24:mi:ss'), to_date('25-04-2021', 'dd-mm-yyyy'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119967275519528960', 'tradeTotalTaskImpl', null, '0 45 1 * * ?', '0', '交易汇总定时任务：每天凌晨1点45执行一次', to_date('25-04-2021 17:19:31', 'dd-mm-yyyy hh24:mi:ss'), to_date('14-05-2021 11:07:44', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('113335420951105536', 'testTask', '{''date'':''2020-09-17''}', '*/5 * * * * ?', '1', '测试0407', to_date('07-04-2021 10:06:54', 'dd-mm-yyyy hh24:mi:ss'), to_date('27-04-2021 19:35:49', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('125292884147785728', 'odsSkuStockTaskImpl', null, '0 15 3 * * ?', '0', '规格数据下发通联大数据平台', to_date('10-05-2021 10:01:35', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-05-2021 10:01:35', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119966059519496192', 'tradeMarketTaskImpl', null, '0 15 1 * * ?', '0', '营销费用汇总定时任务：每天凌晨15分执行一次', to_date('25-04-2021 17:14:41', 'dd-mm-yyyy hh24:mi:ss'), to_date('25-04-2021', 'dd-mm-yyyy'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119966934338064384', 'tradeTotalCouponTaskImpl', null, '0 30 1 * * ?', '0', '优惠券汇总定时任务：每天凌晨1点半执行一次', to_date('25-04-2021 17:18:10', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-06-2021 16:02:37', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('119969912130326528', 'odsOrderTaskImpl', null, '0 0 2 * * ?', '0', '充值订单下发通联大数据平台定时任务：每天凌晨2点执行', to_date('25-04-2021 17:30:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('25-04-2021', 'dd-mm-yyyy'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('125293400751820800', 'noticeOrderTaskImpl', null, '0 */10 * * * ?', '0', '异步通知回调补偿任务每10分钟执行一次', to_date('10-05-2021 10:03:38', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-06-2021 17:21:34', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('125294639803748352', 'orderTradeTaskImpl', null, '0 30 3 * * ?', '0', '通联对账文件数据定时任务每天凌晨3点半开始执行', to_date('10-05-2021 10:08:34', 'dd-mm-yyyy hh24:mi:ss'), to_date('10-05-2021 10:08:34', 'dd-mm-yyyy hh24:mi:ss'), '系统管理员', '系统管理员');

insert into schedule_job (JOB_ID, BEAN_NAME, PARAMS, CRON_EXPRESSION, STATUS, REMARK, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values (146677467323682816, 'mobileRechargeStatusTaskImpl', 'null', '0 0/5 * * * ?', 0, '话费订单自动更新定时任务5分钟执行一次', TO_DATE('2021-07-08 10:16:17', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2021-07-08 10:17:08', 'YYYY-MM-DD HH24:MI:SS'), '系统管理员', '系统管理员');