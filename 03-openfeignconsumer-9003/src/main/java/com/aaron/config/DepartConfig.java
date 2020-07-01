package com.aaron.config;

import org.springframework.context.annotation.Configuration;

/**
 * Java Config Bean
 */
@Configuration
public class DepartConfig {

    //使用feign以后不需要去定义RestTemplate
    /*@Bean
    @LoadBalanced //均衡负载 默认使用轮询机制
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }*/
}
