package com.qdbank.mall.model.paymentFlow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentFlowDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaymentFlowDOExample() {
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

        public Criteria andPaymentFolwIdIsNull() {
            addCriterion("PAYMENT_FOLW_ID is null");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdIsNotNull() {
            addCriterion("PAYMENT_FOLW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdEqualTo(Long value) {
            addCriterion("PAYMENT_FOLW_ID =", value, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdNotEqualTo(Long value) {
            addCriterion("PAYMENT_FOLW_ID <>", value, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdGreaterThan(Long value) {
            addCriterion("PAYMENT_FOLW_ID >", value, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PAYMENT_FOLW_ID >=", value, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdLessThan(Long value) {
            addCriterion("PAYMENT_FOLW_ID <", value, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdLessThanOrEqualTo(Long value) {
            addCriterion("PAYMENT_FOLW_ID <=", value, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdIn(List<Long> values) {
            addCriterion("PAYMENT_FOLW_ID in", values, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdNotIn(List<Long> values) {
            addCriterion("PAYMENT_FOLW_ID not in", values, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdBetween(Long value1, Long value2) {
            addCriterion("PAYMENT_FOLW_ID between", value1, value2, "paymentFolwId");
            return (Criteria) this;
        }

        public Criteria andPaymentFolwIdNotBetween(Long value1, Long value2) {
            addCriterion("PAYMENT_FOLW_ID not between", value1, value2, "paymentFolwId");
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

        public Criteria andAcctTypeIsNull() {
            addCriterion("ACCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNotNull() {
            addCriterion("ACCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeEqualTo(String value) {
            addCriterion("ACCT_TYPE =", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotEqualTo(String value) {
            addCriterion("ACCT_TYPE <>", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThan(String value) {
            addCriterion("ACCT_TYPE >", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_TYPE >=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThan(String value) {
            addCriterion("ACCT_TYPE <", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("ACCT_TYPE <=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLike(String value) {
            addCriterion("ACCT_TYPE like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotLike(String value) {
            addCriterion("ACCT_TYPE not like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIn(List<String> values) {
            addCriterion("ACCT_TYPE in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotIn(List<String> values) {
            addCriterion("ACCT_TYPE not in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeBetween(String value1, String value2) {
            addCriterion("ACCT_TYPE between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotBetween(String value1, String value2) {
            addCriterion("ACCT_TYPE not between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andOriTransDtIsNull() {
            addCriterion("ORI_TRANS_DT is null");
            return (Criteria) this;
        }

        public Criteria andOriTransDtIsNotNull() {
            addCriterion("ORI_TRANS_DT is not null");
            return (Criteria) this;
        }

        public Criteria andOriTransDtEqualTo(String value) {
            addCriterion("ORI_TRANS_DT =", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtNotEqualTo(String value) {
            addCriterion("ORI_TRANS_DT <>", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtGreaterThan(String value) {
            addCriterion("ORI_TRANS_DT >", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtGreaterThanOrEqualTo(String value) {
            addCriterion("ORI_TRANS_DT >=", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtLessThan(String value) {
            addCriterion("ORI_TRANS_DT <", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtLessThanOrEqualTo(String value) {
            addCriterion("ORI_TRANS_DT <=", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtLike(String value) {
            addCriterion("ORI_TRANS_DT like", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtNotLike(String value) {
            addCriterion("ORI_TRANS_DT not like", value, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtIn(List<String> values) {
            addCriterion("ORI_TRANS_DT in", values, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtNotIn(List<String> values) {
            addCriterion("ORI_TRANS_DT not in", values, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtBetween(String value1, String value2) {
            addCriterion("ORI_TRANS_DT between", value1, value2, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransDtNotBetween(String value1, String value2) {
            addCriterion("ORI_TRANS_DT not between", value1, value2, "oriTransDt");
            return (Criteria) this;
        }

        public Criteria andOriTransserIsNull() {
            addCriterion("ORI_TRANSSER is null");
            return (Criteria) this;
        }

        public Criteria andOriTransserIsNotNull() {
            addCriterion("ORI_TRANSSER is not null");
            return (Criteria) this;
        }

        public Criteria andOriTransserEqualTo(String value) {
            addCriterion("ORI_TRANSSER =", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserNotEqualTo(String value) {
            addCriterion("ORI_TRANSSER <>", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserGreaterThan(String value) {
            addCriterion("ORI_TRANSSER >", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserGreaterThanOrEqualTo(String value) {
            addCriterion("ORI_TRANSSER >=", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserLessThan(String value) {
            addCriterion("ORI_TRANSSER <", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserLessThanOrEqualTo(String value) {
            addCriterion("ORI_TRANSSER <=", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserLike(String value) {
            addCriterion("ORI_TRANSSER like", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserNotLike(String value) {
            addCriterion("ORI_TRANSSER not like", value, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserIn(List<String> values) {
            addCriterion("ORI_TRANSSER in", values, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserNotIn(List<String> values) {
            addCriterion("ORI_TRANSSER not in", values, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserBetween(String value1, String value2) {
            addCriterion("ORI_TRANSSER between", value1, value2, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andOriTransserNotBetween(String value1, String value2) {
            addCriterion("ORI_TRANSSER not between", value1, value2, "oriTransser");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIsNull() {
            addCriterion("QUERY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIsNotNull() {
            addCriterion("QUERY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeEqualTo(String value) {
            addCriterion("QUERY_TYPE =", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotEqualTo(String value) {
            addCriterion("QUERY_TYPE <>", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeGreaterThan(String value) {
            addCriterion("QUERY_TYPE >", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_TYPE >=", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLessThan(String value) {
            addCriterion("QUERY_TYPE <", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLessThanOrEqualTo(String value) {
            addCriterion("QUERY_TYPE <=", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLike(String value) {
            addCriterion("QUERY_TYPE like", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotLike(String value) {
            addCriterion("QUERY_TYPE not like", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIn(List<String> values) {
            addCriterion("QUERY_TYPE in", values, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotIn(List<String> values) {
            addCriterion("QUERY_TYPE not in", values, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeBetween(String value1, String value2) {
            addCriterion("QUERY_TYPE between", value1, value2, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotBetween(String value1, String value2) {
            addCriterion("QUERY_TYPE not between", value1, value2, "queryType");
            return (Criteria) this;
        }

        public Criteria andTransAmtIsNull() {
            addCriterion("TRANS_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTransAmtIsNotNull() {
            addCriterion("TRANS_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTransAmtEqualTo(String value) {
            addCriterion("TRANS_AMT =", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtNotEqualTo(String value) {
            addCriterion("TRANS_AMT <>", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtGreaterThan(String value) {
            addCriterion("TRANS_AMT >", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_AMT >=", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtLessThan(String value) {
            addCriterion("TRANS_AMT <", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtLessThanOrEqualTo(String value) {
            addCriterion("TRANS_AMT <=", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtLike(String value) {
            addCriterion("TRANS_AMT like", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtNotLike(String value) {
            addCriterion("TRANS_AMT not like", value, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtIn(List<String> values) {
            addCriterion("TRANS_AMT in", values, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtNotIn(List<String> values) {
            addCriterion("TRANS_AMT not in", values, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtBetween(String value1, String value2) {
            addCriterion("TRANS_AMT between", value1, value2, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTransAmtNotBetween(String value1, String value2) {
            addCriterion("TRANS_AMT not between", value1, value2, "transAmt");
            return (Criteria) this;
        }

        public Criteria andTranseIsNull() {
            addCriterion("TRANSE is null");
            return (Criteria) this;
        }

        public Criteria andTranseIsNotNull() {
            addCriterion("TRANSE is not null");
            return (Criteria) this;
        }

        public Criteria andTranseEqualTo(String value) {
            addCriterion("TRANSE =", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseNotEqualTo(String value) {
            addCriterion("TRANSE <>", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseGreaterThan(String value) {
            addCriterion("TRANSE >", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSE >=", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseLessThan(String value) {
            addCriterion("TRANSE <", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseLessThanOrEqualTo(String value) {
            addCriterion("TRANSE <=", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseLike(String value) {
            addCriterion("TRANSE like", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseNotLike(String value) {
            addCriterion("TRANSE not like", value, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseIn(List<String> values) {
            addCriterion("TRANSE in", values, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseNotIn(List<String> values) {
            addCriterion("TRANSE not in", values, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseBetween(String value1, String value2) {
            addCriterion("TRANSE between", value1, value2, "transe");
            return (Criteria) this;
        }

        public Criteria andTranseNotBetween(String value1, String value2) {
            addCriterion("TRANSE not between", value1, value2, "transe");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
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