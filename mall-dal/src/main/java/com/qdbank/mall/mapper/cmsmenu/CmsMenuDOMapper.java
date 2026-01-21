package com.qdbank.mall.mapper.cmsmenu;

import com.qdbank.mall.model.menu.UmsMenuDO;
import com.qdbank.mall.model.menu.UmsMenuDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsMenuDOMapper {
    long countByExample(UmsMenuDOExample example);

    int deleteByExample(UmsMenuDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMenuDO record);

    int insertSelective(UmsMenuDO record);

    List<UmsMenuDO> selectByExample(UmsMenuDOExample example);

    UmsMenuDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMenuDO record, @Param("example") UmsMenuDOExample example);

    int updateByExample(@Param("record") UmsMenuDO record, @Param("example") UmsMenuDOExample example);

    int updateByPrimaryKeySelective(UmsMenuDO record);

    int updateByPrimaryKey(UmsMenuDO record);
}