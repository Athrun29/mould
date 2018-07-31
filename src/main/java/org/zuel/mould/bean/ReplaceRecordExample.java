package org.zuel.mould.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReplaceRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReplaceRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSrcNameIsNull() {
            addCriterion("src_name is null");
            return (Criteria) this;
        }

        public Criteria andSrcNameIsNotNull() {
            addCriterion("src_name is not null");
            return (Criteria) this;
        }

        public Criteria andSrcNameEqualTo(String value) {
            addCriterion("src_name =", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameNotEqualTo(String value) {
            addCriterion("src_name <>", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameGreaterThan(String value) {
            addCriterion("src_name >", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameGreaterThanOrEqualTo(String value) {
            addCriterion("src_name >=", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameLessThan(String value) {
            addCriterion("src_name <", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameLessThanOrEqualTo(String value) {
            addCriterion("src_name <=", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameLike(String value) {
            addCriterion("src_name like", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameNotLike(String value) {
            addCriterion("src_name not like", value, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameIn(List<String> values) {
            addCriterion("src_name in", values, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameNotIn(List<String> values) {
            addCriterion("src_name not in", values, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameBetween(String value1, String value2) {
            addCriterion("src_name between", value1, value2, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcNameNotBetween(String value1, String value2) {
            addCriterion("src_name not between", value1, value2, "srcName");
            return (Criteria) this;
        }

        public Criteria andSrcDiaIsNull() {
            addCriterion("src_dia is null");
            return (Criteria) this;
        }

        public Criteria andSrcDiaIsNotNull() {
            addCriterion("src_dia is not null");
            return (Criteria) this;
        }

        public Criteria andSrcDiaEqualTo(Double value) {
            addCriterion("src_dia =", value, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaNotEqualTo(Double value) {
            addCriterion("src_dia <>", value, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaGreaterThan(Double value) {
            addCriterion("src_dia >", value, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaGreaterThanOrEqualTo(Double value) {
            addCriterion("src_dia >=", value, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaLessThan(Double value) {
            addCriterion("src_dia <", value, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaLessThanOrEqualTo(Double value) {
            addCriterion("src_dia <=", value, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaIn(List<Double> values) {
            addCriterion("src_dia in", values, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaNotIn(List<Double> values) {
            addCriterion("src_dia not in", values, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaBetween(Double value1, Double value2) {
            addCriterion("src_dia between", value1, value2, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcDiaNotBetween(Double value1, Double value2) {
            addCriterion("src_dia not between", value1, value2, "srcDia");
            return (Criteria) this;
        }

        public Criteria andSrcRadIsNull() {
            addCriterion("src_rad is null");
            return (Criteria) this;
        }

        public Criteria andSrcRadIsNotNull() {
            addCriterion("src_rad is not null");
            return (Criteria) this;
        }

        public Criteria andSrcRadEqualTo(Double value) {
            addCriterion("src_rad =", value, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadNotEqualTo(Double value) {
            addCriterion("src_rad <>", value, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadGreaterThan(Double value) {
            addCriterion("src_rad >", value, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadGreaterThanOrEqualTo(Double value) {
            addCriterion("src_rad >=", value, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadLessThan(Double value) {
            addCriterion("src_rad <", value, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadLessThanOrEqualTo(Double value) {
            addCriterion("src_rad <=", value, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadIn(List<Double> values) {
            addCriterion("src_rad in", values, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadNotIn(List<Double> values) {
            addCriterion("src_rad not in", values, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadBetween(Double value1, Double value2) {
            addCriterion("src_rad between", value1, value2, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcRadNotBetween(Double value1, Double value2) {
            addCriterion("src_rad not between", value1, value2, "srcRad");
            return (Criteria) this;
        }

        public Criteria andSrcLenIsNull() {
            addCriterion("src_len is null");
            return (Criteria) this;
        }

        public Criteria andSrcLenIsNotNull() {
            addCriterion("src_len is not null");
            return (Criteria) this;
        }

        public Criteria andSrcLenEqualTo(Double value) {
            addCriterion("src_len =", value, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenNotEqualTo(Double value) {
            addCriterion("src_len <>", value, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenGreaterThan(Double value) {
            addCriterion("src_len >", value, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenGreaterThanOrEqualTo(Double value) {
            addCriterion("src_len >=", value, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenLessThan(Double value) {
            addCriterion("src_len <", value, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenLessThanOrEqualTo(Double value) {
            addCriterion("src_len <=", value, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenIn(List<Double> values) {
            addCriterion("src_len in", values, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenNotIn(List<Double> values) {
            addCriterion("src_len not in", values, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenBetween(Double value1, Double value2) {
            addCriterion("src_len between", value1, value2, "srcLen");
            return (Criteria) this;
        }

        public Criteria andSrcLenNotBetween(Double value1, Double value2) {
            addCriterion("src_len not between", value1, value2, "srcLen");
            return (Criteria) this;
        }

        public Criteria andTarNameIsNull() {
            addCriterion("tar_name is null");
            return (Criteria) this;
        }

        public Criteria andTarNameIsNotNull() {
            addCriterion("tar_name is not null");
            return (Criteria) this;
        }

        public Criteria andTarNameEqualTo(String value) {
            addCriterion("tar_name =", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameNotEqualTo(String value) {
            addCriterion("tar_name <>", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameGreaterThan(String value) {
            addCriterion("tar_name >", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameGreaterThanOrEqualTo(String value) {
            addCriterion("tar_name >=", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameLessThan(String value) {
            addCriterion("tar_name <", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameLessThanOrEqualTo(String value) {
            addCriterion("tar_name <=", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameLike(String value) {
            addCriterion("tar_name like", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameNotLike(String value) {
            addCriterion("tar_name not like", value, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameIn(List<String> values) {
            addCriterion("tar_name in", values, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameNotIn(List<String> values) {
            addCriterion("tar_name not in", values, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameBetween(String value1, String value2) {
            addCriterion("tar_name between", value1, value2, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarNameNotBetween(String value1, String value2) {
            addCriterion("tar_name not between", value1, value2, "tarName");
            return (Criteria) this;
        }

        public Criteria andTarDiaIsNull() {
            addCriterion("tar_dia is null");
            return (Criteria) this;
        }

        public Criteria andTarDiaIsNotNull() {
            addCriterion("tar_dia is not null");
            return (Criteria) this;
        }

        public Criteria andTarDiaEqualTo(Double value) {
            addCriterion("tar_dia =", value, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaNotEqualTo(Double value) {
            addCriterion("tar_dia <>", value, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaGreaterThan(Double value) {
            addCriterion("tar_dia >", value, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaGreaterThanOrEqualTo(Double value) {
            addCriterion("tar_dia >=", value, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaLessThan(Double value) {
            addCriterion("tar_dia <", value, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaLessThanOrEqualTo(Double value) {
            addCriterion("tar_dia <=", value, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaIn(List<Double> values) {
            addCriterion("tar_dia in", values, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaNotIn(List<Double> values) {
            addCriterion("tar_dia not in", values, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaBetween(Double value1, Double value2) {
            addCriterion("tar_dia between", value1, value2, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarDiaNotBetween(Double value1, Double value2) {
            addCriterion("tar_dia not between", value1, value2, "tarDia");
            return (Criteria) this;
        }

        public Criteria andTarRadIsNull() {
            addCriterion("tar_rad is null");
            return (Criteria) this;
        }

        public Criteria andTarRadIsNotNull() {
            addCriterion("tar_rad is not null");
            return (Criteria) this;
        }

        public Criteria andTarRadEqualTo(Double value) {
            addCriterion("tar_rad =", value, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadNotEqualTo(Double value) {
            addCriterion("tar_rad <>", value, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadGreaterThan(Double value) {
            addCriterion("tar_rad >", value, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadGreaterThanOrEqualTo(Double value) {
            addCriterion("tar_rad >=", value, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadLessThan(Double value) {
            addCriterion("tar_rad <", value, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadLessThanOrEqualTo(Double value) {
            addCriterion("tar_rad <=", value, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadIn(List<Double> values) {
            addCriterion("tar_rad in", values, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadNotIn(List<Double> values) {
            addCriterion("tar_rad not in", values, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadBetween(Double value1, Double value2) {
            addCriterion("tar_rad between", value1, value2, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarRadNotBetween(Double value1, Double value2) {
            addCriterion("tar_rad not between", value1, value2, "tarRad");
            return (Criteria) this;
        }

        public Criteria andTarLenIsNull() {
            addCriterion("tar_len is null");
            return (Criteria) this;
        }

        public Criteria andTarLenIsNotNull() {
            addCriterion("tar_len is not null");
            return (Criteria) this;
        }

        public Criteria andTarLenEqualTo(Double value) {
            addCriterion("tar_len =", value, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenNotEqualTo(Double value) {
            addCriterion("tar_len <>", value, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenGreaterThan(Double value) {
            addCriterion("tar_len >", value, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenGreaterThanOrEqualTo(Double value) {
            addCriterion("tar_len >=", value, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenLessThan(Double value) {
            addCriterion("tar_len <", value, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenLessThanOrEqualTo(Double value) {
            addCriterion("tar_len <=", value, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenIn(List<Double> values) {
            addCriterion("tar_len in", values, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenNotIn(List<Double> values) {
            addCriterion("tar_len not in", values, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenBetween(Double value1, Double value2) {
            addCriterion("tar_len between", value1, value2, "tarLen");
            return (Criteria) this;
        }

        public Criteria andTarLenNotBetween(Double value1, Double value2) {
            addCriterion("tar_len not between", value1, value2, "tarLen");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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