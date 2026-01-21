package com.qdbank.mall.mobile.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.MobileAddress;
import com.qdbank.mall.constant.payment.MobileRechargeStatus;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.constent.payment.MobileSupply;
import com.qdbank.mall.dao.virtual.MobileDao;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.mobile.mapper.MobileMapper;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.mobileSkuProvince.MobileSkuProvinceDO;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDO;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import com.qdbank.mall.response.mobile.MobileLocationResDTO;
import com.qdbank.mall.response.mobile.MobileSkuResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.third.mobile.PhoneResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName PrefectureServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class MobileServiceImpl extends BaseServiceImpl implements MobileService, InitializingBean {


    @Autowired
    private MobileDao mobileDao;

    @Autowired
    private MobileMapper mobileMapper;

    @Autowired
    MobileRechargePaymenHandler mobileRechargePaymenHandler;

    /**
     * 所有规格条目汇总
     */
    List<MobileSkuResDTO> mariPrice = new ArrayList<>();


    /**
     * 全部
     */
    Map<Long,MobileSkuDO> allMobile = new HashMap<>();

    /**
     * 全部-sku 为key
     */
    Map<String,MobileSkuDO> allMobile_sku = new HashMap<>();


    /**
     * 运营商逻辑
     */
    Map<String,Map<Long,MobileSkuDO>>  allSuppiers = new HashMap<>();

    /**
     * 省--运营商--规格
     * key：省 -- 规格ID-具体对象
     */
    Map<String,Map<Long,MobileSkuProvinceDO>> allProvince = new HashMap();

    @Autowired
    OrderService orderService;

    @Override
    public boolean createMobileFlow(Long custNo, String mobile, Long mobileSkuId, String orderSn, MobileRechargeStatus status, String wxOrderId){
        RechargeMobileFlowDO dto = new RechargeMobileFlowDO();
        Date now = new Date();
        //更新时间
        dto.setUpdateTime(now);
        dto.setMobile(mobile+"");

        RechargeMobileFlowDO flow = new RechargeMobileFlowDO();
        flow.setRechargeMobileFolwId(super.generateId());
        flow.setCustNo(custNo);
        flow.setMobile(mobile+"");
        flow.setSkuId(mobileSkuId);
        flow.setOrderSn(orderSn);
        flow.setStatus(status.code);
        flow.setWxOrderId(wxOrderId);
        //更新失败，插入记录
        int count =  mobileDao.createMobileFlow(flow);
        log.info("insert result[{}]-[{}]",count,JsonUtil.toJSONString(dto));
        return count==1;
    }

    @Override
    public boolean updateMobileFlow(RechargeMobileFlowDO dto, String orderSn){

        Date now = new Date();
        //更新时间
        dto.setUpdateTime(now);
        //更新
        int count =mobileDao.updateMobileFlow(dto,orderSn);
        log.info("update result[{}]-[{}]",count,JsonUtil.toJSONString(dto));
        return count==1;
    }


   /* @Override
    public boolean updateCustMobile(String custNo, Long mobile, Long mobileSkuId){
        RechargeMobileDO dto = new RechargeMobileDO();
        Date now = new Date();
        //更新时间
        dto.setUpdateTime(now);
        dto.setMobile(mobile);
        //更新
        int count =mobileDao.updateMobile(dto,custNo);
        log.info("update result[{}]-[{}]",count,JsonUtil.toJSONString(dto));
        if(count==0){
            dto.setRechargeMobileId(super.generateId());
            dto.setSkuId(mobileSkuId);
            dto.setCustNo(Long.parseLong(custNo));
            dto.setCreateTime(now);
            //更新失败，插入记录
            count = mobileDao.createMobile(dto);
            log.info("insert result[{}]-[{}]",count,JsonUtil.toJSONString(dto));
        }
        return count==1;
    }
*/

    @Override
    public OrderResDTO qryMobileByCustNo(String custNo, ProductEnum productEnum){
        //分页查询
        Page page = PageHelper.startPage(1,1);
        List<OrderResDTO> results =orderService.qryOrders(custNo, MobileReChargePayOrderStatus.Status.HAS_FINISH.status+"",productEnum);
        if(!CollectionUtils.isEmpty(results)){
           return results.get(0);
        }
        return null;
    }


    @Override
    public List<MobileSkuDO>  qryMobileSkus(Long status){
        return mobileDao.qryMobileSku(status);
    }

    /**
     * 获取规格列表
     * @return
     */
    @Override
    public List<MobileSkuResDTO>  qryMobileSkus(){
        return new ArrayList<>(mariPrice);
    }


    @Override
    public boolean updateMobileSku(String supplyProductId,Long status){
        MobileSkuDO dto = new MobileSkuDO();
        Date now = new Date();
        //更新时间
        dto.setUpdateTime(now);
        dto.setStatus(status);
        //更新
        int count =mobileDao.updateMobileSku(dto,supplyProductId);
        log.info("update result[{}]-[{}]",count,JsonUtil.toJSONString(dto));
        return count==1;
    }

    @Override
    public void noticMobileSkuProvinceStatus(String operatorStr, String provinceStr, String priceStr, Long status) {
        //供应商
        String[] operators = operatorStr.split(",");

        String[] prices = null;
        if ("all".equals(priceStr)) {
            //全部规格处理
            List<MobileSkuResDTO> skus = this.qryMobileSkus();
            if (!CollectionUtils.isEmpty(skus)) {
                prices = new String[skus.size()];
                for (int i = 0; i < skus.size(); i++) {
                    prices[i] = skus.get(0).getSupplyProductSize();
                }
            } else {
                return;
            }
        } else {
            //指定处理
            prices = priceStr.split(",");
        }

        String[] provinces = null;
        if ("all".equals(provinceStr)) {
            //全部城市
            MobileAddress[] mobileAddresses = MobileAddress.values();
            provinces = new String[mobileAddresses.length];
            for (int i = 0; i < mobileAddresses.length; i++) {
                provinces[i] = mobileAddresses[i].code;
            }
            //this.qryMobileSkus(Constant.MOBILE_SKU_UP);
        } else {
            provinces = provinceStr.split(",");
        }

        //为空：判断值
        if (provinces == null || provinces.length == 0 || operators == null || operators.length == 0 || prices == null || prices.length == 0) {
            return;
        }
        //操作
        for (String province : provinces) {
            for (String operate : operators) {
                for (String price : prices) {
                    try{
                        //规格拼接
                        String sku = operate + price;
                        MobileSkuDO mobileSkuDO = this.qryMobileSkuBySuppSkuId(sku);
                        if(mobileSkuDO==null){
                            log.info("手机规格不存在[{}]",sku);
                            continue;
                        }
                        MobileSkuProvinceDO dto = new MobileSkuProvinceDO();
                        Date now = new Date();
                        //更新时间
                        dto.setUpdateTime(now);
                        dto.setStatus(status);
                        //更新
                        int count = 0;
                        count=mobileDao.updateMobileSkuProvince(dto,mobileSkuDO.getMobileSkuId(),province);
                        log.info("update MobileSkuProvince result[{}]-[{}]", count, JsonUtil.toJSONString(dto));
                        if (count == 0) {
                            dto.setMobileSkuProvinceId(super.generateId());
                            dto.setMobileAddress(province);
                            dto.setCreateTime(now);
                            dto.setMobileSkuId(mobileSkuDO.getMobileSkuId());
                            StringBuilder sb = new StringBuilder();
                            //地点
                            sb.append(MobileAddress.getName(province));
                            //供应商
                            sb.append(MobileSupply.getName(operate));
                            //金额
                            sb.append(price).append("元");
                            dto.setProductName(sb.toString());
                            BigDecimal money = new BigDecimal(price);
                            dto.setSupplyPrice(money);
                            dto.setProductPrice(money);
                            //更新失败，插入记录
                            count = mobileDao.createMobileSkuProvince(dto);
                            log.info("insert MobileSkuProvince result[{}]-[{}]", count, JsonUtil.toJSONString(dto));
                        }
                    }catch (Exception e){
                        log.error("执行异常[{}][{}][{}]",new Object[]{province,operate,price},e);
                    }
                }
            }
        }

        //重置省信息
        initNotProvinces();
    }





    @Override
    public MobileLocationResDTO mobileLocation(String phone){
        MobileLocationResDTO res = new MobileLocationResDTO();
        PhoneResDTO result =mobileRechargePaymenHandler.phone(phone);
        res.setRechargeMobile(phone);
        res.setSupplierType(result.getSupplierType());
        res.setMobileAddress(result.getProvinceType());
        res.setMobileAddressDesc(MobileAddress.getName(res.getMobileAddress()));
        log.info("手机定位[{}]",JsonUtil.toJSONString(res));
        return res;
    }


    /**
     *初始化规格
     */
    public void initSkus(){
        log.info("规格初始化 begine...");
        Map<String,MobileSkuResDTO> _mariPrice = new HashMap();

        Map<String,Map<Long,MobileSkuDO>> _allSuppiers = new HashMap<>();

        Map<Long,MobileSkuDO> _allMobile = new HashMap<>();


        Map<String,MobileSkuDO>  _allMobile_sku = new HashMap<>();

        List<MobileSkuDO> skus =this.qryMobileSkus(Constant.MOBILE_SKU_UP);

        for(MobileSkuDO sku : skus){
            String supplyType = sku.getSupplyType();
            String supplyProductSize = sku.getSupplyProductSize();
            //话费充值
            if("1".equals(supplyType)){
                //规格处理
                if(!_mariPrice.containsKey(supplyProductSize)){
                    MobileSkuResDTO mobileSkuRes = new MobileSkuResDTO();
                    mobileSkuRes.setProductPrice(sku.getProductPrice());
                    mobileSkuRes.setSupplyProductSize(supplyProductSize);
                    _mariPrice.put(supplyProductSize,mobileSkuRes);
                }

                // 运营商逻辑处理
                //供应商处理
                String supplierType =sku.getSupplySupplierType();
                Map<Long,MobileSkuDO> suppier=_allSuppiers.get(supplierType);
                if(suppier==null){
                    suppier=new HashMap();
                    _allSuppiers.put(supplierType,suppier);
                }
                Long mobileSkuId=sku.getMobileSkuId();
                suppier.put(mobileSkuId,sku);

                //all
                _allMobile.put(sku.getMobileSkuId(),sku);
                _allMobile_sku.put(sku.getSupplyProductId(),sku);
            }
        }
        //    Collections.sort(_mariPrice, Comparator.comparingLong(o -> Long.parseLong((String) o)));

        log.info("初始化 end...[{}]",JsonUtil.toJSONString(_mariPrice));
        if(!CollectionUtils.isEmpty(_mariPrice)){
            synchronized (mariPrice){
                //总规格处理
                mariPrice.clear();
                mariPrice = _mariPrice.values().stream().collect(Collectors.toList());
                //排序
                Collections.sort(mariPrice, Comparator.comparingLong(o -> Long.parseLong(o.getSupplyProductSize())));
                //运营商逻辑处理
                allSuppiers.clear();
                allSuppiers.putAll(_allSuppiers);

                allMobile.clear();
                allMobile.putAll(_allMobile);

                allMobile_sku.clear();
                allMobile_sku.putAll(_allMobile_sku);
                log.info("省初始化完成 end...[{}]",JsonUtil.toJSONString(mariPrice));
                log.info("运营商初始化完成 end...[{}]",JsonUtil.toJSONString(allSuppiers));
            }
        }
    }

    /**
     * 获取充值规格
     * @return
     */
    @Override
    public  MobileSkuDO   qryMobileSkuByMobileSkuId(String supplier, Long mobileSkuId){
        //运营商对应规格列表
        return  this.allSuppiers.get(supplier).get(mobileSkuId);
    }

    /**
     * 获取充值规格
     * @return
     */
    @Override
    public  MobileSkuDO   qryMobileSkuByMobileSkuId(Long mobileSkuId){
        //运营商对应规格列表
        return  this.allMobile.get(mobileSkuId);
    }

    /**
     * 通过网信产品id获取充值规格
     * @return
     */
    @Override
    public  MobileSkuDO   qryMobileSkuBySuppSkuId(String suppMobileSkuId){
        //运营商对应规格列表
        return  this.allMobile_sku.get(suppMobileSkuId);
    }




    /**
     * 通过省+运营商获取规格列表
     * @return
     */
    @Override
    public  List<MobileSkuResDTO>   qryeffectiveMobileSkus(String province, String supplier){
        //运营商对应规格列表
        Map<Long,MobileSkuDO> suppiersMap =this.allSuppiers.get(supplier);

        //省份对应规格
        Map<Long,MobileSkuProvinceDO> provinceMap=this.allProvince.get(province);

        //交集处理
        Set<Long> result = new HashSet();
        if(!CollectionUtils.isEmpty(suppiersMap)){
           Set<Long> suppiersSet=suppiersMap.keySet();
            result.addAll(suppiersSet);
        }
        if(!CollectionUtils.isEmpty(provinceMap)){
            Set<Long> provinceSet=provinceMap.keySet();
            //剔除未上架的规格
            result.removeAll(provinceSet);
        }
        log.info("话费汇总处理:[{}]-[{}]-[{}]",province,supplier,result);
        List<MobileSkuResDTO> res = new ArrayList<>();
        for(Long key : result){
            //默认值
            MobileSkuResDTO mobileSkuResDTO = mobileMapper.poToDTO(suppiersMap.get(key));
            //省市 值
            //   provinceMap.get(key).
            res.add(mobileSkuResDTO);
        }

        //排序
        Collections.sort(res, Comparator.comparingLong(o -> Long.parseLong(o.getSupplyProductSize())));
        return res;
    }

    public void initNotProvinces(){
        log.info("省初始化 begine...");

        List<MobileSkuProvinceDO> mobileSkuProvinces =mobileDao.qryMobileSkuProvince(Constant.MOBILE_SKU_DOWN);
        Map<String,Map<Long,MobileSkuProvinceDO>> _all = new HashMap<>();
        for(MobileSkuProvinceDO skuProvince : mobileSkuProvinces){
            //地区
            String mobileAddress=skuProvince.getMobileAddress();
            //地区关联规格id
            Long mobileSkuId =skuProvince.getMobileSkuId();
            Map<Long,MobileSkuProvinceDO> addressMap =_all.get(mobileAddress);
            if(addressMap==null){
                //存放省
                addressMap = new HashMap<>();
                _all.put(mobileAddress,addressMap);
            }

            MobileSkuProvinceDO mobileSkuProvince =addressMap.get(mobileSkuId);
            if(mobileSkuProvince==null){
                //存放规格
                addressMap.put(mobileSkuId,skuProvince);
            }
        }
        log.info("省初始化 end...[{}]",JsonUtil.toJSONString(_all));
        if(!CollectionUtils.isEmpty(_all)){
            synchronized (allProvince){
                allProvince.clear();
                allProvince.putAll(_all);
                log.info("省初始化完成 end...[{}]",JsonUtil.toJSONString(_all));
            }
        }
    }

    /*public void initProvinces(){
        log.info("省初始化 begine...");

        List<MobileSkuProvinceDO> mobileSkuProvinces =mobileDao.qryMobileSkuProvince(Constant.MOBILE_SKU_UP);
        Map<String,Map<Long,MobileSkuProvinceDO>> _all = new HashMap<>();
        for(MobileSkuProvinceDO skuProvince : mobileSkuProvinces){
            //地区
            String mobileAddress=skuProvince.getMobileAddress();
            //地区关联规格id
            Long mobileSkuId =skuProvince.getMobileSkuId();
            Map<Long,MobileSkuProvinceDO> addressMap =_all.get(mobileAddress);
            if(addressMap==null){
                //存放省
                addressMap = new HashMap<>();
                _all.put(mobileAddress,addressMap);
            }

            MobileSkuProvinceDO mobileSkuProvince =addressMap.get(mobileSkuId);
            if(mobileSkuProvince==null){
                //存放规格
                addressMap.put(mobileSkuId,skuProvince);
            }
        }
        log.info("省初始化 end...[{}]",JsonUtil.toJSONString(_all));
        if(!CollectionUtils.isEmpty(_all)){
            synchronized (allProvince){
                allProvince.clear();
                allProvince.putAll(_all);
                log.info("省初始化完成 end...[{}]",JsonUtil.toJSONString(_all));
            }
        }
    }*/


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化");
        this.initSkus();
        //    this.initProvinces();
        this.initNotProvinces();
    }
}
