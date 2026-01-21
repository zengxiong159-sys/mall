package com.qdbank.mall.mapper.resourcecategory;

import com.qdbank.mall.model.resourcecategory.UmsResourceCategoryDO;
import com.qdbank.mall.model.resource.UmsResourceCategoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UmsResourceCategoryMapper {
    long countByExample(UmsResourceCategoryExample example);

    int deleteByExample(UmsResourceCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsResourceCategoryDO record);

    int insertSelective(UmsResourceCategoryDO record);

    List<UmsResourceCategoryDO> selectByExample(UmsResourceCategoryExample example);

    UmsResourceCategoryDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsResourceCategoryDO record, @Param("example") UmsResourceCategoryExample example);

    int updateByExample(@Param("record") UmsResourceCategoryDO record, @Param("example") UmsResourceCategoryExample example);

    int updateByPrimaryKeySelective(UmsResourceCategoryDO record);

    int updateByPrimaryKey(UmsResourceCategoryDO record);
}