package com.changgou.web.controller;

import com.changgou.entity.Result;
import com.changgou.user.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.changgou.user.feign.FavoriteFeign;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 0:12
 * Description: ....
 */
@Controller
@RequestMapping("/wuser")
@CrossOrigin
public class WebUserController {

    @Autowired
    private FavoriteFeign favoriteFeign;

    /**
     * 添加收藏
     *
     * @param
     */
    @PostMapping("/addFavorite/{skuId}")
    @ResponseBody
    public void addFavorite(@PathVariable("skuId") String skuId) {
        favoriteFeign.addFavorite(skuId);
        System.out.println(skuId);


    }



}
