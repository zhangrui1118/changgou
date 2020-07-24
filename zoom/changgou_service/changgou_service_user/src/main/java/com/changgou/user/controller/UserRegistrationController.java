package com.changgou.user.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.user.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户注册controller
 * zhangrui
 */
@RestController
@RequestMapping("/ruser")
public class UserRegistrationController {
    @Autowired
    private UserRegistrationService userRegistrationService;

    @RequestMapping("/registration")
    public Result userRegistration(@RequestParam("smsCode")String code, @RequestBody Map<String,String> map){
        Result result = userRegistrationService.userRegistration(code, map);
        return result;
    }
}
