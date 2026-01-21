package com.qdbank.mall.cms;

import com.alibaba.fastjson.JSONArray;
import com.qdbank.mall.request.freighttemplate.AreaFreightTemplateReqDTO;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName JsonTest
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/9 13:40
 * @Version 1.0
 **/
public class JsonTest {
    @Test
    public void testJson(){
        String str = "[{\"颜色\":\"蓝色\"}]";

        JSONArray jsonArray = JSONArray.parseArray(str);
        System.out.println(jsonArray);

    }

}
