package com.qdbank.mall.model.user;

import com.qdbank.mall.model.BaseDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UmsAdminRoleRelation extends BaseDO implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;
    /**
     * 用户id
     *
     * @mbg.generated
     */
    private Long adminId;
    /**
     * 角色ID
     *
     * @mbg.generated
     */
    private Long roleId;



    private static final long serialVersionUID = 1L;

}