package com.changgou.web.system.service;

import com.github.pagehelper.Page;
import com.changgou.order.pojo.Order;

import java.util.List;
import java.util.Map;


public interface SystemService {
    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public Page<Order> findPage(int page, int size);

    Page<Order> findList(Map param, Integer pageSize, Integer currentPage);

    void batchSend(Integer id);

    void delectOrder(String id);
}
