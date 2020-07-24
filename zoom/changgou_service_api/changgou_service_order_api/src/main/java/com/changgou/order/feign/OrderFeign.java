package com.changgou.order.feign;

import com.changgou.entity.PageResult;
import com.changgou.entity.QueryPageBean;
import com.changgou.entity.Result;
import com.changgou.order.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

import java.util.Date;


@FeignClient(name = "order")
public interface OrderFeign {

    @PostMapping("/order")
    public Result add(@RequestBody Order order);

    @GetMapping("/order/{id}")
    public Result<Order> findById(@PathVariable("id") String id);

    /**
     * 根据用户名查询我的订单
     * @return
     */
    @GetMapping("/order/index")
    public List index();

    @GetMapping("/order/pay")
    public List pay();

    @GetMapping("/order/send")
    public List send();

    @GetMapping("/order/receive")
    public List receive();

    @GetMapping("/order/evaluate")
    public List evaluate();

    /**
     * 取消订单
     * @param orderid
     */
    @GetMapping("/order/cancelOrder")
    public void cancelOrder(@RequestParam("orderid") String orderid);

    /**
     * 确认收货(修改订单状态)
     * @param orderid
     */
    @GetMapping("/order/updateOrderStatus")
    public void updateOrderStatus(@RequestParam("orderid") String orderid);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/order/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean);
}
