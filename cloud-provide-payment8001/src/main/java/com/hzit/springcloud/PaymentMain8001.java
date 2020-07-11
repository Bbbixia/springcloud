package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author biXia
 * create 2020-07-08-10:35
 * 服务端维护服务，客户端，将自己入驻，提供服务
 */

@SpringBootApplication
@EnableEurekaClient //表示自己已入注服务端
@EnableDiscoveryClient //获取配置信息的注解
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
