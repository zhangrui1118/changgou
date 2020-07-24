package com.changgou.user.controller;

import com.changgou.goods.pojo.Sku;
import com.changgou.user.pojo.Favorite;
import com.changgou.user.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 14:18
 * Description: ....提供收藏数据
 */
@RequestMapping("/favorite")
@RestController
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;



    @GetMapping("/showFavorite")
    public  Map<String,Object> showFavorite( @RequestParam("username") String username,@RequestParam("pageNum")String pageNum){

        Map<String,Object> map= favoriteService.findFavoriteByUserName(username,pageNum);
        return map;

    }









}
