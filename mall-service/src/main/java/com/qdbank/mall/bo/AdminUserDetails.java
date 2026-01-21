package com.qdbank.mall.bo;

import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.model.user.UserInfoDO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 */
@Data
public class AdminUserDetails implements UserDetails {
    private UserInfoDO userInfoDO;
    private List<UmsResourceDO> resourceList;
    public AdminUserDetails(UserInfoDO userInfoDO, List<UmsResourceDO> resourceList) {
        this.userInfoDO = userInfoDO;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(resourceDO ->new SimpleGrantedAuthority(resourceDO.getId()+":"+resourceDO.getResourceName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userInfoDO.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfoDO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        boolean status = userInfoDO.getStatus().equals(1L);
        return status;
    }
}
