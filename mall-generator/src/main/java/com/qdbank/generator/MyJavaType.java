package com.qdbank.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName MyJavaType
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2020/12/11 15:23
 * @Version 1.0
 **/
public class MyJavaType extends JavaTypeResolverDefaultImpl {
    protected List<String> warnings;
    protected Properties properties = new Properties();
    protected Context context;
    protected boolean forceBigDecimals;
    public MyJavaType() {//指定默认类型为Long类型
       super();
        super.typeMap.put(3, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("DECIMAL", new FullyQualifiedJavaType(Long.class.getName())));
        super.typeMap.put(5, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("SMALLINT", new FullyQualifiedJavaType(Long.class.getName())));
    }
    //重写类型转换方法
    @Override
    protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
        FullyQualifiedJavaType answer;
        if (column.getLength() <= 18 && !this.forceBigDecimals) {
            if (column.getLength() > 9) {
                answer = new FullyQualifiedJavaType(Long.class.getName());
            } else if (column.getLength() > 4) {
                answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
            } else {
                answer = new FullyQualifiedJavaType(Long.class.getName());
            }
        } else {
            answer = defaultType;
        }

        return answer;
    }
//    public void addConfigurationProperties(Properties properties) {
//        this.properties.putAll(properties);
//        this.forceBigDecimals = StringUtility.isTrue(properties.getProperty("forceBigDecimals"));
//    }
//
//    public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
//        FullyQualifiedJavaType answer = null;
//        JavaTypeResolverDefaultImpl.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolverDefaultImpl.JdbcTypeInformation)this.typeMap.get(introspectedColumn.getJdbcType());
//        if (jdbcTypeInformation != null) {
//            answer = jdbcTypeInformation.getFullyQualifiedJavaType();
//            answer = this.overrideDefaultType(introspectedColumn, answer);
//        }
//
//        return answer;
//    }
//
//    protected FullyQualifiedJavaType overrideDefaultType(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
//        FullyQualifiedJavaType answer = defaultType;
//        switch(column.getJdbcType()) {
//            case -7:
//                answer = this.calculateBitReplacement(column, defaultType);
//                break;
//            case 2:
//            case 3:
//                answer = this.calculateBigDecimalReplacement(column, defaultType);
//        }
//
//        return answer;
//    }
//
//    protected FullyQualifiedJavaType calculateBitReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
//        FullyQualifiedJavaType answer;
//        if (column.getLength() > 1) {
//            answer = new FullyQualifiedJavaType("byte[]");
//        } else {
//            answer = defaultType;
//        }
//
//        return answer;
//    }
//
//    protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column, FullyQualifiedJavaType defaultType) {
//        FullyQualifiedJavaType answer;
//        if (column.getScale() <= 0 && column.getLength() <= 18 && !this.forceBigDecimals) {
//            if (column.getLength() > 9) {
//                answer = new FullyQualifiedJavaType(Double.class.getName());
//            } else if (column.getLength() > 4) {
//                answer = new FullyQualifiedJavaType(Integer.class.getName());
//            } else {
//                answer = new FullyQualifiedJavaType(Short.class.getName());
//            }
//        } else {
//            answer = defaultType;
//        }
//
//        return answer;
//    }
//
//    public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn) {
//        String answer = null;
//        JavaTypeResolverDefaultImpl.JdbcTypeInformation jdbcTypeInformation = (JavaTypeResolverDefaultImpl.JdbcTypeInformation)this.typeMap.get(introspectedColumn.getJdbcType());
//        if (jdbcTypeInformation != null) {
//            answer = jdbcTypeInformation.getJdbcTypeName();
//        }
//
//        return answer;
//    }
//
//    public void setWarnings(List<String> warnings) {
//        this.warnings = warnings;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    public static class JdbcTypeInformation {
//        private String jdbcTypeName;
//        private FullyQualifiedJavaType fullyQualifiedJavaType;
//
//        public JdbcTypeInformation(String jdbcTypeName, FullyQualifiedJavaType fullyQualifiedJavaType) {
//            this.jdbcTypeName = jdbcTypeName;
//            this.fullyQualifiedJavaType = fullyQualifiedJavaType;
//        }
//
//        public String getJdbcTypeName() {
//            return this.jdbcTypeName;
//        }
//
//        public FullyQualifiedJavaType getFullyQualifiedJavaType() {
//            return this.fullyQualifiedJavaType;
//        }
//    }
}
