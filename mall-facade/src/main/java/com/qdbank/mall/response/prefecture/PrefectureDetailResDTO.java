package com.qdbank.mall.response.prefecture;

import com.qdbank.mall.response.product.ProductSkuResDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PrefectureDetailResDTO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/8 16:39
 * @Version 1.0
 **/
@Data
public class PrefectureDetailResDTO extends PrefectureResDTO {
    @ApiModelProperty(value = "关联商品信息")
    List<ProductSkuResDTO> productInfos;
}
