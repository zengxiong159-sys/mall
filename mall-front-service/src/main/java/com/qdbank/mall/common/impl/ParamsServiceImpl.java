package com.qdbank.mall.common.impl;


import com.qdbank.mall.common.ParamsService;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.dao.merchant.MerchantDao;
import com.qdbank.mall.dao.params.ParamsConfigDao;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.paramsconfig.ParamsConfigDO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 参数服务
 */
@Service("ParamsService")
@Slf4j
public class ParamsServiceImpl extends BaseServiceImpl implements ParamsService, InitializingBean {


    @Autowired
    ParamsConfigDao paramsConfigDao;

    @Autowired
    MerchantDao merchantDao;

    /**
     * 查询客服手机号码
     * @param productEnum
     * @param merchantNo
     * @return
     */
    @Override
    public String handlerSuppiorMobile(ProductEnum productEnum, Long merchantNo){
        //积分兑换券--获取参数表记录
        if(productEnum== ProductEnum.INTEGRAL){
            return this.qryMsgByCode(Constant.SUPPER_MOBILE_TYPE, Constant.SUPPER_MOBILE);
        }else{
            //实物、虚拟订单--查询商户表
            return SpringContextUtils.getBean("ParamsService",ParamsService.class).qrySupperMobileNo(merchantNo);
        }
    }


    Map<Long, LinkedHashMap<String,String>> allParams = new HashMap<>();


    @Override
    public String qrySupperMobileNo(Long merchantNo){
        MerchantDO merchantDO =merchantDao.qryMerchantById(merchantNo);
        if(merchantDO!=null){
            return merchantDO.getSupportPhone();
        }
        return "";
    }


    @Override
    public String qryMsgByCode(Long code, String value){
        Map<String,String> codeMap  = allParams.get(code);
        if(codeMap!=null){
            return codeMap.get(value);
        }
        return null;
    }


    /**
     * 初始化处理
     */
    public void initConfig() {
        List<ParamsConfigDO> list =paramsConfigDao.list();
        if(!CollectionUtils.isEmpty(list)){
            Map<Long, LinkedHashMap<String,String>> _allParams = new HashMap<>();
            for(ParamsConfigDO configDO : list){
                //参数枚举
                Long paramType =configDO.getParamType();
                LinkedHashMap paramsMap = _allParams.get(paramType);
                if(paramsMap==null){
                    paramsMap = new LinkedHashMap();
                    _allParams.put(paramType,paramsMap);
                }
                //参数处理
                paramsMap.put(configDO.getParamName(),configDO.getParamValue());
            }

            synchronized (allParams){
                if(!CollectionUtils.isEmpty(_allParams)){
                    allParams.clear();
                    allParams.putAll(_allParams);
                }
            }
        }
    }


    /**
     *
     * @return
     */
    @Override
    public Integer getOrderTimeOut(){
        Integer time = 30;
        try{
            String val=qryMsgByCode(Constant.ORDER_PARAMS_TYPE,Constant.CLOSE_TIME_OUT);
            time = Integer.parseInt(val);
        }catch (Exception e){
            log.error("配置异常",e);
        }
        return time;
    }

    @Override
    public Integer getAppalyRefundTimeOut(){
        Integer time = 7;
        try{
            String val=qryMsgByCode(Constant.ORDER_PARAMS_TYPE,Constant.REFUND_TIME_OUT);
            time = Integer.parseInt(val);
        }catch (Exception e){
            log.error("配置异常",e);
        }
        return time;

    }

    @Override
    public boolean isRefundTimeOut(Date receiveDate){
        //收货时间为null，不可申请退款
        if(receiveDate==null){
            return true;
        }
        Calendar cc = Calendar.getInstance();
        //订单创建时间
        cc.setTime(receiveDate);
        cc.add(Calendar.DATE, getAppalyRefundTimeOut());
        Date now = new Date();
        log.info("当前时间和超时间件[{}]-[{}]",now,cc.getTime());
        return now.compareTo(cc.getTime())>=0;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getOrderDelivery(){
        Integer time = 7;
        try{
            String val=qryMsgByCode(Constant.ORDER_PARAMS_TYPE,Constant.DELIVERY_TIME_OUT);
            time = Integer.parseInt(val);
        }catch (Exception e){
            log.error("配置异常",e);
        }
        return time;
    }




    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(() -> {
            do{
                log.info("参数重置");
                initConfig();
                try {
                    Thread.sleep(60*1000);
                } catch (InterruptedException e) {
                    log.error("线程执行异常",e);
                }
            }
            while(true);
        });

       thread.start();
    }


}
