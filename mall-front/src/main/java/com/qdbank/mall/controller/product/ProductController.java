package com.qdbank.mall.controller.product;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.common.ParamsService;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.order.business.handler.RealPaymentHandler;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.prefecture.mapper.PrefectureMapper;
import com.qdbank.mall.prefecture.mapper.ProductMapper;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.response.area.FreightFeeResDTO;
import com.qdbank.mall.response.product.ProductDetailPictureResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.productpicurl.ProductPicUrlResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.util.StringUtil;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/14 19:14
 * @Version 1.0
 **/
@RestController
@Api(tags = "ProductController", description = "商品管理")
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    PrefectureService prefectureService;

    @Autowired
    PrefectureMapper prefectureMapper;

    @Autowired
    ProductMapper produtMapper;

    @Autowired
    RealPaymentHandler realPaymentHandler;


    @Autowired
    ParamsService paramsService;


    @ApiOperation("商品详细信息->商品编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<ProductResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        ProductSkuDO skuDO =prefectureService.qryProductSku(commonIDReqDTO.getId(),null,true);


        List<ProductPicUrlDO> res =skuDO.getPicUrls();

        List<ProductDetailPictureResDTO> _ps = new ArrayList<>();
        List<ProductPicUrlDO> p_ps = new ArrayList<>();
        if(!CollectionUtils.isEmpty(res)){
            res.stream().forEach(item->{
               String source = item.getFileSource();
               if("2".equals(source)){
                   ProductDetailPictureResDTO dto = new ProductDetailPictureResDTO();
                   dto.setPicUrl(item.getPicUrl());
                   _ps.add(dto);
               }else if("1".equals(source)){
                  Long mainFlag =item.getMainFlag();
                   p_ps.add(item);

               }
            });
        }
        //mainflag 排序
        p_ps.sort(Comparator.comparing(ProductPicUrlDO::getMainFlag));
        skuDO.setPicUrls(p_ps);

        ProductResDTO productResDTO=produtMapper.proPoToDTO(skuDO);

        productResDTO.setDetailPicUrls(_ps);
        //如果是实物：查询模板编号
       if(skuDO.getFreightTemplateId()!=null){
           //获取运费
           FreightFeeResDTO fee  =realPaymentHandler.freight(null,1,skuDO.getFreightTemplateId(),null);
           productResDTO.setFreightAmount(fee.getFreightAmount());
       }

       //倒计时处理
        for(SkustockResDTO skustockResDTO :productResDTO.getSkustocks()){
            Date startTime  =skustockResDTO.getPromotionStartTime();
            Date endTime =skustockResDTO.getPromotionEndTime();
            Long leftTime = this.getLeftTime(skustockResDTO.getPromotionStartTime(),skustockResDTO.getPromotionEndTime());
            skustockResDTO.setLeftTime(leftTime);
            limitBuy(skustockResDTO);
        }



        String suppiorMobile =paramsService.handlerSuppiorMobile(ProductEnum.PAYMENT_IN_KIND,productResDTO.getMerchantNo());
        productResDTO.setCustomerServicePhone(suppiorMobile);



        return CommonResult.success(productResDTO);
    }

    /**
     * 商品限购数量
     * @param item
     */
    private void limitBuy(SkustockResDTO item) {
        Long limitCount = item.getPromotionPerLimit();
        UserDetails userDetails = UserContextHolder.getUserDetails();
       Date promotionStartTime = item.getPromotionStartTime();
        Date promotionEndTime =item.getPromotionEndTime();
        item.setLimitCountFlag("0");
        Date now = new Date();
        if(userDetails!=null && StringUtils.hasText(userDetails.getCustNo()) && promotionStartTime!=null && promotionEndTime!=null && promotionStartTime.compareTo(now) <=0 && promotionEndTime.compareTo(now) >=0){
              Long  hasBuy = realPaymentHandler.hasBuyCount(userDetails.getCustNo(),promotionStartTime,promotionEndTime,item.getProductId(),item.getProductSkuId());
              item.setLimitCountFlag("1");
              log.info("限购处理：[{}]-[{}]-[{}]",new Object[]{item.getProductSkuId(),hasBuy,limitCount});
              item.setLeftLimitCount(limitCount-hasBuy);
        }
    }


    private Long getLeftTime(Date start , Date end){
        try{
            log.info("begin-end:[{}][{}]",start,end);
            Calendar calendar = Calendar.getInstance();
            Date now =calendar.getTime();
            //活动期内
            if(now.compareTo(start)>=0 && now.compareTo(end) <=0){
                return end.getTime()-now.getTime();
            }
        }catch (Exception e){
            log.warn("执行报错", e);
        }
        return 0L;
    }


}
