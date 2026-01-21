package com.qdbank.mall.user;


import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.resource.ResourceReqDTO;
import com.qdbank.mall.response.resource.ResourceResDTO;

import java.util.List;

/**
 * 后台资源管理Service
 * Created by ningyuehuai on 2020/10/2.
 */
public interface UmsResourceService {
    /**
     * 添加资源
     */
    int create(ResourceReqDTO umsResource);

    /**
     * 修改资源
     */
    int update(Long id, ResourceReqDTO resourceReqDTO);

    /**
     * 获取资源详情
     */
    ResourceResDTO getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    PageInfo<ResourceResDTO> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<ResourceResDTO> listAll();
}
