package com.changgou.user.feign;

import com.changgou.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * cyf
 */
@FeignClient(value = "user")
public interface FavoriteFeign {
    /**
     * 添加收藏
     * @param skuId
     */
    @PostMapping("/user/addFavorite")
     void addFavorite(@RequestParam("skuId") String skuId);


    /**
     * 按照用户名查询收藏
     * @param username
     * @return
     */
    @GetMapping("/favorite/showFavorite")
    public Map<String,Object> showFavorite(@RequestParam("username") String username, @RequestParam("pageNum")String pageNum);

}
