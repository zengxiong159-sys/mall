package com.qdbank.mall.controller.schedule;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.schedule.ScheduleJobLogLikeQueryReqDTO;
import com.qdbank.mall.request.schedule.ScheduleLikeQueryReqDTO;
import com.qdbank.mall.request.schedule.ScheduleReqDTO;
import com.qdbank.mall.request.schedule.UpdateScheduleReqDTO;
import com.qdbank.mall.response.schedule.ScheduleJobLogResDTO;
import com.qdbank.mall.response.schedule.ScheduleResDTO;
import com.qdbank.mall.schedule.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName ScheduleController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/30 16:49
 * @Version 1.0
 **/
@RestController
@Api(tags = "ScheduleController", description = "定时任务管理")
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @ApiOperation("获取定时任务列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<ScheduleResDTO>> list(@RequestBody ScheduleLikeQueryReqDTO scheduleLikeQueryDTO) {
        return CommonResult.success(CommonPage.restPage(scheduleService.list(scheduleLikeQueryDTO)));
    }
    @ApiOperation(value = "定时任务创建")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody ScheduleReqDTO scheduleReqDTO) {
        int count = scheduleService.saveJob(scheduleReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
    @ApiOperation("修改任务信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Validated @RequestBody UpdateScheduleReqDTO updateScheduleReqDTO) {
        int count = scheduleService.update(updateScheduleReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation("立即执行任务")
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult run(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        scheduleService.run(commonIDReqDTO);
        return CommonResult.success(null);
    }

    @ApiOperation("暂停任务")
    @RequestMapping(value = "/pause", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult pause(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = scheduleService.pause(commonIDReqDTO);
        if(count > 0 ){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation("恢复任务")
    @RequestMapping(value = "/resume", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult resume(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = scheduleService.resume(commonIDReqDTO);
        if(count > 0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除任务")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        scheduleService.delete(commonIDReqDTO);
        return CommonResult.success(null);
    }
    @ApiOperation("定时任务日志列表")
    @RequestMapping(value = "/log/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<ScheduleJobLogResDTO>> logList(@RequestBody ScheduleJobLogLikeQueryReqDTO scheduleJobLogLikeQueryReqDTO) {
        return CommonResult.success(CommonPage.restPage(scheduleService.getJobLogs(scheduleJobLogLikeQueryReqDTO)));
    }

}
