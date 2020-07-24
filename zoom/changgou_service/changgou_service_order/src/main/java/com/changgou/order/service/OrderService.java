package com.changgou.order.service;

import com.changgou.entity.PageResult;
import com.changgou.entity.QueryPageBean;
import com.changgou.order.pojo.Order;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /***
     * 查询所有
     * @return
     */
    List<Order> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Order findById(String id);

    /***
     * 新增
     * @param order
     */
    String add(Order order);

    /***
     * 修改
     * @param order
     */
    void update(Order order);

    /***
     * 删除
     * @param id
     */
    void delete(String id);

    /***
     * 多条件搜索
     * @param searchMap
     * @return
     */
    List<Order> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Order> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Order> findPage(Map<String, Object> searchMap, int page, int size);

    //修改订单的支付状态,并记录日志
    void updatePayStatus(String orderId, String transactionId);

    void closeOrder(String message);

    void batchSend(List<Order> orders);

    //手动确认收货
    void confirmTask(String orderId,String operator);

    void autoTack();

    /**
     * 查询我的订单
     * @param username
     * @return
     */
    public List index(String username);
    /**
     * 查询待付款(订单状态为"0")
     * @param username
     * @return
     */
    public List pay(String username);
    /**
     * 查询待发货(订单状态为"1")
     * @param username
     * @return
     */
    public List send(String username);
    /**
     * 查询待发货(订单状态为"2")
     * @param username
     * @return
     */
    public List receive(String username);
    /**
     * 查询待评论(订单状态为"3")
     * @param username
     * @return
     */
    public List evaluate(String username);

    public void cancelOrder(String orderid,String username);

    /**
     * 确认收货(修改订单状态)
     * @param orderid
     * @param username
     */
    public void updateOrderStatus(String orderid,String username);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult pageQuery(QueryPageBean queryPageBean,String username);
}
