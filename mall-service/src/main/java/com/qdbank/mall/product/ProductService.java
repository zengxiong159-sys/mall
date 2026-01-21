package com.qdbank.mall.product;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.CommonStringReqDTO;
import com.qdbank.mall.request.product.*;
import com.qdbank.mall.request.productpicurl.UpLoadPictureReqDTO;
import com.qdbank.mall.response.product.*;
import com.qdbank.mall.response.productpicurl.UpLoadPictureResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    /**
     * 获取商品列表
     * @param productLikeQueryReqDTO
     * @return
     */
    public PageInfo<ProductResDTO> list(ProductLikeQueryReqDTO productLikeQueryReqDTO);

    /**
     * 修改商品状态
     * @return
     */
    public int updateStatus(UpdateProductStatusReqDTO updateProductStatusReqDTO);

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    public ProductResDTO detail(Long id);

    /**
     * 商品上架设置
     * @param
     * @return
     */
    @Transactional
    public int upProduct(CommonStringReqDTO commonStringReqDTO);

    /**
     * 获取商品规格信息列表
     * @param productId
     * @return
     */
    public List<SkustockResDTO>  skustockList(Long productId);

    /**
     * 创建商品
     * @param productReqDTO
     * @return
     */
    public int create(ProductReqDTO productReqDTO);

    /**
     * 编辑商品
     * @param updateProductReqDTO
     * @return
     */
    public int update(UpdateProductReqDTO updateProductReqDTO);

    /**
     * 商品详情-上传图片
     * @param updatePicturesReqDTO
     * @return
     */
    public UpLoadPictureResDTO uploadPictures(UpLoadPictureReqDTO updatePicturesReqDTO);

    /**
     * 查询商品+库存信息
     * @param productLikeQueryReqDTO
     * @return
     */
    PageInfo<ProductSkuResDTO> listProductSkuInfo(ProductLikeQueryReqDTO productLikeQueryReqDTO);

    /**
     * 获取商品规格详情
     * @param productLikeQueryReqDTO
     * @return
     */
    ProductSkuResDTO getProductSkuDetail(ProductLikeQueryReqDTO productLikeQueryReqDTO);

    /**
     * 获取商品分类名称
     * @param productCategoryID
     * @return
     */
    public String productCategoryName(ProductCategoryID productCategoryID);

    public PageInfo<ProductResDTO> listNew(ProductLikeQueryReqDTO productLikeQueryReqDTO);

    /**
     * 根据ID获取商品上架状态
     * @param productId
     * @return
     */
    public ProductPublishStatusResDTO getPublishStatus(Long productId);

    /**
     * 根据id批量查询商品信息
     * @param productIdsReqDTO
     * @return
     */
    public List<ProductIdsResDTO> queryProductByIds(ProductIdsReqDTO productIdsReqDTO);
}
