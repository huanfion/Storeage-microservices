package com.zegobird.feign.service.hystrix;

import com.zegobird.feign.bean.User;
import com.zegobird.feign.service.HelloService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/7/23 11:52
 */
@Component
public class ServiceHiHystrix implements HelloService {
    @Override
    public String Hello(String name) {
        return String.format("your message is %s, but service is bad.",name);
    }

    @Override
    public List<User> userList() {
        return  null;
    }
}
