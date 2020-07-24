package com.changgou.order.pojo;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * orderItem实体类
 * @author 黑马架构师2.5
 *
 */

public class MyOrder {


    private String id;//ID



    private Integer category_id1;//1级分类
    private Integer category_id2;//2级分类
    private Integer category_id3;//3级分类
    private String spu_id;//SPU_ID
    private String sku_id;//SKU_ID
    private String order_id;//订单ID
    private String name;//商品名称
    private Integer price;//单价
    private Integer num;//数量
    private Integer money;//总金额
    private Integer pay_money;//实付金额
    private String image;//图片地址
    private Integer weight;//重量
    private Integer post_fee;//运费
    private String is_return;//是否退货
    private String order_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategory_id1() {
        return category_id1;
    }

    public void setCategory_id1(Integer category_id1) {
        this.category_id1 = category_id1;
    }

    public Integer getCategory_id2() {
        return category_id2;
    }

    public void setCategory_id2(Integer category_id2) {
        this.category_id2 = category_id2;
    }

    public Integer getCategory_id3() {
        return category_id3;
    }

    public void setCategory_id3(Integer category_id3) {
        this.category_id3 = category_id3;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPay_money() {
        return pay_money;
    }

    public void setPay_money(Integer pay_money) {
        this.pay_money = pay_money;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPost_fee() {
        return post_fee;
    }

    public void setPost_fee(Integer post_fee) {
        this.post_fee = post_fee;
    }

    public String getIs_return() {
        return is_return;
    }

    public void setIs_return(String is_return) {
        this.is_return = is_return;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}

