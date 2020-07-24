package com.changgou.web.pojo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/22
 * Time: 13:59
 * Description: ....
 */

public class Result {
    private String number;
    private  String type;
    private List<Map<String,String>> list;
    private  String deliverystatus;
    private  String issign;
    private  List<String> citys;

    private  String expName;

    private  String expSite;

    private  String expPhone;

    private  String logo;

    private  String courier;

    private  String courierPhone;

    private  String updateTime;

    private  String takeTime;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }

    public String getDeliverystatus() {
        return deliverystatus;
    }

    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

    public String getIssign() {
        return issign;
    }

    public void setIssign(String issign) {
        this.issign = issign;
    }

    public List<String> getCitys() {
        return citys;
    }

    public void setCitys(List<String> citys) {
        this.citys = citys;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public String getExpSite() {
        return expSite;
    }

    public void setExpSite(String expSite) {
        this.expSite = expSite;
    }

    public String getExpPhone() {
        return expPhone;
    }

    public void setExpPhone(String expPhone) {
        this.expPhone = expPhone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getCourierPhone() {
        return courierPhone;
    }

    public void setCourierPhone(String courierPhone) {
        this.courierPhone = courierPhone;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }
}
