CREATE or replace PUBLIC SYNONYM oms_order_bak  FOR oms_order_bak ;
grant select,insert,update,delete on oms_order_bak to mallopr;
grant select on oms_order_bak to qdbankcx;

insert into oms_order select * from oms_order_bak;

insert into oms_order_bak select * from OMS_ORDER where to_char(trunc(create_time),'yyyy-mm-dd') < '2021-11-12' and close_type in(0,1,2,3,4)
