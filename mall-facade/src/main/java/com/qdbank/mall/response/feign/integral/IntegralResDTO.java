package com.qdbank.mall.response.feign.integral;

import lombok.Data;

import java.util.List;

/**
 * @ClassName IntegralResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 10:38
 * @Version 1.0
 **/
@Data
public class IntegralResDTO {
   public List<IntegralDetail> acctPoints;
}