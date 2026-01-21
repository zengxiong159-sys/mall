package com.qdbank.mall.mapper.areafreighttemplate;

import com.qdbank.mall.model.areafreighttemplate.AreafreighttemplateDO;
import com.qdbank.mall.model.areafreighttemplate.AreafreighttemplateDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface AreafreighttemplateDOMapper {
    long countByExample(AreafreighttemplateDOExample example);

    int deleteByExample(AreafreighttemplateDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AreafreighttemplateDO record);

    void insertBatch(List<AreafreighttemplateDO> list);

    void updateBatch(List<AreafreighttemplateDO> list);

    int insertSelective(AreafreighttemplateDO record);

    List<AreafreighttemplateDO> selectByExample(AreafreighttemplateDOExample example);

    List<AreafreighttemplateDO> selectByFreightTemplateId(Long freightTemplateId);

    AreafreighttemplateDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AreafreighttemplateDO record, @Param("example") AreafreighttemplateDOExample example);

    int updateByExample(@Param("record") AreafreighttemplateDO record, @Param("example") AreafreighttemplateDOExample example);

    int updateByPrimaryKeySelective(AreafreighttemplateDO record);

    int updateByPrimaryKey(AreafreighttemplateDO record);

    /**
     * 根据主键id批量删除数据
     *
     * @param areaFreightTemplateIds 主键id列表
     */
    void deleteBatch(@Param("areaFreightTemplateIds") Set<Long> areaFreightTemplateIds);
}