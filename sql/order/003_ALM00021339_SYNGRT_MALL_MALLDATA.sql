CREATE or replace PUBLIC SYNONYM oms_download FOR oms_download;
grant select,insert,update,delete on oms_download to mallopr;
grant select on oms_download to qdbankcx;