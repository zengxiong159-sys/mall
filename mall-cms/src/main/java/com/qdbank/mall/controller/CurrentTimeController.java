package com.qdbank.mall.controller;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qdccb
 */
@RestController
@Slf4j
public class CurrentTimeController {
    @RequestMapping("/now")
    public CommonResult<CurrentTime> now() {
        return  CommonResult.success(new CurrentTime(System.currentTimeMillis()), Constant.NOT_ENCRYPT);
    }
}

@Getter
@Setter
@AllArgsConstructor
class CurrentTime{
    private Long now;
}
