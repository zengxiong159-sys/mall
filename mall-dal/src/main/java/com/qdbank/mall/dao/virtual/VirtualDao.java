package com.qdbank.mall.dao.virtual;

import com.qdbank.mall.mapper.mobileSku.MobileSkuDOMapper;
import com.qdbank.mall.mapper.mobileSkuProvince.MobileSkuProvinceDOMapper;
import com.qdbank.mall.mapper.product.VirtualProductDOMapper;
import com.qdbank.mall.mapper.rechargeMobile.RechargeMobileDOMapper;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.mobileSku.MobileSkuDOExample;
import com.qdbank.mall.model.mobileSkuProvince.MobileSkuProvinceDO;
import com.qdbank.mall.model.mobileSkuProvince.MobileSkuProvinceDOExample;
import com.qdbank.mall.model.product.VirtualProductDO;
import com.qdbank.mall.model.product.VirtualProductDOExample;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDO;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDOExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class VirtualDao {

    @Autowired
    private VirtualProductDOMapper virtualProductDOMapper;




    /**
     * 查询虚拟商品
     */
    public List<VirtualProductDO> qryVirtualProducts(){
        VirtualProductDOExample example = new VirtualProductDOExample();
        List<VirtualProductDO> virtualProducts =virtualProductDOMapper.selectByExample(example);
        return virtualProducts;
    }

}