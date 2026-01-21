package com.qdbank.mall.mapper.ods;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface OdsDOMapper {
    int deleteOdsOrder(@Param("date") Date date);
    int deleteOdsOrderRefund(@Param("date") Date date);
    int deleteOdsProduct(@Param("date") Date date);
    int deleteOdsSkuStock(@Param("date") Date date);
    int deleteOdsCoupon(@Param("date") Date date);
    int deleteOdsUserCoupon(@Param("date") Date date);
    int insertOdsOrder(@Param("date") Date date);
    int insertOdsRefundOrder(@Param("date") Date date);
    int insertOdsSmsCoupon(@Param("date") Date date);
    int insertUserCoupon(@Param("date") Date date);
    int insertProduct(@Param("date") Date date);
    int insertSkuStock(@Param("date") Date date);
}
