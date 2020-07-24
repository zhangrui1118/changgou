package com.changgou.web.controller;

import com.changgou.entity.Result;
import com.changgou.user.feign.UserRegistrationFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户登录
 * zhangrui
 */
@Controller
@RequestMapping("/ruser")
public class UserRegistrationController {
    @Autowired
    private UserRegistrationFeign userRegistrationFeign;
//    @GetMapping("/registration/phone/{phone}/password/{password}/code/{code}")
    @RequestMapping("/registration")
    @ResponseBody
    public Result userRegistration(@RequestParam ("smsCode")String code, @RequestBody Map<String,String> map){
        Result result = userRegistrationFeign.userRegistration(code, map);
        return result;
    }

    @GetMapping("/jump")
    public String jump(){
        return "register";
    }
}
