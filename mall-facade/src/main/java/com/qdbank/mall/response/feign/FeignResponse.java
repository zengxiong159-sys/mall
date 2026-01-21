package com.qdbank.mall.response.feign;
import com.qdbank.mall.api.ResultCode;
import lombok.Data;

/**
 * @ClassName FeignResponse
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/3/24 10:23
 * @Version 1.0
 **/
@Data
public class FeignResponse<T> {
    private GwSysHeader gwSysHeader;
    private T respBody;
    public boolean success(){
        return this.gwSysHeader.getServResponse() != null && "S".equals(this.gwSysHeader.getServResponse().getStatus());
    }

    public String getErrorMsg(){
        return this.gwSysHeader.getServResponse() != null ? this.gwSysHeader.getServResponse().getDesc() : ResultCode.SYSTEM_EXCEPTION.getMessage();
    }
}
