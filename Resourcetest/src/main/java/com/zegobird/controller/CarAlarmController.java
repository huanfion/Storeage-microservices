package com.zegobird.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/9 11:53
 */

@Slf4j
@RestController
public class CarAlarmController {
    @RequestMapping("/add/{id}")
    public String add(@PathVariable String id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        log.debug("授权信息:{}",authentication);
        return "Add car alarm";
    }
}

