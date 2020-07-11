package com.hzit.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

/**
 * author biXia
 * create 2020-07-09-20:56
 */
@SpringBootApplication
@EnableEurekaClient     //服务注册的注解
@EnableCircuitBreaker //服务降级的注解
public class PaymentHystrixMain8007 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8007.class, args);
    }

    /**
     * 此配置是为了服务监控而配置的servlet
     * servletRegistrationBean因为springboot的默认路径不是 "/hystrix.stream"
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
