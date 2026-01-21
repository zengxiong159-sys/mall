package com.qdbank.mall.mapper.integralcoupon;

import com.qdbank.mall.model.integralcoupon.IntegralcouponDO;
import com.qdbank.mall.model.integralcoupon.IntegralcouponDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralcouponDOMapper {
    long countByExample(IntegralcouponDOExample example);

    int deleteByExample(IntegralcouponDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IntegralcouponDO record);

    int insertSelective(IntegralcouponDO record);

    List<IntegralcouponDO> selectByExample(IntegralcouponDOExample example);

    IntegralcouponDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IntegralcouponDO record, @Param("example") IntegralcouponDOExample example);

    int updateByExample(@Param("record") IntegralcouponDO record, @Param("example") IntegralcouponDOExample example);

    int updateByPrimaryKeySelective(IntegralcouponDO record);

    int updateByPrimaryKey(IntegralcouponDO record);
}