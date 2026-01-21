package com.qdbank.mall.model.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeDetailDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeDetailDOExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
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

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("PRODUCT_ID like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("PRODUCT_ID not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
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

        public Criteria andProductSkuIdEqualTo(String value) {
            addCriterion("PRODUCT_SKU_ID =", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotEqualTo(String value) {
            addCriterion("PRODUCT_SKU_ID <>", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdGreaterThan(String value) {
            addCriterion("PRODUCT_SKU_ID >", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_SKU_ID >=", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdLessThan(String value) {
            addCriterion("PRODUCT_SKU_ID <", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_SKU_ID <=", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdLike(String value) {
            addCriterion("PRODUCT_SKU_ID like", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotLike(String value) {
            addCriterion("PRODUCT_SKU_ID not like", value, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdIn(List<String> values) {
            addCriterion("PRODUCT_SKU_ID in", values, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotIn(List<String> values) {
            addCriterion("PRODUCT_SKU_ID not in", values, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_SKU_ID between", value1, value2, "productSkuId");
            return (Criteria) this;
        }

        public Criteria andProductSkuIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_SKU_ID not between", value1, value2, "productSkuId");
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

        public Criteria andOrderIntegrationIsNull() {
            addCriterion("ORDER_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationIsNotNull() {
            addCriterion("ORDER_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationEqualTo(String value) {
            addCriterion("ORDER_INTEGRATION =", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationNotEqualTo(String value) {
            addCriterion("ORDER_INTEGRATION <>", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationGreaterThan(String value) {
            addCriterion("ORDER_INTEGRATION >", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_INTEGRATION >=", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationLessThan(String value) {
            addCriterion("ORDER_INTEGRATION <", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationLessThanOrEqualTo(String value) {
            addCriterion("ORDER_INTEGRATION <=", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationLike(String value) {
            addCriterion("ORDER_INTEGRATION like", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationNotLike(String value) {
            addCriterion("ORDER_INTEGRATION not like", value, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationIn(List<String> values) {
            addCriterion("ORDER_INTEGRATION in", values, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationNotIn(List<String> values) {
            addCriterion("ORDER_INTEGRATION not in", values, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationBetween(String value1, String value2) {
            addCriterion("ORDER_INTEGRATION between", value1, value2, "orderIntegration");
            return (Criteria) this;
        }

        public Criteria andOrderIntegrationNotBetween(String value1, String value2) {
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

        public Criteria andIntegrationCouponAmountIsNull() {
            addCriterion("INTEGRATION_COUPON_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountIsNotNull() {
            addCriterion("INTEGRATION_COUPON_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT =", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountNotEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT <>", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountGreaterThan(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT >", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountGreaterThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT >=", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountLessThan(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT <", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountLessThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT <=", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountLike(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT like", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountNotLike(String value) {
            addCriterion("INTEGRATION_COUPON_AMOUNT not like", value, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountIn(List<String> values) {
            addCriterion("INTEGRATION_COUPON_AMOUNT in", values, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountNotIn(List<String> values) {
            addCriterion("INTEGRATION_COUPON_AMOUNT not in", values, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountBetween(String value1, String value2) {
            addCriterion("INTEGRATION_COUPON_AMOUNT between", value1, value2, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponAmountNotBetween(String value1, String value2) {
            addCriterion("INTEGRATION_COUPON_AMOUNT not between", value1, value2, "integrationCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountIsNull() {
            addCriterion("PRODUCT_COUPON_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountIsNotNull() {
            addCriterion("PRODUCT_COUPON_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT =", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountNotEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT <>", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountGreaterThan(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT >", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT >=", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountLessThan(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT <", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT <=", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountLike(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT like", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountNotLike(String value) {
            addCriterion("PRODUCT_COUPON_AMOUNT not like", value, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountIn(List<String> values) {
            addCriterion("PRODUCT_COUPON_AMOUNT in", values, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountNotIn(List<String> values) {
            addCriterion("PRODUCT_COUPON_AMOUNT not in", values, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountBetween(String value1, String value2) {
            addCriterion("PRODUCT_COUPON_AMOUNT between", value1, value2, "productCouponAmount");
            return (Criteria) this;
        }

        public Criteria andProductCouponAmountNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_COUPON_AMOUNT not between", value1, value2, "productCouponAmount");
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

        public Criteria andIntegrationPayFlagEqualTo(String value) {
            addCriterion("INTEGRATION_PAY_FLAG =", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagNotEqualTo(String value) {
            addCriterion("INTEGRATION_PAY_FLAG <>", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagGreaterThan(String value) {
            addCriterion("INTEGRATION_PAY_FLAG >", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagGreaterThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_PAY_FLAG >=", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagLessThan(String value) {
            addCriterion("INTEGRATION_PAY_FLAG <", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagLessThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_PAY_FLAG <=", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagLike(String value) {
            addCriterion("INTEGRATION_PAY_FLAG like", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagNotLike(String value) {
            addCriterion("INTEGRATION_PAY_FLAG not like", value, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagIn(List<String> values) {
            addCriterion("INTEGRATION_PAY_FLAG in", values, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagNotIn(List<String> values) {
            addCriterion("INTEGRATION_PAY_FLAG not in", values, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagBetween(String value1, String value2) {
            addCriterion("INTEGRATION_PAY_FLAG between", value1, value2, "integrationPayFlag");
            return (Criteria) this;
        }

        public Criteria andIntegrationPayFlagNotBetween(String value1, String value2) {
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

        public Criteria andBatchNoIsNull() {
            addCriterion("BATCH_NO is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("BATCH_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("BATCH_NO =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("BATCH_NO <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("BATCH_NO >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_NO >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("BATCH_NO <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("BATCH_NO <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("BATCH_NO like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("BATCH_NO not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("BATCH_NO in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("BATCH_NO not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("BATCH_NO between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("BATCH_NO not between", value1, value2, "batchNo");
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