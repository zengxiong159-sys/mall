package com.qdbank.mall.mapper.merchant;

import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.merchant.MerchantDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MerchantDOMapper {
    long countByExample(MerchantDOExample example);

    int deleteByExample(MerchantDOExample example);

    int deleteByPrimaryKey(Long merchantNo);

    int insert(MerchantDO record);

    int insertSelective(MerchantDO record);

    List<MerchantDO> selectByExample(MerchantDOExample example);

    MerchantDO selectByPrimaryKey(Long merchantNo);

    MerchantDO selectByEmail(String email);

    int updateByExampleSelective(@Param("record") MerchantDO record, @Param("example") MerchantDOExample example);

    int updateByExample(@Param("record") MerchantDO record, @Param("example") MerchantDOExample example);

    int updateByPrimaryKeySelective(MerchantDO record);

    int updateByPrimaryKey(MerchantDO record);

    /**
     * 根据商户邮箱查询上商户信息,忽略是否启用状态
     * @param email 邮箱
     * @return  商户信息
     */
    MerchantDO selectByEmailIgnoreStatus(String email);

    int updateStatusByPrimaryKey(MerchantDO record);
}