CREATE or replace PUBLIC SYNONYM oms_send_return FOR oms_send_return;
grant select,insert,update,delete on oms_send_return to mallopr;
grant select on oms_send_return to qdbankcx;