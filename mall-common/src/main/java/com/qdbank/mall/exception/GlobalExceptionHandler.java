package com.qdbank.mall.exception;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.IErrorCode;
import com.qdbank.mall.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;

/**
 * 全局异常处理
 * Created by ningyuehuai on 2020/10/27.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public CommonResult handle(Throwable e) {
        IErrorCode resultCode= ExceptionMappingEnums.getErrorCode(e.getClass());
        log.info("抛出异常:{}",e);
        return CommonResult.failed(resultCode);
    }

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed();
    }

    @ResponseBody
    @ExceptionHandler(value = MobileException.class)
    public CommonResult handle(MobileException e) {
        return CommonResult.failed(e.getErrorCode());
    }

    @ResponseBody
    @ExceptionHandler(value = FileNotFoundException.class)
    public CommonResult handle(FileNotFoundException e) {
        return CommonResult.success(null);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }


}
