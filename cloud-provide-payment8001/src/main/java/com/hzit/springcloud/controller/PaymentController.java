package com.hzit.springcloud.controller;

import com.hzit.springcloud.entities.CommonResult;
import com.hzit.springcloud.entities.Payment;
import com.hzit.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    /**
     * 服务发现
     * 暴露自身服务的信息 ，通过服务发现来获得该服务的信息
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create( Payment payment) {
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
     * 这个类可以将自己的信息暴露出去，其他服务调用这个服务可以直接获取到所有信息
     *
     * @return
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        //获取服务列表清单
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*********element: " + element);
        }
        discoveryClient.getInstances("");
        //通过微服务的名称，获取更详细的信息(即该微服务下的所有实例)
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
//            获取服务的各类信息
            String instanceId = instance.getInstanceId();
            String host = instance.getHost();
            URI uri = instance.getUri();
            int port = instance.getPort();
            String serviceId = instance.getServiceId();
            String scheme = instance.getScheme();
            Map<String, String> metadata = instance.getMetadata();
            log.info(instanceId + "\t" + host + "\t" + uri + "\t" + port + "\t" + serviceId + "\t" + scheme);
        }
        return this.discoveryClient;
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

    @GetMapping("/payment/feign/timeout")
    public String paymentTimeout() {
        //暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }


}