package com.changgou.web.system.dao;

import com.changgou.order.pojo.Order;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;




public interface OrderMapper extends Mapper<Order> {

    //查询代付款订单0
    @Select("SELECT * FROM tb_order WHERE order_status = '0' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Order> findOrderStatusZ(@Param("startTime") Date startTime,@Param("endTime")Date endTime);

    //查询代发货订单1
    @Select("SELECT * FROM tb_order WHERE order_status = '1' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Order> findOrderStatusO(@Param("startTime") Date startTime,@Param("endTime")Date endTime);

    //查询已发货订单2
    @Select("SELECT * FROM tb_order WHERE order_status = '2' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Order> findOrderStatusT(@Param("startTime") Date startTime,@Param("endTime")Date endTime);

    //查询已完成订单3
    @Select("SELECT * FROM tb_order WHERE order_status = '3' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Order> findOrderStatusS(@Param("startTime") Date startTime,@Param("endTime")Date endTime);

    //查询已关闭订单4
    @Select("SELECT * FROM tb_order WHERE order_status = '4' AND create_time BETWEEN #{startTime} AND #{endTime}")
    List<Order> findOrderStatusF(@Param("startTime") Date startTime,@Param("endTime")Date endTime);
}
