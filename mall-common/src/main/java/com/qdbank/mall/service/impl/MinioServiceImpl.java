package com.qdbank.mall.service.impl;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.service.MinioService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

@Service
@Slf4j
public class MinioServiceImpl implements MinioService {
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucketName:mall}")
    private String bucketName;
    @Override
    public void createBucket() throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String filename =  file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf(".")).concat(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)).concat(RandomStringUtils.randomNumeric(3)).concat(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .stream(file.getInputStream(), file.getInputStream().available(), -1)
                .contentType(file.getContentType())
                .build());
        return  filename;
    }

    @Override
    public InputStream downloadFile(String filename) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .build());
    }

    @Override
    public void deleteFile(String fileName) throws Exception{
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build());
    }

    @Override
    public String uploadFileByInputstream(File file, String filename) throws Exception{
        InputStream inputStream = null;
        try {
           filename =  filename.concat(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)).concat(RandomStringUtils.randomNumeric(3)).concat(".xlsx");
           log.info("文件名：{}",filename);
           String contentType = new MimetypesFileTypeMap().getContentType(file);
           inputStream = new FileInputStream(file);
           minioClient.putObject(PutObjectArgs.builder()
                   .bucket(bucketName)
                   .object(filename)
                   .stream(inputStream, inputStream.available(), -1)
                   .contentType(contentType)
                   .build());
           return  filename;
       }catch (Exception e){
            log.info("上传文件：{}异常：{}",filename,e);
       }finally {
            if(inputStream != null) inputStream.close();
        }
       throw  new ApiException(ResultCode.UPLOAD_FILE_EXCEPTION);
    }
}
