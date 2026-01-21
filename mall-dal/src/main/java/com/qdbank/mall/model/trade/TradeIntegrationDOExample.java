package com.qdbank.mall.model.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeIntegrationDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeIntegrationDOExample() {
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

        public Criteria andNeedPayIntegrationIsNull() {
            addCriterion("NEED_PAY_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationIsNotNull() {
            addCriterion("NEED_PAY_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationEqualTo(String value) {
            addCriterion("NEED_PAY_INTEGRATION =", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationNotEqualTo(String value) {
            addCriterion("NEED_PAY_INTEGRATION <>", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationGreaterThan(String value) {
            addCriterion("NEED_PAY_INTEGRATION >", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationGreaterThanOrEqualTo(String value) {
            addCriterion("NEED_PAY_INTEGRATION >=", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationLessThan(String value) {
            addCriterion("NEED_PAY_INTEGRATION <", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationLessThanOrEqualTo(String value) {
            addCriterion("NEED_PAY_INTEGRATION <=", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationLike(String value) {
            addCriterion("NEED_PAY_INTEGRATION like", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationNotLike(String value) {
            addCriterion("NEED_PAY_INTEGRATION not like", value, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationIn(List<String> values) {
            addCriterion("NEED_PAY_INTEGRATION in", values, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationNotIn(List<String> values) {
            addCriterion("NEED_PAY_INTEGRATION not in", values, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationBetween(String value1, String value2) {
            addCriterion("NEED_PAY_INTEGRATION between", value1, value2, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayIntegrationNotBetween(String value1, String value2) {
            addCriterion("NEED_PAY_INTEGRATION not between", value1, value2, "needPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationIsNull() {
            addCriterion("NOT_NEED_PAY_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationIsNotNull() {
            addCriterion("NOT_NEED_PAY_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationEqualTo(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION =", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationNotEqualTo(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION <>", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationGreaterThan(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION >", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationGreaterThanOrEqualTo(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION >=", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationLessThan(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION <", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationLessThanOrEqualTo(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION <=", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationLike(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION like", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationNotLike(String value) {
            addCriterion("NOT_NEED_PAY_INTEGRATION not like", value, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationIn(List<String> values) {
            addCriterion("NOT_NEED_PAY_INTEGRATION in", values, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationNotIn(List<String> values) {
            addCriterion("NOT_NEED_PAY_INTEGRATION not in", values, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationBetween(String value1, String value2) {
            addCriterion("NOT_NEED_PAY_INTEGRATION between", value1, value2, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andNotNeedPayIntegrationNotBetween(String value1, String value2) {
            addCriterion("NOT_NEED_PAY_INTEGRATION not between", value1, value2, "notNeedPayIntegration");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponIsNull() {
            addCriterion("INTEGRATION_COUPON is null");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponIsNotNull() {
            addCriterion("INTEGRATION_COUPON is not null");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON =", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponNotEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON <>", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponGreaterThan(String value) {
            addCriterion("INTEGRATION_COUPON >", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponGreaterThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON >=", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponLessThan(String value) {
            addCriterion("INTEGRATION_COUPON <", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponLessThanOrEqualTo(String value) {
            addCriterion("INTEGRATION_COUPON <=", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponLike(String value) {
            addCriterion("INTEGRATION_COUPON like", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponNotLike(String value) {
            addCriterion("INTEGRATION_COUPON not like", value, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponIn(List<String> values) {
            addCriterion("INTEGRATION_COUPON in", values, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponNotIn(List<String> values) {
            addCriterion("INTEGRATION_COUPON not in", values, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponBetween(String value1, String value2) {
            addCriterion("INTEGRATION_COUPON between", value1, value2, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andIntegrationCouponNotBetween(String value1, String value2) {
            addCriterion("INTEGRATION_COUPON not between", value1, value2, "integrationCoupon");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationIsNull() {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationIsNotNull() {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationEqualTo(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION =", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationNotEqualTo(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION <>", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationGreaterThan(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION >", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationGreaterThanOrEqualTo(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION >=", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationLessThan(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION <", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationLessThanOrEqualTo(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION <=", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationLike(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION like", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationNotLike(String value) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION not like", value, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationIn(List<String> values) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION in", values, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationNotIn(List<String> values) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION not in", values, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationBetween(String value1, String value2) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION between", value1, value2, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andNeedPayTotalIntegrationNotBetween(String value1, String value2) {
            addCriterion("NEED_PAY_TOTAL_INTEGRATION not between", value1, value2, "needPayTotalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationIsNull() {
            addCriterion("TOTAL_INTEGRATION is null");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationIsNotNull() {
            addCriterion("TOTAL_INTEGRATION is not null");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationEqualTo(String value) {
            addCriterion("TOTAL_INTEGRATION =", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationNotEqualTo(String value) {
            addCriterion("TOTAL_INTEGRATION <>", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationGreaterThan(String value) {
            addCriterion("TOTAL_INTEGRATION >", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_INTEGRATION >=", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationLessThan(String value) {
            addCriterion("TOTAL_INTEGRATION <", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_INTEGRATION <=", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationLike(String value) {
            addCriterion("TOTAL_INTEGRATION like", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationNotLike(String value) {
            addCriterion("TOTAL_INTEGRATION not like", value, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationIn(List<String> values) {
            addCriterion("TOTAL_INTEGRATION in", values, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationNotIn(List<String> values) {
            addCriterion("TOTAL_INTEGRATION not in", values, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationBetween(String value1, String value2) {
            addCriterion("TOTAL_INTEGRATION between", value1, value2, "totalIntegration");
            return (Criteria) this;
        }

        public Criteria andTotalIntegrationNotBetween(String value1, String value2) {
            addCriterion("TOTAL_INTEGRATION not between", value1, value2, "totalIntegration");
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