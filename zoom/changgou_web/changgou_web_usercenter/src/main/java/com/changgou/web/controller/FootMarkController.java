package com.changgou.web.controller;

import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.user.feign.UserFeign;
import com.changgou.user.mongo.FootMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ cyf.
 * User: cyf
 * Date: 2020/7/21
 * Time: 8:45
 * Description: ....
 */
@Controller
@RequestMapping("/wuser/footmark")
public class FootMarkController {
    @Autowired
    private UserFeign userFeign;

    @Autowired
    private  SkuFeign skuFeign;

    /**
     * 返回页面
     * @return
     */
    @GetMapping("/showFootMark")
    public String showFootMark() {


        return "center-footmark";

    }

    /**
     * axios请求的路径
     * @param pageNum
     * @return
     */
    @GetMapping("/footMarkList")
    @ResponseBody
    public Map<String,Object> showFootMark(@RequestParam("pageNum") String pageNum) {

        Map<String, Object> footMark = userFeign.findAllFootMark(pageNum);




        return footMark;

    }
}
