package com.qdbank.mall.productcategory;

import com.qdbank.mall.model.productcategory.ProductcategoryDO;
import com.qdbank.mall.request.productcategory.ProductCategoryReqDTO;
import com.qdbank.mall.request.productcategory.UpdateProductCategoryReqDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryResDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryWithChildrenResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ProductcategoryService
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/28 9:33
 * @Version 1.0
 **/
public interface ProductcategoryCacheService {
    /**
     * 商品分类信息注入缓存
     * @param productCategoryDO
     */
    public void setProductCategoryDO(ProductcategoryDO productCategoryDO);

    /**
     * 根据id缓存中获取商品分类信息
     * @param id
     * @return
     */
    public ProductcategoryDO getProductcategoryById(Long id);

    /**
     * 删除缓存中商品分类信息
     * @param id
     */
    public void delProductcategory(Long id);

}
