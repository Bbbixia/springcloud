package com.hzit.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author biXia
 * create 2020-07-11-16:01
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouterLocator(RouteLocatorBuilder routeLocatorBuilder){
        //对应配置文件中的routes
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
                        //定义id                      指定路由
        routes.route("path_route_hzit",
                r ->r.path("/*")
                        .uri("http://localhost:8001/payment/get/*")).build();

        return routes.build();

    }

    //注意：此处的path需要和uri的最后路径保持一致，不一致会报错找不到的
    @Bean
    public RouteLocator customerRouterLocator1(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("myroutebaidu1", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

}
