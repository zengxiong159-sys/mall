package com.qdbank.mall.mapper.mobileSku;

import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.mobileSku.MobileSkuDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MobileSkuDOMapper {
    long countByExample(MobileSkuDOExample example);

    int deleteByExample(MobileSkuDOExample example);

    int deleteByPrimaryKey(Long mobileSkuId);

    int insert(MobileSkuDO record);

    int insertSelective(MobileSkuDO record);

    List<MobileSkuDO> selectByExample(MobileSkuDOExample example);

    MobileSkuDO selectByPrimaryKey(Long mobileSkuId);

    int updateByExampleSelective(@Param("record") MobileSkuDO record, @Param("example") MobileSkuDOExample example);

    int updateByExample(@Param("record") MobileSkuDO record, @Param("example") MobileSkuDOExample example);

    int updateByPrimaryKeySelective(MobileSkuDO record);

    int updateByPrimaryKey(MobileSkuDO record);
}