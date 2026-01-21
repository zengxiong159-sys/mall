package com.qdbank.mall.mapper.cmsrole;


import com.qdbank.mall.model.role.UmsRoleMenuRelationDO;
import com.qdbank.mall.model.role.UmsRoleMenuRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsRoleMenuRelationDOMapper {
    long countByExample(UmsRoleMenuRelationExample example);

    int deleteByExample(UmsRoleMenuRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRoleMenuRelationDO record);

    int insertSelective(UmsRoleMenuRelationDO record);

    List<UmsRoleMenuRelationDO> selectByExample(UmsRoleMenuRelationExample example);

    UmsRoleMenuRelationDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRoleMenuRelationDO record, @Param("example") UmsRoleMenuRelationExample example);

    int updateByExample(@Param("record") UmsRoleMenuRelationDO record, @Param("example") UmsRoleMenuRelationExample example);

    int updateByPrimaryKeySelective(UmsRoleMenuRelationDO record);

    int updateByPrimaryKey(UmsRoleMenuRelationDO record);
}