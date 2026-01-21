package com.qdbank.mall.response.third.payment;

import com.qdbank.mall.response.third.ThirdResBody;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @ClassName RefundResDTO
 * @Description TODO
 * @Author hongjh
 * @Date 2021/2/23 16:30
 * @Version 1.0
 **/
@Data
public class RefundResDTO extends ThirdResBody {

    @ApiModelProperty("银联账务日期")
   private String cupdDt	;
    @ApiModelProperty("检索参考号")
   private String retRefNo		;













}
