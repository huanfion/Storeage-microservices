package com.zegobird.servicehi;

import com.zegobird.servicehi.generator.PostgreSqlGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zegobird.servicehi.mapper")
public class ServiceHiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceHiApplication.class, args);
//        new  PostgreSqlGenerator().generator("tbl_employee","tbl","",true,"huanfion");
    }


}
