CREATE or replace PUBLIC SYNONYM mq_send FOR mq_send;
grant select,insert,update,delete on mq_send to mallopr;
grant select on mq_send to qdbankcx;