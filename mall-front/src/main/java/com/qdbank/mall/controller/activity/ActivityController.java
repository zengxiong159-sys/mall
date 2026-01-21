package com.qdbank.mall.controller.activity;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.activity.ActivityService;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.user.UmsAdminMapper;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.user.UserInfoDOExample;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.activity.ActivityLikeQueryReqDTO;
import com.qdbank.mall.request.activity.ActivityReqDTO;
import com.qdbank.mall.request.activity.UpdateActivityReqDTO;
import com.qdbank.mall.request.user.UserInfoReqDTO;
import com.qdbank.mall.response.activity.ActivityResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ActivityController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 19:06
 * @Version 1.0
 **/
@RestController
@Api(tags = "ActivityController", description = "限时福利")
@RequestMapping("/activity")
@Slf4j
public class ActivityController {

    @Autowired
    ActivityService activityService;


    @ApiOperation("限时福利列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ActivityResDTO>> list(@RequestBody ActivityLikeQueryReqDTO req) {
        Page page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<ActivityResDTO> resuts=activityService.qryActivitys(req);
        return CommonResult.success(CommonPage.restPage(page,resuts));
    }

    @ApiOperation("福利详情->活动编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ActivityResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(activityService.qryActivityDetail(commonIDReqDTO.getId()));
    }
}
