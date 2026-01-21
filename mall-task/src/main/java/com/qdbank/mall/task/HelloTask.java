package com.qdbank.mall.task;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName HelloTask
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/24 21:49
 * @Version 1.0
 **/
@Component(value = "helloTask")
public class HelloTask implements ITask{
    @Override
    public void run(String params) {
        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"  helloTask");
    }
}
