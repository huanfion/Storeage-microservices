package com.zegobird.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.zegobird.feign.bean.User;
import com.zegobird.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/7/23 11:26
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("consumerhello")
    public String sayHello(@RequestParam String name){
        return helloService.Hello(name);
    }
    @GetMapping("user/list")
    public List<User> userList(){
        return  helloService.userList();
    }
}
