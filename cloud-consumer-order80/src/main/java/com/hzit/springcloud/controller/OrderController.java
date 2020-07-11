package com.hzit.springcloud.controller;

import com.hzit.springcloud.entities.CommonResult;
import com.hzit.springcloud.entities.Payment;
import com.hzit.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * author biXia
 * create 2020-07-08-16:52
 * 接口   IRule 就是用于规范负载均衡
 */
@RestController
@Slf4j
public class OrderController {
    //单机版可以使用这个
    //public static final String PAYMENT_URL = "http://localhost:8001";
    //集群需要直接拿到服务名称 按照如下的写法会报一个异常  UnknownHostException
    //需要在resttempalte下使用loadbalanced注解，开启负载均衡（使用的是轮询的方式），因为template不知道需要拿哪个服务。
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


    @Resource
    private RestTemplate restTemplate;

    //将自己的轮询注入
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("调用添加");
        log.info("***************:   " + payment.getSerial());
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("调用查询");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }


    /**
     * getForEntury 的使用
     *
     * @param id
     * @return
     */
    @GetMapping("/consumer/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable("id") Long id) {

        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            log.info("调用查询" + forEntity.getStatusCode().is2xxSuccessful()
                    + forEntity.getStatusCodeValue() + "\t" + forEntity.getHeaders());
            return forEntity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }

    }

    @PostMapping("/consumer/payment/postCreate")
    public CommonResult<Payment> postCreate(Payment payment) {

        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info("*************" + entity.getHeaders() + "\t" + entity.getStatusCodeValue());
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "插入失败");
        }

    }

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances==null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }


}
