package com.qdbank.mall.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description http监控标签
 * @Date 2021/5/27 10:44
 */
@Data
public class PrometheusHttpMetricLabel implements Serializable {
  private static final long serialVersionUID = -6539738224774410959L;

  /**
   * 异常信息
   */
  private String exception;

  /**
   * 响应状态标识 true:正常 false:异常
   */
  private String result;

  /**
   * 请求类型
   */
  private String type;

  /**
   * 请求uri
   */
  private String uri;

  /**
   * 方法信息
   */
  private String method;

  /**
   * http响应状态
   */
  private String httpStatus;

  /**
   * 业务响应状态码
   */
  private String businessResCode;
}
