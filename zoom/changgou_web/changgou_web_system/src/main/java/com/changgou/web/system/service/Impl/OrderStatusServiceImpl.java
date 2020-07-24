package com.changgou.web.system.service.Impl;


import com.changgou.order.pojo.Order;

import com.changgou.web.system.dao.OrderMapper;
import com.changgou.web.system.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 查询订单状态
 * zhangrui
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String,Object> findOrderStatus(List<Date> list) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (list == null || list.size() <= 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            Date starttime = calendar.getTime();
            Date endtime = new Date();
//            String stime = simpleDateFormat.format(starttime);
//            String etime = simpleDateFormat.format(starttime);
            list.add(starttime);
            list.add(endtime);
        }

        Date startTime = list.get(0);
        Date endTime = list.get(1);
        //查询代付款订单0
        List<Order> ordersZ = orderMapper.findOrderStatusZ(startTime,endTime);
        int ordersZSize = ordersZ.size();

        //查询代发货订单1
        List<Order> ordersO = orderMapper.findOrderStatusO(startTime,endTime);
        int ordersOSize = ordersO.size();
        //查询已发货订单2
        List<Order> ordersL = orderMapper.findOrderStatusT(startTime,endTime);
        int ordersLSize = ordersL.size();

        //查询已完成订单3
        List<Order> ordersS = orderMapper.findOrderStatusS(startTime,endTime);
        int ordersSSize = ordersS.size();

        //查询已关闭订单4
        List<Order> ordersF = orderMapper.findOrderStatusF(startTime,endTime);
        int ordersFSize = ordersF.size();

        //封装最终结果
        Map<String,Object> resultMap = new HashMap<>();
        // data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']

        List<String> nameList = new ArrayList<>();
        nameList.add("代付款订单");
        nameList.add("代发货订单");
        nameList.add("已发货订单");
        nameList.add("已完成订单");
        nameList.add("已关闭订单");
        resultMap.put("nameMap",nameList);

        //data: [{value: 335, name: '直接访问'},{value: 310, name: '邮件营销'},{value: 234, name: '联盟广告'},{value: 135, name: '视频广告'},{value: 1548, name: '搜索引擎'}]
        List<Map> dataList = new ArrayList<>();
        Map<String,Object> dataMap0 = new HashMap<>();
        dataMap0.put("value",ordersZSize);
        dataMap0.put("name","代付款订单");
        dataList.add(dataMap0);
        Map<String,Object> dataMap1 = new HashMap<>();
        dataMap1.put("value",ordersOSize);
        dataMap1.put("name","代发货订单");
        dataList.add(dataMap1);
        Map<String,Object> dataMap2 = new HashMap<>();
        dataMap2.put("value",ordersLSize);
        dataMap2.put("name","已发货订单");
        dataList.add(dataMap2);
        Map<String,Object> dataMap3 = new HashMap<>();
        dataMap3.put("value",ordersSSize);
        dataMap3.put("name","已完成订单");
        dataList.add(dataMap3);
        Map<String,Object> dataMap4 = new HashMap<>();
        dataMap4.put("value",ordersFSize);
        dataMap4.put("name","已关闭订单");
        dataList.add(dataMap4);
        resultMap.put("dataList",dataList);
        //返回
        return resultMap;
    }
}
