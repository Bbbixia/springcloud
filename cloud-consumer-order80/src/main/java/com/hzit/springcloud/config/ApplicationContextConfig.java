package com.hzit.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * author biXia
 * create 2020-07-08-16:54
 */
@Configuration
public class ApplicationContextConfig {


    /**
     * restTemplate的配置类，注入到springboot容器中
     * return
     */
    @Bean
   // @LoadBalanced  //resttemplate负载均衡开启
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }


}
