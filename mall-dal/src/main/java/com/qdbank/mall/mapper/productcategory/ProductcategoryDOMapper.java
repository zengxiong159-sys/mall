package com.qdbank.mall.mapper.productcategory;

import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.productcategory.ProductcategoryDO;
import com.qdbank.mall.model.productcategory.ProductcategoryDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductcategoryDOMapper {
    long countByExample(ProductcategoryDOExample example);

    int deleteByExample(ProductcategoryDOExample example);
    int deleteByPrimaryKey(@Param("id") Long id);
    int deleteByParentId(@Param("parentId") Long parentId);
    int insert(ProductcategoryDO record);
    ProductcategoryDO selectByPrimaryKey(Long id);
    int insertSelective(ProductcategoryDO record);

    List<ProductcategoryDO> selectByExample(ProductcategoryDOExample example);

    int updateByExampleSelective(@Param("record") ProductcategoryDO record, @Param("example") ProductcategoryDOExample example);

    int updateByExample(@Param("record") ProductcategoryDO record, @Param("example") ProductcategoryDOExample example);

}