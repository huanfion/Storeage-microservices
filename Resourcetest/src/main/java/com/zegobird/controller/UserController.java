package com.zegobird.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/14 10:54
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/current")
    public String getCurrentUser(Principal principal){
        System.out.println(principal);
        return "user";
    }
    @PostMapping("/add")
    public String addUser(){
        return  "adduser";
    }
    @GetMapping("/index")
    public Authentication getUser(Authentication user){
        return user;
    }
}
