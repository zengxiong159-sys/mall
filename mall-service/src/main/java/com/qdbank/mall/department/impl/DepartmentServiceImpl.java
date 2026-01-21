package com.qdbank.mall.department.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.department.DepartmentService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.department.UmsDepartmentDOMapper;
import com.qdbank.mall.mapper.user.UmsAdminExtendMapper;
import com.qdbank.mall.model.department.UmsDepartmentDO;
import com.qdbank.mall.model.department.UmsDepartmentDOExample;
import com.qdbank.mall.request.dept.DeptLikeQueryReqDTO;
import com.qdbank.mall.request.dept.DeptReqDTO;
import com.qdbank.mall.request.dept.UpdateDeptReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.dept.DeptResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentServiceImpl extends BaseServiceImpl implements DepartmentService {
    @Autowired
    private UmsDepartmentDOMapper umsDepartmentDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsAdminExtendMapper umsAdminExtendMapper;
    @Override
    public int insert(DeptReqDTO deptReqDTO) {
        UmsDepartmentDO umsDepartmentDO = new UmsDepartmentDO();
        BeanUtils.copyProperties(deptReqDTO,umsDepartmentDO);
        umsAdminService.injectUserValue(umsDepartmentDO);
        umsDepartmentDO.setId(super.generateId());
        return umsDepartmentDOMapper.insert(umsDepartmentDO);
    }

    @Override
    public int update(Long id, UpdateDeptReqDTO updateDeptReqDTO) {
        UmsDepartmentDO umsDepartmentDO = new UmsDepartmentDO();
        BeanUtils.copyProperties(updateDeptReqDTO,umsDepartmentDO);
        umsAdminService.injectUpdateUserValue(umsDepartmentDO);
        UmsDepartmentDOExample umsDepartmentDOExample = new UmsDepartmentDOExample();
        umsDepartmentDOExample.createCriteria().andIdEqualTo(id);
        return umsDepartmentDOMapper.updateByExampleSelective(umsDepartmentDO,umsDepartmentDOExample);
    }

    @Override
    public DeptResDTO getDepartmentById(Long id) {
        DeptResDTO deptResDTO = new DeptResDTO();
        UmsDepartmentDO umsDepartmentDO = umsDepartmentDOMapper.selectDepartmentById(id);
        if (umsDepartmentDO == null) return null;
        BeanUtils.copyProperties(umsDepartmentDO, deptResDTO);
        return deptResDTO;
    }

    @Override
    public PageInfo<DeptResDTO> list(DeptLikeQueryReqDTO deptLikeQueryReqDTO) {
        PageHelper.startPage(deptLikeQueryReqDTO.getPageNum(),deptLikeQueryReqDTO.getPageSize());
        UmsDepartmentDOExample umsDepartmentDOExample = new UmsDepartmentDOExample();
        umsDepartmentDOExample.setOrderByClause("create_time desc");
        UmsDepartmentDOExample.Criteria criteria = umsDepartmentDOExample.createCriteria();
        if(StringUtils.isNotBlank(deptLikeQueryReqDTO.getName())){
            criteria.andNameLike("%"+deptLikeQueryReqDTO.getName()+"%");
        }
        if(deptLikeQueryReqDTO.getId() != null){
            criteria.andIdEqualTo(deptLikeQueryReqDTO.getId());
        }
        List<UmsDepartmentDO> umsDepartmentDOS = umsDepartmentDOMapper.selectByExample(umsDepartmentDOExample);
        List<DeptResDTO> deptResDTOS = new ArrayList<>();
        for(UmsDepartmentDO userInfoDO : umsDepartmentDOS){
            DeptResDTO userInfoResDTO = new DeptResDTO();
            BeanUtils.copyProperties(userInfoDO,userInfoResDTO);
            deptResDTOS.add(userInfoResDTO);
        }
        return super.getPageInfo(umsDepartmentDOS,deptResDTOS);
    }

    @Override
    public int deleteDepartmentById(Long id) {
        int count = umsAdminExtendMapper.selectCountByDeptNo(id);
        if(count > 0) throw new ApiException(ResultCode.DEPT_NO_BIND);
        UmsDepartmentDOExample umsDepartmentDOExample = new UmsDepartmentDOExample();
        umsDepartmentDOExample.createCriteria().andIdEqualTo(id);
        return umsDepartmentDOMapper.deleteByExample(umsDepartmentDOExample);
    }
}
