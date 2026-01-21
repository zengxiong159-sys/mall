package com.qdbank.mall.dao.virtual;

import com.qdbank.mall.mapper.mobileSku.MobileSkuDOMapper;
import com.qdbank.mall.mapper.mobileSkuProvince.MobileSkuProvinceDOMapper;
import com.qdbank.mall.mapper.rechargeMobile.RechargeMobileDOMapper;
import com.qdbank.mall.mapper.rechargeMobileFlow.RechargeMobileFlowDOMapper;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.mobileSku.MobileSkuDOExample;
import com.qdbank.mall.model.mobileSkuProvince.MobileSkuProvinceDO;
import com.qdbank.mall.model.mobileSkuProvince.MobileSkuProvinceDOExample;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDO;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDOExample;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDO;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDOExample;
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
public class MobileDao {

    @Autowired
    private MobileSkuDOMapper mobileSkuDOMapper;


    @Autowired
    private RechargeMobileDOMapper rechargeMobileDOMapper;

    @Autowired
    private MobileSkuProvinceDOMapper mobileSkuProvinceDOMapper;

    @Autowired
    private RechargeMobileFlowDOMapper rechargeMobileFlowDOMapper;


    /**
     * 更新手机信息
     * @param dto
     * @param custNo
     * @return
     */
    public int updateMobile(RechargeMobileDO dto,String custNo){
        RechargeMobileDOExample example = new RechargeMobileDOExample();
        RechargeMobileDOExample.Criteria criteria  =example.createCriteria();
        criteria.andCustNoEqualTo(Long.parseLong(custNo));
       int count =rechargeMobileDOMapper.updateByExampleSelective(dto,example);
       return count;
    }


    /**
     * 创建手机号记录信息
     * @param dto
     * @return
     */
    public int createMobile(RechargeMobileDO dto){
        int count =rechargeMobileDOMapper.insert(dto);
        return count;
    }


    /**
     * 通过客户号查询历史信息
     * @param custNo
     * @return
     */
    public  RechargeMobileDO qryMobileByCustNo(String custNo){
        RechargeMobileDOExample example = new RechargeMobileDOExample();
        RechargeMobileDOExample.Criteria criteria  =example.createCriteria();
        criteria.andCustNoEqualTo(Long.parseLong(custNo));
        List<RechargeMobileDO> list =rechargeMobileDOMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    public List<MobileSkuDO> qryMobileSku(Long status){
        MobileSkuDOExample example = new MobileSkuDOExample();
        MobileSkuDOExample.Criteria criteria  =example.createCriteria();

        if(status!=null){
         criteria.andStatusEqualTo(status);
        }
        //面值排序
        example.setOrderByClause("SUPPLY_PRICE");
        List<MobileSkuDO> mobileSkus =mobileSkuDOMapper.selectByExample(example);
        return mobileSkus;
    }



    /**
     * 更新手机信息
     * @param dto
     * @return
     */
    public int updateMobileSku(MobileSkuDO dto,String supplyProductId){
        MobileSkuDOExample example = new MobileSkuDOExample();
        MobileSkuDOExample.Criteria criteria  =example.createCriteria();
        criteria.andSupplyProductIdEqualTo(supplyProductId);
        int count =mobileSkuDOMapper.updateByExampleSelective(dto,example);
        return count;
    }


    /**
     * 创建手机号记录信息
     * @param dto
     * @return
     */
    public int createMobileSku(MobileSkuDO dto){
        int count =mobileSkuDOMapper.insert(dto);
        return count;
    }


    /**
     * 归属地
     * @param status
     * @return
     */
    public List<MobileSkuProvinceDO> qryMobileSkuProvince(Long status){
        MobileSkuProvinceDOExample example = new MobileSkuProvinceDOExample();
        MobileSkuProvinceDOExample.Criteria criteria  =example.createCriteria();

        if(status!=null){
            criteria.andStatusEqualTo(status);
        }
        List<MobileSkuProvinceDO> mobileSkus =mobileSkuProvinceDOMapper.selectByExample(example);
        return mobileSkus;
    }



    /**
     * 更新手机归属地信息
     * @param dto
     * @param mobileSkuId 规格基本信息id
     * @param mobileAddress 归属地
     * @return
     */
    public int updateMobileSkuProvince(MobileSkuProvinceDO dto,Long mobileSkuId,String mobileAddress){
        MobileSkuProvinceDOExample example = new MobileSkuProvinceDOExample();
        MobileSkuProvinceDOExample.Criteria criteria  =example.createCriteria();
        criteria.andMobileSkuIdEqualTo(mobileSkuId);
        criteria.andMobileAddressEqualTo(mobileAddress);
        int count =mobileSkuProvinceDOMapper.updateByExampleSelective(dto,example);
        return count;
    }


    /**
     * 创建手机号归属地记录信息
     * @param dto
     * @return
     */
    public int createMobileSkuProvince(MobileSkuProvinceDO dto){
        int count =mobileSkuProvinceDOMapper.insert(dto);
        return count;
    }



    /**
     * 更新手机信息
     * @param dto
     * @return
     */
    public int updateMobileFlow(RechargeMobileFlowDO dto, String orderSn){
        RechargeMobileFlowDOExample example = new RechargeMobileFlowDOExample();
        RechargeMobileFlowDOExample.Criteria criteria  =example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        int count =rechargeMobileFlowDOMapper.updateByExampleSelective(dto,example);
        return count;
    }


    /**
     * 创建手机号记录信息
     * @param dto
     * @return
     */
    public int createMobileFlow(RechargeMobileFlowDO dto){
        int count =rechargeMobileFlowDOMapper.insert(dto);
        return count;
    }


    /**
     * 通过客户号查询历史信息
     * @param orderSn
     * @return
     */
    public  RechargeMobileFlowDO qryMobileFlowByOrderSn(String orderSn){
        RechargeMobileFlowDOExample example = new RechargeMobileFlowDOExample();
        RechargeMobileFlowDOExample.Criteria criteria  =example.createCriteria();
        criteria.andOrderSnEqualTo(orderSn);
        List<RechargeMobileFlowDO> list =rechargeMobileFlowDOMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }









}