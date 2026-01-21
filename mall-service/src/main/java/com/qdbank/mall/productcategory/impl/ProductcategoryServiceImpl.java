package com.qdbank.mall.productcategory.impl;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.productcategory.ProductcategoryDOMapper;
import com.qdbank.mall.model.productcategory.ProductcategoryDO;
import com.qdbank.mall.model.productcategory.ProductcategoryDOExample;
import com.qdbank.mall.productcategory.ProductcategoryCacheService;
import com.qdbank.mall.productcategory.ProductcategoryService;
import com.qdbank.mall.request.productcategory.ProductCategoryReqDTO;
import com.qdbank.mall.request.productcategory.UpdateProductCategoryReqDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryResDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryWithChildrenResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ProductcategoryServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/28 9:33
 * @Version 1.0
 **/
@Service
@Slf4j
@RefreshScope
public class ProductcategoryServiceImpl extends BaseServiceImpl implements ProductcategoryService {
    @Autowired
    private ProductcategoryDOMapper productcategoryDOMapper;
    @Autowired
    @Qualifier("umsAdminServiceImpl")
    private UmsAdminService umsAdminService;
    @Autowired
    private ProductcategoryCacheService productcategoryCacheService;

    @Value(value = "${bsn.products.categegoty.cid1}")
    private Long virsualCid;

    @Override
    public int create(ProductCategoryReqDTO productCategoryReqDTO) {
        ProductcategoryDO productcategoryDO = new ProductcategoryDO();
        BeanUtils.copyProperties(productCategoryReqDTO,productcategoryDO);
        productcategoryDO.setId(super.generateId());
        productcategoryDO.setCategorySort(0L);
        umsAdminService.injectUserValue(productcategoryDO);
        return productcategoryDOMapper.insert(productcategoryDO);
    }

    @Override
    public int update(UpdateProductCategoryReqDTO updateProductCategoryReqDTO) {
        ProductcategoryDO productcategoryDO = new ProductcategoryDO();
        BeanUtils.copyProperties(updateProductCategoryReqDTO,productcategoryDO);
        umsAdminService.injectUpdateUserValue(productcategoryDO);
        ProductcategoryDOExample productcategoryDOExample = new ProductcategoryDOExample();
        productcategoryDOExample.createCriteria().andIdEqualTo(updateProductCategoryReqDTO.getId());
        productcategoryCacheService.delProductcategory(updateProductCategoryReqDTO.getId());
        return productcategoryDOMapper.updateByExampleSelective(productcategoryDO,productcategoryDOExample);
    }

    @Override
    public int delete(Long id) {
        ProductcategoryDO productcategoryDO = productcategoryDOMapper.selectByPrimaryKey(id);
        if(productcategoryDO == null) throw  new ApiException(ResultCode.DELETE_CATEGORY_ID_NOT_EXIST);
        this.deleteWithChildren(productcategoryDO.getId());
        productcategoryDOMapper.deleteByPrimaryKey(id);
        productcategoryCacheService.delProductcategory(id);
        return 1;
    }

    private void deleteWithChildren(Long parentId){
        ProductcategoryDOExample productcategoryDOExample = new ProductcategoryDOExample();
        productcategoryDOExample.createCriteria().andParentIdEqualTo(parentId);
        List<ProductcategoryDO> productcategoryDOList = productcategoryDOMapper.selectByExample(productcategoryDOExample);
        for(ProductcategoryDO productcategoryDO : productcategoryDOList){
            productcategoryDOMapper.deleteByPrimaryKey(productcategoryDO.getId());
            productcategoryCacheService.delProductcategory(productcategoryDO.getId());
            deleteWithChildren(productcategoryDO.getId());
        }
    }

    @Override
    public List<ProductCategoryResDTO> list(Long parentId) {
        ProductcategoryDOExample productcategoryDOExample = new ProductcategoryDOExample();
        productcategoryDOExample.createCriteria().andParentIdEqualTo(parentId);
        List<ProductcategoryDO> productcategoryDOS = productcategoryDOMapper.selectByExample(productcategoryDOExample);
        List<ProductCategoryResDTO> productCategoryResDTOList = new ArrayList<>();
        for (ProductcategoryDO productcategoryDO : productcategoryDOS){
            ProductCategoryResDTO productCategoryResDTO = new ProductCategoryResDTO();
            BeanUtils.copyProperties(productcategoryDO,productCategoryResDTO);
            productCategoryResDTOList.add(productCategoryResDTO);
        }
        return productCategoryResDTOList;
    }

