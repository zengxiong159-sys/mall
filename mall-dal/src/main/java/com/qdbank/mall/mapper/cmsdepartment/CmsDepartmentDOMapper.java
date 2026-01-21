package com.qdbank.mall.mapper.cmsdepartment;

import com.qdbank.mall.model.department.UmsDepartmentDO;
import com.qdbank.mall.model.department.UmsDepartmentDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsDepartmentDOMapper {
    long countByExample(UmsDepartmentDOExample example);

    int deleteByExample(UmsDepartmentDOExample example);

    int insert(UmsDepartmentDO record);

    int insertSelective(UmsDepartmentDO record);

    List<UmsDepartmentDO> selectByExample(UmsDepartmentDOExample example);

    int updateByExampleSelective(@Param("record") UmsDepartmentDO record, @Param("example") UmsDepartmentDOExample example);

    int updateByExample(@Param("record") UmsDepartmentDO record, @Param("example") UmsDepartmentDOExample example);
    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    UmsDepartmentDO selectDepartmentById(@Param("id") Long id);

}