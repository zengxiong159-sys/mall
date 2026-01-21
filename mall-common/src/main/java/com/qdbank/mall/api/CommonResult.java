package com.qdbank.mall.api;

import com.qdbank.mall.constant.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用返回对象
 * Created by ningyuehuai on 2020/10/19.
 */
@Getter
@Setter
public class CommonResult<T> {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "响应码")
    private long code;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "响应消息")
    private String message;
    /**
     * 数据封装
     */
    @ApiModelProperty(value = "响应数据")
    private T data;
    @ApiModelProperty(value = "是否加密 0 不加密 1不加密")
    private String enc;
    public CommonResult() {
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.enc = data != null ? Constant.ENCRYPT : Constant.NOT_ENCRYPT;
    }
    public CommonResult(long code, String message, T data,String enc) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.enc = enc;
    }
    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }
    public static <T> CommonResult<T> success(T data,String enc) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data,enc);
    }
    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
//    public static <T> CommonResult<T> success(T data, String message) {
//        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
//    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode,String message) {
        return new CommonResult<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> repeatLogin(T data) {
        return new CommonResult<T>(ResultCode.REPEAT_LOGIN.getCode(), ResultCode.REPEAT_LOGIN.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

}
