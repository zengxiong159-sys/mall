package com.qdbank.mall.model.orderrefundreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderRefundreportDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderRefundreportDOExample() {
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
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
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

        public Criteria andRefundSerialIsNull() {
            addCriterion("REFUND_SERIAL is null");
            return (Criteria) this;
        }

        public Criteria andRefundSerialIsNotNull() {
            addCriterion("REFUND_SERIAL is not null");
            return (Criteria) this;
        }

        public Criteria andRefundSerialEqualTo(Long value) {
            addCriterion("REFUND_SERIAL =", value, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialNotEqualTo(Long value) {
            addCriterion("REFUND_SERIAL <>", value, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialGreaterThan(Long value) {
            addCriterion("REFUND_SERIAL >", value, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialGreaterThanOrEqualTo(Long value) {
            addCriterion("REFUND_SERIAL >=", value, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialLessThan(Long value) {
            addCriterion("REFUND_SERIAL <", value, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialLessThanOrEqualTo(Long value) {
            addCriterion("REFUND_SERIAL <=", value, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialIn(List<Long> values) {
            addCriterion("REFUND_SERIAL in", values, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialNotIn(List<Long> values) {
            addCriterion("REFUND_SERIAL not in", values, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialBetween(Long value1, Long value2) {
            addCriterion("REFUND_SERIAL between", value1, value2, "refundSerial");
            return (Criteria) this;
        }

        public Criteria andRefundSerialNotBetween(Long value1, Long value2) {
            addCriterion("REFUND_SERIAL not between", value1, value2, "refundSerial");
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

        public Criteria andCustNoEqualTo(String value) {
            addCriterion("CUST_NO =", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotEqualTo(String value) {
            addCriterion("CUST_NO <>", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoGreaterThan(String value) {
            addCriterion("CUST_NO >", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_NO >=", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLessThan(String value) {
            addCriterion("CUST_NO <", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLessThanOrEqualTo(String value) {
            addCriterion("CUST_NO <=", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoLike(String value) {
            addCriterion("CUST_NO like", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotLike(String value) {
            addCriterion("CUST_NO not like", value, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoIn(List<String> values) {
            addCriterion("CUST_NO in", values, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotIn(List<String> values) {
            addCriterion("CUST_NO not in", values, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoBetween(String value1, String value2) {
            addCriterion("CUST_NO between", value1, value2, "custNo");
            return (Criteria) this;
        }

        public Criteria andCustNoNotBetween(String value1, String value2) {
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

        public Criteria andOrderSnIsNull() {
            addCriterion("ORDER_SN is null");
            return (Criteria) this;
        }

        public Criteria andOrderSnIsNotNull() {
            addCriterion("ORDER_SN is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSnEqualTo(Long value) {
            addCriterion("ORDER_SN =", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotEqualTo(Long value) {
            addCriterion("ORDER_SN <>", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThan(Long value) {
            addCriterion("ORDER_SN >", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_SN >=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThan(Long value) {
            addCriterion("ORDER_SN <", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_SN <=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnIn(List<Long> values) {
            addCriterion("ORDER_SN in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotIn(List<Long> values) {
            addCriterion("ORDER_SN not in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnBetween(Long value1, Long value2) {
            addCriterion("ORDER_SN between", value1, value2, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotBetween(Long value1, Long value2) {
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

        public Criteria andRefundAmountIsNull() {
            addCriterion("REFUND_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIsNotNull() {
            addCriterion("REFUND_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAmountEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT =", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <>", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThan(BigDecimal value) {
            addCriterion("REFUND_AMOUNT >", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT >=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThan(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_AMOUNT <=", value, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMOUNT in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotIn(List<BigDecimal> values) {
            addCriterion("REFUND_AMOUNT not in", values, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMOUNT between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_AMOUNT not between", value1, value2, "refundAmount");
            return (Criteria) this;
        }

        public Criteria andRefundCashIsNull() {
            addCriterion("REFUND_CASH is null");
            return (Criteria) this;
        }

        public Criteria andRefundCashIsNotNull() {
            addCriterion("REFUND_CASH is not null");
            return (Criteria) this;
        }

        public Criteria andRefundCashEqualTo(BigDecimal value) {
            addCriterion("REFUND_CASH =", value, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashNotEqualTo(BigDecimal value) {
            addCriterion("REFUND_CASH <>", value, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashGreaterThan(BigDecimal value) {
            addCriterion("REFUND_CASH >", value, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_CASH >=", value, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashLessThan(BigDecimal value) {
            addCriterion("REFUND_CASH <", value, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REFUND_CASH <=", value, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashIn(List<BigDecimal> values) {
            addCriterion("REFUND_CASH in", values, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashNotIn(List<BigDecimal> values) {
            addCriterion("REFUND_CASH not in", values, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_CASH between", value1, value2, "refundCash");
            return (Criteria) this;
        }

        public Criteria andRefundCashNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REFUND_CASH not between", value1, value2, "refundCash");
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

        public Criteria andRefundIntegrationIsNull() {
            addCriterion("REFUND_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationIsNotNull() {
            addCriterion("REFUND_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationEqualTo(Long value) {
            addCriterion("REFUND_INTEGRATION =", value, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationNotEqualTo(Long value) {
            addCriterion("REFUND_INTEGRATION <>", value, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationGreaterThan(Long value) {
            addCriterion("REFUND_INTEGRATION >", value, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationGreaterThanOrEqualTo(Long value) {
            addCriterion("REFUND_INTEGRATION >=", value, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationLessThan(Long value) {
            addCriterion("REFUND_INTEGRATION <", value, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationLessThanOrEqualTo(Long value) {
            addCriterion("REFUND_INTEGRATION <=", value, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationIn(List<Long> values) {
            addCriterion("REFUND_INTEGRATION in", values, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationNotIn(List<Long> values) {
            addCriterion("REFUND_INTEGRATION not in", values, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationBetween(Long value1, Long value2) {
            addCriterion("REFUND_INTEGRATION between", value1, value2, "refundIntegration");
            return (Criteria) this;
        }

        public Criteria andRefundIntegrationNotBetween(Long value1, Long value2) {
            addCriterion("REFUND_INTEGRATION not between", value1, value2, "refundIntegration");
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

        public Criteria andHandleStartTimeIsNull() {
            addCriterion("HANDLE_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeIsNotNull() {
            addCriterion("HANDLE_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_START_TIME =", value, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_START_TIME <>", value, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("HANDLE_START_TIME >", value, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_START_TIME >=", value, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("HANDLE_START_TIME <", value, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_START_TIME <=", value, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("HANDLE_START_TIME in", values, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("HANDLE_START_TIME not in", values, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HANDLE_START_TIME between", value1, value2, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HANDLE_START_TIME not between", value1, value2, "handleStartTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeIsNull() {
            addCriterion("HANDLE_FINISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeIsNotNull() {
            addCriterion("HANDLE_FINISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME =", value, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME <>", value, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME >", value, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME >=", value, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeLessThan(Date value) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME <", value, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME <=", value, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeIn(List<Date> values) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME in", values, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME not in", values, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME between", value1, value2, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleFinishTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("HANDLE_FINISH_TIME not between", value1, value2, "handleFinishTime");
            return (Criteria) this;
        }

        public Criteria andHandleResultIsNull() {
            addCriterion("HANDLE_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andHandleResultIsNotNull() {
            addCriterion("HANDLE_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andHandleResultEqualTo(Long value) {
            addCriterion("HANDLE_RESULT =", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultNotEqualTo(Long value) {
            addCriterion("HANDLE_RESULT <>", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultGreaterThan(Long value) {
            addCriterion("HANDLE_RESULT >", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultGreaterThanOrEqualTo(Long value) {
            addCriterion("HANDLE_RESULT >=", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultLessThan(Long value) {
            addCriterion("HANDLE_RESULT <", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultLessThanOrEqualTo(Long value) {
            addCriterion("HANDLE_RESULT <=", value, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultIn(List<Long> values) {
            addCriterion("HANDLE_RESULT in", values, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultNotIn(List<Long> values) {
            addCriterion("HANDLE_RESULT not in", values, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultBetween(Long value1, Long value2) {
            addCriterion("HANDLE_RESULT between", value1, value2, "handleResult");
            return (Criteria) this;
        }

        public Criteria andHandleResultNotBetween(Long value1, Long value2) {
            addCriterion("HANDLE_RESULT not between", value1, value2, "handleResult");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("REASON is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("REASON is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("REASON =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("REASON <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("REASON >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("REASON >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("REASON <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("REASON <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("REASON like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("REASON not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("REASON in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("REASON not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("REASON between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("REASON not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNull() {
            addCriterion("REFUND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIsNotNull() {
            addCriterion("REFUND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTypeEqualTo(Long value) {
            addCriterion("REFUND_TYPE =", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotEqualTo(Long value) {
            addCriterion("REFUND_TYPE <>", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThan(Long value) {
            addCriterion("REFUND_TYPE >", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("REFUND_TYPE >=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThan(Long value) {
            addCriterion("REFUND_TYPE <", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeLessThanOrEqualTo(Long value) {
            addCriterion("REFUND_TYPE <=", value, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeIn(List<Long> values) {
            addCriterion("REFUND_TYPE in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotIn(List<Long> values) {
            addCriterion("REFUND_TYPE not in", values, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeBetween(Long value1, Long value2) {
            addCriterion("REFUND_TYPE between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andRefundTypeNotBetween(Long value1, Long value2) {
            addCriterion("REFUND_TYPE not between", value1, value2, "refundType");
            return (Criteria) this;
        }

        public Criteria andProofPicsIsNull() {
            addCriterion("PROOF_PICS is null");
            return (Criteria) this;
        }

        public Criteria andProofPicsIsNotNull() {
            addCriterion("PROOF_PICS is not null");
            return (Criteria) this;
        }

        public Criteria andProofPicsEqualTo(String value) {
            addCriterion("PROOF_PICS =", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsNotEqualTo(String value) {
            addCriterion("PROOF_PICS <>", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsGreaterThan(String value) {
            addCriterion("PROOF_PICS >", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsGreaterThanOrEqualTo(String value) {
            addCriterion("PROOF_PICS >=", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsLessThan(String value) {
            addCriterion("PROOF_PICS <", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsLessThanOrEqualTo(String value) {
            addCriterion("PROOF_PICS <=", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsLike(String value) {
            addCriterion("PROOF_PICS like", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsNotLike(String value) {
            addCriterion("PROOF_PICS not like", value, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsIn(List<String> values) {
            addCriterion("PROOF_PICS in", values, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsNotIn(List<String> values) {
            addCriterion("PROOF_PICS not in", values, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsBetween(String value1, String value2) {
            addCriterion("PROOF_PICS between", value1, value2, "proofPics");
            return (Criteria) this;
        }

        public Criteria andProofPicsNotBetween(String value1, String value2) {
            addCriterion("PROOF_PICS not between", value1, value2, "proofPics");
            return (Criteria) this;
        }

        public Criteria andAdminNameIsNull() {
            addCriterion("ADMIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdminNameIsNotNull() {
            addCriterion("ADMIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminNameEqualTo(String value) {
            addCriterion("ADMIN_NAME =", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotEqualTo(String value) {
            addCriterion("ADMIN_NAME <>", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameGreaterThan(String value) {
            addCriterion("ADMIN_NAME >", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_NAME >=", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLessThan(String value) {
            addCriterion("ADMIN_NAME <", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_NAME <=", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameLike(String value) {
            addCriterion("ADMIN_NAME like", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotLike(String value) {
            addCriterion("ADMIN_NAME not like", value, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameIn(List<String> values) {
            addCriterion("ADMIN_NAME in", values, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotIn(List<String> values) {
            addCriterion("ADMIN_NAME not in", values, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameBetween(String value1, String value2) {
            addCriterion("ADMIN_NAME between", value1, value2, "adminName");
            return (Criteria) this;
        }

        public Criteria andAdminNameNotBetween(String value1, String value2) {
            addCriterion("ADMIN_NAME not between", value1, value2, "adminName");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andRefundNoteIsNull() {
            addCriterion("REFUND_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andRefundNoteIsNotNull() {
            addCriterion("REFUND_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andRefundNoteEqualTo(String value) {
            addCriterion("REFUND_NOTE =", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteNotEqualTo(String value) {
            addCriterion("REFUND_NOTE <>", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteGreaterThan(String value) {
            addCriterion("REFUND_NOTE >", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteGreaterThanOrEqualTo(String value) {
            addCriterion("REFUND_NOTE >=", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteLessThan(String value) {
            addCriterion("REFUND_NOTE <", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteLessThanOrEqualTo(String value) {
            addCriterion("REFUND_NOTE <=", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteLike(String value) {
            addCriterion("REFUND_NOTE like", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteNotLike(String value) {
            addCriterion("REFUND_NOTE not like", value, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteIn(List<String> values) {
            addCriterion("REFUND_NOTE in", values, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteNotIn(List<String> values) {
            addCriterion("REFUND_NOTE not in", values, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteBetween(String value1, String value2) {
            addCriterion("REFUND_NOTE between", value1, value2, "refundNote");
            return (Criteria) this;
        }

        public Criteria andRefundNoteNotBetween(String value1, String value2) {
            addCriterion("REFUND_NOTE not between", value1, value2, "refundNote");
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