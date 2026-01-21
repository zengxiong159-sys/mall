package com.qdbank.mall.bind.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import com.qdbank.mall.annotation.LockName;
import com.qdbank.mall.annotation.RateLimiter;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bind.WechatBindService;
import com.qdbank.mall.config.WechatBindConfiguration;
import com.qdbank.mall.enums.UserTypeEnum;
import com.qdbank.mall.enums.WechatBindStatus;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.exception.MobilePhoneNoInconsistentException;
import com.qdbank.mall.exception.NoneBankEmployeeException;
import com.qdbank.mall.exception.SessionTimeOutException;
import com.qdbank.mall.mapper.bind.WechatBindInfoMapper;
import com.qdbank.mall.mapper.umsuser.UmsUserInfoDOMapper;
import com.qdbank.mall.mapper.userwhite.UserWhiteDOMapper;
import com.qdbank.mall.message.MessageService;
import com.qdbank.mall.model.bind.WechatBindInfoDO;
import com.qdbank.mall.model.earlybird.employee.EmployeeInfo;
import com.qdbank.mall.model.umsuser.UmsUserInfoDO;
import com.qdbank.mall.model.umsuser.UmsUserInfoDOExample;
import com.qdbank.mall.model.userwhite.UserWhiteDO;
import com.qdbank.mall.model.userwhite.UserWhiteDOExample;
import com.qdbank.mall.proxy.EmployeeService;
import com.qdbank.mall.request.bind.BindRequestInfo;
import com.qdbank.mall.response.bind.UserInfoResDTO;
import com.qdbank.mall.response.bind.WechatBindDetailInfo;
import com.qdbank.mall.response.bind.WechatBindInfo;
import com.qdbank.mall.util.HttpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WechatBindServiceImpl implements WechatBindService {

    private static final String SESSION_KEY_PREFIX="WECHAT_MALL_SESSION_KEY_";

    @Resource
    private WechatBindConfiguration wechatBindConfiguration;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private MessageService messageService;

    @Resource
    private WechatBindInfoMapper wechatBindInfoMapper;

    @Resource
    private EmployeeService employeeService;
    @Autowired
    private UserWhiteDOMapper userWhiteDOMapper;
    @Autowired
    private UmsUserInfoDOMapper umsUserInfoDOMapper;

    @Override
    public String code2Session(String code) {
        Map request= Maps.newHashMap();
        request.put("appid", wechatBindConfiguration.getAppId());
        request.put("secret", wechatBindConfiguration.getAppSecret());
        request.put("js_code", code);
        request.put("grant_type", "authorization_code");

        //调用微信接口
        String strResponse=HttpUtils.getRestTemplate().getForObject(wechatBindConfiguration.getCode2SessionUrl(),String.class,request);
        log.info("微信code2session接口响应:{}",strResponse);
        if(StringUtils.isBlank(strResponse)){
            throw new NullPointerException("微信code2session接口响应为空");
        }
        Code2SessionResponse code2SessionResponse= JSONObject.parseObject(strResponse, Code2SessionResponse.class);
        if(StringUtils.isNotBlank(code2SessionResponse.getErrCode()) &&
                !StringUtils.equals(code2SessionResponse.getErrCode(),"0")){
            throw new RuntimeException("微信code2Session接口调用失败");
        }
        String magicSessionKey=getMagicSessionKey(code2SessionResponse.getSessionKey());
        //保存至redis
        save2Redis(magicSessionKey,code2SessionResponse);

        return magicSessionKey;
    }

    @Override
    public String sendMessageCode(String idNo,  String mobilePhoneNo) {
        //先查询白名单
        UserWhiteDO userWhiteDO = this.getUserWhiteDOByIdNo(idNo,null);
        if(userWhiteDO != null){
            if(!mobilePhoneNo.equals(userWhiteDO.getMobile()))  throw new MobilePhoneNoInconsistentException("手机号与白名单预留手机号不一致");
        }else {
            //查询用户信息
            EmployeeInfo employeeInfo= employeeService.queryEmployeeInfo(idNo,null);
            if(Objects.isNull(employeeInfo)){
                throw new NoneBankEmployeeException("非本行员工");
            }
            //手机号判断
            if(!StringUtils.equals(mobilePhoneNo,employeeInfo.getMobileNo())){
                throw new MobilePhoneNoInconsistentException("手机号与智慧人事系统预留手机号不一致");
            }
        }
        String sendMsgResult = "";
        try {
            sendMsgResult = messageService.sendMessage(mobilePhoneNo);
        }catch (GenericException e){
            log.info("调用短信接口异常：{}",e.getMessage());
            throw new ApiException(ResultCode.MESSAGE_REPEAT);
        }
        return sendMsgResult;
    }

    @Override
    public void verifyMessageCode(String authCode,String messageCode,String mobilePhoneNo) {
        messageService.verifyMessage(authCode, messageCode, mobilePhoneNo);
    }

    private void save2Redis(String key,Code2SessionResponse code2SessionResponse){
        redisTemplate.opsForValue().set(key,code2SessionResponse,30, TimeUnit.MINUTES);
    }

    private Code2SessionResponse getFromRedis(String sessionKey){
        Object value=redisTemplate.opsForValue().get(sessionKey);
        if(Objects.nonNull(value) && value instanceof Code2SessionResponse){
            return (Code2SessionResponse)value;
        }
        return null;
    }

    private String getMagicSessionKey(String sessionKey){
        return SESSION_KEY_PREFIX+revertSessionKey(sessionKey);
    }

    /**
     * sessionKey处理
     */
    private String revertSessionKey(String sessionKey) {
        StringBuffer stringBuffer = new StringBuffer(StringUtils.reverse(sessionKey).toUpperCase());
        return stringBuffer.append(String.format("%08X", System.currentTimeMillis()).toUpperCase()).toString();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WechatBindInfo bind(BindRequestInfo bindRequestInfo) {
        //查询openId
        Code2SessionResponse code2SessionResponse=getSessionInfo(bindRequestInfo.getSessionKey());
        UserWhiteDO userWhiteDO = this.getUserWhiteDOByIdNo(bindRequestInfo.getIdCde(),null);
        //查询员工信息
        EmployeeInfo employeeInfo = null;
        WechatBindInfoDO wechatBindInfoDO = null;
        if(userWhiteDO != null){//优先查询白名单
            wechatBindInfoDO = convert2UserWhiteBindInfoDO(userWhiteDO,bindRequestInfo,code2SessionResponse);
        }else{
            //查询员工信息
            employeeInfo= employeeService.queryEmployeeInfo(bindRequestInfo.getIdCde(),null);
            if(Objects.isNull(employeeInfo)){
                throw new NoneBankEmployeeException("非本行员工");
            }
            wechatBindInfoDO = convert2WechatBindInfoDO(employeeInfo,bindRequestInfo,code2SessionResponse);
        }
        //先对可能的其他绑定进行失效
        wechatBindInfoMapper.disable(code2SessionResponse.getOpenId(),bindRequestInfo.getIdCde());
        int cnt=wechatBindInfoMapper.enableByIdNoAndOpenId(wechatBindInfoDO);
        if(cnt==0){
            wechatBindInfoMapper.insert(wechatBindInfoDO);
        }
        return buildWechatBindInfo(wechatBindInfoDO,true);
    }


    private WechatBindInfoDO convert2WechatBindInfoDO(EmployeeInfo employeeInfo,BindRequestInfo bindRequestInfo,Code2SessionResponse code2SessionResponse){

        WechatBindInfoDO wechatBindInfoDO=new WechatBindInfoDO();
        wechatBindInfoDO.setUpdateDate(new Date());
        wechatBindInfoDO.setIdNo(bindRequestInfo.getIdCde());
        wechatBindInfoDO.setOpenId(code2SessionResponse.getOpenId());
        wechatBindInfoDO.setStatus(WechatBindStatus.BIND.getStatus());
        wechatBindInfoDO.setUserName(employeeInfo.getName());
        wechatBindInfoDO.setGender(employeeInfo.getGender());
        wechatBindInfoDO.setMobileNo(bindRequestInfo.getMobileNo());
        wechatBindInfoDO.setUnionId(code2SessionResponse.getUnionId());
        wechatBindInfoDO.setUserType(UserTypeEnum.BANK_USER.getUserCode());
        return wechatBindInfoDO;
    }

    private WechatBindInfoDO convert2UserWhiteBindInfoDO(UserWhiteDO userWhiteDO,BindRequestInfo bindRequestInfo,Code2SessionResponse code2SessionResponse){
        WechatBindInfoDO wechatBindInfoDO=new WechatBindInfoDO();
        wechatBindInfoDO.setUpdateDate(new Date());
        wechatBindInfoDO.setIdNo(bindRequestInfo.getIdCde());
        wechatBindInfoDO.setOpenId(code2SessionResponse.getOpenId());
        wechatBindInfoDO.setStatus(WechatBindStatus.BIND.getStatus());
        wechatBindInfoDO.setUserName(userWhiteDO.getUserName());
        wechatBindInfoDO.setGender(userWhiteDO.getGender());
        wechatBindInfoDO.setMobileNo(bindRequestInfo.getMobileNo());
        wechatBindInfoDO.setUnionId(code2SessionResponse.getUnionId());
        wechatBindInfoDO.setUserType(UserTypeEnum.WHITE_USER.getUserCode());
        return wechatBindInfoDO;
    }

    /**
     *  是否本行员工
     * */
    public boolean isBanker(String idCde){
        EmployeeInfo employeeInfo=employeeService.queryEmployeeInfo(idCde,null);
        return Objects.nonNull(employeeInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unBind(String openId) {
        if(StringUtils.isBlank(openId)){
            throw new NullPointerException("openId不能为空");
        }

        int cnt=wechatBindInfoMapper.disableByOpenId(openId);
        if(cnt!=1){
           throw new RuntimeException("解除绑定失败");
        }
    }


    @Override
    public WechatBindInfo queryBindInfo(String openId) {
        //查询绑定信息
        WechatBindInfoDO wechatBindInfoDO=wechatBindInfoMapper.queryByOpenId(openId);
        if(Objects.isNull(wechatBindInfoDO)){
            return WechatBindInfo.buildNoneBindInfo();
        }
        //判断当前用户是否为行员
        String idNo=wechatBindInfoDO.getIdNo();
        boolean isBanker=isBanker(idNo);

        return buildWechatBindInfo(wechatBindInfoDO,isBanker);
    }

    /**
     * 构建微信绑定信息
     * */
    private WechatBindInfo buildWechatBindInfo(WechatBindInfoDO wechatBindInfoDO,boolean isBanker){
        WechatBindDetailInfo wechatBindDetailInfo=new WechatBindDetailInfo();
        wechatBindDetailInfo.setGender(wechatBindInfoDO.getGender());
        wechatBindDetailInfo.setName(wechatBindInfoDO.getUserName());
        wechatBindDetailInfo.setOpenId(wechatBindInfoDO.getOpenId());
        wechatBindDetailInfo.setIdCde(wechatBindInfoDO.getIdNo());
        wechatBindDetailInfo.setMobilePhoneNo(wechatBindInfoDO.getMobileNo());
        wechatBindDetailInfo.setUnionId(wechatBindInfoDO.getUnionId());
        wechatBindDetailInfo.setUserType(wechatBindInfoDO.getUserType());
        WechatBindInfo wechatBindInfo=new WechatBindInfo();
        wechatBindInfo.setIsBind(WechatBindStatus.BIND.getStatus());
        wechatBindInfo.setIsBanker(isBanker?"1":"0");
        wechatBindInfo.setUserType(wechatBindInfoDO.getUserType());
        wechatBindInfo.setWechatBindDetailInfo(wechatBindDetailInfo);

        return wechatBindInfo;
    }

    @Override
    public String queryOpenId(String sessionKey) {
        Code2SessionResponse code2SessionResponse=getSessionInfo(sessionKey);
        if(Objects.nonNull(code2SessionResponse)){
            return code2SessionResponse.getOpenId();
        }
        throw new SessionTimeOutException("session超时");
    }

    @Override
    public boolean sessionKeyExpired(String sessionKey) {
        return !redisTemplate.hasKey(sessionKey);
    }

    @Override
    public UserInfoResDTO getUserInfoByMobile(String mobile) {
        //查询用户信息表是否存在
        UserInfoResDTO userInfoResDTO =  this.getUserInfo(mobile);
        if(userInfoResDTO != null) return userInfoResDTO;

        userInfoResDTO = new UserInfoResDTO();
        UserWhiteDO userWhiteDO = this.getUserWhiteDOByIdNo(null,mobile);
        //查询员工信息
        EmployeeInfo employeeInfo = null;
        if(userWhiteDO != null){//优先查询白名单
            userInfoResDTO.setIsBank("0");
            Long userId = this.insertUserInfo(mobile,"1");
            userInfoResDTO.setUserId(userId+"");
            return userInfoResDTO;
        }else{
            //查询员工信息
            employeeInfo= employeeService.queryEmployeeInfo(null,mobile);
            if(employeeInfo != null){
                userInfoResDTO.setIsBank("0");
                Long userId = this.insertUserInfo(mobile,"0");
                userInfoResDTO.setUserId(userId+"");
                return userInfoResDTO;
            }
        }
        //不在白名单且不是行员按非行员处理
        userInfoResDTO.setIsBank("1");
        Long userId = this.insertUserInfo(mobile,"2");
        userInfoResDTO.setUserId(userId+"");
        log.info("手机号：{}，返回用户信息：{}",mobile, JSON.toJSONString(userInfoResDTO));
        return userInfoResDTO;
    }

    private Code2SessionResponse getSessionInfo(String sessionKey){
        Code2SessionResponse code2SessionResponse= getFromRedis(sessionKey);
        if(Objects.isNull(code2SessionResponse)){
            throw new SessionTimeOutException("session超时或不存在");
        }
        return code2SessionResponse;
    }
    private UserWhiteDO getUserWhiteDOByIdNo(String idNo,String mobile){
        UserWhiteDOExample userWhiteDOExample = new UserWhiteDOExample();
        UserWhiteDOExample.Criteria criteria = userWhiteDOExample.createCriteria();
        if(StringUtils.isNotBlank(idNo)){
            criteria.andIdNoEqualTo(idNo);
        }
        if(StringUtils.isNotBlank(mobile)){
            criteria.andMobileEqualTo(mobile);
        }
        criteria.andStatusEqualTo("1");
        List<UserWhiteDO> userWhiteDOList = userWhiteDOMapper.selectByExample(userWhiteDOExample);
        return CollUtil.isNotEmpty(userWhiteDOList) ? userWhiteDOList.get(0) : null;
    }

    @Data
    public static class Code2SessionResponse{
        /**微信openId*/
        @JSONField(name = "openid")
        private String openId;
        /**微信sessionKey*/
        @JSONField(name = "session_key")
        private String sessionKey;
        /**微信unionId*/
        @JSONField(name = "unionid")
        private String unionId;
        /**errCode*/
        @JSONField(name = "errcode")
        private String errCode;
        /**errMsg*/
        @JSONField(name = "errmsg")
        private String errMsg;
    }

    /**
     * 判断用户是否存在
     * @param mobile
     * @return
     */
    private UserInfoResDTO getUserInfo(String mobile){
        UserInfoResDTO userInfoResDTO = new UserInfoResDTO();
        UmsUserInfoDOExample umsUserInfoDOExample = new UmsUserInfoDOExample();
        umsUserInfoDOExample.createCriteria().andMobileEqualTo(mobile);
        List<UmsUserInfoDO> umsUserInfoDOList = umsUserInfoDOMapper.selectByExample(umsUserInfoDOExample);
        if(CollUtil.isNotEmpty(umsUserInfoDOList)){
            UmsUserInfoDO umsUserInfoDO = umsUserInfoDOList.get(0);
            UserWhiteDO userWhiteDO = this.getUserWhiteDOByIdNo(null,mobile);
            userInfoResDTO.setIsBank(umsUserInfoDO.getUserType().equals("0") || umsUserInfoDO.getUserType().equals("1") ? "0" : "1");
            userInfoResDTO.setUserId(umsUserInfoDO.getId()+"");
            if(userWhiteDO != null){
                userInfoResDTO.setIsBank("0");
            }else{//不在白名单内查询智慧人事
                EmployeeInfo employeeInfo = employeeService.queryEmployeeInfo(null,mobile);
                if(employeeInfo != null){
                    userInfoResDTO.setIsBank("0");
                }else{
                    userInfoResDTO.setIsBank("1");
                }
            }
            return userInfoResDTO;
        }//新户
        return null;
    }

    /**
     * 用户信息入库
     * @param mobile
     * @param userType
     * @return
     */
    private Long insertUserInfo(String mobile, String userType){
        UmsUserInfoDO umsUserInfoDO = new UmsUserInfoDO();
        umsUserInfoDO.setCreateTime(new Date());
        umsUserInfoDO.setUpdateTime(new Date());
        umsUserInfoDO.setMobile(mobile);
        umsUserInfoDO.setUserType(userType);
        int count =  umsUserInfoDOMapper.insert(umsUserInfoDO);
        if(count > 0){
            UmsUserInfoDOExample umsUserInfoDOExample = new UmsUserInfoDOExample();
            umsUserInfoDOExample.createCriteria().andMobileEqualTo(mobile);
            UmsUserInfoDO infoDO = umsUserInfoDOMapper.selectByExample(umsUserInfoDOExample).get(0);
            return infoDO.getId();
        }
        throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
    }
}
