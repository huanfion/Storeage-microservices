package com.zegobird.ssoclient.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/14 18:11
 */
@RestController
public class DemoController {
    @RequestMapping("/user")
    public Authentication getUser(Authentication user){
        return  user;
    }
//    @GetMapping("/")
//    public String index()
//    {
//        return  "index";
//    }
}
