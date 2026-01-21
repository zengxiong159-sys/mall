package com.qdbank.mall.user.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.AdminUserDetails;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.exception.Asserts;
import com.qdbank.mall.mapper.merchant.MerchantDOMapper;
import com.qdbank.mall.mapper.user.UmsAdminExtendMapper;
import com.qdbank.mall.mapper.user.UmsAdminLoginLogMapper;
import com.qdbank.mall.mapper.user.UmsAdminMapper;
import com.qdbank.mall.mapper.user.UmsAdminRoleRelationMapper;
import com.qdbank.mall.model.BaseDO;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.role.RoleDO;
import com.qdbank.mall.model.user.*;
import com.qdbank.mall.request.user.UpdatePasswordReqDTO;
import com.qdbank.mall.request.user.UpdateUserInfoReqDTO;
import com.qdbank.mall.request.user.UserInfoLikeQueryReqDTO;
import com.qdbank.mall.request.user.UserInfoReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.response.user.UserInfoResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminCacheService;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.JwtTokenUtil;
import com.qdbank.mall.util.LoginUserContextHolder;
import com.qdbank.mall.util.PasswordCheckUtil;
import com.qdbank.mall.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UmsAdminService实现类
 * Created by ningyuehuai on 2020/10/26.
 */
@Service("umsAdminServiceImpl")
@Slf4j
public class UmsAdminServiceImpl extends BaseServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminExtendMapper adminExtendMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Autowired
    @Qualifier("umsAdminServiceImpl")
    private UmsAdminService umsAdminService;
    @Autowired
    private MerchantDOMapper merchantDOMapper;
    private static final String USER_NAME_SUFFIX = "@qdbankchina.com";
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
        userInfoDO.setPassword(umsAdminParam.getUsername().substring(0,4)+umsAdminParam.getMobile().substring(umsAdminParam.getMobile().length() - 4,umsAdminParam.getMobile().length()));
        String encodePassword = passwordEncoder.encode(userInfoDO.getPassword());
        userInfoDO.setPassword(encodePassword);
        adminMapper.insert(userInfoDO);
        return userInfoDO;
    }

    @Override
    public String login(String username, String password) {
        log.info(username+password);
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!userDetails.isEnabled()){//账号禁用
                Asserts.fail(ResultCode.ACCOUNT_FORBIDDEN);
            }
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                this.updateStatus(username,0L);//用户名密码错误
                Asserts.fail(ResultCode.USER_PASSWORD_ERROR);
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
            //登录成功清除记录登录错误次数缓存
            adminCacheService.deleteKey(Constant.USER_LOGIN_COUNT+username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
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
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UserInfoResDTO getItem(Long id) {
        UserInfoResDTO userInfoResDTO = new UserInfoResDTO();
        UserInfoDO userInfoDO = null;
        userInfoDO = adminCacheService.getAdminById(id);
        if(userInfoDO == null){
            userInfoDO = adminExtendMapper.selectByPrimaryKey(id);
            adminCacheService.setAdmin(userInfoDO);
        }
        BeanUtils.copyProperties(userInfoDO,userInfoResDTO);
        return userInfoResDTO;
    }

    @Override
    public PageInfo<UserInfoResDTO> list(UserInfoLikeQueryReqDTO userInfoLikeQueryReqDTO) {
        PageHelper.startPage(userInfoLikeQueryReqDTO.getPageNum(), userInfoLikeQueryReqDTO.getPageSize());
        UserInfoDO userInfoDORequest = new UserInfoDO();
        BeanUtils.copyProperties(userInfoLikeQueryReqDTO,userInfoDORequest);
        List<UserInfoDO> adminList = adminExtendMapper.selectUserLikeQuery(userInfoDORequest);
        List<UserInfoResDTO> userInfoResDTOList = new ArrayList<>();
        for(UserInfoDO userInfoDO: adminList){
            UserInfoResDTO userInfoResDTO = new UserInfoResDTO();
            BeanUtils.copyProperties(userInfoDO,userInfoResDTO);
            userInfoResDTOList.add(userInfoResDTO);
            StringBuffer sb = new StringBuffer();
            List<String> roleNames = adminExtendMapper.selectRoleNamesById(userInfoDO.getId());
            for(String roleName : roleNames){
                sb.append(roleName).append(" ");
            }
            if(!StringUtils.isEmpty(sb.toString())) userInfoResDTO.setRoleNames(sb.toString());
        }
        return super.getPageInfo(adminList,userInfoResDTOList);
    }

    @Override
    public int update(Long id, UpdateUserInfoReqDTO admin) {
        UserInfoDO rawAdmin = adminExtendMapper.selectByPrimaryKey(id);
        if(rawAdmin == null){
            throw  new ApiException(ResultCode.USER_PASSWORD_ERROR);
        }
        if(rawAdmin.getPassword().equals(admin.getPassword())){
            //与原加密密码相同的不需要修改
            admin.setPassword(null);
        }else{
            //与原加密密码不同的需要加密修改
            if(StrUtil.isEmpty(admin.getPassword())){
                admin.setPassword(null);
            }else{
                //校验密码是否是弱口令
                if(!PasswordCheckUtil.isComplicatedPwd(admin.getPassword())) {
                    throw new ApiException(ResultCode.USER_PASSWORD_NOT_COMPLICATED);
                }
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            }
        }
        BeanUtils.copyProperties(admin,rawAdmin);
        rawAdmin.setId(id);
        this.injectUpdateUserValue(rawAdmin);
        int count = adminMapper.updateByPrimaryKeySelective(rawAdmin);
        adminCacheService.delAdmin(id);
        return count;
    }

    @Override
    public int delete(Long id) {
        adminCacheService.delAdmin(id);
        int count = adminMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceList(id);
        return count;
    }

    @Override
    public int updateRole(Long adminId, List<String> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        try {
             adminRoleRelationMapper.deleteByAdminId(adminId);
        }catch (Exception e){
            e.printStackTrace();
        }
        UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
        umsAdminService.injectUserValue(roleRelation);
        roleRelation.setAdminId(adminId);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            for (String roleId : roleIds) {
                roleRelation.setRoleId(Long.valueOf(roleId));
                roleRelation.setId(super.generateId());
                adminRoleRelationMapper.insert(roleRelation);
            }
            adminCacheService.delResourceList(adminId);
        }

        return count;
    }

    @Override
    public List<RoleResDTO> getRoleList(Long adminId) {
        List<RoleDO> roleDOS = adminRoleRelationMapper.getRoleList(adminId);
        List<RoleResDTO> roleResDTOS = new ArrayList<>();
        for(RoleDO roleDO : roleDOS){
            RoleResDTO roleResDTO = new RoleResDTO();
            BeanUtils.copyProperties(roleDO,roleResDTO);
            roleResDTOS.add(roleResDTO);
        }
        return roleResDTOS;
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


    @Override
    public int updatePassword(UpdatePasswordReqDTO param) {
        if(StrUtil.isEmpty(param.getUsername())
                ||StrUtil.isEmpty(param.getOldPassword())
                ||StrUtil.isEmpty(param.getNewPassword())){
            return -1;
        }
        UserInfoDOExample example = new UserInfoDOExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<UserInfoDO> adminList = adminMapper.selectByExample(example);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }
        UserInfoDO userInfoDO = adminList.get(0);
        if(!passwordEncoder.matches(param.getOldPassword(), userInfoDO.getPassword())){
            return -3;
        }

        //校验密码是否是弱口令
        if(!PasswordCheckUtil.isComplicatedPwd(param.getNewPassword())) {
            throw new ApiException(ResultCode.USER_PASSWORD_NOT_COMPLICATED);
        }

        userInfoDO.setPassword(passwordEncoder.encode(param.getNewPassword()));
        umsAdminService.injectUpdateUserValue(userInfoDO);
        adminMapper.updateByPrimaryKey(userInfoDO);
        adminCacheService.delAdmin(userInfoDO.getId());
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        UserInfoDO admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResourceDO> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException(ResultCode.USER_PASSWORD_ERROR.getMessage());
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
            }
        }
        BeanUtils.copyProperties(merchantDO,merchantResDTO);
        return merchantResDTO;
    }

    @Override
    public UserInfoDO getUserInfoByToken() {
        UserDetails userDetails = LoginUserContextHolder.getUserDetails();
        AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
        UserInfoDO loginUser = adminUserDetails.getUserInfoDO();
        return loginUser;
    }

    @Override
    public void injectUpdateUserValue(BaseDO baseDO) {
        this.injectUserValue(baseDO);
        baseDO.setCreatedBy(null);
        baseDO.setCreateTime(null);
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
     */
    public boolean checkUserExist(String username){
        boolean flag = username.endsWith(USER_NAME_SUFFIX);
        if(!flag) throw new ApiException(ResultCode.EMAIL_FORMAT_ERROR);
        //查询是否有相同用户名的用户
        UserInfoDOExample example = new UserInfoDOExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserInfoDO> userInfoDOList = adminMapper.selectByExample(example);
        return  userInfoDOList.size() > 0;
    }
    private UserInfoDOExample getUserExample(UserInfoLikeQueryReqDTO userInfoLikeQueryReqDTO){
        UserInfoDOExample example = new UserInfoDOExample();
        UserInfoDOExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(userInfoLikeQueryReqDTO.getNickName())) {
            criteria.andNickNameLike("%" + userInfoLikeQueryReqDTO.getNickName() + "%");
        }
        if(!StringUtils.isEmpty(userInfoLikeQueryReqDTO.getId())){
            criteria.andIdEqualTo(userInfoLikeQueryReqDTO.getId());
        }
        if(!StringUtils.isEmpty(userInfoLikeQueryReqDTO.getUsername())){
            criteria.andUsernameEqualTo(userInfoLikeQueryReqDTO.getUsername());
        }
        if(!StringUtils.isEmpty(userInfoLikeQueryReqDTO.getDeptno())){
            criteria.andDeptnoEqualTo(userInfoLikeQueryReqDTO.getDeptno());
        }
        if(!StringUtils.isEmpty(userInfoLikeQueryReqDTO.getStatus())){
            criteria.andStatusEqualTo(userInfoLikeQueryReqDTO.getStatus());
        }
        return example;
    }


}
