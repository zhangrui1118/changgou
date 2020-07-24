package com.changgou.web;

import com.changgou.interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients(basePackages ={"com.changgou.pay.feign","com.changgou.user.feign","com.changgou.goods.feign","com.changgou.order.feign"} )
public class WebUserApp {
    public static void main(String[] args) {
        SpringApplication.run(WebUserApp.class, args);
    }

    @Bean
    public FeignInterceptor feignInterceptor(){
        return  new FeignInterceptor();
    }
}
