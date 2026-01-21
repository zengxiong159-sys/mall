package com.qdbank.mall.prefecture.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.SystemServiceEnum;
import com.qdbank.mall.bo.VirtualConfig;
import com.qdbank.mall.config.BsnConfig;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.enums.SpecificationAttributeStatusEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.prefecturestockrelation.PrefectureStockRelationDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.productpicurl.ProductPicUrlDOMapper;
import com.qdbank.mall.mapper.productskuattrrelation.ProductSkuAttrRelationMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.mapper.specificationattribute.SpecificationattributeDOMapper;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.productskuattrrelation.ProductSkuAttrRelationDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.specificationattribute.SpecificationattributeDO;
import com.qdbank.mall.model.specificationattribute.SpecificationattributeDOExample;
import com.qdbank.mall.order.business.VirtualConfigHandler;
import com.qdbank.mall.prefecture.ProductService;
import com.qdbank.mall.request.product.ProductLikeQueryReqDTO;
import com.qdbank.mall.request.product.ProductPictureReqDTO;
import com.qdbank.mall.request.product.ProductReqDTO;
import com.qdbank.mall.request.product.UpdateProductReqDTO;
import com.qdbank.mall.request.skustock.SkustockReqDTO;
import com.qdbank.mall.request.specificationattribute.SpecificationExtendReqDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.SpringContextUtils;
import com.qdbank.mall.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

import static cn.hutool.core.util.StrUtil.COMMA;
import static cn.hutool.core.util.StrUtil.C_UNDERLINE;
import static com.qdbank.mall.constant.Constant.*;
import static java.util.stream.Collectors.toList;

/**
 * @ClassName ProductServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/2 16:58
 * @Version 1.0
 **/
@Service
@Slf4j
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {
    @Autowired
    private ProductDOMapper productDOMapper;
    @Resource
    private SkustockDOMapper skustockDOMapper;


    @Autowired
    VirtualConfigHandler virtualConfigHandler;

    @PostConstruct
    public void qryProducts(){
        //[{"油卡规格":"100"},{"运营商":"中石油"}]

        List<ProductSkuResDTO> products = this.listProductSkuInfo(ProductEnum.OIL_CARD);

        System.out.println(products);


    }


    @Override
    public List<ProductSkuResDTO> listProductSkuInfo(ProductEnum productEnum) {
        VirtualConfig config = virtualConfigHandler.getVirtualConfig(productEnum);
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        Map categegoty = config.getCategegoty();
        productInfoQueryDO.setCategoryId1(Long.parseLong(categegoty.get("cid1").toString()));
        productInfoQueryDO.setCategoryId2(Long.parseLong(categegoty.get("cid2").toString()));
        productInfoQueryDO.setCategoryId3(Long.parseLong( categegoty.get("cid3").toString()));
        productInfoQueryDO.setCategoryId4(Long.parseLong( categegoty.get("cid4").toString()));

        List<ProductSkuDO> productSkuDOS = productDOMapper.selectProductSkuInfo(productInfoQueryDO);
        List<ProductSkuResDTO> productSkuResDTOList = new ArrayList<>();
        for(ProductSkuDO productSkuDO : productSkuDOS){
            ProductSkuResDTO productSkuResDTO = new ProductSkuResDTO();
            BeanUtils.copyProperties(productSkuDO,productSkuResDTO);
            List<SkustockDO> skustocks = productSkuDO.getSkustocks();
            List<SkustockResDTO> skustockResDTOList = new ArrayList<>();
            for(SkustockDO skustockDO : skustocks){
                SkustockResDTO skustockResDTO = new SkustockResDTO();
                BeanUtils.copyProperties(skustockDO,skustockResDTO);
                skustockResDTOList.add(skustockResDTO);
            }
            productSkuResDTO.setSkustocks(skustockResDTOList);
            productSkuResDTOList.add(productSkuResDTO);
        }
        return productSkuResDTOList;
    }

}
