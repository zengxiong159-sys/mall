package com.qdbank.mall.rpc;

import java.util.Map;

/**
 * @author Hongjianhua
 */
public interface MgtDubboService {

    Map<String, Object> execute(Map<String, Object> map);
}