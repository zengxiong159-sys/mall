package com.qdbank.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName MallTaskMainApplication
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/24 14:58
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("com.qdbank.mall.mapper")
public class MallTaskMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallTaskMainApplication.class, args);
    }

}
