package com.changgou.user.service;

import com.changgou.entity.Result;


import java.util.Map;

/**
 * 用户注册接口
 * zhangrui
 */
public interface UserRegistrationService {

    /**
     * 用户注册
     * @param map
     * @param code
     */
    public Result userRegistration(String code,Map<String,String> map);
}
