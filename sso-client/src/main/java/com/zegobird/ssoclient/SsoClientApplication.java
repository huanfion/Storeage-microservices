package com.zegobird.ssoclient;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/14 18:09
 */
@SpringBootApplication
@EnableOAuth2Sso
public class SsoClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoClientApplication.class,args);
    }
}
