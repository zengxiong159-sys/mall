package com.qdbank.mall.request.file;

import lombok.Data;

/**
 * @ClassName SendFileReqDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/11/17 14:35
 * @Version 1.0
 **/
@Data
public class SendFileReqDTO {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 跑批日期
     */
    private String date;
    /**
     * 批次号
     */
    private String batchNos;
}
