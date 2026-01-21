package com.qdbank.mall.user;


import com.qdbank.mall.request.resourcecategory.ResourceCategoryReqDTO;
import com.qdbank.mall.response.resourcecategory.ResourceCategoryResDTO;

import java.util.List;

/**
 * 后台资源分类管理Service
 * Created by ningyuehuai on 2020/10/5.
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源分类
     */
    List<ResourceCategoryResDTO> listAll();

    /**
     * 创建资源分类
     */
    int create(ResourceCategoryReqDTO umsResourceCategory);

    /**
     * 修改资源分类
     */
    int update(Long id, ResourceCategoryReqDTO umsResourceCategory);

    /**
     * 删除资源分类
     */
    int delete(Long id);
}
