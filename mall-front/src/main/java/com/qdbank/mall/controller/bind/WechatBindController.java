package com.qdbank.mall.controller.bind;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bind.WechatBindService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.exception.MessageVerifyErrorException;
import com.qdbank.mall.exception.SessionTimeOutException;
import com.qdbank.mall.proxy.EmployeeService;
import com.qdbank.mall.request.bind.BindRequestInfo;
import com.qdbank.mall.request.bind.SendMessageRequestInfo;
import com.qdbank.mall.request.bind.UserInfoReqDTO;
import com.qdbank.mall.response.bind.MessageSendResult;
import com.qdbank.mall.response.bind.SessionData;
import com.qdbank.mall.response.bind.UserInfoResDTO;
import com.qdbank.mall.response.bind.WechatBindInfo;
import com.qdbank.mall.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequestMapping("/openapi/wechatBind")
@Api(tags = "WechatBindController", description = "用户相关接口")
@RestController
public class WechatBindController {

    @Resource
    private WechatBindService wechatBindService;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String ID_NO_KEY = "wechatmall:ID_NO_KEY_VERIFY";
    /**
     * code2session
     * */
    @RequestMapping(value = "/code2Session",method = RequestMethod.POST)
    public CommonResult<SessionData> code2Session(String code){
        String sessionKey=wechatBindService.code2Session(code);
        SessionData sessionData=new SessionData();
        sessionData.setSessionKey(sessionKey);
        return CommonResult.success(sessionData);
    }

    /**
     *  查询绑定关系
     * */
    @RequestMapping(value = "/queryBindInfo",method = RequestMethod.POST)
    public CommonResult<WechatBindInfo> queryBindInfo(String sessionKey){
        String openId= wechatBindService.queryOpenId(sessionKey);
        //查询绑定关系表
        WechatBindInfo wechatBindInfo=wechatBindService.queryBindInfo(openId);
        return CommonResult.success(wechatBindInfo);
    }


    /**
     *  查询绑定关系
     * */
    @ApiOperation("用户信息查询")
    @RequestMapping(value = "/queryUserInfo",method = RequestMethod.POST)
    public CommonResult<UserInfoResDTO> queryUserInfo(@RequestBody @Validated UserInfoReqDTO userInfoReqDTO){
        return CommonResult.success(wechatBindService.getUserInfoByMobile(userInfoReqDTO.getMobile()));
    }

    /**
     *  发送验证码
     * */
    @RequestMapping(value = "/sendMsgCode",method = RequestMethod.POST)
    public CommonResult<MessageSendResult> sendMessageCode(@RequestBody SendMessageRequestInfo sendMessageRequestInfo){
        MessageSendResult messageSendResult=new MessageSendResult();
        String authCode=wechatBindService.sendMessageCode(sendMessageRequestInfo.getIdNo(),sendMessageRequestInfo.getMobilePhoneNo());
        messageSendResult.setAuthCode(authCode);
        return CommonResult.success(messageSendResult);
    }

    /**
     * 绑定
     * */
    @RequestMapping(value = "/bind",method = RequestMethod.POST)
    public CommonResult<WechatBindInfo> bind(@RequestBody BindRequestInfo bindRequestInfo){
            String key = ID_NO_KEY+":"+ DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN)+":"+bindRequestInfo.getIdCde();
            this.checkVerifyCount(key,bindRequestInfo.getIdCde());
        try {
            //短信验证
            wechatBindService.verifyMessageCode(bindRequestInfo.getAuthCode(),bindRequestInfo.getMessageCode(),bindRequestInfo.getMobilePhoneNo());
        }catch (MessageVerifyErrorException e){//捕获短信验证码错误异常
            Long increment = redisTemplate.opsForValue().increment(key, 1);
            if(increment == 1L){//防止用户输错小于5次验证码不进行登录，自动过期key
                redisTemplate.expire(key,this.getRemainSecondsOneDay(new Date()), TimeUnit.SECONDS);
            }
            if(increment == 5L){
                redisTemplate.expire(key,120, TimeUnit.SECONDS);
            }
            throw e;
        }
        //短信验证成功删除key
        redisTemplate.delete(key);
        //绑定
        WechatBindInfo wechatBindInfo=wechatBindService.bind(bindRequestInfo);
        return CommonResult.success(wechatBindInfo);
    }

    /**
     * 解除绑定
     * */
    @RequestMapping(value = "/unBind",method = RequestMethod.POST)
    public CommonResult unBind(String sessionKey){
        String openId= wechatBindService.queryOpenId(sessionKey);
        wechatBindService.unBind(openId);
        return CommonResult.success(null);
    }

    /**
     * sessionKey过期检查
     * */
    @RequestMapping(value = "/sessionKeyExpireCheck",method = RequestMethod.POST)
    public CommonResult sessionKeyExpireCheck(String sessionKey){
        if(wechatBindService.sessionKeyExpired(sessionKey)){
            throw new SessionTimeOutException("sessionKey不存在或者已过期");
        }
        return CommonResult.success(null);
    }

    private void checkVerifyCount(String key,String idNo){
        if(StringUtils.isBlank(idNo)) return;
        Object object = redisTemplate.opsForValue().get(key);
        if(object != null){
            Long verifyCount = Long.valueOf(object.toString());
            if(verifyCount > 4) throw new ApiException(ResultCode.ACCOUNT_LOCK);
        }
    }

    /**
     * 获取当前时间到今天结束的秒数
     * @param currentDate
     * @return
     */
    private   Long getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return  seconds;
    }

}
