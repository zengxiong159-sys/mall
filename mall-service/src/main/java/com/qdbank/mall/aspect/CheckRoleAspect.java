package com.qdbank.mall.aspect;

import cn.hutool.core.collection.CollUtil;
import com.qdbank.mall.annotation.CheckRole;
import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class CheckRoleAspect {
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;

    @Around("@annotation(checkRole)")
    public Object errorHandler(final ProceedingJoinPoint joinPoint, CheckRole checkRole) throws Throwable {
        UserInfoDO userInfoDO = umsAdminService.getUserInfoByToken();
        if(userInfoDO != null){
            List<RoleResDTO> list = umsAdminService.getRoleList(userInfoDO.getId());
            if(CollUtil.isNotEmpty(list)){
                List<RoleResDTO> adminRoleList = list.stream().filter(roleResDTO -> "管理员".equals(roleResDTO.getRoleName())).collect(Collectors.toList());
                if(CollUtil.isNotEmpty(adminRoleList))  return joinPoint.proceed();
            }
        }
        throw new ApiException(ResultCode.NO_OPERATE_AUTH);
    }
}

