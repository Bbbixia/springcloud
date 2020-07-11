package com.hzit.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * author biXia
 * create 2020-07-11-17:25
 */
@SpringBootApplication
@EnableEurekaClient
public class MainAppConfigCenter3344 {

    public static void main(String[] args) {


        SpringApplication.run(MainAppConfigCenter3344.class, args);

    }


}
