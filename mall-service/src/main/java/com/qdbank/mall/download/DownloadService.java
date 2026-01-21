package com.qdbank.mall.download;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.download.DownloadReqDTO;
import com.qdbank.mall.response.download.DownloadRespDTO;

import java.util.List;

public interface DownloadService {
    /**
     * 文件列表查询
     * @param downloadReqDTO
     * @return
     */
    public PageInfo<DownloadRespDTO> downloadList(DownloadReqDTO downloadReqDTO);

}
