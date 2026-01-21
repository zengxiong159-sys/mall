package com.qdbank.mall.mobile;

import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.MobileRechargeStatus;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDO;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.coupon.CouponQueryReqDTO;
import com.qdbank.mall.request.coupon.UserCouponReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.mobile.MobileLocationResDTO;
import com.qdbank.mall.response.mobile.MobileSkuResDTO;
import com.qdbank.mall.response.order.OrderResDTO;

import java.util.List;

public interface MobileService {


    boolean createMobileFlow(Long custNo, String mobile, Long mobileSkuId, String orderSn, MobileRechargeStatus status,String wxOrderId);

    boolean updateMobileFlow(RechargeMobileFlowDO dto, String orderSn);

    /*boolean updateCustMobile(String custNo, Long mobile, Long mobileSkuId);*/

    List<MobileSkuResDTO>  qryMobileSkus();

    boolean updateMobileSku(String supplyProductId, Long status);

    OrderResDTO qryMobileByCustNo(String custNo, ProductEnum productEnum);

    List<MobileSkuDO>  qryMobileSkus(Long status);

    void noticMobileSkuProvinceStatus(String operatorStr, String provinceStr, String priceStr, Long status);

    MobileLocationResDTO mobileLocation(String phone);

    /**
     * 通过运营商喝mobileskuid 查询
     * @param supplier
     * @param mobileSkuId
     * @return
     */
    MobileSkuDO   qryMobileSkuByMobileSkuId(String supplier, Long mobileSkuId);

    MobileSkuDO   qryMobileSkuByMobileSkuId(Long mobileSkuId);

    MobileSkuDO   qryMobileSkuBySuppSkuId(String suppMobileSkuId);

    List<MobileSkuResDTO>   qryeffectiveMobileSkus(String province, String supplier);
}
