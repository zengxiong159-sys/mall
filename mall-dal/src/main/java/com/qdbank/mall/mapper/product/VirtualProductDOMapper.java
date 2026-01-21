package com.qdbank.mall.mapper.product;

import com.qdbank.mall.model.product.VirtualProductDO;
import com.qdbank.mall.model.product.VirtualProductDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VirtualProductDOMapper {
    long countByExample(VirtualProductDOExample example);

    int deleteByExample(VirtualProductDOExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(VirtualProductDO record);

    int insertSelective(VirtualProductDO record);

    List<VirtualProductDO> selectByExample(VirtualProductDOExample example);

    VirtualProductDO selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") VirtualProductDO record, @Param("example") VirtualProductDOExample example);

    int updateByExample(@Param("record") VirtualProductDO record, @Param("example") VirtualProductDOExample example);

    int updateByPrimaryKeySelective(VirtualProductDO record);

    int updateByPrimaryKey(VirtualProductDO record);
}