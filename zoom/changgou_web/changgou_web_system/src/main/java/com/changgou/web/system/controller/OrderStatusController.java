package com.changgou.web.system.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.feign.OrderFeign;
import com.changgou.web.system.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 查询订单状态用于图表展示
 */
@Controller
@RequestMapping("/orderstatus")
public class OrderStatusController {
    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private OrderStatusService orderStatusService;

    @PostMapping("/status")
    @ResponseBody
    public Result OrderStatus(@RequestBody List<Date> list){ //这里map接收的是两个时间
        Map<String,Object> resutlMap=orderStatusService.findOrderStatus(list);
        return new Result(true, StatusCode.OK,"查询订单状态成功",resutlMap);
    }

    @GetMapping("/jump")
    public String jump(){
        return "report";

    }

}
