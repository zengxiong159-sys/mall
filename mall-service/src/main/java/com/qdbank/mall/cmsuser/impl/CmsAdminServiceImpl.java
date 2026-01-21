package com.qdbank.mall.cmsuser.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.AdminUserDetails;
import com.qdbank.mall.cmsuser.CmsAdminCacheService;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.enums.UserStatusEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.exception.Asserts;
import com.qdbank.mall.mapper.cmsuser.CmsAdminLoginLogDOMapper;
import com.qdbank.mall.mapper.cmsuser.CmsAdminMapper;
import com.qdbank.mall.mapper.cmsuser.CmsAdminRoleRelationMapper;
import com.qdbank.mall.mapper.merchant.MerchantDOMapper;
import com.qdbank.mall.model.BaseDO;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.user.UmsAdminLoginLog;
import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.user.UserInfoDOExample;
import com.qdbank.mall.request.user.UpdatePasswordReqDTO;
import com.qdbank.mall.request.user.UpdateUserInfoReqDTO;
import com.qdbank.mall.request.user.UserInfoLikeQueryReqDTO;
import com.qdbank.mall.request.user.UserInfoReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.response.user.UserInfoResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.JwtTokenUtil;
import com.qdbank.mall.util.LoginUserContextHolder;
import com.qdbank.mall.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * CmsAdminServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/22 15:59
 * @since 1.0.0
 */
