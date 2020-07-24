package com.changgou.order.dao;

import com.changgou.order.pojo.MyOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.parameters.P;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MyOrderMapper extends Mapper<MyOrder> {
    @Select("select t1.*,t2.order_status from tb_order_item t1,tb_order t2 where t1.order_id = t2.id and t2.username = #{username}")
    List<MyOrder> selectIndex(String username);

    @Select("SELECT t1.*,t2.order_status FROM tb_order_item t1,tb_order t2 WHERE t1.order_id = t2.id AND t2.order_status = '0' AND t2.username = #{username}")
    List<MyOrder> selectPay(String username);

    @Select("SELECT t1.*,t2.order_status FROM tb_order_item t1,tb_order t2 WHERE t1.order_id = t2.id AND t2.order_status = '1' AND t2.username = #{username}")
    List<MyOrder> selectSend(String username);

    @Select("SELECT t1.*,t2.order_status FROM tb_order_item t1,tb_order t2 WHERE t1.order_id = t2.id AND t2.order_status = '2' AND t2.username = #{username}")
    List<MyOrder> selectReceive(String username);

    @Select("SELECT t1.*,t2.order_status FROM tb_order_item t1,tb_order t2 WHERE t1.order_id = t2.id AND t2.order_status = '3' AND t2.username = #{username}")
    List<MyOrder> selectEvaluate(String username);

    @Delete("DELETE FROM tb_order WHERE id = #{orderid} AND username = #{username}")
    void delete_tb_order(@Param("orderid") String orderid, @Param("username") String username);

    @Delete("DELETE FROM tb_order_item WHERE order_id = #{orderid}")
    void delete_tb_order_item(String orderid);

    @Update("UPDATE tb_order SET order_status = '3' WHERE id = #{orderid} AND username = #{username}")
    void updateOrderStatus(@Param("orderid") String orderid, @Param("username") String username);

    @Select("select t1.username,t2.*,t1.order_status from tb_order t1 ,tb_order_item t2 where t1.id = t2.order_id and t1.username = #{username} limit #{currentPage},#{pageSize}")
    List<MyOrder> pageQuery(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize, @Param("username") String username);
}
