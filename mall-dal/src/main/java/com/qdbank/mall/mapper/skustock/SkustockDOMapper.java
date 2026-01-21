package com.qdbank.mall.mapper.skustock;

import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.skustock.SkustockDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SkustockDOMapper {
    long countByExample(SkustockDOExample example);

    int deleteByExample(SkustockDOExample example);

    int deleteByPrimaryKey(Long productSkuId);

    int insert(SkustockDO record);

    int insertSelective(SkustockDO record);

    List<SkustockDO> selectByExample(SkustockDOExample example);

    SkustockDO selectByPrimaryKey(Long productSkuId);

    List<SkustockDO> selectByProductId(Long productId);

    int updateByExampleSelective(@Param("record") SkustockDO record, @Param("example") SkustockDOExample example);

    int updateByExample(@Param("record") SkustockDO record, @Param("example") SkustockDOExample example);

    int updateByPrimaryKeySelective(SkustockDO record);

    int updateByProductSkuId(SkustockDO record);

    int updateByPrimaryKey(SkustockDO record);

    int updateSkuStockById(@Param("stockNum") Long stockNum,@Param("productSkuId") Long productSkuId,@Param("plusFlag") boolean plusFlag);

    /**
     * 根据商品id更新规格信息数据
     * @param skustockDO    规格信息
     * @return  更新结果
     */
    int updateByProductId(SkustockDO skustockDO);

    /**
     * 将指定商品的规格部分信息置空处理
     * @param productId 商品id
     * @return  更新结果
     */
    int resetProductSkuInfoByProductId(@Param("productId") Long productId);

    List<SkustockDO>selectAllByProductId(@Param("productId") Long productId);
}