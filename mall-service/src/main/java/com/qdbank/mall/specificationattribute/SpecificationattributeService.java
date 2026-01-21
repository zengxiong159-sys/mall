package com.qdbank.mall.specificationattribute;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.specificationattribute.*;
import com.qdbank.mall.response.specificationattribute.SpecificationattributeResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpecificationattributeService {

    /**
     * 新建规格
     * @param specificationattributeReqDTO
     * @return
     */
    public int create(SpecificationattributeReqDTO specificationattributeReqDTO);

    /**
     * 编辑规格
     * @param updateSpecificationNameReqDTO
     * @return
     */
    public int update(UpdateSpecificationNameReqDTO updateSpecificationNameReqDTO);


    /**
     * 删除规格
     * @param deleteSpecificationReqDTO
     * @return
     */
    @Transactional
    public int delete(DeleteSpecificationReqDTO deleteSpecificationReqDTO);


    /**
     * 查询
     * @return
     */
    public List<SpecificationattributeResDTO> getList(SpecificationattributeParentListReqDTO specificationattributeParentListReqDTO);

    /**
     * 查询列表
     * @return
     */
    public PageInfo<SpecificationattributeResDTO> list(SpecificationattributeListReqDTO specificationattributeListReqDTO);

    /**
     * 查询父级列表
     * @return
     */
    public List<SpecificationattributeResDTO> parentlist(ParentSpecificationattributeReqDTO specificationattributeListReqDTO);


}
