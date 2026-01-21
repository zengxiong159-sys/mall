package com.qdbank.mall.controller.task;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.task.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName TaskController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/10/12 13:37
 * @Version 1.0
 **/
@RestController
@Api(tags = "TaskController", description = "定时任务配置")
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    private TaskService taskService;
    @ApiOperation("定时任务")
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    @ExcludeHandler
    public void task(@RequestBody Map<String,String> param){
        taskService.task(param);
    }
}
