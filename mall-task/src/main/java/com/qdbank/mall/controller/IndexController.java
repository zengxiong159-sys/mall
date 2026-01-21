package com.qdbank.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/24 22:27
 * @Version 1.0
 **/
@Controller
@RequestMapping("/task")
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "schedule";
    }

    @GetMapping("/scheduleLog")
    public String scheduleLog(){
        return "schedule_log";
    }
}
