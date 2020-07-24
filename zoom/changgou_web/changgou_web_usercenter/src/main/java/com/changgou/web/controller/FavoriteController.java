package com.changgou.web.controller;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/20
 * Time: 11:54
 * Description: .... 用户收藏展示
 */

import com.changgou.goods.pojo.Sku;
import com.changgou.user.feign.FavoriteFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


/**
 *
 */


@Controller
@RequestMapping("/wfavorite")
public class FavoriteController {


    @Autowired
    private FavoriteFeign favoriteFeign;

    /**
     * 返回收藏页面
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/showfavorite")
    public String gotoshowFavorite() {

        return "center-collect";
    }

    /**
     * 获取数据
     * @param username
     * @return
     */
    @PostMapping("/getfavoriteData")
    @ResponseBody
    public Map<String,Object> showFavorite(@RequestParam("username") String username,@RequestParam("pageNum")String pageNum ) {
        Map<String,Object>  map = favoriteFeign.showFavorite(username,pageNum);
       return map;
    }

}
