package com.hzit.springcloud.service.impl;

import com.hzit.springcloud.dao.PaymentDao;
import com.hzit.springcloud.entities.Payment;
import com.hzit.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author biXia
 * create 2020-07-08-11:08
 */
@Slf4j
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {

        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        log.info("************************service");
        return paymentDao.getPaymentById(id);
    }
}
