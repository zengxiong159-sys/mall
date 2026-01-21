package com.qdbank.mall.mobile.mapper;

import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.mobile.MobileSkuResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MobileMapper {

    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    MobileSkuResDTO poToDTO(MobileSkuDO mobileSkuDO);


    @Mappings({
            /*@Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),
            @Mapping(source = "",target = ""),*/
    })
    List<MobileSkuResDTO> poToDTOList(List<MobileSkuDO> mobileSkuDO);


}
