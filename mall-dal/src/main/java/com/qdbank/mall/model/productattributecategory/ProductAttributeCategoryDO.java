package com.qdbank.mall.model.productattributecategory;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class ProductAttributeCategoryDO implements Serializable {
    private Long id;

    private String name;

    private Long attributeCount;

    private Long paramCount;

    private Date createTime;

    private Date updateTime;

    private String createdBy;

    private String updatedBy;

    private static final long serialVersionUID = 1L;
}