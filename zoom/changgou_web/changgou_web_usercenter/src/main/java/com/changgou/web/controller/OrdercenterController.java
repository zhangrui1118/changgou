package com.changgou.web.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.QueryPageBean;
import com.changgou.order.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wordercenter")
public class OrdercenterController {
    @Autowired
    private OrderFeign orderFeign;

    @RequestMapping("/index")
    public String index(Model model) {
        List orderItemList = orderFeign.index();
        model.addAttribute("orderItemList", orderItemList);
        return "center-index";
    }

    @RequestMapping("/pay")
    public String pay(Model model) {
        List orderItemList = orderFeign.pay();
        model.addAttribute("orderItemList", orderItemList);
        return "center-order-pay";
    }

    @RequestMapping("/send")
    public String send(Model model) {
        List orderItemList = orderFeign.send();
        model.addAttribute("orderItemList", orderItemList);
        return "center-order-send";
    }

    @RequestMapping("/receive")
    public String receive(Model model) {
        List orderItemList = orderFeign.receive();
        model.addAttribute("orderItemList", orderItemList);
        return "center-order-receive";
    }

    @RequestMapping("/evaluate")
    public String evaluate(Model model) {
        List orderItemList = orderFeign.evaluate();
        model.addAttribute("orderItemList", orderItemList);
        return "center-order-evaluate";
    }

    //跳转到支付页面
    @RequestMapping("/toPay")
    public String toPay(@Param("order_id") String order_id, @Param("money") String money, Model model) {
        model.addAttribute("orderId", order_id);
        model.addAttribute("payMoney", money);
        return "pay";
    }

    //index的确认收货(修改订单状态)
    @GetMapping("/index_updateOrderStatus")
    public String index_updateOrderStatus(@RequestParam("orderid") String orderid,Model model){
        orderFeign.updateOrderStatus(orderid);
        List orderItemList = orderFeign.index();
        model.addAttribute("orderItemList", orderItemList);
        return "center-index";
    }

    //index的取消订单并刷新页面
    @GetMapping("/index_cancelOrder")
    public String index_cancelOrder(@RequestParam("orderid") String orderid, Model model) {
        //取消订单
        orderFeign.cancelOrder(orderid);
        //刷新页面
        List orderItemList = orderFeign.index();
        model.addAttribute("orderItemList", orderItemList);
        return "center-index";
    }

    //receive的确认收货(修改订单状态)
    @GetMapping("/receive_updateOrderStatus")
    public String receive_updateOrderStatus(@RequestParam("orderid") String orderid,Model model){
        orderFeign.updateOrderStatus(orderid);
        List orderItemList = orderFeign.receive();
        model.addAttribute("orderItemList", orderItemList);
        return "center-order-receive";
    }

    //pay的取消订单并刷新页面
    @GetMapping("/pay_cancelOrder")
    public String pay_cancelOrder(@RequestParam("orderid") String orderid, Model model) {
        //取消订单
        orderFeign.cancelOrder(orderid);
        //刷新页面
        List orderItemList = orderFeign.pay();
        model.addAttribute("orderItemList", orderItemList);
        return "center-order-pay";
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = orderFeign.findPage(queryPageBean);
        return pageResult;
    }

}
