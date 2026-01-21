package com.qdbank.mall.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public interface MinioService {
    /**
     * 创建桶
     * @param
     * @throws Exception
     */
    public void createBucket() throws Exception;

    /**
     * 上传文件
     * @param
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;

    /**
     * 下载文件
     * @param
     * @param filename
     * @return
     * @throws Exception
     */
    public InputStream downloadFile(String filename) throws Exception;

    /**
     * 删除文件
     * @param fileName
     */
    public void deleteFile(String fileName) throws Exception;

    /**
     * 文件流上传文件
     * @param inputStream
     * @param filename
     * @return
     */
    public String uploadFileByInputstream(File file, String filename) throws Exception;
}
