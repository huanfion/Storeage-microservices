package com.zegobird.oauth2center.controller;

import com.zegobird.oauth2center.domain.TbUser;
import com.zegobird.oauth2center.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/10 17:53
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String hello() {
        return "login";
    }

    @Autowired
    private TbUserService tbUserService;

    /**
     * 测试资源 ，下一步需要考虑将资源服务器和授权服务器分离。
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public List<TbUser> query() {

        return tbUserService.getUserList();

    }
    //首页，无需授权，需要在ZBWebSecurityConfig配置 ignore
    @GetMapping("/index")
    public String index(){
        return  "index";
    }


    @GetMapping("/error")
    public String error(){
        return  "failure";
    }

    @GetMapping("/success")
    public String success(){
        return  "success";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }
}
