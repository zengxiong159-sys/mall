package com.qdbank.mall.user.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.mapper.resource.UmsResourceDOMapper;
import com.qdbank.mall.model.user.UmsResourceDO;
import com.qdbank.mall.model.user.UmsResourceDOExample;
import com.qdbank.mall.request.resource.ResourceReqDTO;
import com.qdbank.mall.response.resource.ResourceResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminCacheService;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.user.UmsResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 后台资源管理Service实现类
 * Created by ningyuehuai on 2020/10/2.
 */
@Service
public class UmsResourceServiceImpl extends BaseServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceDOMapper resourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Override
    public int create(ResourceReqDTO umsResource) {
        UmsResourceDO umsResourceDO = new UmsResourceDO();
        BeanUtils.copyProperties(umsResource,umsResourceDO);
        umsResourceDO.setId(super.generateId());
        umsAdminService.injectUserValue(umsResourceDO);
        return resourceMapper.insert(umsResourceDO);
    }

    @Override
    public int update(Long id, ResourceReqDTO resourceReqDTO) {
        UmsResourceDO umsResourceDO = new UmsResourceDO();
        BeanUtils.copyProperties(resourceReqDTO,umsResourceDO);
        umsResourceDO.setId(id);
        umsAdminService.injectUpdateUserValue(umsResourceDO);
        int count = resourceMapper.updateByPrimaryKeySelective(umsResourceDO);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public ResourceResDTO getItem(Long id) {
        ResourceResDTO resourceResDTO = new ResourceResDTO();
        UmsResourceDO umsResourceDO = resourceMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(umsResourceDO,resourceResDTO);
        return resourceResDTO;
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteByPrimaryKey(id);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public PageInfo<ResourceResDTO> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UmsResourceDOExample example = new UmsResourceDOExample();
        UmsResourceDOExample.Criteria criteria = example.createCriteria();
        if(categoryId!=null){
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            criteria.andResourceNameLike('%'+nameKeyword+'%');
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            criteria.andResourceUrlLike('%'+urlKeyword+'%');
        }
        criteria.andIdNotIn(Arrays.asList(214469071173869568L,214469218180030464L,214469407926149120L));
        List<UmsResourceDO> umsResourceDOList = resourceMapper.selectByExample(example);
        return this.getResourceList(umsResourceDOList);
    }

    @Override
    public List<ResourceResDTO> listAll() {
        UmsResourceDOExample example = new UmsResourceDOExample();
        List<UmsResourceDO> list = resourceMapper.selectByExample(example);
        PageInfo<ResourceResDTO> pageInfo = this.getResourceList(list);
        return pageInfo.getList();
    }

    private PageInfo<ResourceResDTO> getResourceList(List<UmsResourceDO> sourceList){
        List<ResourceResDTO> resourceResDTOList = new ArrayList<>();
        for(UmsResourceDO umsResourceDO : sourceList){
            ResourceResDTO resourceResDTO = new ResourceResDTO();
            BeanUtils.copyProperties(umsResourceDO,resourceResDTO);
            resourceResDTOList.add(resourceResDTO);
        }
        return super.getPageInfo(sourceList,resourceResDTOList);
    }
}
