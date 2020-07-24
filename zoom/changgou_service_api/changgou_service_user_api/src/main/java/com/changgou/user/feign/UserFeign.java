package com.changgou.user.feign;

import com.changgou.user.mongo.FootMark;
import com.changgou.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "user")
public interface UserFeign {




    @GetMapping("/user/load/{username}")
    public User findUserInfo(@PathVariable("username") String username);





    /**
     * 查询用户足迹
     * @return
     */
    @GetMapping("/user/footmark/findAll")
    public Map<String, Object> findAllFootMark(@RequestParam("pageNum") String pageNum);


}
