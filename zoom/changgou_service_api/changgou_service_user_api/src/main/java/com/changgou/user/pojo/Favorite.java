package com.changgou.user.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 8:57
 * Description: ....
 */
@Table(name = "tb_user_favorite")
public class Favorite {

    @Id
    private Integer id;

    @Column
    private  String username;


    @Column(name = "sku_id")
    private  String skuId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
