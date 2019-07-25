package com.zegobird.servicehi.controller;

import com.zegobird.servicehi.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/7/23 12:02
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String Index(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }
    @GetMapping("/user/list")
    public List<User> userList(){
        List<User> userList=new ArrayList<User>();
        userList.add(new User("张三",20,"zhangsan","123456"));
        userList.add(new User("李四",23,"xiaosi","123456"));
        userList.add(new User("王五",19,"xiaowu","123456"));
        return  userList;
    }
}

