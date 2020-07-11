package com.hzit.springcloud.service.impl;

import com.hzit.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * author biXia
 * create 2020-07-09-23:31
 */
@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "-----PaymentFallbackServiceImpl---- fall -------paymentInfo_Ok  /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----PaymentFallbackServiceImpl---- fall -------paymentInfo_Timeout  /(ㄒoㄒ)/~~";
    }
}
