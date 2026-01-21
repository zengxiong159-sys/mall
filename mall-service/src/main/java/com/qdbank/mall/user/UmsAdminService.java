package com.qdbank.mall.user;


import com.github.pagehelper.PageInfo;
import com.qdbank.mall.model.BaseDO;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.request.user.UpdatePasswordReqDTO;
import com.qdbank.mall.request.user.UpdateUserInfoReqDTO;
import com.qdbank.mall.request.user.UserInfoLikeQueryReqDTO;
import com.qdbank.mall.request.user.UserInfoReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.response.user.UserInfoResDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UserInfoDO getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UserInfoDO register(UserInfoReqDTO umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UserInfoResDTO getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    PageInfo<UserInfoResDTO> list(UserInfoLikeQueryReqDTO userInfoLikeQueryReqDTO);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UpdateUserInfoReqDTO admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<String > roleIds);

    /**
     * 获取用户对于角色
     */
    List<RoleResDTO> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResourceDO> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdatePasswordReqDTO updatePasswordReqDTO);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 注入修改人创建人信息
     * @param
     * @param baseDO
     */
    public void injectUserValue( BaseDO baseDO);

    /**
     * 修改用户时只修改修改用户字段和修改时间字段
     * @param baseDO
     */
    public void injectUpdateUserValue(BaseDO baseDO);

    /**
     * 修改用户状态
     * @param username
     * @param status
     * @return
     */
    public void updateStatus(String username,Long status);

    public boolean checkUserExist(String username);

    public MerchantResDTO injectUserValueAndMerchantNo(BaseDO baseDO);

    public MerchantDO queryUserNameAndMerchantNo();

    public MerchantResDTO selectUserNameAndMerchantNo();

    public UserInfoDO getUserInfoByToken();

}
