package com.hzit.springcloud.controller;

import com.hzit.springcloud.entities.CommonResult;
import com.hzit.springcloud.entities.Payment;
import com.hzit.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author biXia
 * create 2020-07-09-18:47
 * 自己服务的service调用其他服务的service,然后，controller层中对外暴露端口
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("调用成功");

        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/feign/timeout")
    public String paymentTimeout(){
        //openfeign-ribbon 客户端默认等待为1s  这里再配置类中ribbon :
        //  #指的是建立连接所有的时间，适用于网络状况正常的情况下，两端所使用的时间
        //  ReadTimeout:  5000
        //  #指的是建立连接后从服务端读取到可用资源所用的时间 ，5秒还没有响应就是超时
        //  ConnecTimeout:  5000

    return paymentFeignService.paymentTimeout();
    }

}
