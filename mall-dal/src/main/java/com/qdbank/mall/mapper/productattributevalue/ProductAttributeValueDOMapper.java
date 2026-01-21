package com.qdbank.mall.mapper.productattributevalue;

import com.qdbank.mall.model.productattributevalue.ProductAttributeValueDO;
import com.qdbank.mall.model.productattributevalue.ProductAttributeValueDOExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductAttributeValueDOMapper {
    long countByExample(ProductAttributeValueDOExample example);

    int deleteByExample(ProductAttributeValueDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(ProductAttributeValueDO record);

    int insertSelective(ProductAttributeValueDO record);

    List<ProductAttributeValueDO> selectByExample(ProductAttributeValueDOExample example);

    ProductAttributeValueDO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") ProductAttributeValueDO record, @Param("example") ProductAttributeValueDOExample example);

    int updateByExample(@Param("record") ProductAttributeValueDO record, @Param("example") ProductAttributeValueDOExample example);

    int updateByPrimaryKeySelective(ProductAttributeValueDO record);

    int updateByPrimaryKey(ProductAttributeValueDO record);

    /**
     * 批量创建
     */
    int insertList(@Param("list")List<ProductAttributeValueDO> productAttributeValueList);
}