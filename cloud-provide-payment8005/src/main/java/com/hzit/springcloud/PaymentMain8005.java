package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * author biXia
 * create 2020-07-08-10:35
 * 服务端维护服务，客户端，将自己入驻，提供服务
 */

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeeper作为注册中心时注册服务
public class PaymentMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8005.class,args);
    }
}
