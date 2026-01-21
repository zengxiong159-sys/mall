package com.qdbank.mall.mapper.productpicurl;

import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductPicUrlDOMapper {
    long countByExample(ProductPicUrlDOExample example);

    int deleteByExample(ProductPicUrlDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductPicUrlDO record);

    int insertSelective(ProductPicUrlDO record);

    List<ProductPicUrlDO> selectByExample(ProductPicUrlDOExample example);

    ProductPicUrlDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductPicUrlDO record, @Param("example") ProductPicUrlDOExample example);

    int updateByExample(@Param("record") ProductPicUrlDO record, @Param("example") ProductPicUrlDOExample example);

    int updateByPrimaryKeySelective(ProductPicUrlDO record);

    int updateByPrimaryKey(ProductPicUrlDO record);

    /**
     * 查询出待删除图片数据
     * @return
     */
    List<ProductPicUrlDO>selectExpirePicUrl();
}