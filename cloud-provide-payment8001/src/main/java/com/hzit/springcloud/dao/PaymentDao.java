package com.hzit.springcloud.dao;

import com.hzit.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * author biXia
 * create 2020-07-08-10:51
 */
@Mapper
//@Repository  不推荐用这个，有时候插入会出问题
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
