package com.changgou.user.service.impl;

import com.changgou.entity.Result;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.user.dao.FavoriteMapper;
import com.changgou.user.feign.UserFeign;
import com.changgou.user.pojo.Favorite;
import com.changgou.user.service.FavoriteService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 14:24
 * Description: ....
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {


    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private SkuFeign skuFeign;

    @Override
    public  Map<String,Object> findFavoriteByUserName(String username,String pageNum) {
        Example example = new Example(Favorite.class);
        Example.Criteria critial = example.createCriteria();
        critial.andEqualTo("username", username);
        Integer size = 5 ; // 每页的显示多少条

        PageHelper.startPage(Integer.parseInt(pageNum),size);

       Page<Favorite> page= (Page<Favorite>) favoriteMapper.selectByExample(example);

        List<Favorite> favoriteList = page.getResult();
        long total = page.getTotal();
        int  pageSize = page.getPages();


        List<Sku> skuList = favoriteList.stream().map(favorite -> skuFeign.findById(favorite.getSkuId()).getData())
                .collect(Collectors.toList());

        Map<String,Object> map = new HashMap();
        map.put("favoriteList",skuList);
        map.put("totalNum",total);
        map.put("totalPage",pageSize);

        return map;
    }
}
