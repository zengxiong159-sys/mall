package com.qdbank.mall.userwhite;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.userwhite.UpdateStatusReqDTO;
import com.qdbank.mall.request.userwhite.UserWhiteCreateReqDTO;
import com.qdbank.mall.request.userwhite.UserWhiteQueryReqDTO;
import com.qdbank.mall.request.userwhite.UserWhiteUpdateReqDTO;
import com.qdbank.mall.response.userwhite.UserWhiteResDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface UserWhiteService {
    /**
     * 白名单文件上传
     * @param multipartFile
     */
    public void uploadUserName(MultipartFile multipartFile);

    /**
     * 白名单用户列表查询
     * @param userWhiteQueryReqDTO
     * @return
     */
    public PageInfo<UserWhiteResDTO> list(UserWhiteQueryReqDTO userWhiteQueryReqDTO);

    /**
     * 白名单用户新增接口
     * @param userWhiteCreateReqDTO
     */
    public void create(UserWhiteCreateReqDTO userWhiteCreateReqDTO);

    /**
     * 白名单信息修改
     * @param userWhiteUpdateReqDTO
     */
    public void update(UserWhiteUpdateReqDTO userWhiteUpdateReqDTO);

    /**
     * 白名单详情
     * @param id
     * @return
     */
    public UserWhiteResDTO detail(Long id);

    /**
     * 批量启用用户状态
     * @param ids
     */
    public void batchUpdatStatus(String ids);

    /**
     * 白名单信息导出
     * @param userWhiteQueryReqDTO
     */
    public void export(HttpServletResponse response,UserWhiteQueryReqDTO userWhiteQueryReqDTO);

    /**
     * 修改状态
     * @param updateStatusReqDTO
     * @return
     */
    public int updateStatus(UpdateStatusReqDTO updateStatusReqDTO);
}
