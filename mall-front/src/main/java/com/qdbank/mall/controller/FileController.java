package com.qdbank.mall.controller;

import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.common.FileService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.request.auth.AuthReqDTO;
import com.qdbank.mall.request.productpicurl.UpLoadPictureReqDTO;
import com.qdbank.mall.response.auth.AuthResDTO;
import com.qdbank.mall.response.productpicurl.UpLoadPictureResDTO;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.RSAUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName FileController
 * @Author hongjh
 * @Date 2021/4/29 10:09
 * @Version 1.0
 **/
@RestController
@Api(tags = "fileController", description = "文件服务")
@RequestMapping("/file")
@Slf4j
public class FileController {


    @Autowired
    FileService fileService;






    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @ResponseBody
    @CheckUser
    @ExcludeHandler
    public CommonResult<UpLoadPictureResDTO> upload(@RequestParam("file") MultipartFile file) {
  //      MultipartFile file = upLoadPictureReq.getFile();

        if (file==null || file.isEmpty()) {
            throw new ApiException(ResultCode.UPLOAD_FILE_EXCEPTION);
        }


        String[] filePath = fileService.upload( file);
        UpLoadPictureResDTO resDTO = new UpLoadPictureResDTO();
        resDTO.setPicUrl(filePath[2]);
        resDTO.setFileName(file.getOriginalFilename());

        return CommonResult.success(resDTO);
    }



}
