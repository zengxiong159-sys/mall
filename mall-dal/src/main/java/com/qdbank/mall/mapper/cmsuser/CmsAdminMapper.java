package com.qdbank.mall.mapper.cmsuser;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.user.UserInfoDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsAdminMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    List<UserInfoDO> selectByExample(UserInfoDOExample example);
    
    int updateByExampleSelective(@Param("record") UserInfoDO record, @Param("example") UserInfoDOExample example);

    int updateByExample(@Param("record") UserInfoDO record, @Param("example") UserInfoDOExample example);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);
}