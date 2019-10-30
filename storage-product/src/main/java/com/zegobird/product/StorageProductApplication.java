package com.zegobird.product;

import com.zegobird.product.generator.PostgreSqlGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zegobird.product.mapper")
@EnableFeignClients
public class StorageProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageProductApplication.class, args);
//        new PostgreSqlGenerator().generator(
//                "tb_productinfospecvalue,tb_productinfo",
//                "tb", "", true, "huanfion");
    }

}
