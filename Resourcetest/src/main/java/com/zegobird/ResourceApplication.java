package com.zegobird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/9 19:32
 */
@SpringBootApplication
@EnableOAuth2Sso //单点登录客户端
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class,args);
    }
}
