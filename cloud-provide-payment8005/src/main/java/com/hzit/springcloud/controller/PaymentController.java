package com.hzit.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * author biXia
 * create 2020-07-08-23:04
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String   paymentZk(){
//        返回端口和流水号，UUID生成的流水号
        return "springcloud with zookeeper: " +serverPort +"\t" + UUID.randomUUID().toString();
    }

}
