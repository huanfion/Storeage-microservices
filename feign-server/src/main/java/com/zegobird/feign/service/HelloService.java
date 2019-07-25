package com.zegobird.feign.service;

import com.zegobird.feign.bean.User;
import com.zegobird.feign.service.hystrix.ServiceHiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/7/23 11:24
 */
@FeignClient(value = "service-hi",fallback = ServiceHiHystrix.class)
public interface HelloService {
    @GetMapping("/hi")
    String Hello(@RequestParam String name);

    @GetMapping("/user/list")
    List<User> userList();
}
