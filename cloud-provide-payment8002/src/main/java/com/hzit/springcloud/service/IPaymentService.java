package com.hzit.springcloud.service;

import com.hzit.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * author biXia
 * create 2020-07-08-11:07
 */
public interface IPaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
