package com.qdbank.mall.mapper.mobileSkuProvince;

import com.qdbank.mall.model.mobileSkuProvince.MobileSkuProvinceDO;
import com.qdbank.mall.model.mobileSkuProvince.MobileSkuProvinceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MobileSkuProvinceDOMapper {
    long countByExample(MobileSkuProvinceDOExample example);

    int deleteByExample(MobileSkuProvinceDOExample example);

    int deleteByPrimaryKey(Long mobileSkuProvinceId);

    int insert(MobileSkuProvinceDO record);

    int insertSelective(MobileSkuProvinceDO record);

    List<MobileSkuProvinceDO> selectByExample(MobileSkuProvinceDOExample example);

    MobileSkuProvinceDO selectByPrimaryKey(Long mobileSkuProvinceId);

    int updateByExampleSelective(@Param("record") MobileSkuProvinceDO record, @Param("example") MobileSkuProvinceDOExample example);

    int updateByExample(@Param("record") MobileSkuProvinceDO record, @Param("example") MobileSkuProvinceDOExample example);

    int updateByPrimaryKeySelective(MobileSkuProvinceDO record);

    int updateByPrimaryKey(MobileSkuProvinceDO record);
}