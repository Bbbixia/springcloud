package com.hzit.springcloud.controller;

import com.hzit.springcloud.entities.CommonResult;
import com.hzit.springcloud.entities.Payment;
import com.hzit.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * author biXia
 * create 2020-07-08-11:12
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private IPaymentService iPaymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {
        log.info("************: " + payment.getSerial() + "****************:  " + payment.getId());
        int result = iPaymentService.create(payment);
        log.info("*******插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功" + serverPort, result);
        } else {
            return new CommonResult(404, "插入数据库失败", null);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        log.info("************************controller");
        Payment payment = iPaymentService.getPaymentById(id);
        log.info("查询数据成功" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功" + serverPort, payment);
        } else {
            return new CommonResult(404, "查询失败" + id, null);
        }
    }


    /**
     * 测试手写的轮询算法是否成功
     *
     * @return
     */
    @GetMapping("/payment/lb")
    public String getPaymentLb() {
        return serverPort;
    }


}