    @Override
    public ProductCategoryResDTO detail(Long id) {
        ProductcategoryDO productcategoryDO = null;
        productcategoryDO = productcategoryCacheService.getProductcategoryById(id);
        if(productcategoryDO == null){
            productcategoryDO = productcategoryDOMapper.selectByPrimaryKey(id);
            if(productcategoryDO != null) productcategoryCacheService.setProductCategoryDO(productcategoryDO);
        }
        ProductCategoryResDTO productCategoryResDTO = new ProductCategoryResDTO();
        BeanUtils.copyProperties(productcategoryDO,productCategoryResDTO);
        return productCategoryResDTO;
    }

    @Override
    public List<ProductCategoryResDTO> listWithChildren() {
        List<ProductcategoryDO> productcategoryDOList = productcategoryDOMapper.selectByExample(null);
        List<ProductCategoryResDTO> productCategoryResDTOList = new ArrayList<>();
        for (ProductcategoryDO productcategoryDO : productcategoryDOList){
            ProductCategoryResDTO productCategoryResDTO = new ProductCategoryResDTO();
            BeanUtils.copyProperties(productcategoryDO,productCategoryResDTO);
            productCategoryResDTOList.add(productCategoryResDTO);
        }
        List<ProductCategoryResDTO> resDTOList = productCategoryResDTOList.stream().filter(
                //已上架，非虚拟产品
                poductCategoryResDTO -> poductCategoryResDTO.getParentId().equals(0L) && !virsualCid.equals(poductCategoryResDTO.getId())
        ).map(poductCategoryResDTO -> covertProductCategory(poductCategoryResDTO,productCategoryResDTOList)).collect(Collectors.toList());
        return resDTOList;
    }

    @Override
    public List<ProductCategoryResDTO> couponCategory() {
        List<ProductCategoryResDTO> parentCategoryList = new ArrayList<ProductCategoryResDTO>();
        List<ProductCategoryResDTO> categoryList = new ArrayList<ProductCategoryResDTO>();
        ProductCategoryResDTO productCategoryResDTO = new ProductCategoryResDTO();
        ProductcategoryDO parentcategoryDO = productcategoryDOMapper.selectByPrimaryKey(99620061278502912L);
        BeanUtils.copyProperties(parentcategoryDO,productCategoryResDTO);
        ProductcategoryDO categoryDO = productcategoryDOMapper.selectByPrimaryKey(99622444788219904L);
        ProductCategoryResDTO categoryResDTO = new ProductCategoryResDTO();
        BeanUtils.copyProperties(categoryDO,categoryResDTO);
        categoryList.add(categoryResDTO);
        ProductcategoryDO childCategoryDO = productcategoryDOMapper.selectByPrimaryKey(99627057524375552L);
        ProductCategoryResDTO childCategoryResDTO = new ProductCategoryResDTO();
        BeanUtils.copyProperties(childCategoryDO,childCategoryResDTO);
        List<ProductCategoryResDTO> childCategoryList = new ArrayList<ProductCategoryResDTO>();
        childCategoryList.add(childCategoryResDTO);
        categoryResDTO.setChildren(childCategoryList);
        productCategoryResDTO.setChildren(categoryList);
        parentCategoryList.add(productCategoryResDTO);
        return parentCategoryList;
    }

    private ProductCategoryResDTO covertProductCategory(ProductCategoryResDTO productCategoryResDTO, List<ProductCategoryResDTO> productCategoryResDTOS) {
        ProductCategoryWithChildrenResDTO productCategoryWithChildrenResDTO = new ProductCategoryWithChildrenResDTO();
        BeanUtils.copyProperties(productCategoryResDTO, productCategoryWithChildrenResDTO);
        List<ProductCategoryResDTO> children = productCategoryResDTOS.stream()
                .filter(subProductCategory -> subProductCategory.getParentId().equals(productCategoryResDTO.getId()))
                .map(subMenu -> covertProductCategory(subMenu, productCategoryResDTOS)).collect(Collectors.toList());
        if(CollUtil.isEmpty(children)){//方便前端处理 过滤子集对象字段
            return productCategoryResDTO;
        }else {
            productCategoryWithChildrenResDTO.setChildren(children);
        }
        return productCategoryWithChildrenResDTO;
    }
}
