package com.hzit.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * author biXia
 * create 2020-07-09-17:11
 */
public interface LoadBalancer {

    /**
     * 获取所i有的服务器
     * param serviceInstances
     * return
     */
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
