package com.changgou.user.mongo;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.io.Serializable;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/21
 * Time: 18:50
 * Description: ....
 */

public class SpringDataPageable implements Pageable, Serializable {




    private static final long serialVersionUID = 213123l;
    // 当前页
    private Integer pagenumber = 1;
    // 当前页面条数
    private Integer pagesize = 10;
    //排序条件
    private Sort sort;

    public SpringDataPageable(Integer pagenumber, Integer pagesize) {
        this.pagenumber = pagenumber;
        this.pagesize = pagesize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
    // 当前页面
    @Override
    public int getPageNumber() {
        return getPagenumber();
    }
    // 每一页显示的条数
    @Override
    public int getPageSize() {
        return getPagesize();
    }
    // 第二页所需要增加的数量
    @Override
    public long getOffset() {
        return (getPagenumber() - 1) * getPagesize();
    }
    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    public Integer getPagenumber() {
        return pagenumber;
    }

    public Integer getPagesize() {
        return pagesize;
    }
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public  void setPagenumber(Integer pagenumber){
        this.pagenumber=pagenumber;


    }






}
