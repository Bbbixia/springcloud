package com.hzit.springcloud.service;

import com.hzit.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * author biXia
 * create 2020-07-09-18:41
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //调用的微服务
public interface PaymentFeignService {

    //    int create(Payment payment);
    //    CommonResult<Payment> getPaymentById(@Param("id") Long id);


    @GetMapping("/payment/get/{id}")        //url要与调用类一致，不然找不到
     CommonResult getPaymentById(@PathVariable("id") Long id);

    /**
     * 超时时间的测试 调用8001的Controller
     * return
     */
    @GetMapping("/payment/feign/timeout")
     String paymentTimeout();

}
