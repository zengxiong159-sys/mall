package com.qdbank.mall.model.areafreighttemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreafreighttemplateDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AreafreighttemplateDOExample() {
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

        public Criteria andProvinceIdsIsNull() {
            addCriterion("PROVINCE_IDS is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsIsNotNull() {
            addCriterion("PROVINCE_IDS is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsEqualTo(String value) {
            addCriterion("PROVINCE_IDS =", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsNotEqualTo(String value) {
            addCriterion("PROVINCE_IDS <>", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsGreaterThan(String value) {
            addCriterion("PROVINCE_IDS >", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE_IDS >=", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsLessThan(String value) {
            addCriterion("PROVINCE_IDS <", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE_IDS <=", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsLike(String value) {
            addCriterion("PROVINCE_IDS like", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsNotLike(String value) {
            addCriterion("PROVINCE_IDS not like", value, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsIn(List<String> values) {
            addCriterion("PROVINCE_IDS in", values, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsNotIn(List<String> values) {
            addCriterion("PROVINCE_IDS not in", values, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsBetween(String value1, String value2) {
            addCriterion("PROVINCE_IDS between", value1, value2, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andProvinceIdsNotBetween(String value1, String value2) {
            addCriterion("PROVINCE_IDS not between", value1, value2, "provinceIds");
            return (Criteria) this;
        }

        public Criteria andTransferFlagIsNull() {
            addCriterion("TRANSFER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTransferFlagIsNotNull() {
            addCriterion("TRANSFER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTransferFlagEqualTo(Long value) {
            addCriterion("TRANSFER_FLAG =", value, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagNotEqualTo(Long value) {
            addCriterion("TRANSFER_FLAG <>", value, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagGreaterThan(Long value) {
            addCriterion("TRANSFER_FLAG >", value, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("TRANSFER_FLAG >=", value, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagLessThan(Long value) {
            addCriterion("TRANSFER_FLAG <", value, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagLessThanOrEqualTo(Long value) {
            addCriterion("TRANSFER_FLAG <=", value, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagIn(List<Long> values) {
            addCriterion("TRANSFER_FLAG in", values, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagNotIn(List<Long> values) {
            addCriterion("TRANSFER_FLAG not in", values, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagBetween(Long value1, Long value2) {
            addCriterion("TRANSFER_FLAG between", value1, value2, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andTransferFlagNotBetween(Long value1, Long value2) {
            addCriterion("TRANSFER_FLAG not between", value1, value2, "transferFlag");
            return (Criteria) this;
        }

        public Criteria andCountUnitIsNull() {
            addCriterion("COUNT_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andCountUnitIsNotNull() {
            addCriterion("COUNT_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andCountUnitEqualTo(Long value) {
            addCriterion("COUNT_UNIT =", value, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitNotEqualTo(Long value) {
            addCriterion("COUNT_UNIT <>", value, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitGreaterThan(Long value) {
            addCriterion("COUNT_UNIT >", value, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitGreaterThanOrEqualTo(Long value) {
            addCriterion("COUNT_UNIT >=", value, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitLessThan(Long value) {
            addCriterion("COUNT_UNIT <", value, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitLessThanOrEqualTo(Long value) {
            addCriterion("COUNT_UNIT <=", value, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitIn(List<Long> values) {
            addCriterion("COUNT_UNIT in", values, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitNotIn(List<Long> values) {
            addCriterion("COUNT_UNIT not in", values, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitBetween(Long value1, Long value2) {
            addCriterion("COUNT_UNIT between", value1, value2, "countUnit");
            return (Criteria) this;
        }

        public Criteria andCountUnitNotBetween(Long value1, Long value2) {
            addCriterion("COUNT_UNIT not between", value1, value2, "countUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitIsNull() {
            addCriterion("CHARGE_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andChargeUnitIsNotNull() {
            addCriterion("CHARGE_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andChargeUnitEqualTo(BigDecimal value) {
            addCriterion("CHARGE_UNIT =", value, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitNotEqualTo(BigDecimal value) {
            addCriterion("CHARGE_UNIT <>", value, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitGreaterThan(BigDecimal value) {
            addCriterion("CHARGE_UNIT >", value, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CHARGE_UNIT >=", value, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitLessThan(BigDecimal value) {
            addCriterion("CHARGE_UNIT <", value, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CHARGE_UNIT <=", value, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitIn(List<BigDecimal> values) {
            addCriterion("CHARGE_UNIT in", values, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitNotIn(List<BigDecimal> values) {
            addCriterion("CHARGE_UNIT not in", values, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHARGE_UNIT between", value1, value2, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andChargeUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CHARGE_UNIT not between", value1, value2, "chargeUnit");
            return (Criteria) this;
        }

        public Criteria andAddCountIsNull() {
            addCriterion("ADD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAddCountIsNotNull() {
            addCriterion("ADD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAddCountEqualTo(Long value) {
            addCriterion("ADD_COUNT =", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountNotEqualTo(Long value) {
            addCriterion("ADD_COUNT <>", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountGreaterThan(Long value) {
            addCriterion("ADD_COUNT >", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountGreaterThanOrEqualTo(Long value) {
            addCriterion("ADD_COUNT >=", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountLessThan(Long value) {
            addCriterion("ADD_COUNT <", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountLessThanOrEqualTo(Long value) {
            addCriterion("ADD_COUNT <=", value, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountIn(List<Long> values) {
            addCriterion("ADD_COUNT in", values, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountNotIn(List<Long> values) {
            addCriterion("ADD_COUNT not in", values, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountBetween(Long value1, Long value2) {
            addCriterion("ADD_COUNT between", value1, value2, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddCountNotBetween(Long value1, Long value2) {
            addCriterion("ADD_COUNT not between", value1, value2, "addCount");
            return (Criteria) this;
        }

        public Criteria andAddChargeIsNull() {
            addCriterion("ADD_CHARGE is null");
            return (Criteria) this;
        }

        public Criteria andAddChargeIsNotNull() {
            addCriterion("ADD_CHARGE is not null");
            return (Criteria) this;
        }

        public Criteria andAddChargeEqualTo(BigDecimal value) {
            addCriterion("ADD_CHARGE =", value, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeNotEqualTo(BigDecimal value) {
            addCriterion("ADD_CHARGE <>", value, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeGreaterThan(BigDecimal value) {
            addCriterion("ADD_CHARGE >", value, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ADD_CHARGE >=", value, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeLessThan(BigDecimal value) {
            addCriterion("ADD_CHARGE <", value, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ADD_CHARGE <=", value, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeIn(List<BigDecimal> values) {
            addCriterion("ADD_CHARGE in", values, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeNotIn(List<BigDecimal> values) {
            addCriterion("ADD_CHARGE not in", values, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADD_CHARGE between", value1, value2, "addCharge");
            return (Criteria) this;
        }

        public Criteria andAddChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADD_CHARGE not between", value1, value2, "addCharge");
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