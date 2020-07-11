package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author biXia
 * create 2020-07-11-13:58
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWayMain8663 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain8663.class,args);
    }

}
