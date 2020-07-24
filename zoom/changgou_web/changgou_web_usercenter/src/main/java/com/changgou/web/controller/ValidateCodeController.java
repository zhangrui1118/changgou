package com.changgou.web.controller;

import com.aliyuncs.exceptions.ClientException;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.util.SMSUtils;
import com.changgou.util.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 用户注册发送验证码
 * zhangrui
 */
@RestController
@RequestMapping("/wcode")
public class ValidateCodeController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/sendcode")
    public Result send40rder(@RequestParam("mobile") String telephone){

        //生成验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,validateCode.toString());
        } catch (ClientException e) {
            return new Result(false,StatusCode.ERROR,"发送验证码失败");

        }
        //发送完成后 把验证码存入redis中  设置存活时间
        stringRedisTemplate.boundValueOps(telephone+"ValidateCode").set("0000",300000, TimeUnit.SECONDS);
        return new Result(true, StatusCode.OK,"发送验证码成功");
    }

}
