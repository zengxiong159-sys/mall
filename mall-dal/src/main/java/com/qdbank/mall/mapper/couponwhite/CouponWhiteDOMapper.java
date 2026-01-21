package com.qdbank.mall.mapper.couponwhite;

import com.qdbank.mall.model.couponwhite.CouponWhiteDO;
import com.qdbank.mall.model.couponwhite.CouponWhiteDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponWhiteDOMapper {
    long countByExample(CouponWhiteDOExample example);

    int deleteByExample(CouponWhiteDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CouponWhiteDO record);

    int insertSelective(CouponWhiteDO record);

    List<CouponWhiteDO> selectByExample(CouponWhiteDOExample example);

    CouponWhiteDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CouponWhiteDO record, @Param("example") CouponWhiteDOExample example);

    int updateByExample(@Param("record") CouponWhiteDO record, @Param("example") CouponWhiteDOExample example);

    int updateByPrimaryKeySelective(CouponWhiteDO record);

    int updateByPrimaryKey(CouponWhiteDO record);
}