package com.qdbank.mall.mapper.cmsresource;


import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.model.user.UmsResourceDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsResourceDOMapper {
    long countByExample(UmsResourceDOExample example);

    int deleteByExample(UmsResourceDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsResourceDO record);

    int insertSelective(UmsResourceDO record);

    List<UmsResourceDO> selectByExample(UmsResourceDOExample example);

    UmsResourceDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsResourceDO record, @Param("example") UmsResourceDOExample example);

    int updateByExample(@Param("record") UmsResourceDO record, @Param("example") UmsResourceDOExample example);

    int updateByPrimaryKeySelective(UmsResourceDO record);

    int updateByPrimaryKey(UmsResourceDO record);
}