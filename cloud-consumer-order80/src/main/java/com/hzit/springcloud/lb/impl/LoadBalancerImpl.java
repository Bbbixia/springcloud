package com.hzit.springcloud.lb.impl;

import com.hzit.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author biXia
 * create 2020-07-09-17:20
 * 这是为了实现自己的轮询算法
 */
@Component
@Slf4j
public class LoadBalancerImpl implements LoadBalancer {

//    @Resource  这个地方不用resource注解而是new出来一个，为防止其他线程改变了这个值
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));  //这一步是自旋取反
        log.info("*******第几次 next :"+ next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        //具体的服务器的下标
        int i = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(i);
    }
}
