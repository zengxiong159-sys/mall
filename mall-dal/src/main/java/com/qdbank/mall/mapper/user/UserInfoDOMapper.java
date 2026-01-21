package com.qdbank.mall.mapper.user;

import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.user.UserInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserInfoDOMapper {
    long countByExample(UserInfoDOExample example);

    int deleteByExample(UserInfoDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    List<UserInfoDO> selectByExample(UserInfoDOExample example);

    UserInfoDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserInfoDO record, @Param("example") UserInfoDOExample example);

    int updateByExample(@Param("record") UserInfoDO record, @Param("example") UserInfoDOExample example);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);
}