package com.qdbank.mall.controller.order;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.request.order.CallBackReqDTO;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CommonBsn
 * @Author hongjh
 * @Date 2021/1/15 15:34
 * @Version 1.0
 **/

public class CommonBsn {


    protected void initUserDetail(CommonOrderReqDTO commonOrderReqDTO){
        UserDetails userDetails  = UserContextHolder.getUserDetails();
        if(userDetails==null || !StringUtils.hasText(userDetails.getCustNo())){
            throw new ApiException(ResultCode.USER_UNFIND_ERROR);
        }
    }





}
