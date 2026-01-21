package com.qdbank.mall.model.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeFileDataDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeFileDataDOExample() {
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

        public Criteria andMerCodeIsNull() {
            addCriterion("MER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMerCodeIsNotNull() {
            addCriterion("MER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMerCodeEqualTo(String value) {
            addCriterion("MER_CODE =", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeNotEqualTo(String value) {
            addCriterion("MER_CODE <>", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeGreaterThan(String value) {
            addCriterion("MER_CODE >", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MER_CODE >=", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeLessThan(String value) {
            addCriterion("MER_CODE <", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeLessThanOrEqualTo(String value) {
            addCriterion("MER_CODE <=", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeLike(String value) {
            addCriterion("MER_CODE like", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeNotLike(String value) {
            addCriterion("MER_CODE not like", value, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeIn(List<String> values) {
            addCriterion("MER_CODE in", values, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeNotIn(List<String> values) {
            addCriterion("MER_CODE not in", values, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeBetween(String value1, String value2) {
            addCriterion("MER_CODE between", value1, value2, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerCodeNotBetween(String value1, String value2) {
            addCriterion("MER_CODE not between", value1, value2, "merCode");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNull() {
            addCriterion("MER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMerNameIsNotNull() {
            addCriterion("MER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMerNameEqualTo(String value) {
            addCriterion("MER_NAME =", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotEqualTo(String value) {
            addCriterion("MER_NAME <>", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThan(String value) {
            addCriterion("MER_NAME >", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameGreaterThanOrEqualTo(String value) {
            addCriterion("MER_NAME >=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThan(String value) {
            addCriterion("MER_NAME <", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLessThanOrEqualTo(String value) {
            addCriterion("MER_NAME <=", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameLike(String value) {
            addCriterion("MER_NAME like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotLike(String value) {
            addCriterion("MER_NAME not like", value, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameIn(List<String> values) {
            addCriterion("MER_NAME in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotIn(List<String> values) {
            addCriterion("MER_NAME not in", values, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameBetween(String value1, String value2) {
            addCriterion("MER_NAME between", value1, value2, "merName");
            return (Criteria) this;
        }

        public Criteria andMerNameNotBetween(String value1, String value2) {
            addCriterion("MER_NAME not between", value1, value2, "merName");
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

        public Criteria andTxnDateIsNull() {
            addCriterion("TXN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTxnDateIsNotNull() {
            addCriterion("TXN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTxnDateEqualTo(String value) {
            addCriterion("TXN_DATE =", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotEqualTo(String value) {
            addCriterion("TXN_DATE <>", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateGreaterThan(String value) {
            addCriterion("TXN_DATE >", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateGreaterThanOrEqualTo(String value) {
            addCriterion("TXN_DATE >=", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateLessThan(String value) {
            addCriterion("TXN_DATE <", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateLessThanOrEqualTo(String value) {
            addCriterion("TXN_DATE <=", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateLike(String value) {
            addCriterion("TXN_DATE like", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotLike(String value) {
            addCriterion("TXN_DATE not like", value, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateIn(List<String> values) {
            addCriterion("TXN_DATE in", values, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotIn(List<String> values) {
            addCriterion("TXN_DATE not in", values, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateBetween(String value1, String value2) {
            addCriterion("TXN_DATE between", value1, value2, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnDateNotBetween(String value1, String value2) {
            addCriterion("TXN_DATE not between", value1, value2, "txnDate");
            return (Criteria) this;
        }

        public Criteria andTxnTimeIsNull() {
            addCriterion("TXN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTxnTimeIsNotNull() {
            addCriterion("TXN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTxnTimeEqualTo(String value) {
            addCriterion("TXN_TIME =", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeNotEqualTo(String value) {
            addCriterion("TXN_TIME <>", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeGreaterThan(String value) {
            addCriterion("TXN_TIME >", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TXN_TIME >=", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeLessThan(String value) {
            addCriterion("TXN_TIME <", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeLessThanOrEqualTo(String value) {
            addCriterion("TXN_TIME <=", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeLike(String value) {
            addCriterion("TXN_TIME like", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeNotLike(String value) {
            addCriterion("TXN_TIME not like", value, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeIn(List<String> values) {
            addCriterion("TXN_TIME in", values, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeNotIn(List<String> values) {
            addCriterion("TXN_TIME not in", values, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeBetween(String value1, String value2) {
            addCriterion("TXN_TIME between", value1, value2, "txnTime");
            return (Criteria) this;
        }

        public Criteria andTxnTimeNotBetween(String value1, String value2) {
            addCriterion("TXN_TIME not between", value1, value2, "txnTime");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("ORDER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("ORDER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(String value) {
            addCriterion("ORDER_STATUS =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(String value) {
            addCriterion("ORDER_STATUS <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(String value) {
            addCriterion("ORDER_STATUS >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(String value) {
            addCriterion("ORDER_STATUS <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLike(String value) {
            addCriterion("ORDER_STATUS like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotLike(String value) {
            addCriterion("ORDER_STATUS not like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<String> values) {
            addCriterion("ORDER_STATUS in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<String> values) {
            addCriterion("ORDER_STATUS not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNull() {
            addCriterion("ORDER_AMT is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNotNull() {
            addCriterion("ORDER_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtEqualTo(Long value) {
            addCriterion("ORDER_AMT =", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotEqualTo(Long value) {
            addCriterion("ORDER_AMT <>", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThan(Long value) {
            addCriterion("ORDER_AMT >", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMT >=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThan(Long value) {
            addCriterion("ORDER_AMT <", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMT <=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIn(List<Long> values) {
            addCriterion("ORDER_AMT in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotIn(List<Long> values) {
            addCriterion("ORDER_AMT not in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMT between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMT not between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andExpendPointIsNull() {
            addCriterion("EXPEND_POINT is null");
            return (Criteria) this;
        }

        public Criteria andExpendPointIsNotNull() {
            addCriterion("EXPEND_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andExpendPointEqualTo(String value) {
            addCriterion("EXPEND_POINT =", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointNotEqualTo(String value) {
            addCriterion("EXPEND_POINT <>", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointGreaterThan(String value) {
            addCriterion("EXPEND_POINT >", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointGreaterThanOrEqualTo(String value) {
            addCriterion("EXPEND_POINT >=", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointLessThan(String value) {
            addCriterion("EXPEND_POINT <", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointLessThanOrEqualTo(String value) {
            addCriterion("EXPEND_POINT <=", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointLike(String value) {
            addCriterion("EXPEND_POINT like", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointNotLike(String value) {
            addCriterion("EXPEND_POINT not like", value, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointIn(List<String> values) {
            addCriterion("EXPEND_POINT in", values, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointNotIn(List<String> values) {
            addCriterion("EXPEND_POINT not in", values, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointBetween(String value1, String value2) {
            addCriterion("EXPEND_POINT between", value1, value2, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendPointNotBetween(String value1, String value2) {
            addCriterion("EXPEND_POINT not between", value1, value2, "expendPoint");
            return (Criteria) this;
        }

        public Criteria andExpendAmtIsNull() {
            addCriterion("EXPEND_AMT is null");
            return (Criteria) this;
        }

        public Criteria andExpendAmtIsNotNull() {
            addCriterion("EXPEND_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andExpendAmtEqualTo(Long value) {
            addCriterion("EXPEND_AMT =", value, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtNotEqualTo(Long value) {
            addCriterion("EXPEND_AMT <>", value, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtGreaterThan(Long value) {
            addCriterion("EXPEND_AMT >", value, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("EXPEND_AMT >=", value, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtLessThan(Long value) {
            addCriterion("EXPEND_AMT <", value, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtLessThanOrEqualTo(Long value) {
            addCriterion("EXPEND_AMT <=", value, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtIn(List<Long> values) {
            addCriterion("EXPEND_AMT in", values, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtNotIn(List<Long> values) {
            addCriterion("EXPEND_AMT not in", values, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtBetween(Long value1, Long value2) {
            addCriterion("EXPEND_AMT between", value1, value2, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andExpendAmtNotBetween(Long value1, Long value2) {
            addCriterion("EXPEND_AMT not between", value1, value2, "expendAmt");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
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

        public Criteria andAdvicePriceEqualTo(Long value) {
            addCriterion("ADVICE_PRICE =", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceNotEqualTo(Long value) {
            addCriterion("ADVICE_PRICE <>", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceGreaterThan(Long value) {
            addCriterion("ADVICE_PRICE >", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("ADVICE_PRICE >=", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceLessThan(Long value) {
            addCriterion("ADVICE_PRICE <", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceLessThanOrEqualTo(Long value) {
            addCriterion("ADVICE_PRICE <=", value, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceIn(List<Long> values) {
            addCriterion("ADVICE_PRICE in", values, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceNotIn(List<Long> values) {
            addCriterion("ADVICE_PRICE not in", values, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceBetween(Long value1, Long value2) {
            addCriterion("ADVICE_PRICE between", value1, value2, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andAdvicePriceNotBetween(Long value1, Long value2) {
            addCriterion("ADVICE_PRICE not between", value1, value2, "advicePrice");
            return (Criteria) this;
        }

        public Criteria andReservedIsNull() {
            addCriterion("RESERVED is null");
            return (Criteria) this;
        }

        public Criteria andReservedIsNotNull() {
            addCriterion("RESERVED is not null");
            return (Criteria) this;
        }

        public Criteria andReservedEqualTo(String value) {
            addCriterion("RESERVED =", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedNotEqualTo(String value) {
            addCriterion("RESERVED <>", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedGreaterThan(String value) {
            addCriterion("RESERVED >", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedGreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED >=", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedLessThan(String value) {
            addCriterion("RESERVED <", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedLessThanOrEqualTo(String value) {
            addCriterion("RESERVED <=", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedLike(String value) {
            addCriterion("RESERVED like", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedNotLike(String value) {
            addCriterion("RESERVED not like", value, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedIn(List<String> values) {
            addCriterion("RESERVED in", values, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedNotIn(List<String> values) {
            addCriterion("RESERVED not in", values, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedBetween(String value1, String value2) {
            addCriterion("RESERVED between", value1, value2, "reserved");
            return (Criteria) this;
        }

        public Criteria andReservedNotBetween(String value1, String value2) {
            addCriterion("RESERVED not between", value1, value2, "reserved");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNull() {
            addCriterion("PAYMENT_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIsNotNull() {
            addCriterion("PAYMENT_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodEqualTo(String value) {
            addCriterion("PAYMENT_METHOD =", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotEqualTo(String value) {
            addCriterion("PAYMENT_METHOD <>", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThan(String value) {
            addCriterion("PAYMENT_METHOD >", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodGreaterThanOrEqualTo(String value) {
            addCriterion("PAYMENT_METHOD >=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThan(String value) {
            addCriterion("PAYMENT_METHOD <", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLessThanOrEqualTo(String value) {
            addCriterion("PAYMENT_METHOD <=", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodLike(String value) {
            addCriterion("PAYMENT_METHOD like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotLike(String value) {
            addCriterion("PAYMENT_METHOD not like", value, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodIn(List<String> values) {
            addCriterion("PAYMENT_METHOD in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotIn(List<String> values) {
            addCriterion("PAYMENT_METHOD not in", values, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodBetween(String value1, String value2) {
            addCriterion("PAYMENT_METHOD between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPaymentMethodNotBetween(String value1, String value2) {
            addCriterion("PAYMENT_METHOD not between", value1, value2, "paymentMethod");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkIsNull() {
            addCriterion("POINT_SET_MARK is null");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkIsNotNull() {
            addCriterion("POINT_SET_MARK is not null");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkEqualTo(String value) {
            addCriterion("POINT_SET_MARK =", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkNotEqualTo(String value) {
            addCriterion("POINT_SET_MARK <>", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkGreaterThan(String value) {
            addCriterion("POINT_SET_MARK >", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkGreaterThanOrEqualTo(String value) {
            addCriterion("POINT_SET_MARK >=", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkLessThan(String value) {
            addCriterion("POINT_SET_MARK <", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkLessThanOrEqualTo(String value) {
            addCriterion("POINT_SET_MARK <=", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkLike(String value) {
            addCriterion("POINT_SET_MARK like", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkNotLike(String value) {
            addCriterion("POINT_SET_MARK not like", value, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkIn(List<String> values) {
            addCriterion("POINT_SET_MARK in", values, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkNotIn(List<String> values) {
            addCriterion("POINT_SET_MARK not in", values, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkBetween(String value1, String value2) {
            addCriterion("POINT_SET_MARK between", value1, value2, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andPointSetMarkNotBetween(String value1, String value2) {
            addCriterion("POINT_SET_MARK not between", value1, value2, "pointSetMark");
            return (Criteria) this;
        }

        public Criteria andTxDtIsNull() {
            addCriterion("TX_DT is null");
            return (Criteria) this;
        }

        public Criteria andTxDtIsNotNull() {
            addCriterion("TX_DT is not null");
            return (Criteria) this;
        }

        public Criteria andTxDtEqualTo(String value) {
            addCriterion("TX_DT =", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtNotEqualTo(String value) {
            addCriterion("TX_DT <>", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtGreaterThan(String value) {
            addCriterion("TX_DT >", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtGreaterThanOrEqualTo(String value) {
            addCriterion("TX_DT >=", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtLessThan(String value) {
            addCriterion("TX_DT <", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtLessThanOrEqualTo(String value) {
            addCriterion("TX_DT <=", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtLike(String value) {
            addCriterion("TX_DT like", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtNotLike(String value) {
            addCriterion("TX_DT not like", value, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtIn(List<String> values) {
            addCriterion("TX_DT in", values, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtNotIn(List<String> values) {
            addCriterion("TX_DT not in", values, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtBetween(String value1, String value2) {
            addCriterion("TX_DT between", value1, value2, "txDt");
            return (Criteria) this;
        }

        public Criteria andTxDtNotBetween(String value1, String value2) {
            addCriterion("TX_DT not between", value1, value2, "txDt");
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

        public Criteria andOrgIsNull() {
            addCriterion("ORG is null");
            return (Criteria) this;
        }

        public Criteria andOrgIsNotNull() {
            addCriterion("ORG is not null");
            return (Criteria) this;
        }

        public Criteria andOrgEqualTo(String value) {
            addCriterion("ORG =", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotEqualTo(String value) {
            addCriterion("ORG <>", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgGreaterThan(String value) {
            addCriterion("ORG >", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgGreaterThanOrEqualTo(String value) {
            addCriterion("ORG >=", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLessThan(String value) {
            addCriterion("ORG <", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLessThanOrEqualTo(String value) {
            addCriterion("ORG <=", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgLike(String value) {
            addCriterion("ORG like", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotLike(String value) {
            addCriterion("ORG not like", value, "org");
            return (Criteria) this;
        }

        public Criteria andOrgIn(List<String> values) {
            addCriterion("ORG in", values, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotIn(List<String> values) {
            addCriterion("ORG not in", values, "org");
            return (Criteria) this;
        }

        public Criteria andOrgBetween(String value1, String value2) {
            addCriterion("ORG between", value1, value2, "org");
            return (Criteria) this;
        }

        public Criteria andOrgNotBetween(String value1, String value2) {
            addCriterion("ORG not between", value1, value2, "org");
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