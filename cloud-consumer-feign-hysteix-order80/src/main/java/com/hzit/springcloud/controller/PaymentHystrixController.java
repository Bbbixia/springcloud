package com.hzit.springcloud.controller;

import com.hzit.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author biXia
 * create 2020-07-09-22:09
 * 降级方法全局配置和局部配置，若只有@HystrixCommand 注解但未指定方法，
 * 那么就是使用默认的方法，若指定方法，就使用自己配置的类
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //指定降级方法的注解
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id")Integer id){
        String s = paymentHystrixService.paymentInfo_Ok(id);
        log.info("****************调用成功");
        return s;
    }

    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
            //热部署对于 HystrixProperty内的改动不敏感，每次修改最好重启
    })*/
    @HystrixCommand //此注释代表该方法是降级方法
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
        int a = 10/0;
        String s = paymentHystrixService.paymentInfo_Timeout(id);
        log.info("*****************调用超时成功");
        return s;
    }

    /**
     * 此类是用于 paymentInfo_Timeout 异常时降级的类
     * param id
     * return
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池 ： " + Thread.currentThread().getName() + "我是消费者80 ，出错了喂 paymentInfo_TimeOutHandler,id:" + id + "\t" + "/(ㄒoㄒ)/~~";
    }

    /**
     * 全局的fallback方法，若未指定都是走这个降级方法
     * @return
     */
    public String payment_Global_FallbackMethod(){
        return "Global异常才处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }


}
