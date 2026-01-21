package com.qdbank.mall.user.impl;
import com.qdbank.mall.mapper.resourcecategory.UmsResourceCategoryMapper;
import com.qdbank.mall.model.resourcecategory.UmsResourceCategoryDO;
import com.qdbank.mall.model.resource.UmsResourceCategoryExample;
import com.qdbank.mall.request.resourcecategory.ResourceCategoryReqDTO;
import com.qdbank.mall.response.resourcecategory.ResourceCategoryResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.user.UmsResourceCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台资源分类管理Service实现类
 * Created by ningyuehuai on 2020/10/5.
 */
@Service
public class UmsResourceCategoryServiceImpl extends BaseServiceImpl implements UmsResourceCategoryService {
    @Autowired
    private UmsResourceCategoryMapper resourceCategoryMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService adminService;
    @Override
    public List<ResourceCategoryResDTO> listAll() {
        UmsResourceCategoryExample example = new UmsResourceCategoryExample();
        example.setOrderByClause("sort desc");
        List<ResourceCategoryResDTO> categoryResDTOList = new ArrayList<>();
        List<UmsResourceCategoryDO> list = resourceCategoryMapper.selectByExample(example);
        for(UmsResourceCategoryDO umsResourceCategoryDO : list){
            ResourceCategoryResDTO resourceCategoryResDTO = new ResourceCategoryResDTO();
            BeanUtils.copyProperties(umsResourceCategoryDO,resourceCategoryResDTO);
            categoryResDTOList.add(resourceCategoryResDTO);
        }
        return categoryResDTOList;
    }

    @Override
    public int create(ResourceCategoryReqDTO resourceCategoryReqDTO) {
        UmsResourceCategoryDO umsResourceCategory = new UmsResourceCategoryDO();
        BeanUtils.copyProperties(resourceCategoryReqDTO,umsResourceCategory);
        umsResourceCategory.setId(super.generateId());
        adminService.injectUserValue(umsResourceCategory);
        return resourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, ResourceCategoryReqDTO resourceCategoryReqDTO) {
        UmsResourceCategoryDO umsResourceCategory = new UmsResourceCategoryDO();
        BeanUtils.copyProperties(resourceCategoryReqDTO,umsResourceCategory);
        umsResourceCategory.setId(id);
        adminService.injectUpdateUserValue(umsResourceCategory);
        return resourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id);
    }
}
