package com.hzit.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * author biXia
 * create 2020-07-09-20:59
 */
@Service
public class PaymentService {

    /**
     * 正常的调用
     * param id
     * return
     */
    public String paymentInfo_Ok(Integer id) {

        return "线程池 ： " + Thread.currentThread().getName() + "paymentInfo_Ok,id:" + id + "\t" + "O(∩_∩)O";
    }


    /**
     * 模仿任务执行未达到预期要求，异常，或超时等等... 服务降级
     * param id
     * return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            //热部署对于 HystrixProperty内的改动不敏感，每次修改最好重启
    })
    public String paymentInfo_Timeout(Integer id) {
        //模仿异常
//        int age = 10/0;
        //模仿超时
        int timeNumber = 5;
        try {
//TimeUnit.MILLISECONDS.sleep("3000"); 这是把时间改为毫秒
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池 ： " + Thread.currentThread().getName() + "paymentInfo_Timeout,id:" + id + "\t" + "O(∩_∩)O";

    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池 ： " + Thread.currentThread().getName() + "paymentInfo_TimeOutHandler,id:" + id + "\t" + "/(ㄒoㄒ)/~~";
    }


    //    服务熔断   HystrixCommandProperties具体配置在这个里面查看
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String   paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("*******id，不能为负数😘");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号"+s;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，/(ㄒoㄒ)/~~ id :" +id ;
    }


}
