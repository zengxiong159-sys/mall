package com.qdbank.mall.mapper.freighttemplate;

import com.qdbank.mall.model.areafreighttemplate.AreaFreightTemplateDetailDO;
import com.qdbank.mall.model.freighttemplate.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FreighttemplateDOMapper {
    long countByExample(FreighttemplateDOExample example);

    int deleteByExample(FreighttemplateDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FreighttemplateDO record);

    int insertSelective(FreighttemplateDO record);

    List<String> selectByMerchantNo(FreighttemplateDO freighttemplateDO);

    List<FreighttemplateDO> selectIdAndNameByMerchantNo(FreighttemplateDO freighttemplateDO);

    List<FreighttemplateDO> selectByExample(FreighttemplateDOExample example);

    List<FreighttemplateDO> selectAllByStatus(Long merchantNo);

    List<FreighttemplateListDO> selectAllByMerchanrNo(FreighttemplateDO freighttemplateDO);

    List<FreighttemplateDO> selectListByMerchanrNo(FreighttemplateDO freighttemplateDO);

    FreighttemplateDO selectByPrimaryKey(@Param("freightTemplateId") Long id,@Param("merchantNo") Long merchantNo);

    FreighttemplateListDO selectByIdAndMerchantNo(AreaFreightTemplateDetailDO areaFreightTemplateDetail);

    int updateByExampleSelective(@Param("record") FreighttemplateDO record, @Param("example") FreighttemplateDOExample example);

    int updateByExample(@Param("record") FreighttemplateDO record, @Param("example") FreighttemplateDOExample example);

    int updateByPrimaryKeySelective(FreighttemplateDO record);

    int updateByPrimaryKey(FreighttemplateDO record);
}