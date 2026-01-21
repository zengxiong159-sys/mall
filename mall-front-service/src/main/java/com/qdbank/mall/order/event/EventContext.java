package com.qdbank.mall.order.event;

import lombok.Data;

/**
 * @author hongjh
 * @param <T>
 */
@Data
public class EventContext<T> {

    T body;


}
