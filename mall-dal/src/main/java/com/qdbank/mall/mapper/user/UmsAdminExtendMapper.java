package com.qdbank.mall.mapper.user;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.user.UserInfoDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UmsAdminExtendMapper {
    List<UserInfoDO> selectUserLikeQuery(UserInfoDO userInfoDO);
    UserInfoDO selectByPrimaryKey(@Param("id") Long id);
    Integer selectCountByDeptNo(@Param("deptNo") Long deptNo);
    List<String>selectRoleNamesById(@Param("adminId")Long adminId);
}