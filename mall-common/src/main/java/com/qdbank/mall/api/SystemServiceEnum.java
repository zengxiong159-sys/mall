package com.qdbank.mall.api;

import lombok.Getter;

@Getter
public enum SystemServiceEnum {
    CMS_ADMIN_SERVICE("CMS","cmsAdminServiceImpl","商户后台"),
    UMS_ADMIN_SERVICE("UMS","umsAdminServiceImpl","银行后管");
    /**
     * 所属系统
     */
    private String system;
    /**
     * 服务实现类
     */
    private String serviceName;
    /**
     * 描述
     */
    private String desc;
    private SystemServiceEnum(String system,String serviceName,String desc){
        this.system = system;
        this.serviceName = serviceName;
        this.desc = desc;
    }
    public static String getServiceName(String system){
        for(SystemServiceEnum serviceEnum : SystemServiceEnum.values()){
            if(serviceEnum.getSystem().equals(system)) return serviceEnum.getServiceName();
        }
        return null;
    }
}
