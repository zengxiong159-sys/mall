package com.qdbank.mall.mapper.productskuattrrelation;

import com.qdbank.mall.model.productskuattrrelation.ProductSkuAttrRelationDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zengxiong
 * @Description 商品规格和规格属性关系
 * @Date 2021/6/7 17:37
 */
@Repository
public interface ProductSkuAttrRelationMapper {
    /**
     * 新增关联数据
     *
     * @param productSkuAttrRelationDO 商品规格和规格属性关系
     * @return 新增结果条数
     */
    int insert(ProductSkuAttrRelationDO productSkuAttrRelationDO);

    /**
     * 删除关联关系数据
     * @param productSkuAttrRelationDO  商品规格和规格属性关系
     */
    void delete(ProductSkuAttrRelationDO productSkuAttrRelationDO);

    /**
     * 根据指定条件查询商品规格和规格属性关系
     * @param productSkuAttrRelationDO  商品规格和规格属性关系
     * @return  商品规格和规格属性关系结果
     */
    List<ProductSkuAttrRelationDO> select(ProductSkuAttrRelationDO productSkuAttrRelationDO);

    /**
     * 根据规格属性id查询指定商品状态(已上架、已入库、待入库等)的商品-规格关联信息
     * @param attributeId   属性id
     * @param productStatusList 商品状态(支持多种状态)
     * @return  该规格属性对应商品状态的商品-规格关联信息
     */
    List<ProductSkuAttrRelationDO> selectByAttrIdAndProductStatus(@Param("attributeId") Long attributeId, @Param("productStatusList") List<Long> productStatusList);
}
