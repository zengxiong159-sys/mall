package com.qdbank.mall.model.freighttemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreighttemplateDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FreighttemplateDOExample() {
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

        public Criteria andFreightTemplateIdIsNull() {
            addCriterion("FREIGHT_TEMPLATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIsNotNull() {
            addCriterion("FREIGHT_TEMPLATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdEqualTo(Long value) {
            addCriterion("FREIGHT_TEMPLATE_ID =", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotEqualTo(Long value) {
            addCriterion("FREIGHT_TEMPLATE_ID <>", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdGreaterThan(Long value) {
            addCriterion("FREIGHT_TEMPLATE_ID >", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("FREIGHT_TEMPLATE_ID >=", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdLessThan(Long value) {
            addCriterion("FREIGHT_TEMPLATE_ID <", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("FREIGHT_TEMPLATE_ID <=", value, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdIn(List<Long> values) {
            addCriterion("FREIGHT_TEMPLATE_ID in", values, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotIn(List<Long> values) {
            addCriterion("FREIGHT_TEMPLATE_ID not in", values, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdBetween(Long value1, Long value2) {
            addCriterion("FREIGHT_TEMPLATE_ID between", value1, value2, "freightTemplateId");
            return (Criteria) this;
        }

        public Criteria andFreightTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("FREIGHT_TEMPLATE_ID not between", value1, value2, "freightTemplateId");
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

        public Criteria andTemplateNameIsNull() {
            addCriterion("TEMPLATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNotNull() {
            addCriterion("TEMPLATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameEqualTo(String value) {
            addCriterion("TEMPLATE_NAME =", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotEqualTo(String value) {
            addCriterion("TEMPLATE_NAME <>", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThan(String value) {
            addCriterion("TEMPLATE_NAME >", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_NAME >=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThan(String value) {
            addCriterion("TEMPLATE_NAME <", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_NAME <=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLike(String value) {
            addCriterion("TEMPLATE_NAME like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotLike(String value) {
            addCriterion("TEMPLATE_NAME not like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIn(List<String> values) {
            addCriterion("TEMPLATE_NAME in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotIn(List<String> values) {
            addCriterion("TEMPLATE_NAME not in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameBetween(String value1, String value2) {
            addCriterion("TEMPLATE_NAME between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_NAME not between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andFreeFlagIsNull() {
            addCriterion("FREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFreeFlagIsNotNull() {
            addCriterion("FREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFreeFlagEqualTo(Long value) {
            addCriterion("FREE_FLAG =", value, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagNotEqualTo(Long value) {
            addCriterion("FREE_FLAG <>", value, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagGreaterThan(Long value) {
            addCriterion("FREE_FLAG >", value, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("FREE_FLAG >=", value, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagLessThan(Long value) {
            addCriterion("FREE_FLAG <", value, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagLessThanOrEqualTo(Long value) {
            addCriterion("FREE_FLAG <=", value, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagIn(List<Long> values) {
            addCriterion("FREE_FLAG in", values, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagNotIn(List<Long> values) {
            addCriterion("FREE_FLAG not in", values, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagBetween(Long value1, Long value2) {
            addCriterion("FREE_FLAG between", value1, value2, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andFreeFlagNotBetween(Long value1, Long value2) {
            addCriterion("FREE_FLAG not between", value1, value2, "freeFlag");
            return (Criteria) this;
        }

        public Criteria andChargeRuleIsNull() {
            addCriterion("CHARGE_RULE is null");
            return (Criteria) this;
        }

        public Criteria andChargeRuleIsNotNull() {
            addCriterion("CHARGE_RULE is not null");
            return (Criteria) this;
        }

        public Criteria andChargeRuleEqualTo(Long value) {
            addCriterion("CHARGE_RULE =", value, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleNotEqualTo(Long value) {
            addCriterion("CHARGE_RULE <>", value, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleGreaterThan(Long value) {
            addCriterion("CHARGE_RULE >", value, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleGreaterThanOrEqualTo(Long value) {
            addCriterion("CHARGE_RULE >=", value, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleLessThan(Long value) {
            addCriterion("CHARGE_RULE <", value, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleLessThanOrEqualTo(Long value) {
            addCriterion("CHARGE_RULE <=", value, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleIn(List<Long> values) {
            addCriterion("CHARGE_RULE in", values, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleNotIn(List<Long> values) {
            addCriterion("CHARGE_RULE not in", values, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleBetween(Long value1, Long value2) {
            addCriterion("CHARGE_RULE between", value1, value2, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andChargeRuleNotBetween(Long value1, Long value2) {
            addCriterion("CHARGE_RULE not between", value1, value2, "chargeRule");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNull() {
            addCriterion("TRANSFER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNotNull() {
            addCriterion("TRANSFER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeEqualTo(Long value) {
            addCriterion("TRANSFER_TYPE =", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotEqualTo(Long value) {
            addCriterion("TRANSFER_TYPE <>", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThan(Long value) {
            addCriterion("TRANSFER_TYPE >", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("TRANSFER_TYPE >=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThan(Long value) {
            addCriterion("TRANSFER_TYPE <", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThanOrEqualTo(Long value) {
            addCriterion("TRANSFER_TYPE <=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIn(List<Long> values) {
            addCriterion("TRANSFER_TYPE in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotIn(List<Long> values) {
            addCriterion("TRANSFER_TYPE not in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeBetween(Long value1, Long value2) {
            addCriterion("TRANSFER_TYPE between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotBetween(Long value1, Long value2) {
            addCriterion("TRANSFER_TYPE not between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountIsNull() {
            addCriterion("DEFAULT_PRODUCT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountIsNotNull() {
            addCriterion("DEFAULT_PRODUCT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountEqualTo(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT =", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountNotEqualTo(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT <>", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountGreaterThan(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT >", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountGreaterThanOrEqualTo(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT >=", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountLessThan(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT <", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountLessThanOrEqualTo(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT <=", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountLike(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT like", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountNotLike(String value) {
            addCriterion("DEFAULT_PRODUCT_COUNT not like", value, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountIn(List<String> values) {
            addCriterion("DEFAULT_PRODUCT_COUNT in", values, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountNotIn(List<String> values) {
            addCriterion("DEFAULT_PRODUCT_COUNT not in", values, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountBetween(String value1, String value2) {
            addCriterion("DEFAULT_PRODUCT_COUNT between", value1, value2, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultProductCountNotBetween(String value1, String value2) {
            addCriterion("DEFAULT_PRODUCT_COUNT not between", value1, value2, "defaultProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeIsNull() {
            addCriterion("DEFAULT_CHARGE is null");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeIsNotNull() {
            addCriterion("DEFAULT_CHARGE is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_CHARGE =", value, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeNotEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_CHARGE <>", value, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeGreaterThan(BigDecimal value) {
            addCriterion("DEFAULT_CHARGE >", value, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_CHARGE >=", value, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeLessThan(BigDecimal value) {
            addCriterion("DEFAULT_CHARGE <", value, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_CHARGE <=", value, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeIn(List<BigDecimal> values) {
            addCriterion("DEFAULT_CHARGE in", values, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeNotIn(List<BigDecimal> values) {
            addCriterion("DEFAULT_CHARGE not in", values, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEFAULT_CHARGE between", value1, value2, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEFAULT_CHARGE not between", value1, value2, "defaultCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountIsNull() {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountIsNotNull() {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountEqualTo(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT =", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountNotEqualTo(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT <>", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountGreaterThan(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT >", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountGreaterThanOrEqualTo(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT >=", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountLessThan(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT <", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountLessThanOrEqualTo(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT <=", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountLike(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT like", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountNotLike(String value) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT not like", value, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountIn(List<String> values) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT in", values, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountNotIn(List<String> values) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT not in", values, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountBetween(String value1, String value2) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT between", value1, value2, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductCountNotBetween(String value1, String value2) {
            addCriterion("DEFAULT_ADD_PRODUCT_COUNT not between", value1, value2, "defaultAddProductCount");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeIsNull() {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE is null");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeIsNotNull() {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE =", value, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeNotEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE <>", value, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeGreaterThan(BigDecimal value) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE >", value, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE >=", value, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeLessThan(BigDecimal value) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE <", value, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE <=", value, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeIn(List<BigDecimal> values) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE in", values, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeNotIn(List<BigDecimal> values) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE not in", values, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE between", value1, value2, "defaultAddProductCharge");
            return (Criteria) this;
        }

        public Criteria andDefaultAddProductChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DEFAULT_ADD_PRODUCT_CHARGE not between", value1, value2, "defaultAddProductCharge");
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