insert into bank_node_address (ID, CITY_NAME, BRANCH_NO, BRANCH_NAME, BRANCH_ADDRESS, CREATE_TIME, UPDATE_TIME, CREATED_BY, UPDATED_BY)
values ('6', '青岛市', '', '青岛银行仙家寨支行', '青岛市城阳区南流路6号仙家寨馨苑A区A-11号', sysdate, sysdate, 'SYSTEM', 'SYSTEM');

update CMS_POSITION t set t.pic_url = replace(t.pic_url,'http://10.1.98.38:8091/minio','https://minf.qdccb.com:9072/mall');
