package com.qdbank.mall.mapper.productattribute;

import com.qdbank.mall.model.productattribute.ProductAttributeDO;
import com.qdbank.mall.model.productattribute.ProductAttributeDOExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductAttributeDOMapper {
    long countByExample(ProductAttributeDOExample example);

    int deleteByExample(ProductAttributeDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(ProductAttributeDO record);

    int insertSelective(ProductAttributeDO record);

    List<ProductAttributeDO> selectByExample(ProductAttributeDOExample example);

    ProductAttributeDO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") ProductAttributeDO record, @Param("example") ProductAttributeDOExample example);

    int updateByExample(@Param("record") ProductAttributeDO record, @Param("example") ProductAttributeDOExample example);

    int updateByPrimaryKeySelective(ProductAttributeDO record);

    int updateByPrimaryKey(ProductAttributeDO record);
}