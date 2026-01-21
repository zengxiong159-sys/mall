package com.qdbank.mall.mapper.userwhite;

import com.qdbank.mall.model.userwhite.UserWhiteDO;
import com.qdbank.mall.model.userwhite.UserWhiteDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface UserWhiteDOMapper {
    long countByExample(UserWhiteDOExample example);

    int deleteByExample(UserWhiteDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(UserWhiteDO record);

    int insertSelective(UserWhiteDO record);

    List<UserWhiteDO> selectByExample(UserWhiteDOExample example);

    UserWhiteDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserWhiteDO record, @Param("example") UserWhiteDOExample example);

    int updateByExample(@Param("record") UserWhiteDO record, @Param("example") UserWhiteDOExample example);

    int updateByPrimaryKeySelective(UserWhiteDO record);

    int updateByPrimaryKey(UserWhiteDO record);
}