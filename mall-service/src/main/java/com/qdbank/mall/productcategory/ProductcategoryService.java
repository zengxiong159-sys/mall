package com.qdbank.mall.productcategory;

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
public interface ProductcategoryService {
    /**
     * 创建商品分类
     * @param productCategoryReqDTO
     * @return
     */
    public int create(ProductCategoryReqDTO productCategoryReqDTO);

    /**
     * 修改商品分类信息
     * @param updateProductCategoryReqDTO
     * @return
     */
    public int update(UpdateProductCategoryReqDTO updateProductCategoryReqDTO);

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    @Transactional
    public int delete(Long id);

    /**
     * 获取父级分类列表
     * @param parentId
     * @return
     */
    public List<ProductCategoryResDTO> list(Long parentId);

    /**
     * 获取分类详情
     * @param id
     * @return
     */
    public ProductCategoryResDTO detail(Long id);

    /**
     * 获取所有分类，包括子分类
     * @param
     * @return
     */
    public List<ProductCategoryResDTO> listWithChildren();

    /**
     * 获取卡券分类
     * @param
     * @return
     */
    public List<ProductCategoryResDTO> couponCategory();
}
