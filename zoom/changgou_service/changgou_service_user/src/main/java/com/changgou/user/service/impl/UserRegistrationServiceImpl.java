package com.changgou.user.service.impl;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.user.dao.UserRegistrationMapper;
import com.changgou.user.pojo.User;
import com.changgou.user.service.UserRegistrationService;
import com.changgou.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

/**
 * 用户注册服务
 * zhangrui
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRegistrationMapper userRegistrationMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IdWorker idWorker;
    @Override
    public Result userRegistration(String code,Map<String,String> map) {

        String password = (String) map.get("password");
        String phone = (String) map.get("phone");
        //首先对用户的验证码进行判断
        Object ValidateCode = (String)stringRedisTemplate.boundValueOps(phone+"ValidateCode").get();
        if(!code.equals(ValidateCode)){
            return new Result(false, StatusCode.ERROR,"验证码错误");
        }
        //接收到用户的手机号和密码,对密码进行一个加密
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User();
        user.setPhone(phone);
        user.setPassword(hashpw);
        String username = String.valueOf(idWorker.nextId());
        user.setUsername(username);
        user.setUpdated(new Date());
        user.setCreated(new Date());
        userRegistrationMapper.insertSelective(user);
        return new Result(true, StatusCode.OK,"注册成功");

    }
}
