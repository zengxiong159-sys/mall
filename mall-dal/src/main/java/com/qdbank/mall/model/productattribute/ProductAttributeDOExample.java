package com.qdbank.mall.model.productattribute;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductAttributeDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductAttributeDOExample() {
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

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdIsNull() {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdIsNotNull() {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID =", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdNotEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID <>", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdGreaterThan(BigDecimal value) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID >", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID >=", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdLessThan(BigDecimal value) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID <", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID <=", value, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID in", values, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdNotIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID not in", values, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID between", value1, value2, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductAttributeCategoryIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_ATTRIBUTE_CATEGORY_ID not between", value1, value2, "productAttributeCategoryId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSelectTypeIsNull() {
            addCriterion("SELECT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSelectTypeIsNotNull() {
            addCriterion("SELECT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSelectTypeEqualTo(Short value) {
            addCriterion("SELECT_TYPE =", value, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeNotEqualTo(Short value) {
            addCriterion("SELECT_TYPE <>", value, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeGreaterThan(Short value) {
            addCriterion("SELECT_TYPE >", value, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("SELECT_TYPE >=", value, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeLessThan(Short value) {
            addCriterion("SELECT_TYPE <", value, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeLessThanOrEqualTo(Short value) {
            addCriterion("SELECT_TYPE <=", value, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeIn(List<Short> values) {
            addCriterion("SELECT_TYPE in", values, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeNotIn(List<Short> values) {
            addCriterion("SELECT_TYPE not in", values, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeBetween(Short value1, Short value2) {
            addCriterion("SELECT_TYPE between", value1, value2, "selectType");
            return (Criteria) this;
        }

        public Criteria andSelectTypeNotBetween(Short value1, Short value2) {
            addCriterion("SELECT_TYPE not between", value1, value2, "selectType");
            return (Criteria) this;
        }

        public Criteria andInputTypeIsNull() {
            addCriterion("INPUT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInputTypeIsNotNull() {
            addCriterion("INPUT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInputTypeEqualTo(Short value) {
            addCriterion("INPUT_TYPE =", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotEqualTo(Short value) {
            addCriterion("INPUT_TYPE <>", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThan(Short value) {
            addCriterion("INPUT_TYPE >", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("INPUT_TYPE >=", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThan(Short value) {
            addCriterion("INPUT_TYPE <", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThanOrEqualTo(Short value) {
            addCriterion("INPUT_TYPE <=", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeIn(List<Short> values) {
            addCriterion("INPUT_TYPE in", values, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotIn(List<Short> values) {
            addCriterion("INPUT_TYPE not in", values, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeBetween(Short value1, Short value2) {
            addCriterion("INPUT_TYPE between", value1, value2, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotBetween(Short value1, Short value2) {
            addCriterion("INPUT_TYPE not between", value1, value2, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputListIsNull() {
            addCriterion("INPUT_LIST is null");
            return (Criteria) this;
        }

        public Criteria andInputListIsNotNull() {
            addCriterion("INPUT_LIST is not null");
            return (Criteria) this;
        }

        public Criteria andInputListEqualTo(String value) {
            addCriterion("INPUT_LIST =", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListNotEqualTo(String value) {
            addCriterion("INPUT_LIST <>", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListGreaterThan(String value) {
            addCriterion("INPUT_LIST >", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_LIST >=", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListLessThan(String value) {
            addCriterion("INPUT_LIST <", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListLessThanOrEqualTo(String value) {
            addCriterion("INPUT_LIST <=", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListLike(String value) {
            addCriterion("INPUT_LIST like", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListNotLike(String value) {
            addCriterion("INPUT_LIST not like", value, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListIn(List<String> values) {
            addCriterion("INPUT_LIST in", values, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListNotIn(List<String> values) {
            addCriterion("INPUT_LIST not in", values, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListBetween(String value1, String value2) {
            addCriterion("INPUT_LIST between", value1, value2, "inputList");
            return (Criteria) this;
        }

        public Criteria andInputListNotBetween(String value1, String value2) {
            addCriterion("INPUT_LIST not between", value1, value2, "inputList");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Long value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Long value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Long value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Long value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Long value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Long value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Long> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Long> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Long value1, Long value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Long value1, Long value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andFilterTypeIsNull() {
            addCriterion("FILTER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFilterTypeIsNotNull() {
            addCriterion("FILTER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFilterTypeEqualTo(Short value) {
            addCriterion("FILTER_TYPE =", value, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeNotEqualTo(Short value) {
            addCriterion("FILTER_TYPE <>", value, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeGreaterThan(Short value) {
            addCriterion("FILTER_TYPE >", value, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("FILTER_TYPE >=", value, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeLessThan(Short value) {
            addCriterion("FILTER_TYPE <", value, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeLessThanOrEqualTo(Short value) {
            addCriterion("FILTER_TYPE <=", value, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeIn(List<Short> values) {
            addCriterion("FILTER_TYPE in", values, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeNotIn(List<Short> values) {
            addCriterion("FILTER_TYPE not in", values, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeBetween(Short value1, Short value2) {
            addCriterion("FILTER_TYPE between", value1, value2, "filterType");
            return (Criteria) this;
        }

        public Criteria andFilterTypeNotBetween(Short value1, Short value2) {
            addCriterion("FILTER_TYPE not between", value1, value2, "filterType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeIsNull() {
            addCriterion("SEARCH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSearchTypeIsNotNull() {
            addCriterion("SEARCH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSearchTypeEqualTo(Short value) {
            addCriterion("SEARCH_TYPE =", value, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeNotEqualTo(Short value) {
            addCriterion("SEARCH_TYPE <>", value, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeGreaterThan(Short value) {
            addCriterion("SEARCH_TYPE >", value, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("SEARCH_TYPE >=", value, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeLessThan(Short value) {
            addCriterion("SEARCH_TYPE <", value, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeLessThanOrEqualTo(Short value) {
            addCriterion("SEARCH_TYPE <=", value, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeIn(List<Short> values) {
            addCriterion("SEARCH_TYPE in", values, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeNotIn(List<Short> values) {
            addCriterion("SEARCH_TYPE not in", values, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeBetween(Short value1, Short value2) {
            addCriterion("SEARCH_TYPE between", value1, value2, "searchType");
            return (Criteria) this;
        }

        public Criteria andSearchTypeNotBetween(Short value1, Short value2) {
            addCriterion("SEARCH_TYPE not between", value1, value2, "searchType");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusIsNull() {
            addCriterion("RELATED_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusIsNotNull() {
            addCriterion("RELATED_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusEqualTo(Short value) {
            addCriterion("RELATED_STATUS =", value, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusNotEqualTo(Short value) {
            addCriterion("RELATED_STATUS <>", value, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusGreaterThan(Short value) {
            addCriterion("RELATED_STATUS >", value, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("RELATED_STATUS >=", value, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusLessThan(Short value) {
            addCriterion("RELATED_STATUS <", value, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusLessThanOrEqualTo(Short value) {
            addCriterion("RELATED_STATUS <=", value, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusIn(List<Short> values) {
            addCriterion("RELATED_STATUS in", values, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusNotIn(List<Short> values) {
            addCriterion("RELATED_STATUS not in", values, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusBetween(Short value1, Short value2) {
            addCriterion("RELATED_STATUS between", value1, value2, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andRelatedStatusNotBetween(Short value1, Short value2) {
            addCriterion("RELATED_STATUS not between", value1, value2, "relatedStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusIsNull() {
            addCriterion("HAND_ADD_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusIsNotNull() {
            addCriterion("HAND_ADD_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusEqualTo(Short value) {
            addCriterion("HAND_ADD_STATUS =", value, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusNotEqualTo(Short value) {
            addCriterion("HAND_ADD_STATUS <>", value, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusGreaterThan(Short value) {
            addCriterion("HAND_ADD_STATUS >", value, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("HAND_ADD_STATUS >=", value, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusLessThan(Short value) {
            addCriterion("HAND_ADD_STATUS <", value, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusLessThanOrEqualTo(Short value) {
            addCriterion("HAND_ADD_STATUS <=", value, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusIn(List<Short> values) {
            addCriterion("HAND_ADD_STATUS in", values, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusNotIn(List<Short> values) {
            addCriterion("HAND_ADD_STATUS not in", values, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusBetween(Short value1, Short value2) {
            addCriterion("HAND_ADD_STATUS between", value1, value2, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andHandAddStatusNotBetween(Short value1, Short value2) {
            addCriterion("HAND_ADD_STATUS not between", value1, value2, "handAddStatus");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("type not between", value1, value2, "type");
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