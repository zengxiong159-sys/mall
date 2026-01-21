package com.qdbank.mall.config;
import com.qdbank.mall.component.DynamicSecurityService;
import com.qdbank.mall.component.RepeatLoginManage;
import com.qdbank.mall.user.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CmsMallSecurityConfig extends SecurityConfig {
    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService adminService;


    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
//                List<ResourceResDTO> resourceList = resourceService.listAll();
//                for (ResourceResDTO resource : resourceList) {
//                    map.put(resource.getResourceUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getResourceName()));
//                }
                return map;
            }
        };
    }

    @Bean
    public RepeatLoginManage repeatLoginManage(){
        return new RepeatLoginManage();
    }
}
