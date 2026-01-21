COMMENT ON TABLE mq_send IS '写入MQ失败记录流水表';
COMMENT ON COLUMN mq_send.ID IS '主键';
COMMENT ON COLUMN mq_send.unique_id IS '补发数据唯一ID';
COMMENT ON COLUMN mq_send.create_time IS '创建时间';