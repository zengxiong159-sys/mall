package com.qdbank.mall.controller.activity;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.activity.ActivityService;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.activity.*;
import com.qdbank.mall.response.activity.ActivityResDTO;
import com.qdbank.mall.task.ITaskService;
import com.qdbank.mall.task.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName ActivityController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 19:06
 * @Version 1.0
 **/
@RestController
@Api(tags = "ActivityController", description = "活动配置")
@RequestMapping("/activity")
@Slf4j
public class ActivityController {

    @Autowired
    private ActivityService activityService;
    @ApiOperation("活动列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<ActivityResDTO>> list(@Validated @RequestBody ActivityLikeQueryReqDTO activityLikeQueryReqDTO) {

        return CommonResult.success(CommonPage.restPage(activityService.list(activityLikeQueryReqDTO)));
    }
    @ApiOperation(value = "新建活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create( ActivityReqDTO activityReqDTO) {
        int count = activityService.create(activityReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "编辑活动")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Validated @RequestBody UpdateActivityReqDTO updateActivityReqDTO) {
        int count = activityService.update(updateActivityReqDTO,null);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "编辑活动-修改图片")
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public CommonResult update(UploadActivityPicReqDTO updateActivityReqDTO) {
        int count = activityService.update(updateActivityReqDTO,updateActivityReqDTO.getFile());
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "修改活动状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateActivityStatusReqDTO updateActivityStatusReqDTO) {
        int count = activityService.updateStatus(updateActivityStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation("活动详情->活动编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<ActivityResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(activityService.detail(commonIDReqDTO.getId()));
    }

}
