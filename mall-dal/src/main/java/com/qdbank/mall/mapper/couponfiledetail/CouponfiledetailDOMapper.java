package com.qdbank.mall.mapper.couponfiledetail;

import com.qdbank.mall.model.couponfiledetail.CouponfiledetailDO;
import com.qdbank.mall.model.couponfiledetail.CouponfiledetailDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponfiledetailDOMapper {
    long countByExample(CouponfiledetailDOExample example);

    int deleteByExample(CouponfiledetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CouponfiledetailDO record);

    int insertSelective(CouponfiledetailDO record);

    List<CouponfiledetailDO> selectByExample(CouponfiledetailDOExample example);

    CouponfiledetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CouponfiledetailDO record, @Param("example") CouponfiledetailDOExample example);

    int updateByExample(@Param("record") CouponfiledetailDO record, @Param("example") CouponfiledetailDOExample example);

    int updateByPrimaryKeySelective(CouponfiledetailDO record);

    int updateByPrimaryKey(CouponfiledetailDO record);
}