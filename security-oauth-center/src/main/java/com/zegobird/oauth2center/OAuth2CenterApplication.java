package com.zegobird.oauth2center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/8/8 19:09
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.zegobird.oauth2center.mapper")
public class OAuth2CenterApplication {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OAuth2CenterApplication.class,args);
    }
}
