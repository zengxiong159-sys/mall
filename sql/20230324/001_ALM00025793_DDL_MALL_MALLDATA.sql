alter table pms_sku_stock add  min_integral_deduct  number(8,0);
alter table CMS_PREFECTURE_STOCK_RELATION add product_level number(6);
update CMS_PREFECTURE_STOCK_RELATION t set t.PRODUCT_LEVEL = 100 where t.PRODUCT_LEVEL is null;


