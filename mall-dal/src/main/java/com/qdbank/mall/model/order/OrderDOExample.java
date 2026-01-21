package com.qdbank.mall.model.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDOExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Timestamp ts = new Timestamp(value.getTime());
            addCriterion(condition, ts  , property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andCustNoIsNull() {
            addCriterion("CUST_NO is null");
            return (Criteria) this;
        }

        public Criteria andCustNoIsNotNull() {
            addCriterion("CUST_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCustNoEqualTo(Long value) {
            addCriterion("CUST_NO =", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotEqualTo(Long value) {
            addCriterion("CUST_NO <>", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoGreaterThan(Long value) {
            addCriterion("CUST_NO >", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoGreaterThanOrEqualTo(Long value) {
            addCriterion("CUST_NO >=", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLessThan(Long value) {
            addCriterion("CUST_NO <", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLessThanOrEqualTo(Long value) {
            addCriterion("CUST_NO <=", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoIn(List<Long> values) {
            addCriterion("CUST_NO in", values, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotIn(List<Long> values) {
            addCriterion("CUST_NO not in", values, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoBetween(Long value1, Long value2) {
            addCriterion("CUST_NO between", value1, value2, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotBetween(Long value1, Long value2) {
            addCriterion("CUST_NO not between", value1, value2, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNull() {
            addCriterion("CUST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustNameIsNotNull() {
            addCriterion("CUST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustNameEqualTo(String value) {
            addCriterion("CUST_NAME =", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotEqualTo(String value) {
            addCriterion("CUST_NAME <>", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThan(String value) {
            addCriterion("CUST_NAME >", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_NAME >=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThan(String value) {
            addCriterion("CUST_NAME <", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLessThanOrEqualTo(String value) {
            addCriterion("CUST_NAME <=", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameLike(String value) {
            addCriterion("CUST_NAME like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotLike(String value) {
            addCriterion("CUST_NAME not like", value, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameIn(List<String> values) {
            addCriterion("CUST_NAME in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotIn(List<String> values) {
            addCriterion("CUST_NAME not in", values, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameBetween(String value1, String value2) {
            addCriterion("CUST_NAME between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustNameNotBetween(String value1, String value2) {
            addCriterion("CUST_NAME not between", value1, value2, "custName");
            return (Criteria) this;
        }

        public Criteria andCustMobileIsNull() {
            addCriterion("CUST_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andCustMobileIsNotNull() {
            addCriterion("CUST_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andCustMobileEqualTo(Long value) {
            addCriterion("CUST_MOBILE =", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotEqualTo(Long value) {
            addCriterion("CUST_MOBILE <>", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileGreaterThan(Long value) {
            addCriterion("CUST_MOBILE >", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileGreaterThanOrEqualTo(Long value) {
            addCriterion("CUST_MOBILE >=", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLessThan(Long value) {
            addCriterion("CUST_MOBILE <", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileLessThanOrEqualTo(Long value) {
            addCriterion("CUST_MOBILE <=", value, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileIn(List<Long> values) {
            addCriterion("CUST_MOBILE in", values, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotIn(List<Long> values) {
            addCriterion("CUST_MOBILE not in", values, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileBetween(Long value1, Long value2) {
            addCriterion("CUST_MOBILE between", value1, value2, "custMobile");
            return (Criteria) this;
        }

        public Criteria andCustMobileNotBetween(Long value1, Long value2) {
            addCriterion("CUST_MOBILE not between", value1, value2, "custMobile");
            return (Criteria) this;
        }

        public Criteria andMerchantNoIsNull() {
            addCriterion("MERCHANT_NO is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNoIsNotNull() {
            addCriterion("MERCHANT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNoEqualTo(Long value) {
            addCriterion("MERCHANT_NO =", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotEqualTo(Long value) {
            addCriterion("MERCHANT_NO <>", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoGreaterThan(Long value) {
            addCriterion("MERCHANT_NO >", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoGreaterThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_NO >=", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLessThan(Long value) {
            addCriterion("MERCHANT_NO <", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLessThanOrEqualTo(Long value) {
            addCriterion("MERCHANT_NO <=", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoIn(List<Long> values) {
            addCriterion("MERCHANT_NO in", values, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotIn(List<Long> values) {
            addCriterion("MERCHANT_NO not in", values, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_NO between", value1, value2, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotBetween(Long value1, Long value2) {
            addCriterion("MERCHANT_NO not between", value1, value2, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNull() {
            addCriterion("MERCHANT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNotNull() {
            addCriterion("MERCHANT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameEqualTo(String value) {
            addCriterion("MERCHANT_NAME =", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotEqualTo(String value) {
            addCriterion("MERCHANT_NAME <>", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThan(String value) {
            addCriterion("MERCHANT_NAME >", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_NAME >=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThan(String value) {
            addCriterion("MERCHANT_NAME <", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_NAME <=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLike(String value) {
            addCriterion("MERCHANT_NAME like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotLike(String value) {
            addCriterion("MERCHANT_NAME not like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIn(List<String> values) {
            addCriterion("MERCHANT_NAME in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotIn(List<String> values) {
            addCriterion("MERCHANT_NAME not in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameBetween(String value1, String value2) {
            addCriterion("MERCHANT_NAME between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_NAME not between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andOrderSnIsNull() {
            addCriterion("ORDER_SN is null");
            return (Criteria) this;
        }

        public Criteria andOrderSnIsNotNull() {
            addCriterion("ORDER_SN is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSnEqualTo(String value) {
            addCriterion("ORDER_SN =", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotEqualTo(String value) {
            addCriterion("ORDER_SN <>", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThan(String value) {
            addCriterion("ORDER_SN >", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_SN >=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThan(String value) {
            addCriterion("ORDER_SN <", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThanOrEqualTo(String value) {
            addCriterion("ORDER_SN <=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLike(String value) {
            addCriterion("ORDER_SN like", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotLike(String value) {
            addCriterion("ORDER_SN not like", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnIn(List<String> values) {
            addCriterion("ORDER_SN in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotIn(List<String> values) {
            addCriterion("ORDER_SN not in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnBetween(String value1, String value2) {
            addCriterion("ORDER_SN between", value1, value2, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotBetween(String value1, String value2) {
            addCriterion("ORDER_SN not between", value1, value2, "orderSn");
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

        public Criteria andProductSkuIdIsNull() {
            addCriterion("PRODUCT_SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdIsNotNull() {
            addCriterion("PRODUCT_SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdEqualTo(Long value) {
            addCriterion("PRODUCT_SKU_ID =", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotEqualTo(Long value) {
            addCriterion("PRODUCT_SKU_ID <>", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdGreaterThan(Long value) {
            addCriterion("PRODUCT_SKU_ID >", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_SKU_ID >=", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdLessThan(Long value) {
            addCriterion("PRODUCT_SKU_ID <", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_SKU_ID <=", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdIn(List<Long> values) {
            addCriterion("PRODUCT_SKU_ID in", values, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotIn(List<Long> values) {
            addCriterion("PRODUCT_SKU_ID not in", values, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_SKU_ID between", value1, value2, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_SKU_ID not between", value1, value2, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNull() {
            addCriterion("PRODUCT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNotNull() {
            addCriterion("PRODUCT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andProductCountEqualTo(Long value) {
            addCriterion("PRODUCT_COUNT =", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotEqualTo(Long value) {
            addCriterion("PRODUCT_COUNT <>", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThan(Long value) {
            addCriterion("PRODUCT_COUNT >", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_COUNT >=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThan(Long value) {
            addCriterion("PRODUCT_COUNT <", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_COUNT <=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIn(List<Long> values) {
            addCriterion("PRODUCT_COUNT in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotIn(List<Long> values) {
            addCriterion("PRODUCT_COUNT not in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_COUNT between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_COUNT not between", value1, value2, "productCount");
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

        public Criteria andProductCashIsNull() {
            addCriterion("PRODUCT_CASH is null");
            return (Criteria) this;
        }

        public Criteria andProductCashIsNotNull() {
            addCriterion("PRODUCT_CASH is not null");
            return (Criteria) this;
        }

        public Criteria andProductCashEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_CASH =", value, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashNotEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_CASH <>", value, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashGreaterThan(BigDecimal value) {
            addCriterion("PRODUCT_CASH >", value, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_CASH >=", value, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashLessThan(BigDecimal value) {
            addCriterion("PRODUCT_CASH <", value, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_CASH <=", value, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_CASH in", values, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashNotIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_CASH not in", values, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_CASH between", value1, value2, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductCashNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_CASH not between", value1, value2, "productCash");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationIsNull() {
            addCriterion("PRODUCT_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationIsNotNull() {
            addCriterion("PRODUCT_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationEqualTo(Long value) {
            addCriterion("PRODUCT_INTEGRATION =", value, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationNotEqualTo(Long value) {
            addCriterion("PRODUCT_INTEGRATION <>", value, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationGreaterThan(Long value) {
            addCriterion("PRODUCT_INTEGRATION >", value, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_INTEGRATION >=", value, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationLessThan(Long value) {
            addCriterion("PRODUCT_INTEGRATION <", value, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_INTEGRATION <=", value, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationIn(List<Long> values) {
            addCriterion("PRODUCT_INTEGRATION in", values, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationNotIn(List<Long> values) {
            addCriterion("PRODUCT_INTEGRATION not in", values, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_INTEGRATION between", value1, value2, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andProductIntegrationNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_INTEGRATION not between", value1, value2, "productIntegration");
            return (Criteria) this;
        }

        public Criteria andCategoryId1IsNull() {
            addCriterion("CATEGORY_ID_1 is null");
            return (Criteria) this;
        }

        public Criteria andCategoryId1IsNotNull() {
            addCriterion("CATEGORY_ID_1 is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryId1EqualTo(Long value) {
            addCriterion("CATEGORY_ID_1 =", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1NotEqualTo(Long value) {
            addCriterion("CATEGORY_ID_1 <>", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1GreaterThan(Long value) {
            addCriterion("CATEGORY_ID_1 >", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1GreaterThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_1 >=", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1LessThan(Long value) {
            addCriterion("CATEGORY_ID_1 <", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1LessThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_1 <=", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1In(List<Long> values) {
            addCriterion("CATEGORY_ID_1 in", values, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1NotIn(List<Long> values) {
            addCriterion("CATEGORY_ID_1 not in", values, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1Between(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_1 between", value1, value2, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1NotBetween(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_1 not between", value1, value2, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId2IsNull() {
            addCriterion("CATEGORY_ID_2 is null");
            return (Criteria) this;
        }

        public Criteria andCategoryId2IsNotNull() {
            addCriterion("CATEGORY_ID_2 is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryId2EqualTo(Long value) {
            addCriterion("CATEGORY_ID_2 =", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2NotEqualTo(Long value) {
            addCriterion("CATEGORY_ID_2 <>", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2GreaterThan(Long value) {
            addCriterion("CATEGORY_ID_2 >", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2GreaterThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_2 >=", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2LessThan(Long value) {
            addCriterion("CATEGORY_ID_2 <", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2LessThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_2 <=", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2In(List<Long> values) {
            addCriterion("CATEGORY_ID_2 in", values, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2NotIn(List<Long> values) {
            addCriterion("CATEGORY_ID_2 not in", values, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2Between(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_2 between", value1, value2, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2NotBetween(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_2 not between", value1, value2, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId3IsNull() {
            addCriterion("CATEGORY_ID_3 is null");
            return (Criteria) this;
        }

        public Criteria andCategoryId3IsNotNull() {
            addCriterion("CATEGORY_ID_3 is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryId3EqualTo(Long value) {
            addCriterion("CATEGORY_ID_3 =", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3NotEqualTo(Long value) {
            addCriterion("CATEGORY_ID_3 <>", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3GreaterThan(Long value) {
            addCriterion("CATEGORY_ID_3 >", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3GreaterThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_3 >=", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3LessThan(Long value) {
            addCriterion("CATEGORY_ID_3 <", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3LessThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_3 <=", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3In(List<Long> values) {
            addCriterion("CATEGORY_ID_3 in", values, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3NotIn(List<Long> values) {
            addCriterion("CATEGORY_ID_3 not in", values, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3Between(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_3 between", value1, value2, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3NotBetween(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_3 not between", value1, value2, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId4IsNull() {
            addCriterion("CATEGORY_ID_4 is null");
            return (Criteria) this;
        }

        public Criteria andCategoryId4IsNotNull() {
            addCriterion("CATEGORY_ID_4 is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryId4EqualTo(Long value) {
            addCriterion("CATEGORY_ID_4 =", value, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4NotEqualTo(Long value) {
            addCriterion("CATEGORY_ID_4 <>", value, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4GreaterThan(Long value) {
            addCriterion("CATEGORY_ID_4 >", value, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4GreaterThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_4 >=", value, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4LessThan(Long value) {
            addCriterion("CATEGORY_ID_4 <", value, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4LessThanOrEqualTo(Long value) {
            addCriterion("CATEGORY_ID_4 <=", value, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4In(List<Long> values) {
            addCriterion("CATEGORY_ID_4 in", values, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4NotIn(List<Long> values) {
            addCriterion("CATEGORY_ID_4 not in", values, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4Between(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_4 between", value1, value2, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andCategoryId4NotBetween(Long value1, Long value2) {
            addCriterion("CATEGORY_ID_4 not between", value1, value2, "categoryId4");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("PAY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("PAY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("PAY_AMOUNT >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("PAY_AMOUNT <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PAY_AMOUNT <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("PAY_AMOUNT in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("PAY_AMOUNT not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAY_AMOUNT between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAY_AMOUNT not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andOrderCashIsNull() {
            addCriterion("ORDER_CASH is null");
            return (Criteria) this;
        }

        public Criteria andOrderCashIsNotNull() {
            addCriterion("ORDER_CASH is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCashEqualTo(BigDecimal value) {
            addCriterion("ORDER_CASH =", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashNotEqualTo(BigDecimal value) {
            addCriterion("ORDER_CASH <>", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashGreaterThan(BigDecimal value) {
            addCriterion("ORDER_CASH >", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDER_CASH >=", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashLessThan(BigDecimal value) {
            addCriterion("ORDER_CASH <", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDER_CASH <=", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashIn(List<BigDecimal> values) {
            addCriterion("ORDER_CASH in", values, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashNotIn(List<BigDecimal> values) {
            addCriterion("ORDER_CASH not in", values, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDER_CASH between", value1, value2, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDER_CASH not between", value1, value2, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationIsNull() {
            addCriterion("ORDER_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationIsNotNull() {
            addCriterion("ORDER_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationEqualTo(Long value) {
            addCriterion("ORDER_INTEGRATION =", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationNotEqualTo(Long value) {
            addCriterion("ORDER_INTEGRATION <>", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationGreaterThan(Long value) {
            addCriterion("ORDER_INTEGRATION >", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_INTEGRATION >=", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationLessThan(Long value) {
            addCriterion("ORDER_INTEGRATION <", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_INTEGRATION <=", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationIn(List<Long> values) {
            addCriterion("ORDER_INTEGRATION in", values, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationNotIn(List<Long> values) {
            addCriterion("ORDER_INTEGRATION not in", values, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationBetween(Long value1, Long value2) {
            addCriterion("ORDER_INTEGRATION between", value1, value2, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_INTEGRATION not between", value1, value2, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andFreightAmountIsNull() {
            addCriterion("FREIGHT_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFreightAmountIsNotNull() {
            addCriterion("FREIGHT_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFreightAmountEqualTo(BigDecimal value) {
            addCriterion("FREIGHT_AMOUNT =", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotEqualTo(BigDecimal value) {
            addCriterion("FREIGHT_AMOUNT <>", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountGreaterThan(BigDecimal value) {
            addCriterion("FREIGHT_AMOUNT >", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FREIGHT_AMOUNT >=", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountLessThan(BigDecimal value) {
            addCriterion("FREIGHT_AMOUNT <", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FREIGHT_AMOUNT <=", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountIn(List<BigDecimal> values) {
            addCriterion("FREIGHT_AMOUNT in", values, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotIn(List<BigDecimal> values) {
            addCriterion("FREIGHT_AMOUNT not in", values, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FREIGHT_AMOUNT between", value1, value2, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FREIGHT_AMOUNT not between", value1, value2, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIsNull() {
            addCriterion("USER_COUPON_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIsNotNull() {
            addCriterion("USER_COUPON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdEqualTo(Long value) {
            addCriterion("USER_COUPON_ID =", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotEqualTo(Long value) {
            addCriterion("USER_COUPON_ID <>", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdGreaterThan(Long value) {
            addCriterion("USER_COUPON_ID >", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("USER_COUPON_ID >=", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdLessThan(Long value) {
            addCriterion("USER_COUPON_ID <", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("USER_COUPON_ID <=", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIn(List<Long> values) {
            addCriterion("USER_COUPON_ID in", values, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotIn(List<Long> values) {
            addCriterion("USER_COUPON_ID not in", values, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdBetween(Long value1, Long value2) {
            addCriterion("USER_COUPON_ID between", value1, value2, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("USER_COUPON_ID not between", value1, value2, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNull() {
            addCriterion("COUPON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNotNull() {
            addCriterion("COUPON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeEqualTo(Long value) {
            addCriterion("COUPON_TYPE =", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotEqualTo(Long value) {
            addCriterion("COUPON_TYPE <>", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThan(Long value) {
            addCriterion("COUPON_TYPE >", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("COUPON_TYPE >=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThan(Long value) {
            addCriterion("COUPON_TYPE <", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThanOrEqualTo(Long value) {
            addCriterion("COUPON_TYPE <=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIn(List<Long> values) {
            addCriterion("COUPON_TYPE in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotIn(List<Long> values) {
            addCriterion("COUPON_TYPE not in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeBetween(Long value1, Long value2) {
            addCriterion("COUPON_TYPE between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotBetween(Long value1, Long value2) {
            addCriterion("COUPON_TYPE not between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNull() {
            addCriterion("COUPON_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNotNull() {
            addCriterion("COUPON_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountEqualTo(BigDecimal value) {
            addCriterion("COUPON_AMOUNT =", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotEqualTo(BigDecimal value) {
            addCriterion("COUPON_AMOUNT <>", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThan(BigDecimal value) {
            addCriterion("COUPON_AMOUNT >", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COUPON_AMOUNT >=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThan(BigDecimal value) {
            addCriterion("COUPON_AMOUNT <", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COUPON_AMOUNT <=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIn(List<BigDecimal> values) {
            addCriterion("COUPON_AMOUNT in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotIn(List<BigDecimal> values) {
            addCriterion("COUPON_AMOUNT not in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COUPON_AMOUNT between", value1, value2, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COUPON_AMOUNT not between", value1, value2, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("PAY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("PAY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Long value) {
            addCriterion("PAY_TYPE =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Long value) {
            addCriterion("PAY_TYPE <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Long value) {
            addCriterion("PAY_TYPE >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("PAY_TYPE >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Long value) {
            addCriterion("PAY_TYPE <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Long value) {
            addCriterion("PAY_TYPE <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Long> values) {
            addCriterion("PAY_TYPE in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Long> values) {
            addCriterion("PAY_TYPE not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Long value1, Long value2) {
            addCriterion("PAY_TYPE between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Long value1, Long value2) {
            addCriterion("PAY_TYPE not between", value1, value2, "payType");
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

        public Criteria andCloseTypeIsNull() {
            addCriterion("CLOSE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIsNotNull() {
            addCriterion("CLOSE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTypeEqualTo(Long value) {
            addCriterion("CLOSE_TYPE =", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotEqualTo(Long value) {
            addCriterion("CLOSE_TYPE <>", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeGreaterThan(Long value) {
            addCriterion("CLOSE_TYPE >", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("CLOSE_TYPE >=", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeLessThan(Long value) {
            addCriterion("CLOSE_TYPE <", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeLessThanOrEqualTo(Long value) {
            addCriterion("CLOSE_TYPE <=", value, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeIn(List<Long> values) {
            addCriterion("CLOSE_TYPE in", values, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotIn(List<Long> values) {
            addCriterion("CLOSE_TYPE not in", values, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeBetween(Long value1, Long value2) {
            addCriterion("CLOSE_TYPE between", value1, value2, "closeType");
            return (Criteria) this;
        }

        public Criteria andCloseTypeNotBetween(Long value1, Long value2) {
            addCriterion("CLOSE_TYPE not between", value1, value2, "closeType");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIsNull() {
            addCriterion("REFUND_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIsNotNull() {
            addCriterion("REFUND_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRefundStatusEqualTo(Long value) {
            addCriterion("REFUND_STATUS =", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotEqualTo(Long value) {
            addCriterion("REFUND_STATUS <>", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusGreaterThan(Long value) {
            addCriterion("REFUND_STATUS >", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("REFUND_STATUS >=", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusLessThan(Long value) {
            addCriterion("REFUND_STATUS <", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusLessThanOrEqualTo(Long value) {
            addCriterion("REFUND_STATUS <=", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIn(List<Long> values) {
            addCriterion("REFUND_STATUS in", values, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotIn(List<Long> values) {
            addCriterion("REFUND_STATUS not in", values, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusBetween(Long value1, Long value2) {
            addCriterion("REFUND_STATUS between", value1, value2, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotBetween(Long value1, Long value2) {
            addCriterion("REFUND_STATUS not between", value1, value2, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("PRODUCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("PRODUCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(Long value) {
            addCriterion("PRODUCT_TYPE =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(Long value) {
            addCriterion("PRODUCT_TYPE <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(Long value) {
            addCriterion("PRODUCT_TYPE >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_TYPE >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(Long value) {
            addCriterion("PRODUCT_TYPE <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_TYPE <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<Long> values) {
            addCriterion("PRODUCT_TYPE in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<Long> values) {
            addCriterion("PRODUCT_TYPE not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_TYPE between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_TYPE not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameIsNull() {
            addCriterion("DELIVERY_COMPANY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameIsNotNull() {
            addCriterion("DELIVERY_COMPANY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameEqualTo(String value) {
            addCriterion("DELIVERY_COMPANY_NAME =", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotEqualTo(String value) {
            addCriterion("DELIVERY_COMPANY_NAME <>", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameGreaterThan(String value) {
            addCriterion("DELIVERY_COMPANY_NAME >", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("DELIVERY_COMPANY_NAME >=", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameLessThan(String value) {
            addCriterion("DELIVERY_COMPANY_NAME <", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("DELIVERY_COMPANY_NAME <=", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameLike(String value) {
            addCriterion("DELIVERY_COMPANY_NAME like", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotLike(String value) {
            addCriterion("DELIVERY_COMPANY_NAME not like", value, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameIn(List<String> values) {
            addCriterion("DELIVERY_COMPANY_NAME in", values, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotIn(List<String> values) {
            addCriterion("DELIVERY_COMPANY_NAME not in", values, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameBetween(String value1, String value2) {
            addCriterion("DELIVERY_COMPANY_NAME between", value1, value2, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCompanyNameNotBetween(String value1, String value2) {
            addCriterion("DELIVERY_COMPANY_NAME not between", value1, value2, "deliveryCompanyName");
            return (Criteria) this;
        }

        public Criteria andDeliverySnIsNull() {
            addCriterion("DELIVERY_SN is null");
            return (Criteria) this;
        }

        public Criteria andDeliverySnIsNotNull() {
            addCriterion("DELIVERY_SN is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverySnEqualTo(String value) {
            addCriterion("DELIVERY_SN =", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnNotEqualTo(String value) {
            addCriterion("DELIVERY_SN <>", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnGreaterThan(String value) {
            addCriterion("DELIVERY_SN >", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnGreaterThanOrEqualTo(String value) {
            addCriterion("DELIVERY_SN >=", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnLessThan(String value) {
            addCriterion("DELIVERY_SN <", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnLessThanOrEqualTo(String value) {
            addCriterion("DELIVERY_SN <=", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnLike(String value) {
            addCriterion("DELIVERY_SN like", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnNotLike(String value) {
            addCriterion("DELIVERY_SN not like", value, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnIn(List<String> values) {
            addCriterion("DELIVERY_SN in", values, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnNotIn(List<String> values) {
            addCriterion("DELIVERY_SN not in", values, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnBetween(String value1, String value2) {
            addCriterion("DELIVERY_SN between", value1, value2, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andDeliverySnNotBetween(String value1, String value2) {
            addCriterion("DELIVERY_SN not between", value1, value2, "deliverySn");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("RECEIVER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("RECEIVER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("RECEIVER_NAME =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("RECEIVER_NAME <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("RECEIVER_NAME >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_NAME >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("RECEIVER_NAME <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_NAME <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("RECEIVER_NAME like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("RECEIVER_NAME not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("RECEIVER_NAME in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("RECEIVER_NAME not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("RECEIVER_NAME between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_NAME not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIsNull() {
            addCriterion("RECEIVER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIsNotNull() {
            addCriterion("RECEIVER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneEqualTo(String value) {
            addCriterion("RECEIVER_PHONE =", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotEqualTo(String value) {
            addCriterion("RECEIVER_PHONE <>", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneGreaterThan(String value) {
            addCriterion("RECEIVER_PHONE >", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_PHONE >=", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLessThan(String value) {
            addCriterion("RECEIVER_PHONE <", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_PHONE <=", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLike(String value) {
            addCriterion("RECEIVER_PHONE like", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotLike(String value) {
            addCriterion("RECEIVER_PHONE not like", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIn(List<String> values) {
            addCriterion("RECEIVER_PHONE in", values, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotIn(List<String> values) {
            addCriterion("RECEIVER_PHONE not in", values, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneBetween(String value1, String value2) {
            addCriterion("RECEIVER_PHONE between", value1, value2, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_PHONE not between", value1, value2, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIsNull() {
            addCriterion("RECEIVER_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIsNotNull() {
            addCriterion("RECEIVER_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceEqualTo(String value) {
            addCriterion("RECEIVER_PROVINCE =", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotEqualTo(String value) {
            addCriterion("RECEIVER_PROVINCE <>", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceGreaterThan(String value) {
            addCriterion("RECEIVER_PROVINCE >", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_PROVINCE >=", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLessThan(String value) {
            addCriterion("RECEIVER_PROVINCE <", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_PROVINCE <=", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLike(String value) {
            addCriterion("RECEIVER_PROVINCE like", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotLike(String value) {
            addCriterion("RECEIVER_PROVINCE not like", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIn(List<String> values) {
            addCriterion("RECEIVER_PROVINCE in", values, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotIn(List<String> values) {
            addCriterion("RECEIVER_PROVINCE not in", values, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceBetween(String value1, String value2) {
            addCriterion("RECEIVER_PROVINCE between", value1, value2, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_PROVINCE not between", value1, value2, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNull() {
            addCriterion("RECEIVER_CITY is null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNotNull() {
            addCriterion("RECEIVER_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityEqualTo(String value) {
            addCriterion("RECEIVER_CITY =", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotEqualTo(String value) {
            addCriterion("RECEIVER_CITY <>", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThan(String value) {
            addCriterion("RECEIVER_CITY >", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_CITY >=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThan(String value) {
            addCriterion("RECEIVER_CITY <", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_CITY <=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLike(String value) {
            addCriterion("RECEIVER_CITY like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotLike(String value) {
            addCriterion("RECEIVER_CITY not like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIn(List<String> values) {
            addCriterion("RECEIVER_CITY in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotIn(List<String> values) {
            addCriterion("RECEIVER_CITY not in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityBetween(String value1, String value2) {
            addCriterion("RECEIVER_CITY between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_CITY not between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionIsNull() {
            addCriterion("RECEIVER_REGION is null");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionIsNotNull() {
            addCriterion("RECEIVER_REGION is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionEqualTo(String value) {
            addCriterion("RECEIVER_REGION =", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotEqualTo(String value) {
            addCriterion("RECEIVER_REGION <>", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionGreaterThan(String value) {
            addCriterion("RECEIVER_REGION >", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_REGION >=", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionLessThan(String value) {
            addCriterion("RECEIVER_REGION <", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_REGION <=", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionLike(String value) {
            addCriterion("RECEIVER_REGION like", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotLike(String value) {
            addCriterion("RECEIVER_REGION not like", value, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionIn(List<String> values) {
            addCriterion("RECEIVER_REGION in", values, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotIn(List<String> values) {
            addCriterion("RECEIVER_REGION not in", values, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionBetween(String value1, String value2) {
            addCriterion("RECEIVER_REGION between", value1, value2, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverRegionNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_REGION not between", value1, value2, "receiverRegion");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressIsNull() {
            addCriterion("RECEIVER_DETAIL_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressIsNotNull() {
            addCriterion("RECEIVER_DETAIL_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressEqualTo(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS =", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotEqualTo(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS <>", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressGreaterThan(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS >", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS >=", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressLessThan(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS <", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS <=", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressLike(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS like", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotLike(String value) {
            addCriterion("RECEIVER_DETAIL_ADDRESS not like", value, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressIn(List<String> values) {
            addCriterion("RECEIVER_DETAIL_ADDRESS in", values, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotIn(List<String> values) {
            addCriterion("RECEIVER_DETAIL_ADDRESS not in", values, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressBetween(String value1, String value2) {
            addCriterion("RECEIVER_DETAIL_ADDRESS between", value1, value2, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverDetailAddressNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_DETAIL_ADDRESS not between", value1, value2, "receiverDetailAddress");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("NOTE is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNull() {
            addCriterion("CONFIRM_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIsNotNull() {
            addCriterion("CONFIRM_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusEqualTo(Long value) {
            addCriterion("CONFIRM_STATUS =", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotEqualTo(Long value) {
            addCriterion("CONFIRM_STATUS <>", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThan(Long value) {
            addCriterion("CONFIRM_STATUS >", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("CONFIRM_STATUS >=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThan(Long value) {
            addCriterion("CONFIRM_STATUS <", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusLessThanOrEqualTo(Long value) {
            addCriterion("CONFIRM_STATUS <=", value, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusIn(List<Long> values) {
            addCriterion("CONFIRM_STATUS in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotIn(List<Long> values) {
            addCriterion("CONFIRM_STATUS not in", values, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusBetween(Long value1, Long value2) {
            addCriterion("CONFIRM_STATUS between", value1, value2, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andConfirmStatusNotBetween(Long value1, Long value2) {
            addCriterion("CONFIRM_STATUS not between", value1, value2, "confirmStatus");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagIsNull() {
            addCriterion("INTEGRATION_PAY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagIsNotNull() {
            addCriterion("INTEGRATION_PAY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagEqualTo(Long value) {
            addCriterion("INTEGRATION_PAY_FLAG =", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagNotEqualTo(Long value) {
            addCriterion("INTEGRATION_PAY_FLAG <>", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagGreaterThan(Long value) {
            addCriterion("INTEGRATION_PAY_FLAG >", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("INTEGRATION_PAY_FLAG >=", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagLessThan(Long value) {
            addCriterion("INTEGRATION_PAY_FLAG <", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagLessThanOrEqualTo(Long value) {
            addCriterion("INTEGRATION_PAY_FLAG <=", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagIn(List<Long> values) {
            addCriterion("INTEGRATION_PAY_FLAG in", values, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagNotIn(List<Long> values) {
            addCriterion("INTEGRATION_PAY_FLAG not in", values, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagBetween(Long value1, Long value2) {
            addCriterion("INTEGRATION_PAY_FLAG between", value1, value2, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagNotBetween(Long value1, Long value2) {
            addCriterion("INTEGRATION_PAY_FLAG not between", value1, value2, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIsNull() {
            addCriterion("PAYMENT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIsNotNull() {
            addCriterion("PAYMENT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeEqualTo(Date value) {
            addCriterionForJDBCDate("PAYMENT_TIME =", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("PAYMENT_TIME <>", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("PAYMENT_TIME >", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PAYMENT_TIME >=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThan(Date value) {
            addCriterionForJDBCDate("PAYMENT_TIME <", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PAYMENT_TIME <=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIn(List<Date> values) {
            addCriterionForJDBCDate("PAYMENT_TIME in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("PAYMENT_TIME not in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PAYMENT_TIME between", value1, value2, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PAYMENT_TIME not between", value1, value2, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("DELIVERY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("DELIVERY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterionForJDBCDate("DELIVERY_TIME =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("DELIVERY_TIME <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("DELIVERY_TIME >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DELIVERY_TIME >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterionForJDBCDate("DELIVERY_TIME <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DELIVERY_TIME <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterionForJDBCDate("DELIVERY_TIME in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("DELIVERY_TIME not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DELIVERY_TIME between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DELIVERY_TIME not between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("RECEIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("RECEIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterionForJDBCDate("RECEIVE_TIME =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("RECEIVE_TIME <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("RECEIVE_TIME >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("RECEIVE_TIME >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterionForJDBCDate("RECEIVE_TIME <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("RECEIVE_TIME <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterionForJDBCDate("RECEIVE_TIME in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("RECEIVE_TIME not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("RECEIVE_TIME between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("RECEIVE_TIME not between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileIsNull() {
            addCriterion("RECHARGE_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileIsNotNull() {
            addCriterion("RECHARGE_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileEqualTo(Long value) {
            addCriterion("RECHARGE_MOBILE =", value, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileNotEqualTo(Long value) {
            addCriterion("RECHARGE_MOBILE <>", value, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileGreaterThan(Long value) {
            addCriterion("RECHARGE_MOBILE >", value, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileGreaterThanOrEqualTo(Long value) {
            addCriterion("RECHARGE_MOBILE >=", value, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileLessThan(Long value) {
            addCriterion("RECHARGE_MOBILE <", value, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileLessThanOrEqualTo(Long value) {
            addCriterion("RECHARGE_MOBILE <=", value, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileIn(List<Long> values) {
            addCriterion("RECHARGE_MOBILE in", values, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileNotIn(List<Long> values) {
            addCriterion("RECHARGE_MOBILE not in", values, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileBetween(Long value1, Long value2) {
            addCriterion("RECHARGE_MOBILE between", value1, value2, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andRechargeMobileNotBetween(Long value1, Long value2) {
            addCriterion("RECHARGE_MOBILE not between", value1, value2, "rechargeMobile");
            return (Criteria) this;
        }

        public Criteria andMobileAddressIsNull() {
            addCriterion("MOBILE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andMobileAddressIsNotNull() {
            addCriterion("MOBILE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andMobileAddressEqualTo(String value) {
            addCriterion("MOBILE_ADDRESS =", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressNotEqualTo(String value) {
            addCriterion("MOBILE_ADDRESS <>", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressGreaterThan(String value) {
            addCriterion("MOBILE_ADDRESS >", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_ADDRESS >=", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressLessThan(String value) {
            addCriterion("MOBILE_ADDRESS <", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_ADDRESS <=", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressLike(String value) {
            addCriterion("MOBILE_ADDRESS like", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressNotLike(String value) {
            addCriterion("MOBILE_ADDRESS not like", value, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressIn(List<String> values) {
            addCriterion("MOBILE_ADDRESS in", values, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressNotIn(List<String> values) {
            addCriterion("MOBILE_ADDRESS not in", values, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressBetween(String value1, String value2) {
            addCriterion("MOBILE_ADDRESS between", value1, value2, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andMobileAddressNotBetween(String value1, String value2) {
            addCriterion("MOBILE_ADDRESS not between", value1, value2, "mobileAddress");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusIsNull() {
            addCriterion("NOTICE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusIsNotNull() {
            addCriterion("NOTICE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusEqualTo(Long value) {
            addCriterion("NOTICE_STATUS =", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusNotEqualTo(Long value) {
            addCriterion("NOTICE_STATUS <>", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusGreaterThan(Long value) {
            addCriterion("NOTICE_STATUS >", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusGreaterThanOrEqualTo(Long value) {
            addCriterion("NOTICE_STATUS >=", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusLessThan(Long value) {
            addCriterion("NOTICE_STATUS <", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusLessThanOrEqualTo(Long value) {
            addCriterion("NOTICE_STATUS <=", value, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusIn(List<Long> values) {
            addCriterion("NOTICE_STATUS in", values, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusNotIn(List<Long> values) {
            addCriterion("NOTICE_STATUS not in", values, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusBetween(Long value1, Long value2) {
            addCriterion("NOTICE_STATUS between", value1, value2, "noticeStatus");
            return (Criteria) this;
        }

        public Criteria andNoticeStatusNotBetween(Long value1, Long value2) {
            addCriterion("NOTICE_STATUS not between", value1, value2, "noticeStatus");
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

        public Criteria andReqUrlIsNull() {
            addCriterion("REQ_URL is null");
            return (Criteria) this;
        }

        public Criteria andReqUrlIsNotNull() {
            addCriterion("REQ_URL is not null");
            return (Criteria) this;
        }

        public Criteria andReqUrlEqualTo(String value) {
            addCriterion("REQ_URL =", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlNotEqualTo(String value) {
            addCriterion("REQ_URL <>", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlGreaterThan(String value) {
            addCriterion("REQ_URL >", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_URL >=", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlLessThan(String value) {
            addCriterion("REQ_URL <", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlLessThanOrEqualTo(String value) {
            addCriterion("REQ_URL <=", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlLike(String value) {
            addCriterion("REQ_URL like", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlNotLike(String value) {
            addCriterion("REQ_URL not like", value, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlIn(List<String> values) {
            addCriterion("REQ_URL in", values, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlNotIn(List<String> values) {
            addCriterion("REQ_URL not in", values, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlBetween(String value1, String value2) {
            addCriterion("REQ_URL between", value1, value2, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andReqUrlNotBetween(String value1, String value2) {
            addCriterion("REQ_URL not between", value1, value2, "reqUrl");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdIsNull() {
            addCriterion("ACCESS_SIGN_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdIsNotNull() {
            addCriterion("ACCESS_SIGN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdEqualTo(String value) {
            addCriterion("ACCESS_SIGN_ID =", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdNotEqualTo(String value) {
            addCriterion("ACCESS_SIGN_ID <>", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdGreaterThan(String value) {
            addCriterion("ACCESS_SIGN_ID >", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_SIGN_ID >=", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdLessThan(String value) {
            addCriterion("ACCESS_SIGN_ID <", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_SIGN_ID <=", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdLike(String value) {
            addCriterion("ACCESS_SIGN_ID like", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdNotLike(String value) {
            addCriterion("ACCESS_SIGN_ID not like", value, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdIn(List<String> values) {
            addCriterion("ACCESS_SIGN_ID in", values, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdNotIn(List<String> values) {
            addCriterion("ACCESS_SIGN_ID not in", values, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdBetween(String value1, String value2) {
            addCriterion("ACCESS_SIGN_ID between", value1, value2, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andAccessSignIdNotBetween(String value1, String value2) {
            addCriterion("ACCESS_SIGN_ID not between", value1, value2, "accessSignId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdIsNull() {
            addCriterion("EXCHANGE_COUPON_ID is null");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdIsNotNull() {
            addCriterion("EXCHANGE_COUPON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdEqualTo(Long value) {
            addCriterion("EXCHANGE_COUPON_ID =", value, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdNotEqualTo(Long value) {
            addCriterion("EXCHANGE_COUPON_ID <>", value, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdGreaterThan(Long value) {
            addCriterion("EXCHANGE_COUPON_ID >", value, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EXCHANGE_COUPON_ID >=", value, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdLessThan(Long value) {
            addCriterion("EXCHANGE_COUPON_ID <", value, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("EXCHANGE_COUPON_ID <=", value, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdIn(List<Long> values) {
            addCriterion("EXCHANGE_COUPON_ID in", values, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdNotIn(List<Long> values) {
            addCriterion("EXCHANGE_COUPON_ID not in", values, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdBetween(Long value1, Long value2) {
            addCriterion("EXCHANGE_COUPON_ID between", value1, value2, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andExchangeCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("EXCHANGE_COUPON_ID not between", value1, value2, "exchangeCouponId");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkIsNull() {
            addCriterion("RECHARGE_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkIsNotNull() {
            addCriterion("RECHARGE_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkEqualTo(String value) {
            addCriterion("RECHARGE_REMARK =", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkNotEqualTo(String value) {
            addCriterion("RECHARGE_REMARK <>", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkGreaterThan(String value) {
            addCriterion("RECHARGE_REMARK >", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("RECHARGE_REMARK >=", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkLessThan(String value) {
            addCriterion("RECHARGE_REMARK <", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkLessThanOrEqualTo(String value) {
            addCriterion("RECHARGE_REMARK <=", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkLike(String value) {
            addCriterion("RECHARGE_REMARK like", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkNotLike(String value) {
            addCriterion("RECHARGE_REMARK not like", value, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkIn(List<String> values) {
            addCriterion("RECHARGE_REMARK in", values, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkNotIn(List<String> values) {
            addCriterion("RECHARGE_REMARK not in", values, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkBetween(String value1, String value2) {
            addCriterion("RECHARGE_REMARK between", value1, value2, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeRemarkNotBetween(String value1, String value2) {
            addCriterion("RECHARGE_REMARK not between", value1, value2, "rechargeRemark");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqIsNull() {
            addCriterion("RECHARGE_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqIsNotNull() {
            addCriterion("RECHARGE_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqEqualTo(String value) {
            addCriterion("RECHARGE_SEQ =", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqNotEqualTo(String value) {
            addCriterion("RECHARGE_SEQ <>", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqGreaterThan(String value) {
            addCriterion("RECHARGE_SEQ >", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqGreaterThanOrEqualTo(String value) {
            addCriterion("RECHARGE_SEQ >=", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqLessThan(String value) {
            addCriterion("RECHARGE_SEQ <", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqLessThanOrEqualTo(String value) {
            addCriterion("RECHARGE_SEQ <=", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqLike(String value) {
            addCriterion("RECHARGE_SEQ like", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqNotLike(String value) {
            addCriterion("RECHARGE_SEQ not like", value, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqIn(List<String> values) {
            addCriterion("RECHARGE_SEQ in", values, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqNotIn(List<String> values) {
            addCriterion("RECHARGE_SEQ not in", values, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqBetween(String value1, String value2) {
            addCriterion("RECHARGE_SEQ between", value1, value2, "rechargeSeq");
            return (Criteria) this;
        }

        public Criteria andRechargeSeqNotBetween(String value1, String value2) {
            addCriterion("RECHARGE_SEQ not between", value1, value2, "rechargeSeq");
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