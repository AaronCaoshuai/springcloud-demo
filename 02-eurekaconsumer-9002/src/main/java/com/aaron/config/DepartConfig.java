package com.aaron.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Java Config Bean
 */
@Configuration
public class DepartConfig {

    @Bean
    @LoadBalanced //均衡负载 默认使用轮询机制
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
