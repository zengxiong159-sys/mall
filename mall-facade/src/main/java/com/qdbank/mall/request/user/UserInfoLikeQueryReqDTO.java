package com.qdbank.mall.request.user;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdbank.mall.request.PageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName UserInfoLikeQueryReqDTO
 * @Description 用户模糊匹配查询用户列表
 * @Author ningyuehuai
 * @Date 2020/12/22 15:10
 * @Version 1.0
 **/
@Data
public class UserInfoLikeQueryReqDTO extends PageParams implements Serializable {
    @ApiModelProperty(value = "用户编号")
    private Long id;
    @ApiModelProperty(value = "账号、邮箱")
    private String username;
    @ApiModelProperty(value = "用户姓名")
    private String nickName;
    @ApiModelProperty(value = "用户状态")
    private Long status ;
    @ApiModelProperty(value = "所在部门编号")
    private Long deptno;
    private String xxx;
    public List<TestDto> change(String xxx){
        try {
            ObjectMapper om = new ObjectMapper();
            JavaType javaType = om.getTypeFactory().constructParametricType(List.class, TestDto.class);
            List<TestDto> list = (List<TestDto>)om.readValue(xxx, javaType);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
