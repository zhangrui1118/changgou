package com.changgou.user.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 18:27
 * Description: ....
 */
@Document(collection = "uc_footmark")
public class FootMark implements Serializable {

    @Id
    private String id;
    @Field("sku_id")
    private String skuId;
    private  String name;
    private  String price;
    private  String image;
    private  String username;
    @Field("mark_time")
    private Date markTime;
    @Field("foot_key")
    private  String footKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public String getFootKey() {
        return footKey;
    }

    public void setFootKey(String footKey) {
        this.footKey = footKey;
    }
}

