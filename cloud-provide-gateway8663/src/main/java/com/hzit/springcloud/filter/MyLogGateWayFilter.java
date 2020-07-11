package com.hzit.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Date;

/**
 * author biXia
 * create 2020-07-11-16:28
 */
//@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered, Serializable {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("******** comer in MyLogGateway " + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if (uname == null) {
            log.info("**********用户名为null ,回去吧o(╥﹏╥)o");
            //不成功，返回一个值给回去
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    //这是一个顺序的东西
    @Override
    public int getOrder() {
        return 0;
    }
}
