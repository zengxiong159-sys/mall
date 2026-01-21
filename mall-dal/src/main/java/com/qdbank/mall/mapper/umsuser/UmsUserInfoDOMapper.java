package com.qdbank.mall.mapper.umsuser;

import com.qdbank.mall.model.umsuser.UmsUserInfoDO;
import com.qdbank.mall.model.umsuser.UmsUserInfoDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface UmsUserInfoDOMapper {
    long countByExample(UmsUserInfoDOExample example);

    int deleteByExample(UmsUserInfoDOExample example);

    int insert(UmsUserInfoDO record);

    int insertSelective(UmsUserInfoDO record);

    List<UmsUserInfoDO> selectByExample(UmsUserInfoDOExample example);

    int updateByExampleSelective(@Param("record") UmsUserInfoDO record, @Param("example") UmsUserInfoDOExample example);

    int updateByExample(@Param("record") UmsUserInfoDO record, @Param("example") UmsUserInfoDOExample example);
}