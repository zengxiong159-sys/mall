package com.qdbank.mall.model.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeTotalDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeTotalDOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andMerchantNoEqualTo(String value) {
            addCriterion("MERCHANT_NO =", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotEqualTo(String value) {
            addCriterion("MERCHANT_NO <>", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoGreaterThan(String value) {
            addCriterion("MERCHANT_NO >", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_NO >=", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLessThan(String value) {
            addCriterion("MERCHANT_NO <", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_NO <=", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoLike(String value) {
            addCriterion("MERCHANT_NO like", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotLike(String value) {
            addCriterion("MERCHANT_NO not like", value, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoIn(List<String> values) {
            addCriterion("MERCHANT_NO in", values, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotIn(List<String> values) {
            addCriterion("MERCHANT_NO not in", values, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoBetween(String value1, String value2) {
            addCriterion("MERCHANT_NO between", value1, value2, "merchantNo");
            return (Criteria) this;
        }

        public Criteria andMerchantNoNotBetween(String value1, String value2) {
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

        public Criteria andOrderTypeIsNull() {
            addCriterion("ORDER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("ORDER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("ORDER_TYPE =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("ORDER_TYPE <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("ORDER_TYPE >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_TYPE >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("ORDER_TYPE <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("ORDER_TYPE <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("ORDER_TYPE like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("ORDER_TYPE not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("ORDER_TYPE in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("ORDER_TYPE not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("ORDER_TYPE between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("ORDER_TYPE not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNull() {
            addCriterion("ORDER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNotNull() {
            addCriterion("ORDER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCountEqualTo(String value) {
            addCriterion("ORDER_COUNT =", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotEqualTo(String value) {
            addCriterion("ORDER_COUNT <>", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThan(String value) {
            addCriterion("ORDER_COUNT >", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_COUNT >=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThan(String value) {
            addCriterion("ORDER_COUNT <", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThanOrEqualTo(String value) {
            addCriterion("ORDER_COUNT <=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLike(String value) {
            addCriterion("ORDER_COUNT like", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotLike(String value) {
            addCriterion("ORDER_COUNT not like", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountIn(List<String> values) {
            addCriterion("ORDER_COUNT in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotIn(List<String> values) {
            addCriterion("ORDER_COUNT not in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountBetween(String value1, String value2) {
            addCriterion("ORDER_COUNT between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotBetween(String value1, String value2) {
            addCriterion("ORDER_COUNT not between", value1, value2, "orderCount");
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

        public Criteria andProductPriceEqualTo(String value) {
            addCriterion("PRODUCT_PRICE =", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotEqualTo(String value) {
            addCriterion("PRODUCT_PRICE <>", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThan(String value) {
            addCriterion("PRODUCT_PRICE >", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_PRICE >=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThan(String value) {
            addCriterion("PRODUCT_PRICE <", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_PRICE <=", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceLike(String value) {
            addCriterion("PRODUCT_PRICE like", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotLike(String value) {
            addCriterion("PRODUCT_PRICE not like", value, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceIn(List<String> values) {
            addCriterion("PRODUCT_PRICE in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotIn(List<String> values) {
            addCriterion("PRODUCT_PRICE not in", values, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceBetween(String value1, String value2) {
            addCriterion("PRODUCT_PRICE between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andProductPriceNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_PRICE not between", value1, value2, "productPrice");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountIsNull() {
            addCriterion("INTEGRATION_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountIsNotNull() {
            addCriterion("INTEGRATION_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountEqualTo(String value) {
            addCriterion("INTEGRATION_COUNT =", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountNotEqualTo(String value) {
            addCriterion("INTEGRATION_COUNT <>", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountGreaterThan(String value) {
            addCriterion("INTEGRATION_COUNT >", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountGreaterThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_COUNT >=", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountLessThan(String value) {
            addCriterion("INTEGRATION_COUNT <", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountLessThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_COUNT <=", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountLike(String value) {
            addCriterion("INTEGRATION_COUNT like", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountNotLike(String value) {
            addCriterion("INTEGRATION_COUNT not like", value, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountIn(List<String> values) {
            addCriterion("INTEGRATION_COUNT in", values, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountNotIn(List<String> values) {
            addCriterion("INTEGRATION_COUNT not in", values, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountBetween(String value1, String value2) {
            addCriterion("INTEGRATION_COUNT between", value1, value2, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCountNotBetween(String value1, String value2) {
            addCriterion("INTEGRATION_COUNT not between", value1, value2, "integrationCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountIsNull() {
            addCriterion("COUPON_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCouponCountIsNotNull() {
            addCriterion("COUPON_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCountEqualTo(String value) {
            addCriterion("COUPON_COUNT =", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountNotEqualTo(String value) {
            addCriterion("COUPON_COUNT <>", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountGreaterThan(String value) {
            addCriterion("COUPON_COUNT >", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_COUNT >=", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountLessThan(String value) {
            addCriterion("COUPON_COUNT <", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountLessThanOrEqualTo(String value) {
            addCriterion("COUPON_COUNT <=", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountLike(String value) {
            addCriterion("COUPON_COUNT like", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountNotLike(String value) {
            addCriterion("COUPON_COUNT not like", value, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountIn(List<String> values) {
            addCriterion("COUPON_COUNT in", values, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountNotIn(List<String> values) {
            addCriterion("COUPON_COUNT not in", values, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountBetween(String value1, String value2) {
            addCriterion("COUPON_COUNT between", value1, value2, "couponCount");
            return (Criteria) this;
        }

        public Criteria andCouponCountNotBetween(String value1, String value2) {
            addCriterion("COUPON_COUNT not between", value1, value2, "couponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountIsNull() {
            addCriterion("PRODUCT_COUPON_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountIsNotNull() {
            addCriterion("PRODUCT_COUPON_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_COUNT =", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountNotEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_COUNT <>", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountGreaterThan(String value) {
            addCriterion("PRODUCT_COUPON_COUNT >", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_COUNT >=", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountLessThan(String value) {
            addCriterion("PRODUCT_COUPON_COUNT <", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_COUNT <=", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountLike(String value) {
            addCriterion("PRODUCT_COUPON_COUNT like", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountNotLike(String value) {
            addCriterion("PRODUCT_COUPON_COUNT not like", value, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountIn(List<String> values) {
            addCriterion("PRODUCT_COUPON_COUNT in", values, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountNotIn(List<String> values) {
            addCriterion("PRODUCT_COUPON_COUNT not in", values, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountBetween(String value1, String value2) {
            addCriterion("PRODUCT_COUPON_COUNT between", value1, value2, "productCouponCount");
            return (Criteria) this;
        }

        public Criteria andProductCouponCountNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_COUPON_COUNT not between", value1, value2, "productCouponCount");
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

        public Criteria andOrderCashEqualTo(String value) {
            addCriterion("ORDER_CASH =", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashNotEqualTo(String value) {
            addCriterion("ORDER_CASH <>", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashGreaterThan(String value) {
            addCriterion("ORDER_CASH >", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_CASH >=", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashLessThan(String value) {
            addCriterion("ORDER_CASH <", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashLessThanOrEqualTo(String value) {
            addCriterion("ORDER_CASH <=", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashLike(String value) {
            addCriterion("ORDER_CASH like", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashNotLike(String value) {
            addCriterion("ORDER_CASH not like", value, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashIn(List<String> values) {
            addCriterion("ORDER_CASH in", values, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashNotIn(List<String> values) {
            addCriterion("ORDER_CASH not in", values, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashBetween(String value1, String value2) {
            addCriterion("ORDER_CASH between", value1, value2, "orderCash");
            return (Criteria) this;
        }

        public Criteria andOrderCashNotBetween(String value1, String value2) {
            addCriterion("ORDER_CASH not between", value1, value2, "orderCash");
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

        public Criteria andFreightAmountEqualTo(String value) {
            addCriterion("FREIGHT_AMOUNT =", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotEqualTo(String value) {
            addCriterion("FREIGHT_AMOUNT <>", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountGreaterThan(String value) {
            addCriterion("FREIGHT_AMOUNT >", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountGreaterThanOrEqualTo(String value) {
            addCriterion("FREIGHT_AMOUNT >=", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountLessThan(String value) {
            addCriterion("FREIGHT_AMOUNT <", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountLessThanOrEqualTo(String value) {
            addCriterion("FREIGHT_AMOUNT <=", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountLike(String value) {
            addCriterion("FREIGHT_AMOUNT like", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotLike(String value) {
            addCriterion("FREIGHT_AMOUNT not like", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountIn(List<String> values) {
            addCriterion("FREIGHT_AMOUNT in", values, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotIn(List<String> values) {
            addCriterion("FREIGHT_AMOUNT not in", values, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountBetween(String value1, String value2) {
            addCriterion("FREIGHT_AMOUNT between", value1, value2, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotBetween(String value1, String value2) {
            addCriterion("FREIGHT_AMOUNT not between", value1, value2, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountIsNull() {
            addCriterion("CASH_FREIGHT_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountIsNotNull() {
            addCriterion("CASH_FREIGHT_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountEqualTo(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT =", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountNotEqualTo(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT <>", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountGreaterThan(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT >", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountGreaterThanOrEqualTo(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT >=", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountLessThan(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT <", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountLessThanOrEqualTo(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT <=", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountLike(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT like", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountNotLike(String value) {
            addCriterion("CASH_FREIGHT_AMOUNT not like", value, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountIn(List<String> values) {
            addCriterion("CASH_FREIGHT_AMOUNT in", values, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountNotIn(List<String> values) {
            addCriterion("CASH_FREIGHT_AMOUNT not in", values, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountBetween(String value1, String value2) {
            addCriterion("CASH_FREIGHT_AMOUNT between", value1, value2, "cashFreightAmount");
            return (Criteria) this;
        }

        public Criteria andCashFreightAmountNotBetween(String value1, String value2) {
            addCriterion("CASH_FREIGHT_AMOUNT not between", value1, value2, "cashFreightAmount");
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

        public Criteria andPaymentTimeEqualTo(String value) {
            addCriterion("PAYMENT_TIME =", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotEqualTo(String value) {
            addCriterion("PAYMENT_TIME <>", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThan(String value) {
            addCriterion("PAYMENT_TIME >", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PAYMENT_TIME >=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThan(String value) {
            addCriterion("PAYMENT_TIME <", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThanOrEqualTo(String value) {
            addCriterion("PAYMENT_TIME <=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLike(String value) {
            addCriterion("PAYMENT_TIME like", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotLike(String value) {
            addCriterion("PAYMENT_TIME not like", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIn(List<String> values) {
            addCriterion("PAYMENT_TIME in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotIn(List<String> values) {
            addCriterion("PAYMENT_TIME not in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeBetween(String value1, String value2) {
            addCriterion("PAYMENT_TIME between", value1, value2, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotBetween(String value1, String value2) {
            addCriterion("PAYMENT_TIME not between", value1, value2, "paymentTime");
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

        public Criteria andCouponTypeIsNull() {
            addCriterion("COUPON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNotNull() {
            addCriterion("COUPON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeEqualTo(String value) {
            addCriterion("COUPON_TYPE =", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotEqualTo(String value) {
            addCriterion("COUPON_TYPE <>", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThan(String value) {
            addCriterion("COUPON_TYPE >", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE >=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThan(String value) {
            addCriterion("COUPON_TYPE <", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE <=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLike(String value) {
            addCriterion("COUPON_TYPE like", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotLike(String value) {
            addCriterion("COUPON_TYPE not like", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIn(List<String> values) {
            addCriterion("COUPON_TYPE in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotIn(List<String> values) {
            addCriterion("COUPON_TYPE not in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE not between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andDistributeWayIsNull() {
            addCriterion("DISTRIBUTE_WAY is null");
            return (Criteria) this;
        }

        public Criteria andDistributeWayIsNotNull() {
            addCriterion("DISTRIBUTE_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andDistributeWayEqualTo(String value) {
            addCriterion("DISTRIBUTE_WAY =", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayNotEqualTo(String value) {
            addCriterion("DISTRIBUTE_WAY <>", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayGreaterThan(String value) {
            addCriterion("DISTRIBUTE_WAY >", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTE_WAY >=", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayLessThan(String value) {
            addCriterion("DISTRIBUTE_WAY <", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayLessThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTE_WAY <=", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayLike(String value) {
            addCriterion("DISTRIBUTE_WAY like", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayNotLike(String value) {
            addCriterion("DISTRIBUTE_WAY not like", value, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayIn(List<String> values) {
            addCriterion("DISTRIBUTE_WAY in", values, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayNotIn(List<String> values) {
            addCriterion("DISTRIBUTE_WAY not in", values, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayBetween(String value1, String value2) {
            addCriterion("DISTRIBUTE_WAY between", value1, value2, "distributeWay");
            return (Criteria) this;
        }

        public Criteria andDistributeWayNotBetween(String value1, String value2) {
            addCriterion("DISTRIBUTE_WAY not between", value1, value2, "distributeWay");
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