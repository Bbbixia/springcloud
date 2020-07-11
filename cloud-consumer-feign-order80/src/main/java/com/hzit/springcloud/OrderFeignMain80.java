package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * author biXia
 * create 2020-07-09-18:34
 */
@SpringBootApplication
@EnableFeignClients     //应用openfeign     这是注解表示注入
public class OrderFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class);
    }
}
