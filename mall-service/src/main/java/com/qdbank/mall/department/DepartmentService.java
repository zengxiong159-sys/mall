package com.qdbank.mall.department;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.dept.DeptLikeQueryReqDTO;
import com.qdbank.mall.request.dept.DeptReqDTO;
import com.qdbank.mall.request.dept.UpdateDeptReqDTO;
import com.qdbank.mall.response.dept.DeptResDTO;

import java.util.List;

public interface DepartmentService {

    /**
     * 新增部门信息
     * @param deptReqDTO
     * @return
     */
    public int insert(DeptReqDTO deptReqDTO);

    /**
     * 修改部门信息
     * @param id
     * @param updateDeptReqDTO
     * @return
     */
    public int update (Long id, UpdateDeptReqDTO updateDeptReqDTO);
    /**
     * 根据部门id获取部门信息
     */
    DeptResDTO getDepartmentById(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    PageInfo<DeptResDTO> list(DeptLikeQueryReqDTO deptLikeQueryReqDTO);

    /**
     * 根据id删除部门信息
     * @param id
     * @return
     */
    int deleteDepartmentById(Long id);

}
