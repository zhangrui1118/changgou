package com.changgou.user.feign;

import com.changgou.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "user")
public interface UserRegistrationFeign {

    @RequestMapping("/ruser/registration")
    public Result userRegistration(@RequestParam ("smsCode")String code, @RequestBody Map<String,String> map);
}
