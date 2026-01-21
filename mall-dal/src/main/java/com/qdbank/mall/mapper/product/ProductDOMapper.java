package com.qdbank.mall.mapper.product;

import com.qdbank.mall.model.product.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDOMapper {
    long countByExample(ProductDOExample example);

    int deleteByExample(ProductDOExample example);

    int deleteByPrimaryKey(Long productId);

    List<String> selectByMerchantNo(Long merchantNo);

    List<ProductDO> selectIdAndNameByMerchantNo(Long merchantNo);

    int insert(ProductDO record);

    int insertSelective(ProductDO record);

    List<ProductDO> selectByExampleWithBLOBs(ProductDOExample example);

    List<ProductDO> selectByExample(ProductDOExample example);

    ProductDO selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") ProductDO record, @Param("example") ProductDOExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductDO record, @Param("example") ProductDOExample example);

    int updateByExample(@Param("record") ProductDO record, @Param("example") ProductDOExample example);

    int updateByPrimaryKeySelective(ProductDO record);

    int updateByPrimaryKeyWithBLOBs(ProductDO record);

    int updateByPrimaryKey(ProductDO record);

    List<ProductInfoDO> selectProductInfo(ProductInfoQueryDO productInfoQueryDO);

    List<ProductSkuDO> selectProductSkuInfo(ProductInfoQueryDO productInfoQueryDO);

    List<ProductSkuDO> selectProductDetailSkuInfo(ProductInfoQueryDO productInfoQueryDO);

    ProductInfoDO selectProductDetail(ProductInfoQueryDO productInfoQueryDO);

    /**
     *
     * @param templateId
     * @return
     */
    Long selectTemplateProduct(@Param("templateId") Long templateId, @Param("publishStatus") Long publishStatus);

    /**
     * 解除禁用状态模板且商品状态待入库关联关系
     * @param templateId
     * @return
     */
    int updateTemplateProduct(@Param("templateId") Long templateId);

    List<ProductDO> selectProductInfoNew(ProductInfoQueryDO productInfoQueryDO);
}