package com.qdbank.mall.model.skustock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SkustockDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SkustockDOExample() {
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

        public Criteria andStatusEqualTo(Long value) {
            addCriterion("STATUS =", value, "status");
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

        public Criteria andMarketPriceIsNull() {
            addCriterion("MARKET_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("MARKET_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(BigDecimal value) {
            addCriterion("MARKET_PRICE =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("MARKET_PRICE <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("MARKET_PRICE >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MARKET_PRICE >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(BigDecimal value) {
            addCriterion("MARKET_PRICE <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MARKET_PRICE <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<BigDecimal> values) {
            addCriterion("MARKET_PRICE in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("MARKET_PRICE not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MARKET_PRICE between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MARKET_PRICE not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceIsNull() {
            addCriterion("ADVICE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceIsNotNull() {
            addCriterion("ADVICE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceEqualTo(BigDecimal value) {
            addCriterion("ADVICE_PRICE =", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceNotEqualTo(BigDecimal value) {
            addCriterion("ADVICE_PRICE <>", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceGreaterThan(BigDecimal value) {
            addCriterion("ADVICE_PRICE >", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ADVICE_PRICE >=", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceLessThan(BigDecimal value) {
            addCriterion("ADVICE_PRICE <", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ADVICE_PRICE <=", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceIn(List<BigDecimal> values) {
            addCriterion("ADVICE_PRICE in", values, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceNotIn(List<BigDecimal> values) {
            addCriterion("ADVICE_PRICE not in", values, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADVICE_PRICE between", value1, value2, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADVICE_PRICE not between", value1, value2, "advicePrice");
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

        public Criteria andPromotionStartTimeIsNull() {
            addCriterion("PROMOTION_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeIsNotNull() {
            addCriterion("PROMOTION_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_START_TIME =", value, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_START_TIME <>", value, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("PROMOTION_START_TIME >", value, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_START_TIME >=", value, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("PROMOTION_START_TIME <", value, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_START_TIME <=", value, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("PROMOTION_START_TIME in", values, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("PROMOTION_START_TIME not in", values, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PROMOTION_START_TIME between", value1, value2, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PROMOTION_START_TIME not between", value1, value2, "promotionStartTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeIsNull() {
            addCriterion("PROMOTION_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeIsNotNull() {
            addCriterion("PROMOTION_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_END_TIME =", value, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_END_TIME <>", value, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("PROMOTION_END_TIME >", value, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_END_TIME >=", value, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("PROMOTION_END_TIME <", value, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PROMOTION_END_TIME <=", value, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("PROMOTION_END_TIME in", values, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("PROMOTION_END_TIME not in", values, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PROMOTION_END_TIME between", value1, value2, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PROMOTION_END_TIME not between", value1, value2, "promotionEndTime");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitIsNull() {
            addCriterion("PROMOTION_PER_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitIsNotNull() {
            addCriterion("PROMOTION_PER_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitEqualTo(Long value) {
            addCriterion("PROMOTION_PER_LIMIT =", value, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitNotEqualTo(Long value) {
            addCriterion("PROMOTION_PER_LIMIT <>", value, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitGreaterThan(Long value) {
            addCriterion("PROMOTION_PER_LIMIT >", value, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("PROMOTION_PER_LIMIT >=", value, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitLessThan(Long value) {
            addCriterion("PROMOTION_PER_LIMIT <", value, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitLessThanOrEqualTo(Long value) {
            addCriterion("PROMOTION_PER_LIMIT <=", value, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitIn(List<Long> values) {
            addCriterion("PROMOTION_PER_LIMIT in", values, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitNotIn(List<Long> values) {
            addCriterion("PROMOTION_PER_LIMIT not in", values, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitBetween(Long value1, Long value2) {
            addCriterion("PROMOTION_PER_LIMIT between", value1, value2, "promotionPerLimit");
            return (Criteria) this;
        }

        public Criteria andPromotionPerLimitNotBetween(Long value1, Long value2) {
            addCriterion("PROMOTION_PER_LIMIT not between", value1, value2, "promotionPerLimit");
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

        public Criteria andProductStockIsNull() {
            addCriterion("PRODUCT_STOCK is null");
            return (Criteria) this;
        }

        public Criteria andProductStockIsNotNull() {
            addCriterion("PRODUCT_STOCK is not null");
            return (Criteria) this;
        }

        public Criteria andProductStockEqualTo(Long value) {
            addCriterion("PRODUCT_STOCK =", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockNotEqualTo(Long value) {
            addCriterion("PRODUCT_STOCK <>", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockGreaterThan(Long value) {
            addCriterion("PRODUCT_STOCK >", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_STOCK >=", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockLessThan(Long value) {
            addCriterion("PRODUCT_STOCK <", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_STOCK <=", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockIn(List<Long> values) {
            addCriterion("PRODUCT_STOCK in", values, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockNotIn(List<Long> values) {
            addCriterion("PRODUCT_STOCK not in", values, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_STOCK between", value1, value2, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_STOCK not between", value1, value2, "productStock");
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

        public Criteria andProductLockStockIsNull() {
            addCriterion("PRODUCT_LOCK_STOCK is null");
            return (Criteria) this;
        }

        public Criteria andProductLockStockIsNotNull() {
            addCriterion("PRODUCT_LOCK_STOCK is not null");
            return (Criteria) this;
        }

        public Criteria andProductLockStockEqualTo(Long value) {
            addCriterion("PRODUCT_LOCK_STOCK =", value, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockNotEqualTo(Long value) {
            addCriterion("PRODUCT_LOCK_STOCK <>", value, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockGreaterThan(Long value) {
            addCriterion("PRODUCT_LOCK_STOCK >", value, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockGreaterThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_LOCK_STOCK >=", value, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockLessThan(Long value) {
            addCriterion("PRODUCT_LOCK_STOCK <", value, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockLessThanOrEqualTo(Long value) {
            addCriterion("PRODUCT_LOCK_STOCK <=", value, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockIn(List<Long> values) {
            addCriterion("PRODUCT_LOCK_STOCK in", values, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockNotIn(List<Long> values) {
            addCriterion("PRODUCT_LOCK_STOCK not in", values, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_LOCK_STOCK between", value1, value2, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductLockStockNotBetween(Long value1, Long value2) {
            addCriterion("PRODUCT_LOCK_STOCK not between", value1, value2, "productLockStock");
            return (Criteria) this;
        }

        public Criteria andProductSpDataIsNull() {
            addCriterion("PRODUCT_SP_DATA is null");
            return (Criteria) this;
        }

        public Criteria andProductSpDataIsNotNull() {
            addCriterion("PRODUCT_SP_DATA is not null");
            return (Criteria) this;
        }

        public Criteria andProductSpDataEqualTo(String value) {
            addCriterion("PRODUCT_SP_DATA =", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataNotEqualTo(String value) {
            addCriterion("PRODUCT_SP_DATA <>", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataGreaterThan(String value) {
            addCriterion("PRODUCT_SP_DATA >", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_SP_DATA >=", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataLessThan(String value) {
            addCriterion("PRODUCT_SP_DATA <", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_SP_DATA <=", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataLike(String value) {
            addCriterion("PRODUCT_SP_DATA like", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataNotLike(String value) {
            addCriterion("PRODUCT_SP_DATA not like", value, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataIn(List<String> values) {
            addCriterion("PRODUCT_SP_DATA in", values, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataNotIn(List<String> values) {
            addCriterion("PRODUCT_SP_DATA not in", values, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataBetween(String value1, String value2) {
            addCriterion("PRODUCT_SP_DATA between", value1, value2, "productSpData");
            return (Criteria) this;
        }

        public Criteria andProductSpDataNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_SP_DATA not between", value1, value2, "productSpData");
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