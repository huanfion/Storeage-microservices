package com.zegobird.oauth2center;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:09
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zegobird.oauth2center.mapper")
public class OAuth2CenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2CenterApplication.class,args);
    }
}
