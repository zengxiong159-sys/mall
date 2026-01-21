package com.qdbank.mall.model.mobileSku;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MobileSkuDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MobileSkuDOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andMobileSkuIdIsNull() {
            addCriterion("MOBILE_SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdIsNotNull() {
            addCriterion("MOBILE_SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdEqualTo(Long value) {
            addCriterion("MOBILE_SKU_ID =", value, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdNotEqualTo(Long value) {
            addCriterion("MOBILE_SKU_ID <>", value, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdGreaterThan(Long value) {
            addCriterion("MOBILE_SKU_ID >", value, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MOBILE_SKU_ID >=", value, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdLessThan(Long value) {
            addCriterion("MOBILE_SKU_ID <", value, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("MOBILE_SKU_ID <=", value, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdIn(List<Long> values) {
            addCriterion("MOBILE_SKU_ID in", values, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdNotIn(List<Long> values) {
            addCriterion("MOBILE_SKU_ID not in", values, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdBetween(Long value1, Long value2) {
            addCriterion("MOBILE_SKU_ID between", value1, value2, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andMobileSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("MOBILE_SKU_ID not between", value1, value2, "mobileSkuId");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNull() {
            addCriterion("PRODUCT_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andProductPriceIsNotNull() {
            addCriterion("PRODUCT_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andProductPriceEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_PRICE =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_PRICE <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(BigDecimal value) {
            addCriterion("PRODUCT_PRICE >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_PRICE >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(BigDecimal value) {
            addCriterion("PRODUCT_PRICE <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_PRICE <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_PRICE in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_PRICE not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_PRICE between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_PRICE not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("PRODUCT_NAME =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("PRODUCT_NAME <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("PRODUCT_NAME >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("PRODUCT_NAME <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("PRODUCT_NAME like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("PRODUCT_NAME not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("PRODUCT_NAME in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("PRODUCT_NAME not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlIsNull() {
            addCriterion("SKU_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlIsNotNull() {
            addCriterion("SKU_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlEqualTo(String value) {
            addCriterion("SKU_PIC_URL =", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotEqualTo(String value) {
            addCriterion("SKU_PIC_URL <>", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlGreaterThan(String value) {
            addCriterion("SKU_PIC_URL >", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SKU_PIC_URL >=", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlLessThan(String value) {
            addCriterion("SKU_PIC_URL <", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlLessThanOrEqualTo(String value) {
            addCriterion("SKU_PIC_URL <=", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlLike(String value) {
            addCriterion("SKU_PIC_URL like", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotLike(String value) {
            addCriterion("SKU_PIC_URL not like", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlIn(List<String> values) {
            addCriterion("SKU_PIC_URL in", values, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotIn(List<String> values) {
            addCriterion("SKU_PIC_URL not in", values, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlBetween(String value1, String value2) {
            addCriterion("SKU_PIC_URL between", value1, value2, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotBetween(String value1, String value2) {
            addCriterion("SKU_PIC_URL not between", value1, value2, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdIsNull() {
            addCriterion("SUPPLY_PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdIsNotNull() {
            addCriterion("SUPPLY_PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_ID =", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdNotEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_ID <>", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdGreaterThan(String value) {
            addCriterion("SUPPLY_PRODUCT_ID >", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_ID >=", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdLessThan(String value) {
            addCriterion("SUPPLY_PRODUCT_ID <", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdLessThanOrEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_ID <=", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdLike(String value) {
            addCriterion("SUPPLY_PRODUCT_ID like", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdNotLike(String value) {
            addCriterion("SUPPLY_PRODUCT_ID not like", value, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdIn(List<String> values) {
            addCriterion("SUPPLY_PRODUCT_ID in", values, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdNotIn(List<String> values) {
            addCriterion("SUPPLY_PRODUCT_ID not in", values, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdBetween(String value1, String value2) {
            addCriterion("SUPPLY_PRODUCT_ID between", value1, value2, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyProductIdNotBetween(String value1, String value2) {
            addCriterion("SUPPLY_PRODUCT_ID not between", value1, value2, "supplyProductId");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeIsNull() {
            addCriterion("SUPPLY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeIsNotNull() {
            addCriterion("SUPPLY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeEqualTo(String value) {
            addCriterion("SUPPLY_TYPE =", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeNotEqualTo(String value) {
            addCriterion("SUPPLY_TYPE <>", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeGreaterThan(String value) {
            addCriterion("SUPPLY_TYPE >", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLY_TYPE >=", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeLessThan(String value) {
            addCriterion("SUPPLY_TYPE <", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeLessThanOrEqualTo(String value) {
            addCriterion("SUPPLY_TYPE <=", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeLike(String value) {
            addCriterion("SUPPLY_TYPE like", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeNotLike(String value) {
            addCriterion("SUPPLY_TYPE not like", value, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeIn(List<String> values) {
            addCriterion("SUPPLY_TYPE in", values, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeNotIn(List<String> values) {
            addCriterion("SUPPLY_TYPE not in", values, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeBetween(String value1, String value2) {
            addCriterion("SUPPLY_TYPE between", value1, value2, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyTypeNotBetween(String value1, String value2) {
            addCriterion("SUPPLY_TYPE not between", value1, value2, "supplyType");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeIsNull() {
            addCriterion("SUPPLY_PRODUCT_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeIsNotNull() {
            addCriterion("SUPPLY_PRODUCT_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE =", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeNotEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE <>", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeGreaterThan(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE >", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE >=", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeLessThan(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE <", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeLessThanOrEqualTo(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE <=", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeLike(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE like", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeNotLike(String value) {
            addCriterion("SUPPLY_PRODUCT_SIZE not like", value, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeIn(List<String> values) {
            addCriterion("SUPPLY_PRODUCT_SIZE in", values, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeNotIn(List<String> values) {
            addCriterion("SUPPLY_PRODUCT_SIZE not in", values, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeBetween(String value1, String value2) {
            addCriterion("SUPPLY_PRODUCT_SIZE between", value1, value2, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplyProductSizeNotBetween(String value1, String value2) {
            addCriterion("SUPPLY_PRODUCT_SIZE not between", value1, value2, "supplyProductSize");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeIsNull() {
            addCriterion("SUPPLY_SUPPLIER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeIsNotNull() {
            addCriterion("SUPPLY_SUPPLIER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeEqualTo(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE =", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeNotEqualTo(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE <>", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeGreaterThan(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE >", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE >=", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeLessThan(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE <", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeLessThanOrEqualTo(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE <=", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeLike(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE like", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeNotLike(String value) {
            addCriterion("SUPPLY_SUPPLIER_TYPE not like", value, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeIn(List<String> values) {
            addCriterion("SUPPLY_SUPPLIER_TYPE in", values, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeNotIn(List<String> values) {
            addCriterion("SUPPLY_SUPPLIER_TYPE not in", values, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeBetween(String value1, String value2) {
            addCriterion("SUPPLY_SUPPLIER_TYPE between", value1, value2, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplySupplierTypeNotBetween(String value1, String value2) {
            addCriterion("SUPPLY_SUPPLIER_TYPE not between", value1, value2, "supplySupplierType");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceIsNull() {
            addCriterion("SUPPLY_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceIsNotNull() {
            addCriterion("SUPPLY_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceEqualTo(BigDecimal value) {
            addCriterion("SUPPLY_PRICE =", value, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceNotEqualTo(BigDecimal value) {
            addCriterion("SUPPLY_PRICE <>", value, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceGreaterThan(BigDecimal value) {
            addCriterion("SUPPLY_PRICE >", value, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SUPPLY_PRICE >=", value, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceLessThan(BigDecimal value) {
            addCriterion("SUPPLY_PRICE <", value, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SUPPLY_PRICE <=", value, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceIn(List<BigDecimal> values) {
            addCriterion("SUPPLY_PRICE in", values, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceNotIn(List<BigDecimal> values) {
            addCriterion("SUPPLY_PRICE not in", values, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUPPLY_PRICE between", value1, value2, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andSupplyPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUPPLY_PRICE not between", value1, value2, "supplyPrice");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Long value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Long value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Long value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Long value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Long value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Long> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Long> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Long value1, Long value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Long value1, Long value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("CREATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("CREATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("CREATED_BY =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("CREATED_BY <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("CREATED_BY >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATED_BY >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("CREATED_BY <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("CREATED_BY <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("CREATED_BY like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("CREATED_BY not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("CREATED_BY in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("CREATED_BY not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("CREATED_BY between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNull() {
            addCriterion("UPDATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIsNotNull() {
            addCriterion("UPDATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedByEqualTo(String value) {
            addCriterion("UPDATED_BY =", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotEqualTo(String value) {
            addCriterion("UPDATED_BY <>", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThan(String value) {
            addCriterion("UPDATED_BY >", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATED_BY >=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThan(String value) {
            addCriterion("UPDATED_BY <", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLessThanOrEqualTo(String value) {
            addCriterion("UPDATED_BY <=", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByLike(String value) {
            addCriterion("UPDATED_BY like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotLike(String value) {
            addCriterion("UPDATED_BY not like", value, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByIn(List<String> values) {
            addCriterion("UPDATED_BY in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotIn(List<String> values) {
            addCriterion("UPDATED_BY not in", values, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByBetween(String value1, String value2) {
            addCriterion("UPDATED_BY between", value1, value2, "updatedBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedByNotBetween(String value1, String value2) {
            addCriterion("UPDATED_BY not between", value1, value2, "updatedBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}