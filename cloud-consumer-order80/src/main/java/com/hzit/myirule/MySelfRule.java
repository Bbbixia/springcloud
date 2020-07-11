package com.hzit.myirule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author biXia
 * create 2020-07-09-15:47
 * 这个类不能被appscan扫描到，所以需要写在主类的外面。
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule iRule(){
//        return new RetryRule();
        return new RandomRule();    //定义为随机
    }

}