@Service("cmsAdminServiceImpl")
@Slf4j
public class CmsAdminServiceImpl extends BaseServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsAdminServiceImpl.class);
    @Autowired
    private CmsAdminCacheService adminCacheService;
    @Autowired
    private CmsAdminMapper adminMapper;
    @Autowired
    private CmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CmsAdminLoginLogDOMapper loginLogMapper;
    @Autowired
    private MerchantDOMapper merchantDOMapper;
    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            //判断当前用户账号是否被禁用
            MerchantDO currentUser = merchantDOMapper.selectByEmailIgnoreStatus(username);
            if(currentUser != null) {
                long status = currentUser.getStatus();
                if(UserStatusEnum.DISABLE.getCode() == status) {
                    Asserts.fail(ResultCode.ACCOUNT_FORBIDDEN);
                }
            } else {
                Asserts.fail(ResultCode.USER_PASSWORD_ERROR);
            }
            UserDetails userDetails = loadUserByUsername(username);
            if(!userDetails.isEnabled()){//账号禁用
                Asserts.fail(ResultCode.ACCOUNT_FORBIDDEN);
            }
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
               // this.updateStatus(username,0L);//用户名密码错误
                Asserts.fail(ResultCode.USER_PASSWORD_ERROR);
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.error("用户登录异常:{}",e);
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UserInfoResDTO getItem(Long id) {
        return null;
    }

    @Override
    public PageInfo<UserInfoResDTO> list(UserInfoLikeQueryReqDTO userInfoLikeQueryReqDTO) {
        return null;
    }

    @Override
    public int update(Long id, UpdateUserInfoReqDTO admin) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int updateRole(Long adminId, List<String> roleIds) {
        return 0;
    }

    @Override
    public List<RoleResDTO> getRoleList(Long adminId) {
        return null;
    }

    @Override
    public List<UmsResourceDO> getResourceList(Long adminId) {
        List<UmsResourceDO> resourceList = adminCacheService.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            return  resourceList;
        }
        resourceList = adminRoleRelationMapper.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            adminCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UserInfoDO admin = getAdminByUsername(username);
        if(admin==null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        UserInfoDO record = new UserInfoDO();
        record.setLoginTime(new Date());
        UserInfoDOExample example = new UserInfoDOExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public void updateStatus(String username,Long status) {
        Long count = adminCacheService.getRedisIncrID(Constant.USER_LOGIN_COUNT+username,1l);
        if(count > 4){
            UserInfoDO userInfoDO = new UserInfoDO();
            userInfoDO.setStatus(status);
            UserInfoDOExample example = new UserInfoDOExample();
            example.createCriteria().andUsernameEqualTo(username);
            adminMapper.updateByExampleSelective(userInfoDO,example);
            adminCacheService.deleteByUsername(username);//清除缓存中数据
            adminCacheService.deleteKey(Constant.USER_LOGIN_COUNT+username);
        }
    }

    /**
     * 校验用户是否存在
     * @param username
     * @return
     */
    @Override
    public boolean checkUserExist(String username) {
        //查询是否有相同用户名的用户
        UserInfoDOExample example = new UserInfoDOExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserInfoDO> userInfoDOList = adminMapper.selectByExample(example);
        return  userInfoDOList.size() > 0;
    }

    @Override
    public MerchantResDTO injectUserValueAndMerchantNo(BaseDO baseDO) {

        UserDetails userDetails = LoginUserContextHolder.getUserDetails();
        MerchantResDTO merchantResDTO = new MerchantResDTO();
        MerchantDO merchantDO = new MerchantDO();
        if(userDetails != null && userDetails instanceof AdminUserDetails){
            AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
            UserInfoDO loginUser = adminUserDetails.getUserInfoDO();
            if(loginUser != null){
                merchantDO = merchantDOMapper.selectByEmail(loginUser.getUsername());
                baseDO.setUpdatedBy(loginUser.getNickName());
                baseDO.setCreatedBy(loginUser.getNickName());
            }
        }
        if (merchantDO != null){
            BeanUtils.copyProperties(merchantDO,merchantResDTO);
        }
        baseDO.setCreateTime(new Date());
        baseDO.setUpdateTime(new Date());
        return merchantResDTO;
    }

    @Override
    public MerchantDO queryUserNameAndMerchantNo() {
        UserDetails userDetails = LoginUserContextHolder.getUserDetails();
        MerchantDO merchantDO = new MerchantDO();
        if(userDetails != null && userDetails instanceof AdminUserDetails){
            AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
            UserInfoDO loginUser = adminUserDetails.getUserInfoDO();
            if(loginUser != null){
                merchantDO = merchantDOMapper.selectByEmail(loginUser.getUsername());
            }
        }
        return merchantDO;
    }

    @Override
    public MerchantResDTO selectUserNameAndMerchantNo() {
        MerchantResDTO merchantResDTO = new MerchantResDTO();
        UserDetails userDetails = LoginUserContextHolder.getUserDetails();
        MerchantDO merchantDO = new MerchantDO();
        if(userDetails != null && userDetails instanceof AdminUserDetails){
            AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
            UserInfoDO loginUser = adminUserDetails.getUserInfoDO();
            if(loginUser != null){
                merchantDO = merchantDOMapper.selectByEmail(loginUser.getUsername());
                merchantResDTO.setUpdatedBy(loginUser.getNickName());
                merchantResDTO.setCreatedBy(loginUser.getNickName());
            }
        }
        BeanUtils.copyProperties(merchantDO,merchantResDTO);
        merchantResDTO.setCreateTime(new Date());
        merchantResDTO.setUpdateTime(new Date());
        return merchantResDTO;
    }

    @Override
    public UserInfoDO getUserInfoByToken() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        UserInfoDO admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResourceDO> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public void injectUserValue(BaseDO baseDO) {
        UserDetails userDetails = LoginUserContextHolder.getUserDetails();
        if(userDetails != null && userDetails instanceof AdminUserDetails){
            AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
            UserInfoDO loginUser = adminUserDetails.getUserInfoDO();
            if(loginUser != null){
                baseDO.setUpdatedBy(loginUser.getNickName());
                baseDO.setCreatedBy(loginUser.getNickName());
            }
        }
        baseDO.setCreateTime(new Date());
        baseDO.setUpdateTime(new Date());
    }


    @Override
    public void injectUpdateUserValue(BaseDO baseDO) {
        this.injectUserValue(baseDO);
        baseDO.setCreatedBy(null);
        baseDO.setCreateTime(null);
    }

    @Override
    public int updatePassword(UpdatePasswordReqDTO updatePasswordReqDTO) {
        return 0;
    }

    @Override
    public UserInfoDO getAdminByUsername(String username) {
        UserInfoDO admin = adminCacheService.getAdmin(username);
        if(admin!=null) return  admin;
        UserInfoDOExample example = new UserInfoDOExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserInfoDO> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UserInfoDO register(UserInfoReqDTO umsAdminParam) {
        if(this.checkUserExist(umsAdminParam.getUsername())) throw new ApiException(ResultCode.USER_EXIST);
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(umsAdminParam, userInfoDO);
        userInfoDO.setId(super.generateId());
        this.injectUserValue(userInfoDO);
        //将密码进行加密操作 默认密码邮箱前4位+后四位手机号
        userInfoDO.setPassword(umsAdminParam.getEmail().substring(0,4)+umsAdminParam.getMobile().substring(umsAdminParam.getMobile().length() - 4,umsAdminParam.getMobile().length()));
        String encodePassword = passwordEncoder.encode(userInfoDO.getPassword());
        userInfoDO.setPassword(encodePassword);
        adminMapper.insert(userInfoDO);
        return userInfoDO;
    }
}
