package com.changgou.order.controller;
import com.changgou.entity.PageResult;
import com.changgou.entity.QueryPageBean;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.config.TokenDecode;
import com.changgou.order.service.OrderService;
import com.changgou.order.pojo.Order;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll(){
        List<Order> orderList = orderService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",orderList) ;
    }

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Order> findById(@PathVariable("id") String id){
        Order order = orderService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",order);
    }

    @Autowired
    private TokenDecode tokenDecode;

    /***
     * 新增数据
     * @param order
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Order order){
        //获取登录人名称
        String username = tokenDecode.getUserInfo().get("username");
        order.setUsername(username);
        String orderId = orderService.add(order);
        return new Result(true,StatusCode.OK,"添加成功",orderId);
    }


    /***
     * 修改数据
     * @param order
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody Order order,@PathVariable String id){
        order.setId(id);
        orderService.update(order);
        return new Result(true,StatusCode.OK,"修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        orderService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search" )
    public Result findList(@RequestParam Map searchMap){
        List<Order> list = orderService.findList(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }


    /***
     * 分页搜索实现
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result findPage(@RequestParam Map searchMap, @PathVariable  int page, @PathVariable  int size){
        Page<Order> pageList = orderService.findPage(searchMap, page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
    /***
     * 订单发货
     * @param orders
     * @return
     */
    @PostMapping("/batchSend")
    public Result batchSend(@RequestBody List<Order> orders){
        orderService.batchSend(orders);
        return new Result(true,StatusCode.OK,"发货成功");
    }

    /**
     * 根据用户名查询我的订单
     * @return
     */
    @GetMapping("/index")
    public List index(){
        String username = tokenDecode.getUserInfo().get("username");
        List orderItemList = orderService.index(username);
        return orderItemList;
    }

    /**
     * 根据用户名查询待付款
     * @return
     */
    @GetMapping("/pay")
    public List pay(){
        String username = tokenDecode.getUserInfo().get("username");
        List orderItemList = orderService.pay(username);
        return orderItemList;
    }

    @GetMapping("/send")
    public List send(){
        String username = tokenDecode.getUserInfo().get("username");
        List orderItemList = orderService.send(username);
        return orderItemList;
    }

    @GetMapping("/receive")
    public List receive(){
        String username = tokenDecode.getUserInfo().get("username");
        List orderItemList = orderService.receive(username);
        return orderItemList;
    }

    @GetMapping("/evaluate")
    public List evaluate(){
        String username = tokenDecode.getUserInfo().get("username");
        List orderItemList = orderService.evaluate(username);
        return orderItemList;
    }

    /**
     * 取消订单
     * @return
     */
    @GetMapping("/cancelOrder")
    public void cancelOrder(@RequestParam("orderid") String orderid){
        String username = tokenDecode.getUserInfo().get("username");
        orderService.cancelOrder(orderid, username);
    }

    /**
     * 确认收货(修改订单状态)
     * @param orderid
     */
    @GetMapping("/updateOrderStatus")
    public void updateOrderStatus(@RequestParam("orderid") String orderid){
        String username = tokenDecode.getUserInfo().get("username");
        orderService.updateOrderStatus(orderid, username);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        String username = tokenDecode.getUserInfo().get("username");
        PageResult pageResult = orderService.pageQuery(queryPageBean,username);
        return pageResult;
    }

}
