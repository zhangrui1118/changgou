package com.changgou.web.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 查询订单状态
 * zhangrui
 */
public interface OrderStatusService {

    public Map<String,Object> findOrderStatus(List<Date> list);
}
