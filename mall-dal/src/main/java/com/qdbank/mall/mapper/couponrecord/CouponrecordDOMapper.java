package com.qdbank.mall.mapper.couponrecord;

import com.qdbank.mall.model.couponrecord.CouponrecordDO;
import com.qdbank.mall.model.couponrecord.CouponrecordDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponrecordDOMapper {
    long countByExample(CouponrecordDOExample example);

    int deleteByExample(CouponrecordDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CouponrecordDO record);

    int insertSelective(CouponrecordDO record);

    List<CouponrecordDO> selectByExample(CouponrecordDOExample example);

    CouponrecordDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CouponrecordDO record, @Param("example") CouponrecordDOExample example);

    int updateByExample(@Param("record") CouponrecordDO record, @Param("example") CouponrecordDOExample example);

    int updateByPrimaryKeySelective(CouponrecordDO record);

    int updateByPrimaryKey(CouponrecordDO record);
}