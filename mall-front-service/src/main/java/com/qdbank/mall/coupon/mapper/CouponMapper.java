package com.qdbank.mall.coupon.mapper;

import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.prefecture.PrefectureResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    UserCouponResDTO poToDTO(UserCouponDetailDO userCouponDetailDO);


    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    CouponResDTO cpoToDTO(CouponDO couponDO);



    List<UserCouponResDTO> poToDtoList(List<UserCouponDetailDO> dos);


    List<CouponResDTO> cpoToDtoList(List<CouponDO> dos);


}
