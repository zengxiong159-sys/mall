package com.qdbank.mall.feign;

import com.qdbank.mall.request.file.SendFileReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName BigDataFileService
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/11/17 14:41
 * @Version 1.0
 **/
@FeignClient(value = "big-data-file")
public interface BigDataFileService {
    @RequestMapping("/bigdata/file/sendFile")
    public void sendFile(@RequestBody SendFileReqDTO sendFileReqDTO);
    @RequestMapping("/bigdata/file/failSendFile")
    public void failSendFile();
    @RequestMapping("/bigdata/file/handleSendFile")
    public void handleSendFile(@RequestBody SendFileReqDTO sendFileReqDTO);
}
