package com.qdbank.mall.mapper.role;

import com.qdbank.mall.model.role.UmsRoleResourceRelationDO;
import com.qdbank.mall.model.role.UmsRoleResourceRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UmsRoleResourceRelationMapper {
    long countByExample(UmsRoleResourceRelationExample example);

    int deleteByExample(UmsRoleResourceRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRoleResourceRelationDO record);

    int insertSelective(UmsRoleResourceRelationDO record);

    List<UmsRoleResourceRelationDO> selectByExample(UmsRoleResourceRelationExample example);

    UmsRoleResourceRelationDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRoleResourceRelationDO record, @Param("example") UmsRoleResourceRelationExample example);

    int updateByExample(@Param("record") UmsRoleResourceRelationDO record, @Param("example") UmsRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(UmsRoleResourceRelationDO record);

    int updateByPrimaryKey(UmsRoleResourceRelationDO record);
}