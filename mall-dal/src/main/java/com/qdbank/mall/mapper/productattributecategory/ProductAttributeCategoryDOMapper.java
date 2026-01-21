package com.qdbank.mall.mapper.productattributecategory;

import com.qdbank.mall.model.productattributecategory.ProductAttributeCategoryDO;
import com.qdbank.mall.model.productattributecategory.ProductAttributeCategoryDOExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductAttributeCategoryDOMapper {
    long countByExample(ProductAttributeCategoryDOExample example);

    int deleteByExample(ProductAttributeCategoryDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(ProductAttributeCategoryDO record);

    int insertSelective(ProductAttributeCategoryDO record);

    List<ProductAttributeCategoryDO> selectByExample(ProductAttributeCategoryDOExample example);

    ProductAttributeCategoryDO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") ProductAttributeCategoryDO record, @Param("example") ProductAttributeCategoryDOExample example);

    int updateByExample(@Param("record") ProductAttributeCategoryDO record, @Param("example") ProductAttributeCategoryDOExample example);

    int updateByPrimaryKeySelective(ProductAttributeCategoryDO record);

    int updateByPrimaryKey(ProductAttributeCategoryDO record);
}