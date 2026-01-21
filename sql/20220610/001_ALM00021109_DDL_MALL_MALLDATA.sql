alter table pms_sku_stock add  max_integral_deduct  number(8,0);

alter table ods_pms_sku_stock add  max_integral_deduct  number(8,0);

alter table oms_order add  integral_deduct  number(8,2);

alter table ods_oms_order add  integral_deduct  number(8,2);


alter table oms_order_refund add  integral_deduct  number(8,2);

alter table ods_oms_order_refund add  integral_deduct  number(8,2);
